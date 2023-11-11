<template lang="">
	<div>
		<Header />

		<main id="main" class="main">
			<div class="pagetitle">
				<h1 class="my-4 fs-1">Admin Panel</h1>
				<nav>
					<ol class="breadcrumb">
						<li class="breadcrumb-item">
							<router-link to="/dashboard"><a>Home</a></router-link>
						</li>
					</ol>
				</nav>
			</div>
			<!-- End Page Title -->

			<section class="section">
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title text-center fw-bolder fs-3 mb-3 w-100">
									List Penilaian
								</h5>
							</div>
						</div>
						<div class="card">
							<div class="card-body">
								<table class="table-bordered text-center flex-wrap">
									<thead>
										<tr class="p-2">
											<th scope="col">#</th>
											<th scope="col">Question</th>
											<th
												class="m-2 p-1"
												v-for="(team, index) in nilaiList"
												:key="index"
												scope="col"
											>
												{{ team.teamName.toUpperCase() }}
											</th>
										</tr>
									</thead>
									<tbody>
										<tr
											v-for="(question, qIndex) in uniqueQuestions"
											:key="qIndex"
										>
											<td class="p-1">{{ qIndex + 1 }}</td>
											<td class="w-50 p-1">{{ question }}</td>
											<td v-for="(team, index) in nilaiList" :key="index">
												<!-- Tampilkan nilai jika ada, jika tidak, tampilkan tanda hubung (-) -->
												{{
													team.questions.find(
														(q) => q.questionText === question
													)?.nilai || "-"
												}}
											</td>
										</tr>
									</tbody>
									<tfoot>
										<tr class="p-2">
											<td colspan="2" class="text-center fw-bold">Total</td>
											<td
												class="fw-bold p-1"
												v-for="(team, index) in nilaiList"
												:key="index"
											>
												{{ calculateTeamTotal(team) }}
											</td>
										</tr>
									</tfoot>
								</table>
								<!-- <center class="fs-3 fw-bold">
									Tidak data 🧐
								</center> -->
							</div>
						</div>
					</div>
				</div>
			</section>
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
				nilaiList: [],
				uniqueQuestions: [],
			};
		},

		methods: {
			calculateTeamTotal(team) {
				const total = team.questions.reduce((total, question) => {
					return total + (question.nilai || 0);
				}, 0);
				return total.toFixed(2);
			},

			getAllNilai() {
				try {
					this.$axios.get("/nilai-getAll").then((response) => {
						let nilaiByTeam = {};

						response.data.forEach((nilai) => {
							if (!nilaiByTeam.hasOwnProperty(nilai.teamName)) {
								nilaiByTeam[nilai.teamName] = {
									teamName: nilai.teamName,
									questions: [],
									created: nilai.timestamp,
								};
							}

							nilaiByTeam[nilai.teamName].questions.push({
								questionText: nilai.questionText,
								nilai: nilai.nilai,
							});

							if (!this.uniqueQuestions.includes(nilai.questionText)) {
								this.uniqueQuestions.push(nilai.questionText);
							}
						});

						this.nilaiList = Object.values(nilaiByTeam);
					});
				} catch (error) {
					console.error("Error fetching nilai data:", error);
				}
			},
		},

		created() {
			this.getAllNilai();
		},
	};
</script>
<style lang=""></style>
