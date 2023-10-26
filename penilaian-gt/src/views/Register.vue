<template lang="">
	<div class="register">
		<div class="register-container m-2">
			<h2 class="text-center p-3 fw-bold">Register</h2>
			<hr style="" />
			<form @submit.prevent="RegisterRequest">
				<div class="p-5">
					<div>
						<label for="username" class="form-label">Username</label>
						<input
							v-model="RegisterData.username"
							name="username"
							type="text"
							class="form-control mb-1 shadow"
							id="username"
							placeholder="Username"
							required
						/>
					</div>
					<div>
						<label for="nip" class="form-label">NIP</label>
						<input
							v-model="RegisterData.nip"
							name="nip"
							type="text"
							class="form-control mb-1 shadow"
							id="nip"
							placeholder="1234"
							required
						/>
						<!-- <span v-if="serverError" class="alert alert-danger" role="alert">
							NIP telah digunakan
						</span> -->
					</div>
					<div>
						<label for="password" class="form-label">Password</label>
						<input
							v-model="RegisterData.password"
							name="password"
							type="password"
							class="form-control shadow"
							id="password"
							placeholder="Password"
							required
						/>
					</div>
					<button class="btn btn-dark mt-2 w-100" type="submit">
						Register
					</button>
				</div>
				<div class="text-center pb-2">
					<a>Sudah Punya Akun? </a>
					<span
						><router-link
							class="text-lg text-black fw-bold text-decoration-none"
							to="/"
							>Login</router-link
						></span
					>
				</div>
			</form>
		</div>
	</div>
</template>
<script>
	import router from "../router";

	export default {
		name: "Register-View",
		data() {
			return {
				RegisterData: {
					username: null,
					nip: null,
					password: null,
					role: "2",
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
	.register {
		display: flex;
		background-color: rgb(5, 5, 119);
		justify-content: center;
		align-items: center;
		height: 100vh;
		margin: 0;
	}
	.register-container {
		/* text-align: center; */
		width: 400px;
		height: 500px;
		border-color: black;
		background-color: cornflowerblue;
		border-radius: 20px;
		box-shadow: 5px 5px 10px #888888;
	}
</style>
