<template lang="">
	<div>
		<Header></Header>
		<div class="main" id="main">
			<div class="card">
				<div class="card-header">History Presentasi</div>
				<div class="card-body">
					<div class="table-responsive">
						<div class="table-borderless">
							<thead>
								<tr>
									<th>No</th>
									<th>Team</th>
									<th>Score</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>Team</td>
									<td>teamScore</td>
									<td><button>View</button></td>
								</tr>
							</tbody>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
	import Header from "../../components/Header.vue";
	export default {
		components: { Header },
	};
</script>
<style lang=""></style>
