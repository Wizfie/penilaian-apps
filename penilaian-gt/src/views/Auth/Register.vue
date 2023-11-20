<template lang="">
	<main>
		<div class="container">
			<section
				class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4"
			>
				<div class="container">
					<div class="row justify-content-center">
						<div
							class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center"
						>
							<div class="d-flex justify-content-center py-4">
								<a
									href="index.html"
									class="logo d-flex align-items-center w-auto"
								>
									<!-- <img src="assets/img/logo.png" alt="" /> -->
									<span class="d-lg-block">LOGISTIC - MS</span>
								</a>
							</div>
							<!-- End Logo -->

							<div class="card mb-3">
								<div class="card-body">
									<div class="pt-4 pb-2">
										<h5 class="card-title text-center pb-0 fs-4">
											Create an Account
										</h5>
										<p class="text-center small">
											Enter your personal details to create account
										</p>
									</div>

									<form
										@submit.prevent="RegisterRequest"
										class="row g-3 needs-validation"
										novalidate
									>
										<div class="col-12">
											<label for="username" class="form-label">Username</label>
											<input
												v-model="RegisterData.username"
												type="text"
												name="name"
												class="form-control"
												id="username"
												required
											/>
											<div class="invalid-feedback">
												Please, enter your name!
											</div>
										</div>

										<div class="col-12">
											<label for="nip" class="form-label">Nip</label>
											<div class="input-group has-validation">
												<input
													v-model="RegisterData.nip"
													type="text"
													name="username"
													class="form-control"
													id="nip"
													required
												/>
												<div class="invalid-feedback">
													Please choose a username.
												</div>
											</div>
										</div>

										<div class="col-12">
											<label for="yourPassword" class="form-label"
												>Password</label
											>
											<input
												v-model="RegisterData.password"
												type="password"
												name="password"
												class="form-control"
												id="yourPassword"
												required
											/>
											<div class="invalid-feedback">
												Please enter your password!
											</div>
										</div>

										<div class="col-12">
											<button class="btn btn-primary w-100" type="submit">
												Create Account
											</button>
										</div>
										<div class="col-12">
											<p class="small mb-0">
												Already have an account?
												<router-link
													class="text-lg text-black fw-bold text-decoration-none"
													to="/"
													><a href="">Log in</a>
												</router-link>
											</p>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</main>
	<!-- End #main -->
</template>
<script>
	import router from "../../router";

	export default {
		name: "Register-View",
		data() {
			return {
				RegisterData: {
					username: null,
					nip: null,
					password: null,
					role: "user",
				},
			};
		},

		methods: {
			RegisterRequest() {
				try {
					this.$axios
						.post("/users", this.RegisterData)
						.then((response) => {
							console.log(response);
							if (response.status === 200) {
								alert("Register Success");
								router.push("/");
							}
						})
						.catch((error) => {
							console.error(error);
							if (error.response && error.response.status === 500) {
								alert("NIP Telah Digunakan");
							}
						});
				} catch (error) {
					console.error(error);
					alert("Terjadi kesalahan saat mendaftar.");
				}
			},
		},
	};
</script>
<style scoped>
	/* @import "../../assets/regis.css"; */
</style>
