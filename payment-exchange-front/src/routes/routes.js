// GeneralViews
import NotFound from '../pages/NotFoundPage.vue'

// Admin pages
import UserProfile from '@/pages/User/UserProfile.vue'
import UserList from "@/pages/User/UserList";
import TableList from 'src/pages/TableList.vue'
import TestPage from  'src/pages/TestPage.vue'

import Login from "@/pages/LoginPage";
import ExchangeRatePage from "@/pages/Exchange/ExchangeRate";
import CashRegisterPage from "@/pages/CashRegister/CashRegisterPage";
import WorkPlacesPage from "@/pages/Dashboard/DashboardOld2";
import Dashboard from "@/pages/Dashboard/Dashboard";
import DashboardOLD from "@/pages/Dashboard/DashboardOLD";
import Layout from "@/components/Layout/Layout";
import Notifications from "@/pages/Notifications/Notifications";
import ExchangeBuy from "@/pages/Exchange/ExchangeBuy";
import ExchangeSell from "@/pages/Exchange/ExchangeSell";

const routes = [
  {
    path: '/',
    name: 'Layout',
    redirect: '/dashboard',
    component: Layout,
    /*meta: {
      requiresAuth: true
    },*/
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: Dashboard
      },
      {
        path: 'dashboardOld',
        name: 'DashboardOld',
        component: DashboardOLD
      },
      {
        path: 'userProfile',
        name: 'UserProfile',
        component: UserProfile
      },
      {
        path: 'userList',
        name: 'UserList',
        component: UserList
      },
      {
        path: 'exchangeBuy',
        name: 'ExchangeBuy',
        component: ExchangeBuy
      },
      {
        path: 'exchangeSell',
        name: 'ExchangeSell',
        component: ExchangeSell
      },
      {
        path: 'exchangeRate',
        name: 'Exchange Rate',
        component: ExchangeRatePage
      },
      {
        path: 'table-list',
        name: 'Table List',
        component: TableList
      },
      {
        path: 'cashRegister',
        name: 'Cash Register',
        component: CashRegisterPage
      },
      {
        path: 'workPlace',
        name: 'Work Place',
        component: WorkPlacesPage
      },
      {
        path: 'notifications',
        name: 'Notifications',
        component: Notifications
      },
      {
        path: 'test',
        name: 'TestPage',
        component: TestPage
      }
    ]
  },
  {
    path: "/auth/login",
    name: 'Login',
    component: Login,
  },
  {
    path: '*',
    name: 'NotFound',
    component: NotFound }
]

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes
