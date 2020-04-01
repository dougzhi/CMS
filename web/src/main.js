import Vue from 'vue'
import App from './App.vue'
import axios from 'axios';
import router from './router'
import config from "./config/index";
import store from "./store";

const BaseURL = config.baseUrl

const timeout = 90 * 1000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
axios.defaults.baseURL = BaseURL
axios.defaults.timeout = timeout

Vue.prototype.$axios = axios;

import {Swipe, SwipeItem, Icon, Rate, Image, Tag, Grid, GridItem, Field, Uploader, Checkbox, Button, Dialog, List, Overlay, ImagePreview} from 'vant';

Vue.use(Swipe);
Vue.use(SwipeItem);
Vue.use(Icon);
Vue.use(Rate);
Vue.use(Image);
Vue.use(Tag);
Vue.use(Grid);
Vue.use(GridItem);
Vue.use(Field);
Vue.use(Uploader);
Vue.use(Checkbox);
Vue.use(Button);
Vue.use(Dialog);
Vue.use(List);
Vue.use(Overlay);
Vue.use(ImagePreview);

Vue.config.productionTip = false

// 全局注册应用配置
Vue.prototype.$config = config

// 根据路由设置标题
router.beforeEach((to, from, next) => {
  /*路由发生改变修改页面的title */
  if(to.meta.title) {
    document.title = to.meta.title
  }
  next();
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
