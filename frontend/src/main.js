import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./config/axios";
Vue.config.productionTip = false;

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

library.add(fas, far, fab);
Vue.component("fa", FontAwesomeIcon);

new Vue({
  router,
  store,
  render: function (h) {
    return h(App);
  },
}).$mount("#app");
