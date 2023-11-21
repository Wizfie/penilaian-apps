<template>
	<div>
		<Header />

		<main id="main" class="main">
			<div class="pagetitle">
				<h1 class="my-4 fs-1">Dashboard</h1>
				<!-- <nav>
					<ol class="breadcrumb">
						<li class="breadcrumb-item">
							<router-link to="/dashboard"><a>Home</a></router-link>
						</li>
						<li class="breadcrumb-item active">Plan</li>
						<li class="breadcrumb-item">Do</li>
						<li class="breadcrumb-item">Check</li>
						<li class="breadcrumb-item">Action</li>
					</ol>
				</nav> -->
			</div>
			<!-- End Page Title -->
		</main>
		<!-- End #main -->
	</div>
</template>
<script>
	import Header from "./../components/Header.vue";
	export default {
		components: {
			Header,
		},
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
				localStorage.clear("userData");
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

			const userData = JSON.parse(localStorage.getItem("userData"));
			if (userData) {
				this.requestData.user = userData.username;
			}
		},
	};
</script>
<style></style>
