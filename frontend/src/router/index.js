import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/Home.vue";
import HourlyWageView from "../views/HourlyWage.vue";


Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/site",
    name: "site",
    component: function () {
      return import(/* webpackChunkName: "about" */ "../views/Site.vue");
    },
  },
  {
    path: "/wage",
    name: "wage",
    component: HourlyWageView
  },
];

const router = new VueRouter({
  routes,
});

export default router;
