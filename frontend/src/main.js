import Vue from 'vue'
import App from './App.vue'
import { Row, Column } from 'vue-grid-responsive';
Vue.component('row', Row);
Vue.component('column', Column);
import router from './router'
import VueGeolocation from "vue-browser-geolocation/src";
Vue.config.productionTip = false
Vue.use(VueGeolocation)
import * as VueGoogleMaps from 'vue2-google-maps'
import 'materialize-css/dist/css/materialize.min.css'
import 'material-design-icons/iconfont/material-icons.css'
import VueDynamicForms from '@asigloo/vue-dynamic-forms';
import VueMaterial from 'vue-material'
import axios from 'axios'
import VueAxios from 'vue-axios'
import server from './server.js'
import headers from './server'
Vue.use(server,headers)
Vue.use(VueAxios, axios)
Vue.use(VueMaterial)
Vue.use(VueDynamicForms);
// import Vuex from 'vuex';
// Vue.use(Vuex);
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyAyj0E3gw7lJaCETJE218cTEbpuX-nStn4',
    libraries: 'places',
  },
})
// const store = new Vuex.Store({
//   state : {
//     id: ''
//   },
//   mutations: {
//     getId (state) {
//       state.id=localStorage.id;
//     }
//   }
// })
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
