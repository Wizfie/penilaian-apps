<template lang="">
	<div>
		<Header />
		<main id="main" class="main">
			<section class="section">
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">Detail Nilai</h5>

								<!-- Table with stripped rows -->
								<table class="table datatable">
									<thead>
										<tr>
											<th scope="col">#</th>
											<th scope="col">Question</th>
											<th scope="col">Nilai</th>
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
											<td class="col-10">
												{{ item.questionText }}
											</td>
											<td class="col-2">
												<input
													min="0"
													step="0.01"
													type="number"
													class="form-control text-center w-50"
													v-model="item.nilai"
												/>
											</td>
										</tr>
									</tbody>
								</table>
								<!-- End Table with stripped rows -->
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
							console.log(response.data);
						});
				} catch (error) {
					console.error("Error fetching nilai data:", error);
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
<style lang=""></style>
