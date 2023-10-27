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
				<div
					class="box-question card-body rounded-1 bg-body-secondary mx-auto m-5 p-5 gap-2"
				>
					<div v-for="item in criteriaList" :key="item.criteriaId">
						<div class="border border-2 border border-black p-3">
							<h5 class="card-subtitle border-2">{{ item.criteriaName }}</h5>
						</div>
						<div class="border border-2 border border-black"></div>
						<div>
							<!-- :key="subcriteriaId" -->
							<div
								v-for="item in subcriteriaList.filter(
									(sub) => sub.criteria.criteriaId === item.criteriaId
								)"
								class="border border-2 border border-black my-2 p-2"
							>
								<h6 class="card-text">{{ item.subcriteriaName }}</h6>
								<!-- v-for="item in choiceList.filter(
                  (sub) => sub.questionId.questionId === item.quequestionId
                  )" -->
								<!-- value="{{item.choiceValue}}" -->
								<!-- :key="questionId" -->
								<div
									v-for="item in questionList.filter(
										(sub) =>
											sub.subcriteria.subcriteriaId === item.subcriteriaId
									)"
								>
									<p class="card-text ms-3">{{ item.questionText }}</p>

									<div
										class="radio-input ms-4"
										v-for="(item, index) in choiceList"
										:key="index"
									>
										<div class="form-check form-check-inline">
											<input
												class="form-check-input"
												type="radio"
												:name="'inlineRadioOptions_' + question.questionId"
												:id="'inlineRadio_' + question.questionId + '_' + index"
												:value="choice.choiceValue"
											/>
											<label
												class="form-check-label"
												:for="
													'inlineRadio_' + question.questionId + '_' + index
												"
												>{{ item.choiceValue }}</label
											>
										</div>
										<!-- <div class="form-check form-check-inline">
											<input
												class="form-check-input"
												type="radio"
												name="inlineRadioOptions"
												id="inlineRadio2"
												value="option2"
											/>
											<label class="form-check-label" for="inlineRadio2"
												>2</label
											>
										</div> -->
										<!-- <div class="form-check form-check-inline mb-lg-5">
											<input
												class="form-check-input"
												type="radio"
												name="inlineRadioOptions"
												id="inlineRadio3"
												value="option3"
											/>
											<label class="form-check-label" for="inlineRadio3"
												>3</label
											>
										</div> -->
										<!-- <div class="form-check form-check-inline mb-lg-5">
											<input
												class="form-check-input"
												type="radio"
												name="inlineRadioOptions"
												id="inlineRadio4"
												value="option3"
											/>
											<label class="form-check-label" for="inlineRadio4"
												>4</label
											>
										</div> -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
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
			};
		},
		methods: {
			async getAllCriteria() {
				try {
					this.$axios.get("/criteria/all").then((response) => {
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
					this.$axios.get("/subcriteria/all").then((response) => {
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
					this.$axios.get("/question/all").then((response) => {
						this.questionList = response.data;
						// console.log(this.questionList);
					});
				} catch (error) {
					console.error("Error fetching Question data:", error);
				}
			},
			async getAllChoice() {
				try {
					this.$axios.get("/choice/all").then((response) => {
						this.choiceList = response.data;
						console.log(this.choiceList);
					});
				} catch (error) {
					console.error("Error fetching choice data:", error);
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
</style>
