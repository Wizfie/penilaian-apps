import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "./style.css";

import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/api";
const app = createApp(App);

app.use(router); // Use the router

app.config.globalProperties.$axios = axios; // axios

app.mount("#app");
