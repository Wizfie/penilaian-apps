import { createRouter, createWebHistory } from "vue-router";
import Login from "./views/Login.vue";
import Register from "./views/Register.vue";
import Penilaian from "./views/Penilaian.vue";
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
		path: "/penilaian",
		name: "Penilaian",
		component: Penilaian,
	},
	// Define other routes here
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;
