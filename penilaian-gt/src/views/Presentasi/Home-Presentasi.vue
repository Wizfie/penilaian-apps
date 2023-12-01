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

				<div v-for="(item, index) in questionData" :key="index">
					<div class="card p-1">
						<div class="card-header text-wrap fs-3 fw-bold">
							{{ item.title }}
						</div>
						<ol class="list-group list-group-flush">
							<li
								class="list-group-item fw-semibold"
								v-for="(point, pointIndex) in item.points"
								:key="pointIndex"
							>
								<strong>{{ pointIndex + 1 }}. </strong>{{ point.text }} <br />
								<strong>{{ "Point Max = " + point.maxValue }}</strong>
								<br />
								<!-- <strong>{{ "Score Max = " + item.items.maxScore }}</strong> -->
							</li>
						</ol>
						<div class="card-footer">
							<input
								type="number"
								:id="'input-' + item.title + '-' + index"
								class="form-control"
								placeholder="Answer"
								v-model="score[item.title]"
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
				const evaluationData = [];

				for (const question of this.questionData) {
					const maxScore = question.maxScore;
					const title = question.title;

					const scoreValue = this.score[title] || 0;

					if (scoreValue < 0 || scoreValue > maxScore) {
						alert(
							`Invalid score for ${title}. Please enter a score between 0 and ${maxScore}.`
						);
						return; // Stop further processing if invalid score found
					}

					const limitedScore = Math.max(0, Math.min(scoreValue, maxScore));

					evaluationData.push({
						title: title,
						score: limitedScore,
						username: this.tokenUser.name,
						teamName: this.selectedTeam,
						createdAt: new Date().toISOString().slice(0, 10), // Format tanggal "YYYY-MM-DD"
					});
				}

				try {
					this.$axios.post("/save-score", evaluationData).then((response) => {
						console.log(response);
						alert("Score saved");
					});
				} catch (error) {
					console.log("Failed to save score: " + error);
					alert("Failed to save score");
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
					this.$axios.get("/items-presentasi").then((response) => {
						console.log(response.data);
						this.questionData = response.data;
						// console.log(this.questionData);
					});
				} catch (error) {
					console.log("fail fetch question" + error);
				}
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
