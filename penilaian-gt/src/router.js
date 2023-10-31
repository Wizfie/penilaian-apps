import { createRouter, createWebHistory } from "vue-router";
import Login from "./views/Auth/Login.vue";
import Register from "./views/Auth/Register.vue";
import plan from "./views/Penilaian-Lapangan/Plan-Page.vue";
import dashboard from "./views/Penilaian-Lapangan/Dashboard.vue";
import doPage from "./views/Penilaian-Lapangan/Do-Page.vue";
const routes = [
	{
		path: "/",
		name: "Login",
		component: Login,
	},
	{
		path: "/register",
		name: "Register",
		component: Register,
	},
	{
		path: "/dashboard",
		name: "dashboard",
		component: dashboard,
	},
	{
		path: "/plan",
		name: "plan",
		component: plan,
	},
	{
		path: "/do",
		name: "do",
		component: doPage,
	},
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;
