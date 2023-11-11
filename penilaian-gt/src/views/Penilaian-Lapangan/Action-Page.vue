<template>
	<div>
		<Header />

		<main id="main" class="main">
			<div class="pagetitle">
				<h1 class="my-4 fs-1">ACTION</h1>
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
											v-model="requestData.team"
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
											(crit) => crit.criteria.criteriaId === criteria.criteriaId
										)"
									>
										<h5 class="my-2">
											{{ subs.subcriteriaName }}
										</h5>
										<hr />
										<div
											v-for="question in questionList.filter(
												(sub) =>
													sub.subcriteria.subcriteriaId === subs.subcriteriaId
											)"
										>
											<p class="ms-2 my-4">
												{{ question.questionText }}
											</p>
											<div
												class="form-check form-check-inline flex flex-wrap ms-2"
												v-for="(item, index) in choiceList.filter(
													(quest) =>
														quest.question.questionId === question.questionId
												)"
											>
												<input
													style="width: 20px; height: 20px"
													class="form-check-input"
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
													required
												/>
												<label
													class="form-check-label"
													:for="
														'selectedChoice_' +
														item.question.questionId +
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
								<button class="btn btn-primary w-100" type="submit">
									Submit
								</button>
							</div>
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
			};
		},
		methods: {
			signOut() {
				localStorage.clear("userData");
				this.$router.push("/");
			},

			getAllCriteria() {
				try {
					this.$axios.get("/criteriaAll").then((response) => {
						const allItems = response.data;
						this.criteriaList = allItems.slice(6, 7);
						// console.log(this.criteriaList);
					});
				} catch (error) {
					console.error("Error fetching criteria data:", error);
				}
			},

			getAllSubcriteria() {
				try {
					this.$axios.get("/subcriteriaAll").then((response) => {
						this.subcriteriaList = response.data;
						// console.log(this.subcriteriaList);
					});
				} catch (error) {
					console.error("Error fetching subcriteria data:", error);
				}
			},
			getAllQuestion() {
				try {
					this.$axios.get("/questionAll").then((response) => {
						this.questionList = response.data;
						// console.log(this.questionList);
					});
				} catch (error) {
					console.error("Error fetching Question data:", error);
				}
			},
			getAllChoice() {
				try {
					this.$axios.get("/choiceAll").then((response) => {
						this.choiceList = response.data;
						//   console.log(this.choiceList);
					});
				} catch (error) {
					console.error("Error fetching choice data:", error);
				}
			},
			getAllTeam() {
				try {
					this.$axios.get("/getAllTeam").then((response) => {
						this.teamList = response.data;
						//   console.log(this.teamList);
					});
				} catch (error) {
					console.error("Error fetching Team data:", error);
				}
			},

			inputData() {
				const username = this.requestData.user;
				const teamName = this.requestData.team;

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

				localStorage.setItem("inputData4", JSON.stringify(nilaiData));

				const data1 = JSON.parse(localStorage.getItem("inputData1"));
				const data2 = JSON.parse(localStorage.getItem("inputData2"));
				const data3 = JSON.parse(localStorage.getItem("inputData3"));
				const data4 = JSON.parse(localStorage.getItem("inputData4"));

				this.allData = [
					...(data1 || []),
					...(data2 || []),
					...(data3 || []),
					...(data4 || []),
				];

				const allDataFormatted = this.allData.map((data) => {
					return {
						username: data.username,
						teamName: data.teamName,
						questionId: data.questionId,
						nilai: data.nilai,
					};
				});

				this.$axios
					.post("/save-nilai", allDataFormatted)
					.then((response) => {
						console.log(response);
						alert("Data Berhasil disimpan");
					})
					.catch((error) => {
						console.error("Terjadi Kesalahan:", error);
						alert(error);
					})
					.finally(() => {
						this.requestData.team = null;
						this.requestData.nilai = {};
						window.scrollTo(0, 0);
						localStorage.removeItem("inputData1");
						localStorage.removeItem("inputData2");
						localStorage.removeItem("inputData3");
						localStorage.removeItem("inputData4");
						router.push("/lapangan");
					});
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
