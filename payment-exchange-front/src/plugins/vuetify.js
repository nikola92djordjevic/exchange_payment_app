// src/plugins/vuetify.js

import Vue from 'vue'
import Vuetify from 'vuetify'
import config from '@/config'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import '@mdi/font/css/materialdesignicons.css'

Vue.use(Vuetify)

const opts = {}

export default new Vuetify({
  icons: {
    iconfont: 'md'|| 'mdi',
  },
  theme: {
    themes: {
      light: config.light
    }
  }
})
