<template>
  <v-navigation-drawer
    app
    clipped
    v-model="DRAWER_STATE"
    :mini-variant="!DRAWER_STATE"
    :width="sidebarWidth"
    :permanent="$vuetify.breakpoint.mdAndUp"
    :temporary="$vuetify.breakpoint.smAndDown"
    :mini-variant-width="sidebarMinWidth"
    :class="{'drawer-mini': !DRAWER_STATE}">

    <v-list>
      <!-- filter by roleId-->
      <template v-for="(item, i) in items.filter(it => !(it.role < getRoleId))">
        <v-row
          v-if="item.heading"
          :key="item.heading"
          align="center">
          <v-col cols="6" class="py-5">
            <span
              style="padding-left: 32px"
              class="text-body-1 subheader"
              :class="(item.heading && DRAWER_STATE) ? 'show ' : 'hide'">
              {{ item.heading }}
              </span>
          </v-col>
          <v-col
            cols="6"
            class="text-center">
          </v-col>
        </v-row>
        <v-divider
          v-else-if="item.divider"
          :key="i"
          dark
          class="my-4"
        ></v-divider>
        <v-list-group
          color="primary"
          v-else-if="item.children && DRAWER_STATE "
          :key="item.title"
          v-model="item.model"
          append-icon="">
          <template v-slot:prependIcon>
            <v-icon size="28">mdi-image-filter-none</v-icon>
          </template>
          <template v-slot:activator>
            <v-list-item-content>
              <v-list-item-title
                class="grey--text">
                {{ item.title }}
              </v-list-item-title>
            </v-list-item-content>
          </template>
          <v-list-item
            v-for="(child, i) in item.children.filter(it => !(it.role < getRoleId))"
            :key="i"
            :to="child.link"
            link>
            <v-list-item-action v-if="child.icon">
              <v-icon size="">{{ child.icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title class="grey--text">
                {{ child.title }}
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-group>
        <v-list-item
          color="primary"
          v-else
          :key="item.text"
          :href="item.href ? item.href : null"
          :to="item.link === '#' ? null : item.link"
          link>
          <v-list-item-action>
            <v-icon
              size="28"
              :color="item.color ? item.color : ''"
            >{{ item.icon }}
            </v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title
              class="grey--text"
              link>
              {{ item.title }}
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
import {mapActions, mapState} from 'vuex'

export default {
  props: {
    source: String,
  },
  data() {
    return {
      items: [
        {title: 'Dashboard', icon: 'mdi-home', link: 'dashboard', role: 3},
        {title: 'Buy currency', icon: 'mdi-arrow-down-bold-outline', link: 'exchangeBuy', role: 3},
        {title: 'Sell currency', icon: 'mdi-arrow-up-bold-outline', link: 'exchangeSell', role: 3},
        {title: 'User Profile', icon: 'mdi-account', link: 'userProfile', role: 3},
        {divider: true},
        {title: 'Exchange rate', icon: 'mdi-list-box-outline', link: 'exchangeRate', role: 2},
        {title: 'User List', icon: 'mdi-account-group', link: 'userList', role: 1},
        {title: 'Cash Register', icon: 'mdi-cash-register', link: 'cashRegister', role: 2},
        /*{divider: true},
        {title: 'DashboardOld', icon: 'mdi-home', link: 'dashboardOld', role: 1},
        {title: 'Work Place', icon: 'mdi-desktop-tower-monitor', link: 'workPlace', role: 3},
        {title: 'TEST', icon: 'mdi-test-tube', link: 'test', role: 1},
        {divider: true},*/

      ],
      sidebarWidth: 280,
      sidebarMinWidth: 96,
      userRoleId: null,
    }
  },
  computed: {
    ...mapState(['drawer']),
    DRAWER_STATE: {
      get() {
        return this.drawer
      },
      set(newValue) {
        if (newValue === this.drawer) return;
        this.TOGGLE_DRAWER();
      }
    },
    getRoleId() {
      return sessionStorage.getItem('role-id')
    },
  },
  methods: {
    ...mapActions(['TOGGLE_DRAWER']),
  }
}
</script>

<style src="./Sidebar.scss" lang="scss"/>
