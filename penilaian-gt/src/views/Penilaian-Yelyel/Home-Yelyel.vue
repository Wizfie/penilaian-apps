<template>
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
				<div class="card-header">
					<h5 class="p-2">Penilaian Yel-Yel</h5>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr class="fs-5 fw-bold text-center">
									<th>No</th>
									<th class="col-5 text-center">Question</th>
									<th>Max</th>
									<!-- Kolom untuk setiap tim -->
									<template
										v-for="(team, teamIndex) in TeamData"
										:key="teamIndex"
									>
										<th class="text-center">{{ team.teamName }}</th>
									</template>
								</tr>
							</thead>
							<tbody>
								<!-- Iterasi untuk setiap kriteria -->
								<template v-for="(criteria, index) in QuestionData">
									<tr
										v-for="(subscriteria, subIndex) in criteria.subscriteria"
										:key="subIndex"
									>
										<td class="text-center">{{ index + 1 }}</td>
										<td class="col-6 text-wrap question-cell">
											{{ subscriteria.subsName }}
										</td>
										<td class="text-center">{{ subscriteria.maxPoint }}</td>

										<!-- Kolom input untuk setiap tim di setiap pertanyaan -->
										<template
											v-for="(team, teamIndex) in TeamData"
											:key="teamIndex"
										>
											<td class="text-center">
												<input
													type="text"
													class="form-control"
													v-model="inputValues[index][teamIndex]"
												/>
											</td>
										</template>
										<!-- Akhir iterasi untuk input tim di setiap pertanyaan -->
									</tr>
								</template>
								<!-- Akhir iterasi untuk setiap kriteria -->
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
			async getQuestion() {
				try {
					const response = await this.$axios.get("/question/all");
					this.QuestionData = response.data;
					this.inputValues = Array(this.QuestionData.length)
						.fill([])
						.map(() => Array(this.TeamData.length).fill(0));
					console.log(this.inputValues);
					console.log(this.QuestionData);
				} catch (error) {
					console.error("Error fetching Question data:", error);
				}
			},

			async getTeamsAll() {
				try {
					const response = await this.$axios.get("/teams/all");
					this.TeamData = response.data;
					console.log(this.TeamData);
				} catch (error) {
					console.error("Error fetching Teams data:", error);
				}
			},
		},

		created() {
			this.getQuestion();
			this.getTeamsAll();
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
			width: 40%;
		}
	}
</style>
