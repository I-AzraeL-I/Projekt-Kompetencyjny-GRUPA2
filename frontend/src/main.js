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

import DatePicker from 'vue2-datepicker';
import 'vue2-datepicker/index.css';
import 'materialize-css/dist/js/materialize.min.js'
Vue.use(DatePicker);
Vue.use(VueDynamicForms);
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyAyj0E3gw7lJaCETJE218cTEbpuX-nStn4',
    libraries: 'places',
  },
})

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
