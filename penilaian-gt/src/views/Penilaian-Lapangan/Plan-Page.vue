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
								<button @click="signOut" class="dropdown-item" type="button">
									Sign Out
								</button>
							</li>
						</ul>
					</div>
				</div>
			</nav>
			<!-- Nav Header -->
			<!-- Breadcrumb -->
			<Breadcrumb />
			<!-- Breadcrumb -->
			<!-- kontent -->
			<h1 class="p-3 text-center">PLAN</h1>
			<div class="d-flex flex-lg-row flex-column w-100">
				<form @submit.prevent="inputData" class="w-100">
					<div
						class="box-question card-body rounded-1 bg-body-secondary mx-auto p-3"
					>
						<div>
							<label for="input-user">Nama Juri:</label>
							<input
								class="form-control w-50 bg-white my-2"
								type="text"
								aria-label="Disabled input
							example"
								v-model="requestData.user"
								disabled
								readonly
							/>
						</div>
						<div class="team-group w-50 my-2">
							<label for="teamSelect">Pilih Tim:</label>
							<select
								class="form-select my-2"
								aria-label="Default select example"
								v-model="requestData.team"
								required
							>
								<option v-for="team in teamList" :value="team.teamName">
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

										<div class="radio-input ms-2 my-4">
											<div
												v-for="(item, index) in choiceList.filter(
													(quest) =>
														quest.question.questionId === question.questionId
												)"
												class="form-check"
											>
												<!-- required -->
												<input
													style="width: 20px; height: 20px"
													class="form-check-input ms-1"
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
											<hr style="height: 2px" />
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
	import Sidebar from "../../components/Sidebar.vue";
	import Breadcrumb from "../../components/Breadcrumb.vue";
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
			};
		},
		methods: {
			signOut() {
				sessionStorage.clear("userData");
				this.$router.push("/");
			},

			getAllCriteria() {
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

			getAllSubcriteria() {
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

			toggleSidebar() {
				this.isSidebarHidden = !this.isSidebarHidden;
			},

			inputData() {
				// Mengambil data dari elemen HTML dan menyimpannya dalam variabel
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

				this.$axios
					.post("/save-nilai", nilaiData)
					.then((response) => {
						console.log(response);
						alert("Data Berhasil disimpan");
					})
					.catch((error) => {
						console.error("Terjadi Kesalahan:", error);
					})
					.finally(() => {
						this.requestData.user = null;
						this.requestData.team = null;
						this.requestData.nilai = {};
						window.scrollTo(0, 0);
					});
			},
		},
		created() {
			this.getAllCriteria();
			this.getAllSubcriteria();
			this.getAllQuestion();
			this.getAllChoice();
			this.getAllTeam();

			const userData = JSON.parse(sessionStorage.getItem("userData"));
			if (userData) {
				this.requestData.user = userData.username;
			}
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
		max-height: fit-content;
		overflow: auto;
	}
	.card-text {
		word-wrap: break-word;
	}

	/* Kontent */
	/*radio button */
	.radio-input .form-check {
		display: inline-block;
		margin-right: 10px; /* Jarak antara radio buttons */
	}

	@media (max-width: 768px) {
		.radio-input .form-check {
			display: block; /* Ubah menjadi satu kolom pada layar kecil */
		}
	}
	/*radio button */
</style>
