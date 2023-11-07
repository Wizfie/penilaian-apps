<template>
	<div>
		<Header />
		<main id="main" class="main">
			<section class="section">
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">Detail Nilai</h5>

								<!-- Form  -->
								<form @submit.prevent="submitForm">
									<table class="table-bordered text-center">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Question</th>
												<th scope="col">Nilai</th>
												<th scope="col">Max</th>
											</tr>
										</thead>
										<tbody
											v-for="(item, index) in nilaiList.filter(
												(list) => list.formattedTimestamp === timestamp
											)"
											:key="index"
										>
											<tr>
												<th scope="row">{{ index + 1 }}</th>
												<td class="col-sm-9">{{ item.questionText }}</td>
												<td class="col-sm-1">
													<input
														type="text"
														class="form-control text-center w-100 fw-bold"
														v-model="item.nilai"
													/>
												</td>
												<td class="col-sm-1">{{ item.maxValue }}</td>
											</tr>
										</tbody>
									</table>

									<button type="submit" class="btn btn-primary my-2 w-100">
										Update Data
									</button>
								</form>
								<!-- End Form -->
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
				timestamp: this.$route.params.timestamp,
				user: null,
				nilaiList: [],
			};
		},

		methods: {
			getNilaiByUser() {
				try {
					this.$axios
						.get("/nilai-list?username=" + this.user)
						.then((response) => {
							this.nilaiList = response.data;
						});
				} catch (error) {
					console.error("Error fetching nilai data:", error);
				}
			},
			submitForm() {
				const updatedValues = [];

				for (const item of this.nilaiList) {
					updatedValues.push({
						nilaiId: item.nilaiId,
						nilai: item.nilai,
					});
				}

				this.$axios
					.put("/update-nilai", updatedValues)
					.then((response) => {
						console.log(response.data);
						console.log("Data updated successfully");
						alert("Data updated successfully");
						this.$router.push("/dashboard");
					})
					.catch((error) => {
						console.error("Error updating data:", error);
						alert("Error updating data:", error);
					});
			},
			getAllChoice() {
				try {
					this.$axios.get("/choiceAll").then((response) => {
						this.choiceList = response.data;
						//   console.log(this.choiceList);
					});
				} catch (error) {
					console.error("Error fetching choice data:", error);
				}
			},
		},

		created() {
			const userData = JSON.parse(localStorage.getItem("userData"));
			if (userData) {
				this.user = userData.username;
			}
		},

		mounted() {
			this.getNilaiByUser();
		},
	};
</script>

<style>
	/* CSS styles go here */
</style>
