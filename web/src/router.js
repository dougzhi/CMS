import Vue from 'vue'
import Router from 'vue-router'
import Info from './components/info'
import Rating from "./components/rating";


Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'rating',
      meta: {
        title: '写点评',
      },
      component: Rating
    },
    {
      path: '/info',
      name: 'info',
      meta: {
        title: '商家详情',
      },
      component: Info
    },
  ]
})
