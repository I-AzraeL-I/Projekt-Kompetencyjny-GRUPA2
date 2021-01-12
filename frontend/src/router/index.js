import Vue from 'vue';
import Router from 'vue-router';
import Login from "@/components/Login";
import Register from "@/components/Register";
import Help from "@/components/Help"
import Index from "@/components/Index";
import Lessons from "@/components/Lessons";
import Teacher from "@/components/Teacher";
import Profile from "@/components/ProfilePage/Profile";
import LessonHistory from "@/components/LessonHistoryPage/LessonHistory";
Vue.use(Router)

const router =  new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Index,
    },
    {
      path: '/logowanie',
      name: 'Logowanie',
      component: Login,
    },
    {
      path: '/rejestracja',
      name: 'Rejestracja',
      component: Register,
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
      path: '/nauczyciel',
      name: 'nauczyciel',
      component: Teacher
    },
    {
      path: '/profil',
      name: 'profil',
      component: Profile,
    },
    {
      path: '/historia-lekcji',
      name: 'lessonHistory',
      component: LessonHistory,
    },
  ]
})
router.beforeEach((to, from, next) => {
  if (to.name === 'profil' && !localStorage.id && (from.name !== 'Logowanie' && from.name !== 'Rejestracja'))
    next({ name: 'Logowanie' })
  else next()
})
export default router;