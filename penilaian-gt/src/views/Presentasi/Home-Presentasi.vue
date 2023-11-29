<template lang="">
	<div>
		<Header></Header>

		<main id="main" class="main">
			<div class="pagetitle my-3 mb-4">
				<h1 class="fs-1">Penilaian Presentasi QCC</h1>
			</div>

			<form @submit.prevent="">
				<h4>Hello, <span>Wiz</span></h4>

				<div class="input-group mb-3">
					<label class="input-group-text" for="teams-select">Pilih Team</label>
					<select class="form-select" id="teams-select" required>
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
							</li>
						</ol>
						<div class="card-footer">
							<input type="number" class="form-control" placeholder="Answer" />
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
			};
		},

		methods: {
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
		},
	};
</script>
<style lang=""></style>
