<template lang="">
	<aside class="sidebar bg-dark" :class="{ hidden: isSidebarHidden }">
		<div class="bg-dark-subtle m-2 mb-5">
			<h1 class="text-center p-4 fw-bold bg- imb-5">My Apps</h1>
		</div>
		<ul class="menu-sidebar list-unstyled text-center position-relative">
			<li>
				<a><router-link to="">Dashboard</router-link></a>
			</li>
			<li>
				<a><router-link to="/penilaian">Penilaian</router-link></a>
			</li>
			<!-- <li>
					<a><router-link to="/profile">Profile</router-link></a>
				</li> -->
			<li class="sign-out">
				<a><router-link to="/">Sign Out</router-link> </a>
			</li>
		</ul>
	</aside>
</template>
<script>
	export default {
		name: "Sidebar-Component",
		data() {
			return {
				isSidebarHidden: false,
			};
		},
		props: {
			isSidebarHidden: Boolean, // Terima prop untuk mengatur kelas 'hidden'
		},
	};
</script>
<style>
	/* Sidebar */

	.sidebar {
		height: 100vh;
		min-width: 250px;
		max-width: 250px;
		position: fixed;
	}
	.hidden {
		display: none;
	}

	.sign-out {
		margin-top: 5rem;
	}

	.menu-sidebar li a {
		/* display: block;
		background-color: transparent;
		padding: 1px;
		font-weight: bolder;
		margin-bottom: 10px;
		margin-top: 10;
		justify-content: center; */
		display: flex;
		flex-direction: column;
		align-items: center;
		padding: 5px;
		margin: 5px;
	}

	li a {
		font-weight: bolder;
		justify-content: center;
		color: white;
		text-decoration: none;
		text-align: center;
	}

	.menu-sidebar li a:hover {
		background-color: grey;
		border-radius: 10px;
		padding-top: 3px;
		cursor: pointer;
	}
	/* Sidebar */
</style>
