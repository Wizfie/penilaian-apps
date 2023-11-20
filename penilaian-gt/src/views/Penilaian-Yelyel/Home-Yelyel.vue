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
						<span class="fw-bold fs-6">{{ TokenUser.user.toUpperCase() }}</span>
						<br />
					</p>
					<router-link
						v-if="TokenUser.role === 'admin'"
						to="/input-point"
						class="btn btn-success"
						>History</router-link
					>
				</div>
			</div>
			<form @submit.prevent="saveData">
				<div class="card">
					<div
						class="card-header d-flex justify-content-between align-content-center"
					>
						<h5 class="my-auto fw-bold">Form Yel-yel</h5>
						<button type="submit" class="btn btn-primary p-2 m-3 w-auto">
							Submit
						</button>
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
											<td class="text-wrap question-cell w-50">
												{{ question.subscriteriaName }}
											</td>
											<td class="text-center">{{ question.maxPoint }}</td>

											<template
												v-for="(team, teamIndex) in TeamData"
												:key="teamIndex"
											>
												<td class="text-center">
													<input
														type="number"
														min="0"
														class="form-control"
														:id="'input-' + index + '-' + teamIndex"
														v-model="inputValues[index][teamIndex]"
													/>
												</td>
											</template>
										</tr>
									</template>
								</tbody>
								<tfoot>
									<tr>
										<th class="text-center p-2" colspan="3">Total</th>
										<th
											v-for="(team, teamIndex) in TeamData"
											:key="'total-' + teamIndex"
											class="text-center p-2"
										>
											{{ calculateTotal(teamIndex) }}
										</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</form>
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
					const dataToSend = [];
					let inputError = false;

					// Validasi semua nilai input sebelum mengirim data
					for (let index = 0; index < this.QuestionData.length; index++) {
						const question = this.QuestionData[index];
						for (
							let teamIndex = 0;
							teamIndex < this.TeamData.length;
							teamIndex++
						) {
							const inputValue = this.inputValues[index][teamIndex];

							if (inputValue === "" || isNaN(inputValue)) {
								inputError = true;
								alert(
									`Input pada Question ${index + 1} untuk tim ${
										this.TeamData[teamIndex].teamName
									} tidak valid. Pastikan tidak ada input yang kosong dan harus berupa angka.`
								);
								return; // Hentikan proses jika terdapat kesalahan pada input
							} else {
								const maxPoint = parseFloat(question.maxPoint);
								if (parseFloat(inputValue) > maxPoint) {
									inputError = true;
									alert(
										`Input pada pertanyaan ${index + 1} untuk tim ${
											this.TeamData[teamIndex].teamName
										} melebihi maksimum poin.`
									);
									return; // Hentikan proses jika terdapat kesalahan pada input
								} else {
									dataToSend.push({
										subscriteriaName: question.subscriteriaName,
										point: inputValue,
										teamName: this.TeamData[teamIndex].teamName,
										username: this.TokenUser.user,
									});
								}
							}
						}
					}

					if (!inputError) {
						// Send the data to the Spring backend jika tidak ada kesalahan pada input
						const response = await this.$axios.post("/savePoint", dataToSend);
						console.log("Data sent to Spring backend:", response.data);
						alert("Saved");
					}
				} catch (error) {
					console.error("Error sending data to Spring backend:", error);
					alert("failed");
				}
			},
			calculateTotal(teamIndex) {
				let total = 0;
				for (let i = 0; i < this.QuestionData.length; i++) {
					const inputValue = this.inputValues[i][teamIndex];
					if (!isNaN(inputValue) && inputValue !== "") {
						total += parseFloat(inputValue);
					}
				}
				return total;
			},
			// Metode untuk menghitung total maksimum nilai setiap tim
			initInputValues() {
				// Inisialisasi inputValues untuk setiap pertanyaan
				this.inputValues = this.QuestionData.map(() => {
					return this.TeamData.map(() => "");
				});
			},
			async getQuestion() {
				try {
					const response = await this.$axios.get("/question");
					this.QuestionData = response.data;
					await this.getTeamsAll();
				} catch (error) {
					console.error("Error fetching Question data:", error);
				}
			},

			async getTeamsAll() {
				try {
					const response = await this.$axios.get("/teams/all");
					this.TeamData = response.data;
					this.initInputValues();
				} catch (error) {
					console.error("Error fetching Teams data:", error);
				}
			},
		},

		created() {
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
