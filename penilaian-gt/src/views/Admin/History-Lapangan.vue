<template lang="">
	<div>
		<Header />

		<main id="main" class="main">
			<div class="pagetitle">
				<h1 class="my-4 fs-1">Penilaian Lapangan</h1>
			</div>
			<!-- End Page Title -->

			<section class="section">
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title text-center fw-bolder fs-3 mb-3 w-100">
									History
								</h5>

								<div class="mb-3 col-md-6 col-lg-3">
									<label for="usernameSelect" class="form-label fw-bold"
										>Select User:</label
									>
									<!-- style="width: 15%" -->
									<select
										v-model="selectedUsername"
										@change="updateTable"
										class="form-select p-2 text-center fw-bold"
										id="usernameSelect"
									>
										<option
											v-for="username in userlist"
											:key="username"
											:value="username"
										>
											{{ username.toUpperCase() }}
										</option>
									</select>
								</div>
								<div class="mb-3 col-md-6 col-lg-3">
									<label for="timestampInput" class="form-label fw-bold"
										>Select Date:</label
									>
									<input
										type="date"
										v-model="selectedTimestamp"
										@change="updateTable"
										class="form-control text-center fw-bold"
										id="timestampInput"
									/>
								</div>
							</div>
						</div>
						<!-- Tambahkan tombol atau elemen lainnya -->
						<div class="my-3 align-content-end">
							<button class="btn btn-success" @click="exportToExcel">
								Export to Excel
							</button>
						</div>
						<div class="card">
							<div class="card-body">
								<div
									class="table-responsive"
									v-if="selectedUsername || selectedTimestamp"
								>
									<table class="table-bordered">
										<thead>
											<tr class="p-2">
												<th class="text-center" scope="col">#</th>
												<th class="text-center p-3" scope="col">Question</th>
												<th
													class="p-3 text-center"
													v-for="(team, index) in uniqueTeams"
													:key="index"
													scope="col"
												>
													{{ team.toUpperCase() }}
												</th>
											</tr>
										</thead>
										<tbody>
											<tr
												v-for="(question, qIndex) in uniqueQuestions"
												:key="qIndex"
											>
												<td class="p-1 text-center">{{ qIndex + 1 }}</td>
												<td class="text-wrap p-3">{{ question }}</td>
												<td
													class="text-center fs-5"
													v-for="(team, index) in uniqueTeams"
													:key="index"
												>
													{{
														getCellValue(
															selectedUsername,
															selectedTimestamp,
															team,
															question
														)
													}}
												</td>
											</tr>
											<tr>
												<td class="p-1 text-center" colspan="2">Total</td>
												<td
													class="text-center fw-bold fs-5"
													v-for="(team, index) in uniqueTeams"
													:key="index"
												>
													{{ TotalNilai(team) }}
												</td>
											</tr>
										</tbody>
									</table>
								</div>

								<center v-else class="fs-5 fw-bold p-3">
									Please select a username and Date to view data.
								</center>
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
	import ExcelJS from "exceljs";
	export default {
		components: {
			Header,
		},

		data() {
			return {
				userlist: [],
				nilaiList: [],
				timestampList: [],
				uniqueQuestions: [],
				uniqueTeams: [],
				selectedUsername: null,
				selectedTeam: null,
			};
		},

		methods: {
			async exportToExcel() {
				const workbook = new ExcelJS.Workbook();
				const worksheet = workbook.addWorksheet("Penilaian-Lapangan");

				// Add information about selected username and date
				worksheet.addRow(["Nama Juri:", this.selectedUsername || "None"]);
				worksheet.addRow(["Date:", this.selectedTimestamp || "None"]);
				worksheet.addRow([]);

				// Add headers to the Excel sheet
				const headers = [
					"No",
					"Question",
					...this.uniqueTeams.map((team) => team.toUpperCase()),
				];
				worksheet.addRow(headers);

				// Add data rows to the Excel sheet
				this.uniqueQuestions.forEach((question, qIndex) => {
					const rowData = [
						qIndex + 1,
						question,
						...this.uniqueTeams.map((team) =>
							this.getCellValue(
								this.selectedUsername,
								this.selectedTimestamp,
								team,
								question
							)
						),
					];
					worksheet.addRow(rowData);
				});

				// Add a row for the grand total
				const grandTotalRow = [
					"",
					"Grand Total",
					...this.uniqueTeams.map((team) => this.TotalNilai(team)),
				];
				worksheet.addRow(grandTotalRow);

				// Create a blob from the Excel workbook
				const blob = await workbook.xlsx.writeBuffer();

				// Create a download link and trigger a click event to download the Excel file
				const url = window.URL.createObjectURL(new Blob([blob]));
				const a = document.createElement("a");
				const fileName = `penilaian_Lapangan_${this.selectedUsername}_${
					this.selectedTimestamp || "all"
				}.xlsx`;
				a.href = url;
				a.download = fileName;
				document.body.appendChild(a);
				a.click();
				document.body.removeChild(a);
				window.URL.revokeObjectURL(url);
			},
			TotalNilai(team) {
				let total = 0;
				let nilaiDash = true;

				// Iterasi ke atas pertanyaan unik dan jumlahkan nilai untuk tim
				this.uniqueQuestions.forEach((pertanyaan) => {
					const nilaiSel = this.getCellValue(
						this.selectedUsername,
						this.selectedTimestamp,
						team,
						pertanyaan
					);

					// Periksa apakah nilai saat ini bukan "-"
					if (nilaiSel !== "-") {
						nilaiDash = false;
					}

					// Konversi nilaiSel menjadi angka dan tambahkan ke total
					total += isNaN(nilaiSel) ? 0 : Number(nilaiSel);
				});

				// Periksa apakah semua nilai di tim adalah "-"
				if (nilaiDash) {
					return "-";
				}

				// Periksa apakah total adalah angka; sebaliknya, kembalikan 0
				return isNaN(total) ? 0 : total.toFixed(2);
			},
			updateTable() {
				this.uniqueTeams = this.getTeamsForTimestamp(this.selectedTimestamp);

				if (this.selectedTimestamp && this.selectedUsername) {
					this.selectedTeam = this.nilaiList.find(
						(team) =>
							team.users &&
							team.users[this.selectedUsername] &&
							team.timestamp === this.selectedTimestamp
					);
				} else {
					this.selectedTeam = null;
				}
			},

			getTeamsForTimestamp() {
				return [...new Set(this.nilaiList.map((team) => team.teamName))];
			},

			getCellValue(username, timestamp, team, question) {
				const teamData = this.nilaiList.find(
					(t) => t.teamName === team && t.timestamp === timestamp
				);
				if (teamData && teamData.users && teamData.users[username]) {
					const user = teamData.users[username];
					const userQuestion = user.questions.find(
						(q) => q.questionText === question
					);
					return userQuestion ? userQuestion.nilai : "-";
				}
				return "-";
			},

			getJuriList() {
				this.userlist = [
					...new Set(
						this.nilaiList.flatMap((team) => {
							return team && team.users ? Object.keys(team.users) : [];
						})
					),
				];
			},

			getAllNilai() {
				try {
					this.$axios.get("/nilai-getAll").then((response) => {
						const teamMap = new Map();

						response.data.forEach((nilai) => {
							const key = `${nilai.teamName}-${nilai.timestamp}`;
							let existingTeam = teamMap.get(key);

							if (!existingTeam) {
								existingTeam = {
									teamName: nilai.teamName,
									timestamp: nilai.timestamp,
									users: {},
								};
								teamMap.set(key, existingTeam);
							}

							let user = existingTeam.users[nilai.username];
							if (!user) {
								user = {
									username: nilai.username,
									questions: [],
								};
								existingTeam.users[nilai.username] = user;
							}

							user.questions.push({
								questionText: nilai.questionText,
								nilai: nilai.nilai,
							});

							if (!this.uniqueQuestions.includes(nilai.questionText)) {
								this.uniqueQuestions.push(nilai.questionText);
							}
						});

						this.nilaiList = Array.from(teamMap.values());
						this.timestampList = [
							...new Set(response.data.map((nilai) => nilai.timestamp)),
						];
						this.getJuriList();
					});
				} catch (error) {
					console.error("Error fetching nilai data:", error);
				}
			},
		},

		created() {
			this.getAllNilai();
			this.getJuriList();
		},
	};
</script>

<style lang=""></style>
