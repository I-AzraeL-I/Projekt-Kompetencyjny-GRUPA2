import Vue from 'vue'
import Router from 'vue-router'
import Login from "@/components/Login";
import Register from "@/components/Register";
import Help from "@/components/Help"
import Index from "@/components/Index";
import Lessons from "@/components/Lessons";
import Teacher from "@/components/Teacher";
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/logowanie',
      name: 'Logowanie',
      component: Login
    },
    {
      path: '/rejestracja',
      name: 'Rejestracja',
      component: Register
    },
    {
      path: '/pomoc',
      name: 'Pomoc',
      component: Help
    },
    {
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/korepetycje',
      name: 'Korepetycje',
      component: Lessons
    },
    {
      path: '/profil',
      name: 'profil',
      component: Teacher
    }
  ]
})