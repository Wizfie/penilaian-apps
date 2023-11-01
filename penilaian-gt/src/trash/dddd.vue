<template>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb m-3 fw-bolder fs-6 bg-body-secondary p-4 rounded-2">
			<li class="breadcrumb-item">
				<router-link class="text-dark" to="/plan">PLAN</router-link>
			</li>
			<li class="breadcrumb-item">
				<router-link class="text-dark" to="/do">DO</router-link>
			</li>
			<li class="breadcrumb-item">
				<router-link class="text-dark" to="">CHECK</router-link>
			</li>
			<li class="breadcrumb-item">
				<router-link class="text-dark" to="">ACTION</router-link>
			</li>
		</ol>
	</nav>
</template>
<script>
	export default {
		name: "Breadcrumb-Component",
	};
</script>
<style scoped>
	/* Breadcrumb */
	.breadcrumb li :hover {
		background-color: gray;
		padding: 5px;
		border-radius: 10px;
	}
	/* Breadcrumb */
</style>
