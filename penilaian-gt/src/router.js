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
import adminPanel from "./views/Admin-Panel.vue";
import homeYelyel from "./views/Penilaian-Yelyel/Home-Yelyel.vue";
import adminYelyel from "./views/Penilaian-Yelyel/Admin-Yelyel.vue";
import dashboard from "./views/Dashboard.vue";

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
    path: "/presentasi",
    component: presentasi,
    beforeEnter: checkAuthorization,
  },
  {
    path: "/Penilaian-yelyel",
    component: homeYelyel,
    beforeEnter: checkAuthorization,
  },
  {
    path: "/Admin-point",
    component: adminYelyel,
    beforeEnter: checkAuthorization,
  },
  {
    path: "/detail/:teamName/:timestamp",
    component: detailNilai,
    beforeEnter: checkAuthorization,
  },
  {
    path: "/admin",
    component: adminPanel,
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
