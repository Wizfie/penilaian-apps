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
								<a class="logo d-flex align-items-center w-auto">
									<span class="d-lg-block">LOGISTIC - MS</span>
								</a>
							</div>
							<!-- <img src="../../assets/img/gt-logo.png" alt="" /> -->
							<!-- End Logo -->

							<div class="card mb-3">
								<div class="card-body">
									<div class="pt-4 pb-2">
										<h5 class="card-title text-center pb-0 fs-4">
											Login to Your Account
										</h5>
										<p class="text-center small">
											Enter your NIP & password to login
										</p>
									</div>

									<form
										@submit.prevent="LoginRequest"
										class="row g-3 needs-validation"
										needs-validation
									>
										<div class="col-12">
											<label for="yourUsername" class="form-label">NIP</label>
											<div class="input-group has-validation">
												<input
													type="text"
													name="username"
													class="form-control"
													id="yourUsername"
													v-model="LoginData.nip"
													required
												/>
												<div class="invalid-feedback">
													Please enter your username.
												</div>
											</div>
										</div>

										<div class="col-12">
											<label for="yourPassword" class="form-label"
												>Password</label
											>
											<input
												v-model="LoginData.password"
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
												Login
											</button>
										</div>
										<div class="col-12">
											<p class="small mb-0">
												Don't have account?
												<router-link
													class="text-lg text-black fw-bold text-decoration-none"
													to="/register"
													><a href="">Create an account</a>
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
		name: "Login-View",
		data() {
			return {
				LoginData: {
					nip: null,
					password: null,
				},
			};
		},
		methods: {
			LoginRequest() {
				this.$axios
					.post("/auth/login", this.LoginData)
					.then((response) => {
						console.log(response.data);
						const userData = {
							nip: response.data.data.nip,
							username: response.data.data.username,
							role: response.data.data.role,
						};
						localStorage.setItem("userData", JSON.stringify(userData));
						alert("Login Berhasil");
						setTimeout(() => {
							router.push("/penilaian-yelyel");
						}, 1000);
					})
					.catch((error) => {
						console.log(error);
						if (error.response.status === 401) {
							alert("Username atau password salah");
						}
					});
			},
		},
	};
</script>
<style scoped>
	.login {
		display: flex;
		justify-content: center;
		background-color: rgb(5, 5, 119);
		align-items: center;
		height: 100vh;
		margin: 0;
	}
	.login-container {
		/* text-align: center; */
		width: 400px;
		height: 450px;
		border-color: black;
		background-color: cornflowerblue;
		border-radius: 20px;
		box-shadow: 5px 5px 10px #888888;
	}
</style>
