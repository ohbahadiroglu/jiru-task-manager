import Vue from "vue";
import { BACKEND_URL } from "./index";
import axios from "axios";
import VueAxios from "vue-axios";

Vue.use(VueAxios, axios);

axios.defaults.baseURL = BACKEND_URL;
