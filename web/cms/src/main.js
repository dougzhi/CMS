import Vue from 'vue'
import Vuex from 'vuex'
import App from './App.vue'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueRouter from 'vue-router'
import routes from './base/router'
import store from './vuex/store'

// import { Message } from 'element-ui';
// import utilApi from '../src/common/utils';
// import Mock from './mock'
//  将vue-resource在vue中绑定，自动在vue对象实例上注入一个$http对象就可以使用ajax方法了
// import vueResource from 'vue-resource';
// let Base64 = require('js-base64').Base64;
// let openAuthenticate = sysConfig.openAuthenticate
// let openAuthorize = sysConfig.openAuthorize

// Mock.bootstrap();
Vue.use(Element)
Vue.config.productionTip = false

// 在Vue中全局使用mintui
Vue.use(Vuex)

Vue.use(VueRouter)

// Vue.use(vueResource);
/* eslint-disable no-new */
const router = new VueRouter({
  routes:routes
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
