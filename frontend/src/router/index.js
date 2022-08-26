import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/Home.vue";
import HourlyWageView from "../views/HourlyWage.vue";
import AdditionalAmount from "../views/AddtionalAmount.vue";
import Period from "../views/Period.vue";
import ProjectView from "../views/Project.vue"


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
    component: HourlyWageView,
  },
  {
    path: "/additional",
    name: "additional",
    component: AdditionalAmount,
  },
  {
    path: "/period",
    name: "period",
    component: Period,
  },
  {
    path: "/project",
    name: "project",
    component: ProjectView,
  },
];

const router = new VueRouter({
  routes,
});

export default router;
