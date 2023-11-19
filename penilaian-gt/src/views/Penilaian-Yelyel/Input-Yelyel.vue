<template>
  <div>
    <Header></Header>
    <main id="main" class="main">
      <div class="card">
        <div class="card-body">
          <p class="card-subtitle">
            Hello,
            <span class="fw-bold fs-6">{{ TokenUser.user.toUpperCase() }}</span>
            <br />
          </p>
        </div>
      </div>
      <div class="card">
        <div class="card-header">
          <h5 class="p-2">Penilaian Yel-Yel</h5>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <form @submit.prevent="saveData">
              <table class="table table-bordered">
                <thead>
                  <tr class="fs-5 fw-bold text-center">
                    <th class="text-center p-4">No</th>
                    <th class="text-center p-4">Question</th>
                    <th class="text-center p-4">Max</th>
                    <!-- Kolom untuk setiap tim -->
                    <template
                      v-for="(team, teamIndex) in TeamData"
                      :key="teamIndex"
                    >
                      <th class="text-center p-4">{{ team.teamName }}</th>
                    </template>
                  </tr>
                </thead>
                <tbody>
                  <!-- Iterasi untuk setiap Question -->
                  <template v-for="(question, index) in QuestionData">
                    <tr>
                      <td class="text-center">{{ index + 1 }}</td>
                      <td class="text-wrap question-cell">
                        {{ question.subscriteriaName }}
                      </td>
                      <td class="text-center">{{ question.maxPoint }}</td>

                      <!-- Kolom input untuk setiap tim di setiap pertanyaan -->
                      <template
                        class="w-100"
                        v-for="(team, teamIndex) in TeamData"
                        :key="teamIndex"
                      >
                        <td class="text-center">
                          <input
                            type="number"
                            min="0"
                            class="form-control"
                            :value="inputValues[index][teamIndex]"
                            @input="updateInputValue(index, teamIndex, $event)"
                          />
                        </td>
                      </template>
                    </tr>
                  </template>
                </tbody>
                <button type="submit" class="btn btn-primary mt-3">
                  Submit
                </button>
              </table>
            </form>
          </div>
        </div>
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
      inputValues: [],
      QuestionData: [],
      TeamData: [],
      TokenUser: {
        nip: null,
        user: null,
        role: null,
      },
    };
  },

  methods: {
    async saveData() {
      try {
        const pointData = [];

        if (this.QuestionData.length > 0 && this.TeamData.length > 0) {
          // const currentDate = new Date();
          // const year = currentDate.getFullYear();
          // const month = String(currentDate.getMonth() + 1).padStart(2, "0");
          // const day = String(currentDate.getDate()).padStart(2, "0");

          // const formattedDate = `${year}-${month}-${day}`;
          // const testDate = "2023-11-20";

          let exceededMaxPoint = false; // Menambahkan variabel penanda untuk nilai melebihi maxPoint

          this.QuestionData.forEach((question, questionIndex) => {
            this.TeamData.forEach((team, teamIndex) => {
              const inputValue = this.inputValues[questionIndex][teamIndex];
              const maxPoint = question.maxPoint;

              if (inputValue !== "") {
                const data = {
                  subscriteriaName: question.subscriteriaName,
                  point: inputValue,
                  teamName: team.teamName,
                  // createAt: testDate,
                  username: this.TokenUser.user,
                };
                pointData.push(data);

                if (inputValue > maxPoint) {
                  alert(
                    `Nilai untuk ${team.teamName} melebihi Max Point yang ditetapkan.`
                  );
                  exceededMaxPoint = true; // Set penanda jika nilai melebihi maxPoint
                }
              }
            });
          });

          if (!exceededMaxPoint) {
            // Jika tidak ada nilai melebihi maxPoint, kirim data
            const response = await this.$axios.post(
              "http://localhost:8080/api/savePoint",
              pointData
            );
            console.log(response.data);
            alert("Data Saved");
          }
        }
      } catch (error) {
        console.error("Error saving data:", error);
        alert("Failed Save Data ! " + error);
      }
    },

    updateInputValue(questionIndex, teamIndex, event) {
      // Pastikan array inputValues[questionIndex] sudah tersedia sebelum mengakses teamIndex
      if (!this.inputValues[questionIndex]) {
        this.inputValues[questionIndex] = [];
      }

      // Tetapkan nilai langsung ke dalam array
      this.inputValues[questionIndex][teamIndex] = event.target.value;
    },

    async getQuestion() {
      try {
        const response = await this.$axios.get("/question");
        this.QuestionData = response.data;

        // Lakukan pemanggilan untuk getTeamsAll setelah QuestionData diperoleh
        await this.getTeamsAll();
      } catch (error) {
        console.error("Error fetching Question data:", error);
      }
    },

    async getTeamsAll() {
      try {
        const response = await this.$axios.get("/teams/all");
        this.TeamData = response.data;

        // Setelah mendapatkan kedua data yang diperlukan, inisialisasikan inputValues
        this.inputValues = Array.from(
          { length: this.QuestionData.length },
          () => Array.from({ length: this.TeamData.length }, () => "")
        );
      } catch (error) {
        console.error("Error fetching Teams data:", error);
      }
    },
  },

  created() {
    this.getTeamsAll();
    this.getQuestion();

    const userData = JSON.parse(localStorage.getItem("userData"));
    if (userData) {
      this.TokenUser.user = userData.username;
      this.TokenUser.role = userData.role;
      this.TokenUser.nip = userData.nip;
    }
  },
};
</script>

<style scoped></style>
