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
								<h5 class="card-title text-center fw-bolder fs-3 mb-3">
									List Penilaian
								</h5>
							</div>
						</div>
						<div class="card">
							<div class="card-body">
								<table class="table table-striped">
									<thead>
										<tr>
											<th scope="col">#</th>
											<th scope="col">Question</th>
											<th
												v-for="(team, index) in nilaiList"
												:key="index"
												scope="col"
											>
												{{ team.teamName }}
											</th>
											<th scope="col">Action</th>
										</tr>
									</thead>
									<tbody>
										<tr
											v-for="(question, qIndex) in uniqueQuestions"
											:key="qIndex"
										>
											<td>{{ qIndex + 1 }}</td>
											<td>{{ question }}</td>
											<td v-for="(team, index) in nilaiList" :key="index">
												<!-- Tampilkan nilai jika ada, jika tidak, tampilkan tanda hubung (-) -->
												{{
													team.questions.find(
														(q) => q.questionText === question
													)?.nilai || "-"
												}}
											</td>
											<td>Action</td>
										</tr>
									</tbody>
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
			getAllNilai() {
				try {
					this.$axios.get("/nilai-getAll").then((response) => {
						// Inisialisasi objek untuk menyimpan nilai per tim
						let nilaiByTeam = {};

						// Iterasi melalui data nilai
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

							// Menambahkan pertanyaan ke array uniqueQuestions jika belum ada
							if (!this.uniqueQuestions.includes(nilai.questionText)) {
								this.uniqueQuestions.push(nilai.questionText);
							}
						});

						// Konversi objek nilaiByTeam menjadi array
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
