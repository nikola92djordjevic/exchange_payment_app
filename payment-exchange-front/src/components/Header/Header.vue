<template>
  <v-app-bar
    class="main-header"
    height="65"
    fixed
    app
    clipped-left
    color='primary'
    dark>
    <v-btn icon class="mx-1" @click.stop="TOGGLE_DRAWER">
      <template v-if="DRAWER_STATE">
        <v-icon style="font-size: 28px">mdi-menu</v-icon>
      </template>
      <template v-else>
        <v-icon style="font-size: 28px">mdi-menu</v-icon>
      </template>
    </v-btn>
    <v-toolbar-title>Payment Exchange APP</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-menu
      min-width="180"
      offset-y
      bottom
      left
      nudge-bottom="10">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="mr-0"
          icon
          v-bind="attrs"
          v-on="on">
          <v-icon
            style="font-size: 60px"
            :color="config.light.iconColor">mdi-account
          </v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item-group color="primary">
          <v-list-item disabled>
            <div class="text-h5 grey--text text--darken-3 px-4 pt-4">{{ this.userData.fullName }}</div>
          </v-list-item>
          <v-list-item disabled>
            <div class="text-h7 grey--text text--darken-3 px-4 pt-4">
              <B> Username: </B>{{ this.userData.username }}
            </div>
          </v-list-item>
          <v-list-item disabled>
            <div class="text-h7 grey--text text--darken-3 px-4 pt-4">
              <B>Role:</B> {{ getRoleName() }}
            </div>
          </v-list-item>
          <v-divider/>

          <v-list-item
            v-for="(item, i) in account"
            :to="item.link === '#' ? null : item.link"
            :key="i">
            <v-list-item-icon class="mr-4">
              <v-icon
                style="font-size: 40px"
                :color="item.color"
                v-text="item.icon">
              </v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title :color='config.light.textColor' v-text="item.text"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider/>
        </v-list-item-group>
        <div class="d-flex justify-center my-3">
          <v-btn
            width="80%"
            large

            color="secondary"
            @click="logOut">
            <v-icon>mdi-logout</v-icon>
            Sign Out
          </v-btn>
        </div>

      </v-list>

    </v-menu>

<!--    <v-btn icon @click.prevent="logOut">
      <v-icon>mdi-logout</v-icon>
    </v-btn>-->
  </v-app-bar>
</template>

<script>
import {mapActions, mapState} from 'vuex'
import config from '../../config';

export default {
  name: 'Header',
  data: () => ({
    config,
    searchCollapse: true,
    userData: {
      fullName: null,
      username: null,
      roleId: null,
    },
    notifications: [
      {text: 'Check out this awesome ticket', icon: 'mdi-tag', color: 'warning'},
      {text: 'What is the best way to get ...', icon: 'mdi-thumb-up', color: 'success'},
      {text: 'This is just a simple notification', icon: 'mdi-flag', color: 'error'},
      {text: '12 new orders has arrived today', icon: 'mdi-cart', color: 'primary'},
    ],
    messages: [
      {text: 'JH', name: 'Jane Hew', message: 'Hey! How is it going?', time: '09:32', color: 'warning'},
      {text: 'LB', name: 'Lloyd Brown', message: 'Check out my new Dashboard', time: '10:02', color: 'success'},
      {text: 'MW', name: 'Mark Winstein', message: 'I want rearrange the appointment', time: '12:16', color: 'error'},
      {text: 'LD', name: 'Liana Dutti', message: 'Good news from sale department', time: '14:56', color: 'primary'},
    ],
    account: [
      {text: 'View profile', icon: 'mdi-account', link: 'userProfile'},
      /* { text: 'Tasks', icon: 'mdi-thumb-up', color: 'textColor'  },
       { text: 'Messages', icon: 'mdi-flag', color: 'textColor'  }*/
    ],
    notificationsBadge: true,
    messageBadge: true,
    roles: [{
      roleId: null,
      roleName: null,
    }],
  }),
  computed: {
    ...mapState(['drawer']),
    DRAWER_STATE: {
      get() {
        return this.drawer
      },
    }
  },
  mounted() {
    this.onLoad()
  },
  methods: {
    ...mapActions(['TOGGLE_DRAWER']),
    logOut: function () {
      window.localStorage.setItem('authenticated', false);

      sessionStorage.clear();
      this.$router.push('/login');
    },
    async onLoad() {


      this.roles = await fetch('/api/user/getRoles', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());

      this.userData.fullName = sessionStorage.getItem('full-name');
      this.userData.username = sessionStorage.getItem('username');
      this.userData.roleId = sessionStorage.getItem('role-id');
    },
    getRoleName() {
      const ro = this.roles.findIndex(d => d.roleId == this.userData.roleId)
      return `${this.roles[ro].roleId} - ${this.roles[ro].roleName}`
    }
  }
};
</script>

<style src="./Header.scss" lang="scss"></style>
