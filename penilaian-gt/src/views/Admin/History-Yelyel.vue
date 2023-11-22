<template>
	<div>
		<Header />

		<main id="main" class="main">
			<div class="pagetitle">
				<h1 class="my-4 fs-1">Penilaian Yel-Yel</h1>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="d-flex justify-content-between">
						<h4>History</h4>
						<input
							class="form-control w-auto"
							type="date"
							v-model="selectedDate"
							@change="fetchTeamScores"
						/>
					</div>
				</div>
			</div>

			<div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>No</th>
							<th>Team Name</th>
							<th>Score</th>
						</tr>
					</thead>
					<tbody>
						<!-- Gunakan v-for untuk menampilkan data skor tim -->
						<tr v-for="(team, index) in teamScores" :key="index">
							<td>{{ index + 1 }}</td>
							<td>{{ team.teamName }}</td>
							<td>{{ team.score }}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- End Page Title -->
		</main>
		<!-- End #main -->
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
				requestData: {
					user: null,
					team: null,
				},
				selectedDate: "", // Menyimpan tanggal yang dipilih oleh pengguna
				teamScores: [],
			};
		},
		methods: {
			async fetchTeamScores() {
				try {
					console.log(this.selectedDate);
					// Lakukan panggilan Axios ke endpoint dengan tanggal yang dipilih
					const response = await this.$axios.get(
						`/average-score?startDate=${this.selectedDate}`
					);
					this.teamScores = response.data; // Menyimpan data skor tim yang diterima dari respons
				} catch (error) {
					console.error("Error fetching team scores:", error);
				}
			},
		},
		created() {
			const userData = JSON.parse(localStorage.getItem("userData"));
			if (userData) {
				this.requestData.user = userData.username;
			}
		},
	};
</script>
<style></style>
