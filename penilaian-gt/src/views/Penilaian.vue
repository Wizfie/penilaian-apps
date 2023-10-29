<template>
  <div class="wrapper d-flex">
    <!-- Sidebar -->
    <Sidebar :isSidebarHidden="isSidebarHidden" />
    <!--Sidebar -->

    <main
      class="w-100"
      :style="{ marginLeft: isSidebarHidden ? '0' : '250px' }"
    >
      <!-- Nav Header -->
      <nav
        class="d-flex justify-content-between bg-dark-subtle p-2 align-items-center"
      >
        <button @click="toggleSidebar" class="btn btn-secondary">></button>
        <div class="nav-header d-flex mx-2">
          <div class="dropdown">
            <button
              class="btn btn-secondary btn-sm"
              type="button"
              data-bs-toggle="dropdown"
              aria-expanded="true"
            >
              Profile
            </button>
            <ul class="dropdown-menu">
              <li>
                <button class="dropdown-item" type="button">Sign Out</button>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <!-- Nav Header -->
      <!-- Breadcrumb -->
      <!-- <Breadcrumb /> -->
      <Breadcrumb />
      <!-- Breadcrumb -->
      <!-- kontent -->
      <h1 class="p-3 text-center">PLAN</h1>
      <div class="d-flex flex-lg-row flex-column w-100 mx-auto">
        <form @submit.prevent="inputData" class="w-75 mx-auto" action="">
          <div
            class="box-question card-body rounded-1 bg-body-secondary mx-auto m-5 p-5 gap-2"
          >
            <div>
              <input
                v-model="requestData.user"
                type="text"
                name="user"
                id="user"
                required
              />
            </div>
            <div class="team-group my-2">
              <select
                class="form-select"
                aria-label="Default select example"
                v-model="requestData.team"
                required
              >
                <option value="" selected>Pilih Team</option>
                <option v-for="team in teamList" :value="team.teamId">
                  {{ team.teamName.toUpperCase() }}
                </option>
              </select>
            </div>
            <div v-for="criteria in criteriaList" :key="criteria.criteriaId">
              <div class="border border-2 border border-black p-3">
                <h5 class="card-subtitle border-2">
                  {{ criteria.criteriaName }}
                </h5>
              </div>
              <div class="border border-2 border border-black"></div>
              <div>
                <div
                  v-for="subs in subcriteriaList.filter(
                    (crit) => crit.criteria.criteriaId === criteria.criteriaId
                  )"
                  class="border border-2 border border-black my-2 p-2"
                >
                  <h6 class="card-text">{{ subs.subcriteriaName }}</h6>
                  <div
                    v-for="question in questionList.filter(
                      (sub) =>
                        sub.subcriteria.subcriteriaId === subs.subcriteriaId
                    )"
                  >
                    <p class="card-text ms-3">{{ question.questionText }}</p>

                    <div class="radio-input ms-4 my-4">
                      <div
                        v-for="(item, index) in choiceList.filter(
                          (quest) =>
                            quest.question.questionId === question.questionId
                        )"
                        class="form-check form-check-inline"
                      >
                        <!-- required -->
                        <input
                          style="width: 20px; height: 20px"
                          class="form-check-input ms-2"
                          type="radio"
                          :name="'selectedChoice_' + item.question.questionId"
                          :id="
                            'selectedChoice_' +
                            item.question.questionId +
                            '_' +
                            index
                          "
                          :value="item.choiceValue"
                          v-model="requestData.nilai[item.question.questionId]"
                        />
                        <label
                          class="form-check-label ms-2"
                          :for="
                            'selectedChoice_' +
                            item.question.questionId +
                            '_' +
                            index
                          "
                          >{{ item.choiceValue }}</label
                        >
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div>
              <button class="btn btn-secondary w-100 mt-2" type="submit">
                Submit
              </button>
            </div>
          </div>
        </form>
      </div>

      <!-- kontent -->
    </main>
  </div>
</template>

<script>
import Sidebar from "../components/Sidebar.vue";
import Breadcrumb from "../components/Breadcrumb.vue";
export default {
  components: { Sidebar, Breadcrumb },
  name: "Penilaian-View",
  data() {
    return {
      isSidebarHidden: false,
      criteriaList: [],
      subcriteriaList: [],
      questionList: [],
      choiceList: [],
      teamList: [],
      requestData: {
        user: null,
        team: null,
        nilai: {},
      },
      pertanyaanID: {},
    };
  },
  methods: {
    async getAllCriteria() {
      try {
        this.$axios.get("/criteriaAll").then((response) => {
          const allItems = response.data;
          this.criteriaList = allItems.slice(0, 4);
          //   console.log(this.criteriaList);
        });
      } catch (error) {
        console.error("Error fetching criteria data:", error);
      }
    },

    async getAllSubcriteria() {
      try {
        this.$axios.get("/subcriteriaAll").then((response) => {
          const allItems = response.data;
          this.subcriteriaList = allItems.slice(0, 11);
          // console.log(this.subcriteriaList);
        });
      } catch (error) {
        console.error("Error fetching subcriteria data:", error);
      }
    },
    async getAllQuestion() {
      try {
        this.$axios.get("/questionAll").then((response) => {
          this.questionList = response.data;
          // console.log(this.questionList);
        });
      } catch (error) {
        console.error("Error fetching Question data:", error);
      }
    },
    async getAllChoice() {
      try {
        this.$axios.get("/choiceAll").then((response) => {
          this.choiceList = response.data;
          //   console.log(this.choiceList);
        });
      } catch (error) {
        console.error("Error fetching choice data:", error);
      }
    },
    async getAllTeam() {
      try {
        this.$axios.get("/getAllTeam").then((response) => {
          this.teamList = response.data;
          //   console.log(this.teamList);
        });
      } catch (error) {
        console.error("Error fetching Team data:", error);
      }
    },

    toggleSidebar() {
      this.isSidebarHidden = !this.isSidebarHidden;
    },
  },
  created() {
    this.getAllCriteria();
    this.getAllSubcriteria();
    this.getAllQuestion();
    this.getAllChoice();
    this.getAllTeam();
  },
  computed: {
    inputData() {
      const pertanyaanID = [];
      for (const questionID in this.requestData.nilai) {
        if (this.requestData.nilai[questionID] !== undefined) {
          // Jika nilai radio button tidak undefined, itu berarti dipilih
          pertanyaanID.push(questionID);
        }
      }

      const response = {
        user: this.requestData.user,
        team: this.requestData.team,
        nilai: this.requestData.nilai,
        pertanyaanID,
      };

      console.log(response);
      alert(response);
    },
  },
};
</script>

<style scoped>
/* Kontent */

main {
  margin-left: 250px;
}
.box-question {
  width: 100%;

  min-width: 300px;
  max-width: 60%;
  /* @media (max-width: 991.98px) {
    margin: 1rem;
  } */

  /* max-width: 250px; */
  max-height: fit-content;
  overflow: auto;
}
.card-text {
  word-wrap: break-word;
}

/* Kontent */

/* Radio */
/* .form-check-input {
  width: 20px;
  height: 20px;
} */
/* Radio */
</style>
