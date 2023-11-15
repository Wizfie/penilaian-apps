import { createRouter, createWebHistory } from "vue-router";
import Login from "./views/Auth/Login.vue";
import Register from "./views/Auth/Register.vue";
import plan from "./views/Penilaian-Lapangan/Plan-Page.vue";
import lapangan from "./views/Penilaian-Lapangan/Home-Lapangan.vue";
import doPage from "./views/Penilaian-Lapangan/Do-Page.vue";
import check from "./views/Penilaian-Lapangan/Check-Page.vue";
import action from "./views/Penilaian-Lapangan/Action-Page.vue";
import detailNilai from "./views/Penilaian-Lapangan/Edit-Nilai.vue";
import presentasi from "./views/Penilaian-Lapangan/Presentasi.vue";
import adminPanel from "./views/Penilaian-Lapangan/Admin-Panel.vue";
import HomeYelyel from "./views/Penilaian-Yelyel/Home-Yelyel.vue";

const routes = [
	{
		path: "/",
		component: Login,
	},
	{
		path: "/register",
		component: Register,
	},
	// {
	// 	path: "/dashboard",
	// 	component: dashboard,
	// },
	{
		path: "/lapangan",
		component: lapangan,
	},
	{
		path: "/plan",
		component: plan,
	},
	{
		path: "/do",
		component: doPage,
	},
	{
		path: "/check",
		component: check,
	},
	{
		path: "/action",
		component: action,
	},
	{
		path: "/presentasi",
		component: presentasi,
	},
	{
		path: "/Penilaian-yelyel",
		component: HomeYelyel,
	},
	{
		path: "/detail/:teamName/:timestamp",
		component: detailNilai,
	},
	{
		path: "/admin",
		component: adminPanel,
	},
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;
