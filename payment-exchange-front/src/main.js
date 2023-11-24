import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import store from './store/index'


//router setup
import routes from './routes/routes'

import './registerServiceWorker'
// plugin setup
Vue.use(VueRouter)


// LightBootstrap plugin
/*
import LightBootstrap from './light-bootstrap-main'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
Vue.use(LightBootstrap)

import { LayoutPlugin } from 'bootstrap-vue'
Vue.use(LayoutPlugin)
// This imports <b-modal> as well as the v-b-modal directive as a plugin:
import { ModalPlugin } from 'bootstrap-vue'
Vue.use(ModalPlugin)


// This imports directive v-b-scrollspy as a plugin:
import { VBScrollspyPlugin } from 'bootstrap-vue'
Vue.use(VBScrollspyPlugin)

// This imports the dropdown and table plugins
import { DropdownPlugin, TablePlugin } from 'bootstrap-vue'
Vue.use(DropdownPlugin)
Vue.use(TablePlugin)

//Custom input component
//import VmqInput from '@maqe-vue/text-input'

//Vue.component('vmq-input', VmqInput)
*/

/*** Toast Notification
 */

import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";

Vue.use(Toast);
////////////////////////////////////////////////////


import { VueMaskDirective } from 'v-mask'
Vue.directive('mask', VueMaskDirective);

// This imports <b-card> along with all the <b-card-*> sub-components as a plugin:
import { CardPlugin } from 'bootstrap-vue'
Vue.use(CardPlugin)

import vuetify from '@/plugins/vuetify' // path to vuetify export

import 'material-design-icons-iconfont/dist/material-design-icons.css'


import globalFilters from './filters/index'

Vue.mixin({
  filters: globalFilters
})

// configure router
const router = new VueRouter({
  routes, // short for routes: routes
  linkActiveClass: 'nav-item active',
  scrollBehavior: (to) => {
    if (to.hash) {
      return {selector: to.hash}
    } else {
      return { x: 0, y: 0 }
    }
  }
})

router.beforeEach((to, from, next) => {
  const token = sessionStorage.getItem('user-token');
  const tokenDuration = new Date(sessionStorage.getItem('token-duration'));
  const currentDatetime = new Date;
  // If logged in, or going to the Login page.
  if ((token && tokenDuration > currentDatetime) || to.name === 'Login') {
    // Continue to page.
    const currentDatetime = new Date();
    currentDatetime.setMinutes(currentDatetime.getMinutes() + 20);
    sessionStorage.setItem('token-duration', currentDatetime.toString())

    next()
  } else {
    // Not logged in, redirect to login.
    sessionStorage.clear();
    next({name: "Login"})
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  vuetify,
  render: h => h(App),
  store,
  router
})

