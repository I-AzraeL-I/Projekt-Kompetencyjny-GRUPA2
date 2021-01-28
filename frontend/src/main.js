import Vue from 'vue'
import App from './App.vue'
import { Row, Column } from 'vue-grid-responsive';
Vue.component('row', Row);
Vue.component('column', Column);
import router from './router'
import VueGeolocation from "vue-browser-geolocation/src";
Vue.config.productionTip = false
import Geocoder from "@pderas/vue2-geocoder";
Vue.use(VueGeolocation)
import * as VueGoogleMaps from 'vue2-google-maps'
import 'materialize-css/dist/css/materialize.min.css'
import 'materialize-css/dist/js/materialize.min.js'
// import 'materialize-css/js/collapsible'
import 'material-design-icons/iconfont/material-icons.css'
import VueDynamicForms from '@asigloo/vue-dynamic-forms';
import VueMaterial from 'vue-material'
import axios from 'axios'
import VueAxios from 'vue-axios'
import server from './server.js'
import headers from './server'
import VueToast from 'vue-toast-notification';
import 'vue-toast-notification/dist/theme-sugar.css';
import 'vue-material-slider/dist/vue-material-slider.css';
import VueMaterialSlider from 'vue-material-slider';
Vue.use(VueMaterialSlider);
Vue.use(require('vue-moment'));
Vue.use(VueToast);
Vue.use(server,headers)
Vue.use(VueAxios, axios)
Vue.use(VueMaterial)
Vue.use(VueDynamicForms);
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyAyj0E3gw7lJaCETJE218cTEbpuX-nStn4',
    libraries: 'places',
  },
})
Vue.use(Geocoder, {
  defaultCountryCode: null, // e.g. 'CA'
  defaultLanguage:    null, // e.g. 'en'
  defaultMode:        'address', // or 'lat-lng'
  googleMapsApiKey:   'AIzaSyAyj0E3gw7lJaCETJE218cTEbpuX-nStn4'
});
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
