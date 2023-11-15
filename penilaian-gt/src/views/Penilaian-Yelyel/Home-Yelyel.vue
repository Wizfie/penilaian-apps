<template lang="">
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
				<div class="card-header"><h5 class="p-2">Penilaian Yel-Yel</h5></div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr class="fs-5 fw-bold text-center">
									<th colspan="7">Teams</th>
								</tr>
							</thead>
							<tbody>
								<tr class="text-center">
									<th class="">No</th>
									<th class="col-5">Question</th>
									<th class="">Max</th>
									<!-- hasil vfor misalnya  -->
									<th>Name team A</th>
									<th>Name team B</th>
									<th>Name team C</th>
									<th>Name team D</th>
									<th>Name team E</th>
									<th>Name team F</th>
									<!-- hasil vfor misalnya  -->
								</tr>
								<tr>
									<td class="text-center">1</td>
									<td class="col-6 text-wrap question-cell">
										Question Text ddadawdawdknlawkdnawdawd
									</td>
									<td class="text-center">Max Point</td>
									<td class="text-center">Nilai team A</td>
									<td class="text-center">Nilai team B</td>
									<td class="text-center">Nilai team C</td>
									<td class="text-center">Nilai team D</td>
									<td class="text-center">Nilai team E</td>
									<td class="text-center">Nilai team F</td>
								</tr>
							</tbody>
						</table>
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
				QuestionData: null,
				TokenUser: {
					nip: null,
					user: null,
					role: null,
				},
			};
		},
		methods: {
			async getQuestion() {
				try {
					await this.$Axios.get("/question/all").then((response) => {
						this.QuestionData === response.data;
						console.log(this.QuestionData);
					});
				} catch (error) {
					console.error("Error Fetch questionData " + error);
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
<style scoped>
	@media (max-width: 767px) {
		.question-cell {
			width: 40%; /* Atur lebar kolom agar mengisi lebar layar pada layar kecil */
		}
	}
</style>
