<template lang="">
	<div>
		<Header></Header>

		<main id="main" class="main">
			<div class="pagetitle my-3 mb-4">
				<h1 class="fs-1">Penilaian Presentasi QCC</h1>
			</div>

			<form @submit.prevent="sendScore">
				<h4>Hello, <span>Wiz</span></h4>

				<div class="input-group mb-3">
					<label class="input-group-text" for="teams-select">Pilih Team</label>
					<select
						class="form-select"
						id="teams-select"
						required
						v-model="selectedTeam"
					>
						<option disabled selected>List Teams</option>
						<option v-for="team in teamList" :value="team.name">
							{{ team.name }}
						</option>
					</select>
				</div>

				<div v-for="(grouped, title) in questionByTitle" :key="title">
					<div class="card p-1">
						<div class="card-header text-wrap fs-3 fw-bold">{{ title }}</div>
						<ol class="list-group list-group-flush">
							<li
								v-for="(item, index) in grouped"
								:key="item.id"
								class="list-group-item fw-semibold"
							>
								<strong>{{ index + 1 + ". 	" }}</strong
								>{{ item.text }} <br />
								<strong>{{ "Point Max = " + item.maxValue }}</strong>
								<br />
								<strong>{{ "Score Max = " + item.items.maxScore }}</strong>
							</li>
						</ol>
						<div class="card-footer">
							<input
								type="number"
								:id="'input-' + title + '-' + index"
								class="form-control"
								placeholder="Answer"
								v-model="score[title + '-' + index]"
								required
							/>
						</div>
					</div>
				</div>
				<button class="btn btn-primary w-100">Submit</button>
			</form>
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
				questionData: [],
				teamList: [],
				questionByTitle: {},
				selectedTeam: null,
				score: {},

				tokenUser: {
					nip: null,
					name: null,
					role: null,
				},
			};
		},

		methods: {
			sendScore() {
				const evaluationData = {
					username: this.tokenUser.name,
					teamName: this.selectedTeam,
					evaluations: [],
					createdAt: null, // Sesuaikan nilainya sesuai kebutuhan
				};

				let isInvalidScore = false; // Menandakan jika terdapat skor yang tidak valid

				for (const title in this.questionByTitle) {
					this.questionByTitle[title].forEach((item, index) => {
						const scoreKey = `${title}-${index}`;
						const scoreValue = this.score[scoreKey] || 0; // Default to 0 if score is not provided
						const maxScore = item.items.maxScore;

						// Validasi skor
						if (scoreValue < 0 || scoreValue > maxScore) {
							isInvalidScore = true; // Set isInvalidScore menjadi true jika ada skor yang tidak valid
							// Tampilkan pesan peringatan
							alert(
								`Invalid score for ${item.items.title}. Please enter a score between 0 and ${maxScore}.`
							);
						}

						// Ensure the score is within the range of 0 and maximum allowable score
						const limitedScore = Math.max(0, Math.min(scoreValue, maxScore));

						evaluationData.evaluations.push({
							title: title,
							score: limitedScore,
						});
					});
				}

				if (!isInvalidScore) {
					try {
						this.$axios.post("/save-score", evaluationData).then((response) => {
							console.log(response);
							alert("Score saved");
						});
					} catch (error) {
						console.log("Failed to save score: " + error);
						alert("Failed to save score");
					}
				}
			},

			getTeams() {
				try {
					this.$axios.get("/teams-all").then((response) => {
						console.log(response.data);
						this.teamList = response.data;
					});
				} catch (error) {
					console.log("fail fetch teams" + error);
				}
			},

			getQuestion() {
				try {
					this.$axios.get("/points-presentasi").then((response) => {
						console.log(response.data);
						this.questionData = response.data;
						// console.log(this.questionData);
						this.groupQuestionByTitle();
					});
				} catch (error) {
					console.log("fail fetch question" + error);
				}
			},
			groupQuestionByTitle() {
				this.questionByTitle = {};
				this.questionData.forEach((item) => {
					const title = item.items.title;
					if (!this.questionByTitle[title]) {
						this.questionByTitle[title] = [];
					}
					this.questionByTitle[title].push(item);
				});
				console.log(this.questionByTitle);
			},
		},

		created() {
			this.getQuestion();
			this.getTeams();

			try {
				const userData = JSON.parse(localStorage.getItem("userData"));
				if (userData) {
					this.tokenUser.name = userData.username;
					this.tokenUser.nip = userData.nip;
					this.tokenUser.role = userData.role;

					console.log(this.tokenUser);
				}
			} catch (error) {
				console.log("fail fetch userData " + error);
			}
		},
	};
</script>
<style lang=""></style>
