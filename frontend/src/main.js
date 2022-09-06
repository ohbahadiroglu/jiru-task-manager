import '@/plugins/vue-composition-api'
import '@/styles/styles.scss'
import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import store from './store'
import "./config/axios";
import excel from 'vue-excel-export'

Vue.config.productionTip = false
Vue.use(excel);

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
Vue.use(BootstrapVue);
new Vue({
  router,
  store,
  vuetify,
  render: h => h(App),
}).$mount('#app')
