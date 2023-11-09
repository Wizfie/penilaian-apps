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
									<table
										class="table table-bordered text-center align-content-center"
									>
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Question</th>
												<th scope="col">Nilai</th>
												<th scope="col">Max</th>
											</tr>
										</thead>
										<tbody>
											<tr
												v-for="(item, index) in nilaiList.filter(
													(list) => list.formattedTimestamp === timestamp
												)"
												:key="index"
											>
												<th scope="row">{{ index + 1 }}</th>
												<td class="col-sm-10">
													{{ item.questionText }}
												</td>
												<td class="col-sm-1">
													<input
														:id="'nilaiInput_' + item.nilaiId"
														type="text"
														class="form-control text-center w-100 fw-bold"
														v-model="item.nilai"
														@input="TotalNilai"
														required
													/>
												</td>
												<td class="col-sm-1">{{ item.maxValue }}</td>
											</tr>
											<tr>
												<td colspan="2">Total Nilai</td>
												<td class="fw-bolder">{{ totalNilai }}</td>
												<td>-</td>
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
				totalNilai: null,
			};
		},

		methods: {
			getNilaiByUser() {
				try {
					this.$axios
						.get("/nilai-list?username=" + this.user)
						.then((response) => {
							this.nilaiList = response.data;
							this.TotalNilai();
						});
				} catch (error) {
					console.error("Error fetching nilai data:", error);
				}
			},
			submitForm() {
				for (const item of this.nilaiList.filter(
					(item) => item.formattedTimestamp === this.timestamp
				)) {
					const nilai = parseFloat(item.nilai || 0); // Mengganti koma dengan titik dan mengonversi ke float
					const maxValue = parseFloat(item.maxValue);

					// console.log("Question:", item.questionText);
					// console.log("Nilai:", nilai);
					// console.log("Max Value:", maxValue);

					if (isNaN(nilai) || nilai < 0 || nilai > maxValue) {
						alert(
							"Nilai tidak valid untuk pertanyaan nomor: " +
								item.questionId +
								"\n\nPastikan Nilai Berupa Angka dan tidak melebihi nilai maksimal"
						);
						return;
					}
				}
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

			TotalNilai() {
				const filteredNilaiList = this.nilaiList.filter(
					(item) => item.formattedTimestamp === this.timestamp
				);
				// Cek apakah semua nilai adalah angka
				const allAreNumbers = filteredNilaiList.every(
					(item) => !isNaN(parseFloat(item.nilai))
				);

				if (!allAreNumbers) {
					// Jika ada nilai yang bukan angka, tampilkan pesan error
					this.totalNilai = "Error";
				} else {
					// Jika semua nilai adalah angka, hitung totalnya
					const total = filteredNilaiList.reduce(
						(acc, item) => acc + parseFloat(item.nilai || 0),
						0
					);

					this.totalNilai = total.toFixed(2);
				}
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
			this.TotalNilai();
		},

		mounted() {
			this.getNilaiByUser();
		},
	};
</script>

<style>
	/* CSS styles go here */
</style>
