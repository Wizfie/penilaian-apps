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
					<div>
						<div
							v-for="item in criteria"
							:key="item.criteriaId"
							class="border border-2 border border-black p-3"
						>
							<h4 class="card-subtitle border-2">{{ item.criteriaName }}</h4>
						</div>
						<div class="border border-2 border border-black"></div>
						<div>
							<div class="border border-2 border border-black my-2 p-2">
								<h6 class="card-text">subkriteria</h6>
							</div>
							<div>
								<p class="card-text">Question</p>

								<div class="form-check form-check-inline">
									<input
										class="form-check-input"
										type="radio"
										name="inlineRadioOptions"
										id="inlineRadio1"
										value="option1"
									/>
									<label class="form-check-label" for="inlineRadio1">1</label>
								</div>
								<div class="form-check form-check-inline">
									<input
										class="form-check-input"
										type="radio"
										name="inlineRadioOptions"
										id="inlineRadio2"
										value="option2"
									/>
									<label class="form-check-label" for="inlineRadio2">2</label>
								</div>
								<div class="form-check form-check-inline mb-lg-5">
									<input
										class="form-check-input"
										type="radio"
										name="inlineRadioOptions"
										id="inlineRadio3"
										value="option3"
									/>
									<label class="form-check-label" for="inlineRadio3">3</label>
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
				criteria: [],
			};
		},
		methods: {
			fetchKriteria() {
				this.$axios
					.get("/criteria/all")
					.then((response) => {
						this.criteria = response.data;
					})
					.catch((e) => {
						console.log("Terjadi Kesalahan" + e);
					});
			},

			toggleSidebar() {
				this.isSidebarHidden = !this.isSidebarHidden;
			},
		},
		created() {
			this.fetchKriteria();
			console.log(this.criteria);
		},

		mounted() {
			console.log(this.criteria);
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
