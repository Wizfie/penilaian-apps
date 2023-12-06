<template lang="">
	<div>
		<Header></Header>
		<div class="main" id="main">
			<div class="card">
				<div class="d-flex justify-content-between align-content-center">
					<div class="card-header p-4 fw-bolder fs-4">Details Presentasi</div>
					<button @click="$router.back()" class="btn btn-dark m-4">Back</button>
				</div>
			</div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>No</th>
						<th>Question</th>
						<th>Score</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(item, index) in filteredDetailScore" :key="index">
						<td>{{ index + 1 }}</td>
						<td>
							{{ item.title }} <br />

							<Strong
								><small> Max score : {{ item.maxScore }}</small></Strong
							>
						</td>
						<td>
							<input
								type="number"
								name="score"
								class="form-control text-center"
								id="score"
								:disabled="!isEditMode"
								v-model="item.score"
								@change="validateScore(item)"
							/>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr class="text-center fw-bold">
						<td colspan="2">Total Score</td>
						<td>{{ totalScore }}</td>
					</tr>
				</tfoot>
			</table>
			<div class="d-flex p-2 gap-2 mx-auto">
				<button
					class="btn btn-secondary w-100"
					v-if="!isEditMode"
					@click="enableEditMode"
				>
					Update
				</button>
				<button class="btn btn-danger w-100" v-else @click="cancelEditMode">
					Cancel
				</button>
				<button
					class="btn btn-primary w-100"
					v-if="isEditMode"
					@click="submitChanges"
				>
					Submit
				</button>
			</div>
		</div>
	</div>
</template>
<script>
	import Header from "../../components/Header.vue";
	export default {
		components: { Header },

		data() {
			return {
				team: null,
				date: null,
				detailScore: [],
				isEditMode: false,
				tokenUser: {
					name: null,
					nip: null,
					role: null,
				},
			};
		},

		methods: {
			enableEditMode() {
				this.isEditMode = true;
			},

			cancelEditMode() {
				this.isEditMode = false;
				// Lakukan reset nilai jika diperlukan
			},

			submitChanges() {
				if (confirm("Update Changes?")) {
					// Lakukan validasi sebelum mengirim data ke backend
					for (const item of this.filteredDetailScore) {
						if (
							item.score === null ||
							item.score === "" ||
							item.score > item.maxScore
						) {
							console.error("Invalid score detected.");
							alert(`${item.title} \n  Melebihi Max Score!!`);
							return; // Berhenti jika ada skor yang tidak valid
						}
					}

					// Kirim perubahan ke backend
					this.$axios
						.post("/score/save", this.filteredDetailScore)
						.then((response) => {
							console.log("Changes submitted successfully:", response.data);
							this.isEditMode = false; // Set mode edit kembali ke false
							alert("Updated");
						})
						.catch((error) => {
							console.error("Failed to submit changes:", error);
							alert("fail Update");
						});

					console.log("Changes submitted:", this.filteredDetailScore);
					this.isEditMode = false; // Set mode edit kembali ke false
				} else {
					this.isEditMode = false; // Set mode edit kembali ke false
				}
			},

			validateScore(item) {
				if (
					this.isEditMode &&
					(item.score === null ||
						item.score === "" ||
						item.score > item.maxScore)
				) {
					console.error("Invalid score detected.");
				}
			},
			getByNip() {
				try {
					this.$axios
						.get(`/score/byNip/${this.tokenUser.nip}`)
						.then((response) => {
							console.log(response.data); // Data yang diambil dari API
							this.detailScore = response.data;
						});
				} catch (error) {
					console.log("Fail getByNip " + error);
				}
			},
		},
		computed: {
			filteredDetailScore() {
				const filteredData = this.detailScore.filter((score) => {
					const isMatching =
						score.teamName === this.team && score.createdAt === this.date;
					if (!isMatching) {
						console.log(`Mismatched data:
          Team: ${this.team}, Date: ${this.date},
          Data Team: ${score.teamName}, Data Date: ${score.createdAt}`);
					}
					return isMatching;
				});
				return filteredData;
			},

			totalScore() {
				return this.filteredDetailScore.reduce((total, item) => {
					return total + (item.score || 0);
				}, 0);
			},
		},

		mounted() {
			try {
				const userData = JSON.parse(localStorage.getItem("userData"));
				if (userData) {
					this.tokenUser.name = userData.username;
					this.tokenUser.nip = userData.nip;
					this.tokenUser.role = userData.role;

					this.team = this.$route.query.team;
					this.date = this.$route.query.date;
					console.log(this.team);
					console.log(this.date);
					// console.log(this.tokenUser);
				}
			} catch (error) {
				console.log("fail fetch userData " + error);
			}

			this.getByNip();
		},
	};
</script>
<style lang=""></style>
