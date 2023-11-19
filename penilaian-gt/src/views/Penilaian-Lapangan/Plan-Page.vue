<template>
  <div>
    <Header />

    <main id="main" class="main">
      <div class="pagetitle">
        <h1 class="my-4 fs-1">PLAN</h1>
        <!-- Breadcrumb -->
        <Breadcrumb />
        <!-- Breadcrumb -->
      </div>
      <!-- End Page Title -->

      <section class="section">
        <div class="row">
          <!-- Card with header and footer -->
          <div class="card mx-auto">
            <div class="card-tittle fs-2 text-center mt-5">
              Penilaian Lapangan
            </div>
            <form @submit.prevent="inputData">
              <div>
                <div class="card-tittle">
                  <div class="col mb-3">
                    <label class="col-sm-2 col-form-label">Penilai</label>
                    <div class="col-sm-2">
                      <input
                        type="text"
                        class="form-control"
                        v-model="requestData.user"
                        disabled
                      />
                    </div>
                  </div>
                </div>
                <div class="card-tittle">
                  <label class="col-sm-2 col-form-label">Team</label>
                  <div class="col-sm-2">
                    <select
                      class="form-select"
                      aria-label="Default select example"
                      v-model="selectedTeam"
                      @change="selectTeam"
                      required
                    >
                      <option selected disabled>Pilih Team</option>
                      <option v-for="team in teamList" :value="team.teamName">
                        {{ team.teamName.toUpperCase() }}
                      </option>
                    </select>
                  </div>
                </div>
              </div>
              <hr />
              <div class="card-body">
                <div
                  class="card-tittle"
                  v-for="criteria in criteriaList"
                  :key="criteria.criteriaId"
                >
                  <h3 class="my-5">
                    <span
                      ><i class="bi bi-circle-fill fs-4 me-2"></i>
                      {{ criteria.criteriaName }}</span
                    >
                  </h3>
                  <div
                    class="card-subtitle my-2 ms-2"
                    v-for="subs in subcriteriaList.filter(
                      (crit) =>
                        crit.criteriaLapangan.criteriaId === criteria.criteriaId
                    )"
                  >
                    <h5 class="my-2">
                      {{ subs.subcriteriaName }}
                    </h5>
                    <hr />
                    <div
                      v-for="question in questionList.filter(
                        (sub) =>
                          sub.subcriteriaLapangan.subcriteriaId ===
                          subs.subcriteriaId
                      )"
                    >
                      <p class="ms-2 my-4 fw-medium fs-5">
                        {{ question.questionText }}
                      </p>
                      <div
                        class="form-check form-check-inline flex flex-wrap ms-2"
                        v-for="(item, index) in choiceList.filter(
                          (quest) =>
                            quest.questionLapangan.questionId ===
                            question.questionId
                        )"
                      >
                        <input
                          style="width: 20px; height: 20px"
                          class="form-check-input"
                          type="radio"
                          :name="
                            'selectedChoice_' + item.questionLapangan.questionId
                          "
                          :id="
                            'selectedChoice_' +
                            item.questionLapangan.questionId +
                            '_' +
                            index
                          "
                          :value="item.choiceValue"
                          v-model="
                            requestData.nilai[item.questionLapangan.questionId]
                          "
                          required
                        />
                        <label
                          class="form-check-label"
                          :for="
                            'selectedChoice_' +
                            item.questionLapangan.questionId +
                            '_' +
                            index
                          "
                          >{{ item.choiceValue }}</label
                        >
                      </div>
                      <hr />
                    </div>
                  </div>
                </div>
              </div>
              <button class="btn btn-primary w-100 p-2 m-2" type="submit">
                Next
              </button>
            </form>
          </div>
          <!-- End Card with header and footer -->
        </div>
      </section>
    </main>
    <!-- End #main -->
  </div>
</template>
<script>
import Header from "../../components/Header.vue";
import Breadcrumb from "../../components/Breadcrumb.vue";
import router from "../../router";

export default {
  components: {
    Header,
    Breadcrumb,
  },
  data() {
    return {
      isSidebarHidden: false,
      criteriaList: [],
      subcriteriaList: [],
      questionList: [],
      choiceList: [],
      teamList: [],
      selectedTeam: null,
      requestData: {
        user: null,
        team: null,
        nilai: {},
      },
    };
  },
  methods: {
    selectTeam() {
      this.$store.commit("setSelectedTeam", this.selectedTeam);
    },
    getAllCriteria() {
      try {
        this.$axios.get("/criteriaAll").then((response) => {
          const allItems = response.data;
          this.criteriaList = allItems.slice(0, 4);
          console.log(this.criteriaList);
        });
      } catch (error) {
        console.error("Error fetching criteria data:", error);
      }
    },

    getAllSubcriteria() {
      try {
        this.$axios.get("/subcriteriaAll").then((response) => {
          const allItems = response.data;
          this.subcriteriaList = allItems.slice(0, 11);
          console.log(this.subcriteriaList);
        });
      } catch (error) {
        console.error("Error fetching subcriteria data:", error);
      }
    },
    getAllQuestion() {
      try {
        this.$axios.get("/questionAll").then((response) => {
          this.questionList = response.data;
          console.log(this.questionList);
        });
      } catch (error) {
        console.error("Error fetching Question data:", error);
      }
    },
    getAllChoice() {
      try {
        this.$axios.get("/choiceAll").then((response) => {
          this.choiceList = response.data;
          console.log(this.choiceList);
        });
      } catch (error) {
        console.error("Error fetching choice data:", error);
      }
    },
    getAllTeam() {
      try {
        this.$axios.get("/getAllTeam").then((response) => {
          this.teamList = response.data;
          console.log(this.teamList);
        });
      } catch (error) {
        console.error("Error fetching Team data:", error);
      }
    },

    inputData() {
      const username = this.requestData.user;
      const teamName = this.selectedTeam;

      // Filter the questionList based on user input values
      const filteredQuestionList = this.questionList.filter((question) => {
        const questionId = question.questionId;
        const selectedValue = this.requestData.nilai[questionId];
        return selectedValue !== undefined && selectedValue !== null;
      });

      // Loop through the filtered questionList to collect data for nilaiData
      const nilaiData = filteredQuestionList.map((question) => {
        const questionId = question.questionId;
        const nilai = this.requestData.nilai[questionId];
        return {
          username: username,
          teamName: teamName,
          questionId: questionId,
          nilai: nilai,
        };
      });

      console.log(nilaiData);
      // this.$store.commit("setSelectedTeam", this.selectedTeam);
      localStorage.setItem("inputData1", JSON.stringify(nilaiData));
      router.push("/do");
    },
  },
  created() {
    this.getAllCriteria();
    this.getAllSubcriteria();
    this.getAllQuestion();
    this.getAllChoice();
    this.getAllTeam();

    const userData = JSON.parse(localStorage.getItem("userData"));
    if (userData) {
      this.requestData.user = userData.username;
    }
  },
};
</script>
<style></style>
