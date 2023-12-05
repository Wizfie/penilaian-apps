import { createRouter, createWebHistory } from "vue-router";
import login from "./views/Auth/login.vue";
import register from "./views/Auth/register.vue";
import dashboard from "./views/Dashboard.vue";
import plan from "./views/Penilaian-Lapangan/Plan-Page.vue";
import lapangan from "./views/Penilaian-Lapangan/Home-Lapangan.vue";
import doPage from "./views/Penilaian-Lapangan/Do-Page.vue";
import check from "./views/Penilaian-Lapangan/Check-Page.vue";
import action from "./views/Penilaian-Lapangan/Action-Page.vue";
import detailNilai from "./views/Penilaian-Lapangan/Edit-Nilai.vue";
import historyLapangan from "./views/Admin/History-Lapangan.vue";
import historyYelyel from "./views/Admin/History-Yelyel.vue";
import penilaianYelyel from "./views/Penilaian-Yelyel/Home-Yelyel.vue";
import homePresentasi from "./views/Presentasi/Home-Presentasi.vue";
import detailPresentasi from "./views/Presentasi/Details-Presentasi.vue";

const routes = [
	{
		path: "/",
		component: login,
	},
	{
		path: "/register",
		component: register,
	},
	{
		path: "/dashboard",
		component: dashboard,
		beforeEnter: checkAuthorization,
	},
	{
		path: "/lapangan",
		component: lapangan,
		beforeEnter: checkAuthorization,
	},
	{
		path: "/plan",
		component: plan,
		beforeEnter: checkAuthorization,
	},
	{
		path: "/do",
		component: doPage,
		beforeEnter: checkAuthorization,
	},
	{
		path: "/check",
		component: check,
		beforeEnter: checkAuthorization,
	},
	{
		path: "/action",
		component: action,
		beforeEnter: checkAuthorization,
	},
	{
		path: "/penilaian-yelyel",
		component: penilaianYelyel,
		beforeEnter: checkAuthorization,
	},
	{
		path: "/detail/:teamName/:timestamp",
		component: detailNilai,
		beforeEnter: checkAuthorization,
	},
	{
		path: "/history-lapangan",
		component: historyLapangan,
		beforeEnter: checkAuthorization,
	},
	{
		path: "/history-yelyel",
		component: historyYelyel,
		beforeEnter: checkAuthorization,
	},

	{
		path: "/presentasi",
		component: homePresentasi,
		beforeEnter: checkAuthorization,
	},
	{
		path: "/presentasi/detail-presentasi",
		component: detailPresentasi,
		beforeEnter: checkAuthorization,
	},
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

function checkAuthorization(to, from, next) {
	if (localStorage.getItem("userData")) {
		next();
	} else {
		next("/");
	}
}

export default router;
