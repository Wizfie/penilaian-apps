<template>
  <div>
    <Header></Header>
    <main id="main" class="main">
      <div class="card">
        <div
          class="card-body d-flex justify-content-between align-content-center"
        >
          <p class="card-subtitle">
            Hello,
            <span class="fw-bold fs-6">{{ tokenUser.user.toUpperCase() }}</span>
            <br />
          </p>

          <div>
            <router-link
              v-if="tokenUser.role === 'admin'"
              to="/input-point"
              class="btn btn-success"
              >History</router-link
            >
            <button
              v-if="tokenUser.role === 'user'"
              class="btn btn-success w-auto"
              @click="toggleTable()"
            >
              History
            </button>
          </div>
        </div>
      </div>

      <div v-if="!showTable" class="card">
        <form @submit.prevent="saveData">
          <div class="card-header p-4">
            <h4 class="fw-bold">Penilaian Yel-yel</h4>
          </div>
          <div class="p-4">
            <label class="fw-bold mb-2" for="team">Select Team</label>
            <select
              class="form-control w-auto"
              name="team"
              id="team"
              v-model="selectedTeam"
              required
            >
              <option disabled selected>Pilih Team</option>
              <option v-for="teams in teamData" :value="teams.teamName">
                {{ teams.teamName.toUpperCase() }}
              </option>
            </select>
          </div>
          <div class="table-responsive p-2">
            <table class="table table-bordered">
              <thead>
                <tr class="text-center">
                  <th>No</th>
                  <th>Question</th>
                  <th>Max</th>
                  <th>Point</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(quest, index) in questionData" :key="index">
                  <td class="text-center">{{ index + 1 }}</td>
                  <td class="text-wrap">{{ quest.subscriteriaName }}</td>
                  <td class="text-center">{{ quest.maxPoint }}</td>
                  <td>
                    <input
                      class="form-control"
                      type="number"
                      v-model="pointData[index]"
                      required
                      @input="updateTotalPoint"
                    />
                  </td>
                </tr>
              </tbody>
              <tfoot>
                <tr class="text-center fw-bold">
                  <td colspan="3">Total Point</td>
                  <td>{{ totalPoint }}</td>
                </tr>
              </tfoot>
            </table>
            <button class="btn btn-primary w-100" @click="submitData">
              Submit
            </button>
          </div>
        </form>
      </div>

      <div v-if="showTable" class="card">
        <form>
          <div
            class="card-header d-flex justify-content-between align-content-center"
          >
            <h5 class="my-auto fw-bold">History</h5>
            <div>
              <!-- <button type="submit" class="btn btn-primary p-2 m-2 w-auto">
                Submit
              </button> -->
            </div>
          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered">
                <thead>
                  <tr class="fs-5 fw-bold text-center">
                    <th class="text-center p-4">No</th>
                    <th class="text-center p-4">Question</th>
                    <th class="text-center p-4">Max</th>
                    <!-- Kolom untuk setiap tim -->
                    <template
                      v-for="(team, teamIndex) in teamData"
                      :key="teamIndex"
                    >
                      <th class="text-center p-4">{{ team.teamName }}</th>
                    </template>
                  </tr>
                </thead>
                <tbody>
                  <!-- Iterasi untuk setiap Question -->
                  <template v-for="(question, index) in questionData">
                    <tr>
                      <td class="text-center">{{ index + 1 }}</td>
                      <td class="text-wrap question-cell w-25">
                        {{ question.subscriteriaName }}
                      </td>
                      <td class="text-center">{{ question.maxPoint }}</td>

                      <template
                        v-for="(team, teamIndex) in teamData"
                        :key="teamIndex"
                      >
                        <td class="text-center">
                          <input
                            type="number"
                            min="0"
                            class="form-control"
                            :id="'input-' + index + '-' + teamIndex"
                          />
                        </td>
                      </template>
                    </tr>
                  </template>
                </tbody>
                <tfoot>
                  <tr>
                    <th class="text-center p-2" colspan="3">Total</th>
                    <th class="text-center p-2"></th>
                  </tr>
                </tfoot>
              </table>
            </div>
          </div>
        </form>
      </div>
    </main>
  </div>
</template>

<script>
import Header from "../../components/Header.vue";
export default {
  components: {
    Header,
  },

  data() {
    return {
      username: "",
      selectedTeam: "",
      inputValues: [],
      questionData: [],
      teamData: [],
      pointData: [],
      totalPoint: 0,
      showTable: false,
      tokenUser: {
        nip: null,
        user: null,
        role: null,
      },
    };
  },

  methods: {
    updateTotalPoint() {
      this.totalPoint = this.pointData.reduce((acc, currentValue) => {
        return acc + (parseInt(currentValue) || 0);
      }, 0);
    },
    saveData() {
      const sendData = this.questionData.map((quest, index) => ({
        subscriteriaName: quest.subscriteriaName,
        teamName: this.selectedTeam,
        username: this.tokenUser.user,
        point: this.pointData[index],
        createdAt: null, // Ambil poin dari input
      }));

      const invalidIndex = this.questionData.findIndex((quest, index) => {
        return (
          this.pointData[index] < 0 || this.pointData[index] > quest.maxPoint
        );
      });

      if (invalidIndex !== -1) {
        const invalidColumnName =
          this.questionData[invalidIndex].subscriteriaName;
        alert(
          `Pastikan Pointnya tidak melebihi Max Point \nPoint ${invalidColumnName}`
        );
        return; // Menghentikan proses penyimpanan jika ada nilai yang tidak valid
      }

      this.$axios
        .post("/savePoint", sendData)
        .then((response) => {
          console.log("Data disimpan!", response.data);
          alert("Saved");
        })
        .catch((error) => {
          console.error("Data gagal disimpan!", error);
          alert("Failed");
        });
    },

    toggleTable() {
      this.showTable = !this.showTable;
    },
    async getQuestion() {
      try {
        const response = await this.$axios.get("/question");
        this.questionData = response.data;
        console.log(this.questionData);
      } catch (error) {
        console.error("Error fetching Question data:", error);
      }
    },

    async getTeamsAll() {
      try {
        const response = await this.$axios.get("/teams/all");
        this.teamData = response.data;
      } catch (error) {
        console.error("Error fetching Teams data:", error);
      }
    },
  },

  created() {
    this.getQuestion();
    this.getTeamsAll();

    const userData = JSON.parse(localStorage.getItem("userData"));
    if (userData) {
      this.tokenUser.user = userData.username;
      this.tokenUser.role = userData.role;
      this.tokenUser.nip = userData.nip;
    }
  },
};
</script>

<style scoped></style>
