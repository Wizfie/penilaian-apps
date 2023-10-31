<template lang="">
	<div class="login">
		<div class="login-container m-3">
			<h2 class="text-center p-3">Login</h2>
			<hr />
			<form @submit.prevent="LoginRequest">
				<div class="p-5">
					<div>
						<label for="nip" class="form-label">NIP</label>
						<input
							v-model="LoginData.nip"
							name="nip"
							type="text"
							class="form-control shadow"
							id="nip"
							placeholder="NIP"
							required
						/>
					</div>
					<div>
						<label for="password" class="form-label">Password</label>
						<input
							v-model="LoginData.password"
							name="password"
							type="password"
							class="form-control shadow"
							id="password"
							placeholder="password"
							required
						/>
					</div>
					<button class="btn btn-dark mt-2 w-100" type="submit">Login</button>
				</div>
				<div class="text-center pb-2">
					<a>Belum Punya Akun? </a>
					<span
						><router-link
							class="text-lg text-black fw-bold text-decoration-none"
							to="/register"
							>Register</router-link
						></span
					>
				</div>
			</form>
		</div>
	</div>
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
						};
						sessionStorage.setItem("userData", JSON.stringify(userData));
						alert("Login Berhasil");
						setTimeout(() => {
							router.push("/plan");
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
