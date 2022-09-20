import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
//import sessionMain from '../views/session/SessionMain.vue'
import sessionMain1 from '../components/session/sessionmain1/SessionMain1.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import( '../views/AboutView.vue')
  },
  {
    path: '/session',
    name: 'session',
    component:() => import( '../views/session/SessionMain.vue'),
    redirect: to => {
      return { path: '/session/sessionLobby' }
    },
    children: [
      {
        path: 'sessionLobby',
        name: 'sessionLobby',
        component: () => import( '../components/session/sessionlobby/SessionLobby.vue'),
      },
      {
        path: 'sessionMain1',
        name: 'sessionMain1',
        component: () => import( '../components/session/sessionmain1/SessionMain1.vue'),
      },
     
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
