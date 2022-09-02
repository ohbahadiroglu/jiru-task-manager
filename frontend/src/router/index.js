import Vue from 'vue';
import VueRouter from 'vue-router';
import HomeView from "../views/Home.vue";
import HourlyWageView from "../views/HourlyWage.vue";
import AdditionalAmount from "../views/AddtionalAmount.vue";
import Period from "../views/Period.vue";
import ProjectView from "../views/Project.vue";
import PeriodTasksView from "../views/PeriodTasks.vue";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: 'dashboard',
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: () => import('@/views/dashboard/Dashboard.vue'),
  },
  {
    path: '/typography',
    name: 'typography',
    component: () => import('@/views/typography/Typography.vue'),
  },
  {
    path: '/icons',
    name: 'icons',
    component: () => import('@/views/icons/Icons.vue'),
  },
  {
    path: '/cards',
    name: 'cards',
    component: () => import('@/views/cards/Card.vue'),
  },
  {
    path: '/simple-table',
    name: 'simple-table',
    component: () => import('@/views/simple-table/SimpleTable.vue'),
  },
  
  
  {
    path: '/error-404',
    name: 'error-404',
    component: () => import('@/views/Error.vue'),
    meta: {
      layout: 'blank',
    },
  },
  {
    path: '*',
    redirect: 'error-404',
  },
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
  {
    path: "/periodTasks",
    name: "periodTasks",
    component: PeriodTasksView,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

export default router
