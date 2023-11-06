import { createRouter, createWebHistory } from "vue-router";
import Login from "./views/Auth/Login.vue";
import Register from "./views/Auth/Register.vue";
import plan from "./views/Penilaian-Lapangan/Plan-Page.vue";
import dashboard from "./views/Penilaian-Lapangan/Dashboard.vue";
import doPage from "./views/Penilaian-Lapangan/Do-Page.vue";
import check from "./views/Penilaian-Lapangan/Check-Page.vue";
import action from "./views/Penilaian-Lapangan/Action-Page.vue";
import detailNilai from "./views/Penilaian-Lapangan/Edit-Nilai.vue";
import presentasi from "./views/Penilaian-Lapangan/Presentasi.vue";
const routes = [
	{
		path: "/",
		component: Login,
	},
	{
		path: "/register",
		component: Register,
	},
	{
		path: "/dashboard",
		component: dashboard,
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
		path: "/detail/:timestamp",
		component: detailNilai,
	},
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;
