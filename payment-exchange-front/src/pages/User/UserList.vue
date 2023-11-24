<template>
  <v-container fluid>
    <v-overlay
      absolute
      v-model="overlay"
      contained
      class="align-center justify-center"
    >
      <v-row no-gutters height="90%">
        <v-col align="center" cols="12" sm="12" md="12" lg="12">
          <spacer/>
          <v-progress-circular
            :size="50"
            color="primary"
            indeterminate
          ></v-progress-circular>
          <spacer/>
        </v-col>
        <v-col align="center" cols="12" sm="12" md="12" lg="12">
          <spacer/>
          <h3 class="text-primary">
            Loading...
          </h3>
          <spacer/>
        </v-col>
      </v-row>
    </v-overlay>
    <v-row no-gutters class="d-flex justify-space-between mt-10 mb-6">
      <h1 class="page-title">User list</h1>
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-dialog
            v-model="dialog"
            max-width="1000px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                class="mx-2"
                dark
                rounded
                color="primary"
                v-bind="attrs"
                v-on="on"
                x-large
              >
                <v-icon dark>
                  mdi-plus
                </v-icon>
                New user
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5 center">{{ formTitle }}</span>
              </v-card-title>
              <v-divider/>
              <v-card-text class="pa-6 pt-5 mb-1">
                <v-form
                  ref="createUserForm"
                  v-model="validCreateUserForm"
                  lazy-validation>
                  <v-row>

                    <v-col
                      cols="3"
                      sm="2"
                      md="4"
                    >
                      <v-text-field
                        v-model="editedUser.userId"
                        label="User id"
                        outlined
                        disabled
                        class="zoom1"
                      ></v-text-field>
                    </v-col>
                    <v-col
                      cols="12"
                      sm="6"
                      md="4"
                    >
                      <v-text-field
                        v-model="editedUser.username"
                        label="Username"
                        :rules="[rules.required]"
                        prepend-inner-icon="mdi-account"
                        outlined
                        class="zoom1"
                      ></v-text-field>
                    </v-col>
                    <v-col
                      cols="12"
                      sm="6"
                      md="4"
                    >
                      <v-text-field
                        v-model="editedUser.password"
                        label="Password"
                        :rules="[rules.required]"
                        prepend-inner-icon="mdi-lock"
                        outlined
                        v-show="dialogNewUser"
                        class="zoom1"
                      ></v-text-field>
                    </v-col>
                    <v-col
                      cols="12"
                      sm="6"
                      md="4"
                    >
                      <v-select
                        v-model="editedUser.roleId"
                        label="RoleId"
                        :rules="[rules.required]"
                        :items="roles"
                        item-value="roleId"
                        :item-text="getSelectRoleText"
                        outlined
                        class="zoom1"
                      ></v-select>
                    </v-col>
                    <v-col
                      cols="12"
                      sm="6"
                      md="4"
                    >
                      <v-text-field
                        v-model="editedUser.firstName"
                        :rules="[rules.required]"
                        label="First Name"
                        outlined
                        class="zoom1"
                      ></v-text-field>
                    </v-col>
                    <v-col
                      cols="12"
                      sm="6"
                      md="4"
                    >
                      <v-text-field
                        v-model="editedUser.lastName"
                        :rules="[rules.required]"
                        label="Last Name"
                        outlined
                        class="zoom1"
                      ></v-text-field>
                    </v-col>
                    <v-col
                      cols="12"
                      sm="6"
                      md="4"
                    >
                      <v-menu
                        v-model="menu2"
                        :close-on-content-click="false"
                        :nudge-right="40"
                        transition="scale-transition"
                        offset-y
                        min-width="auto"
                        class="zoom1"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            v-model="editedUser.dateOfBirth"
                            label="Date of birth"
                            :rules="[rules.required]"
                            prepend-inner-icon="mdi-calendar"
                            readonly
                            outlined
                            class="zoom1"
                            v-bind="attrs"
                            v-on="on"
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="editedUser.dateOfBirth"
                          @input="menu2 = false"
                        ></v-date-picker>
                      </v-menu>
                    </v-col>
                    <v-col
                      cols="12"
                      sm="6"
                      md="4"
                    >
                      <v-text-field
                        v-model="editedUser.phoneNumber"
                        :rules="[rules.required]"
                        label="Phone number"
                        prepend-inner-icon="mdi-phone"
                        outlined
                        class="zoom1"
                      ></v-text-field>
                    </v-col>
                    <v-col
                      cols="12"
                      sm="6"
                      md="4"
                    >
                      <v-text-field
                        v-model="editedUser.address"
                        :rules="[rules.required]"
                        label="Address"
                        prepend-inner-icon="mdi-map-marker"
                        outlined
                        class="zoom1"
                      ></v-text-field>
                    </v-col>
                    <v-col
                      cols="12"
                      sm="6"
                      md="4"
                    >
                      <v-text-field
                        v-model="editedUser.city"
                        :rules="[rules.required]"
                        label="City"
                        prepend-inner-icon="mdi-city"
                        outlined
                        class="zoom1"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-form>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="primary"
                  x-large
                  @click="close"
                >
                  Cancel
                </v-btn>
                <v-btn
                  color="primary"
                  x-large
                  v-show="!dialogNewUser"
                  @click="save"
                >
                  Edit
                </v-btn>
                <v-btn
                  color="primary"
                  x-large
                  v-show="dialogNewUser"
                  @click="createNewUser"
                >
                  Create
                </v-btn>
<!--                <v-btn
                  color="primary"
                  @click="validForm"
                >
                  Validate
                </v-btn>-->
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card><v-card-title class="text-h5">
              <v-row justify="center" align="center">
                <h5 class="text-h5">Are you sure you want to delete this item?</h5>
              </v-row>
            </v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="closeDelete">Cancel</v-btn>
                <v-btn color="primary" @click="deleteItemConfirm">OK</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </template>
      </v-menu>
    </v-row>
    <v-card>
      <v-data-table
        :headers="headers"
        :items="users"
        sort-by="userId"
        :single-expand="singleExpand"
        :expanded.sync="expanded"
        item-key="userId"
        show-expand
      ><!-- Pravi problem item-key="roleId"-->

        <template v-slot:item.roleId="{item}">
          {{ getRoleTextTable(item.roleId) }}
        </template>

        <template v-slot:item.dateOfStart="{item}">
          {{ convertDate(item.dateOfStart) }}
        </template>
        <template v-slot:expanded-item="{ headers, item }">
          <td :colspan="headers.length">
            <v-row no-gutters>
              <v-col
                cols="12"
                sm="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >First name: <b>{{ item.firstName }}</b></v-card>
              </v-col>

              <v-col
                cols="12"
                sm="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >Last Name: <b>{{ item.lastName }}</b></v-card>
              </v-col>

              <v-col
                cols="12"
                sm="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >Date of birth: <b>{{ convertDate(item.dateOfBirth) }}</b></v-card>
              </v-col>

              <v-col
                cols="12"
                sm="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >Phone Number: <b>{{ item.phoneNumber }}</b></v-card>
              </v-col>

              <v-col
                cols="12"
                sm="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >Address: <b>{{ item.address }}</b></v-card>
              </v-col>

              <v-col
                cols="12"
                sm="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >City: <b>{{ item.city }}</b></v-card>
              </v-col>
            </v-row>
          </td>
        </template>
        <template v-slot:item.actions="{ item }">
          <v-icon
            large
            class="mr-2"
            @click="editItem(item)"
          >
            mdi-pencil
          </v-icon>
          <v-icon
            large
            @click="deleteItem(item)"
          >
            mdi-delete
          </v-icon>
        </template>
        <template v-slot:no-data>
          <v-btn
            color="primary"
            @click="initialize"
          >
            Reset
          </v-btn>
        </template>
      </v-data-table>
    </v-card>
  </v-container>
</template>
<script>

import UserService from "@/services/UserService";

export default {
  name: "UserList",
  data() {
    return {
      overlay: false,
      date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      sortBy: 'users.userId',
      sortDesc: false,
      headers: [
        {text: 'User Id', value: 'userId', class: "bold--text value"},
        {text: 'Username', value: 'username'},
        {text: 'Role', value: 'roleId'},
        {text: 'Start Date', value: 'dateOfStart'},
        {text: 'Creation timestamp', value: 'timestampOfCreation'},
        {text: 'Actions', value: 'actions', sortable: false},
        {text: '', value: 'data-table-expand'},
      ],
      users: [{
        userId: null,
        username: null,
        roleId: null,
        flActive: null,
        dateOfStart: null,
        dateOfEnd: null,
        timestampOfCreation: null,
      }],
      user: [],
      dialogCompose: false,
      valid: true,
      dialog: false,
      dialogDelete: false,
      dialogNewUser: false,
      editedIndex: -1,
      deleteIndex: -1,
      editedUser: {
        userId: null,
        username: null,
        password: null,
        roleId: null,
        dateOfStart: null,
        dateOfEnd: null,
        timestampOfCreation: null,
        firstName: null,
        lastName: null,
        dateOfBirth: null,
        phoneNumber: null,
        address: null,
        city: null
      },
      emptyUser: {
        userId: null,
        username: null,
        password: null,
        roleId: null,
        dateOfStart: null,
        dateOfEnd: null,
        timestampOfCreation: null,
        firstName: null,
        lastName: null,
        dateOfBirth: null,
        phoneNumber: null,
        address: null,
        city: null
      },
      errorResp:{
        errorCode:null,
        errorMessage:null,
      },
      roles: [{
        roleId: null,
        roleName: null,
      }],
      validCreateUserForm: true,
      expanded: [],
      singleExpand: true,
      rules: {
        required: value => !!value || 'Required field',
        counter: value => value.length <= 20 || 'Max 20 characters',
        email: value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Invalid e-mail.'
        }
      }
    }


  },

  computed: {
    formTitle() {
      this.editedIndex === -1 ? this.dialogNewUser = true : this.dialogNewUser = false
      return this.editedIndex === -1 ? 'New User' : 'Edit User'
    },
  },

  watch: {
    dialog(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },

  created() {
    this.initialize()
  },

  mounted() {
    this.onLoad()
  },
  methods: {
    async onLoad() {
      this.overlay = true;

      this.users = await fetch('/api/user/getAllUsersPersonalInfo', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());


      console.log(this.users);


      this.roles = await fetch('/api/user/getRoles', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());


      console.log(this.roles);

      setTimeout(() => {
        this.overlay = false;
      });
    },

    initialize() {
      this.onLoad();
      //UserService.function.getAllUsers().then(json => this.users = json);
    },
    convertDateTime(item){
      const date = new Date(item);
      return ("0" + (date.getDate())).slice(-2)   + "." + ("0" + (date.getMonth()+1)).slice(-2) + "." + date.getFullYear() + " " +
        ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2) + ":" + ("0" + date.getSeconds()).slice(-2);
    },
    convertDate(item){
      const date = new Date(item);
      return ("0" + (date.getDate())).slice(-2) + "." + ("0" + (date.getMonth()+1)).slice(-2) + "." + date.getFullYear();
    },
    setUser(item, index, button) {
      this.user = item;
      this.$root.$emit('bv::show::modal', 'info-modal', button)
    },
    resetInfoModal() {
    },
    compose() {
      this.dialogCompose = true
    },
    getSelectRoleText(item) {
      return `${item.roleId} - ${item.roleName}`;
    },
    getRoleTextTable(id) {
      //PRAVI PROBLEM
      const ro = this.roles.findIndex(d => d.roleId === id)
      return `${this.roles[ro].roleId} - ${this.roles[ro].roleName}`
    },
    editItem(item) {
      this.editedIndex = this.users.indexOf(item)
      this.editedUser = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem(item) {
      this.deleteIndex = this.users.indexOf(item)
      this.editedUser = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteItemConfirm() {

      console.log(this.users.at(this.deleteIndex).userId)

      await fetch('/api/user/deleteUser/' + this.users.at(this.deleteIndex).userId, {
        method: 'DELETE',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then(async (response) => {

        if (!response.ok) {
          this.errorResp = await response.json();
          this.pushErrorNotification(this.errorResp.errorMessage);
          throw new Error('Something went wrong');
        } else {
          this.pushSuccessNotification("The user was deleted");
          this.users.splice(this.deleteIndex, 1)
        }
      });
      this.closeDelete();

    },

    close() {
      this.dialog = false
      this.$refs.createUserForm.reset()
      this.$nextTick(() => {
        this.editedUser = Object.assign({}, this.emptyUser)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedUser = Object.assign({}, this.emptyUser)
        this.deleteIndex = -1
      })
    },

    save() {
      this.editUser();
      if (this.editedIndex > -1) {
        Object.assign(this.users[this.editedUser], this.editedUser)
      } else {
        this.users.push(this.editedUser)
      }
      this.close()
    },
    validForm() {
      return this.$refs.createUserForm.validate()
    },
    pushSuccessNotification(message) {
      this.$toast.success(message, {
        position: "top-right",
        timeout: 3000,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        draggablePercent: 0.6,
        showCloseButtonOnHover: true,
        hideProgressBar: false,
        closeButton: "button",
        icon: true
      });
    },
    pushErrorNotification(message) {
      this.$toast.error(message, {
        position: "top-right",
        timeout: 3000,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        draggablePercent: 0.6,
        showCloseButtonOnHover: true,
        hideProgressBar: false,
        closeButton: "button",
        icon: true
      });
    },
    createNewUser() {
      if (this.validForm()) {
        const payload = {
          userId: this.editedUser.userId,
          username: this.editedUser.username,
          password: this.editedUser.password,
          roleId: this.editedUser.roleId,
          flActive: true,
          dateOfStart: new Date().toISOString(),
          timestampOfCreation: new Date().toISOString(),
          firstName: this.editedUser.firstName,
          lastName: this.editedUser.lastName,
          dateOfBirth: this.editedUser.dateOfBirth,
          phoneNumber: this.editedUser.phoneNumber,
          address: this.editedUser.address,
          city: this.editedUser.city
        }

        console.log(payload);

        fetch("api/user/create", {
          method: "POST",
          headers: {
            "Authorization": sessionStorage.getItem('user-token'),
            "Content-Type": "application/json"
          },
          body: JSON.stringify(payload),

        }).then(async (response) => {
          if (!response.ok) {
            this.errorResp = await response.json();
            this.pushErrorNotification(this.errorResp.errorMessage);
            this.close();
            this.initialize();
            throw new Error('Something went wrong');
          } else {
            this.pushSuccessNotification("The user was created");
            this.close();
            this.initialize();
          }
        });
      }
    },
    editUser() {

      console.log(this.editedUser)
      //if (this.validForm()) {

      const payload = {
        userId: this.editedUser.userId,
        username: this.editedUser.username,
        password: null,
        roleId: this.editedUser.roleId,
        flActive: true,
        firstName: this.editedUser.firstName,
        lastName: this.editedUser.lastName,
        dateOfBirth: this.editedUser.dateOfBirth,
        phoneNumber: this.editedUser.phoneNumber,
        address: this.editedUser.address,
        city: this.editedUser.city
      }

      console.log(payload);

      fetch("api/user/edit", {
        method: "POST",
        headers: {
          "Authorization": sessionStorage.getItem('user-token'),
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload),
      }).then(async  (response) => {
        if (!response.ok) {
          this.errorResp = await response.json();
          this.pushErrorNotification(this.errorResp.errorMessage);
          this.close();
          this.initialize();
          throw new Error('Something went wrong');

        } else {
          this.pushSuccessNotification("The user was edited");
          this.close();
          this.initialize();
        }
      });
      //}
    },


  }
}

</script>

<style>

.zoom1.v-input input {
  font-size: 1.5em;
}

.zoom1.v-input label {
  font-size: 1.2em;
}

.v-data-table > .v-data-table__wrapper > table > tbody > tr > td,
.v-data-table > .v-data-table__wrapper > table > thead > tr > th,
.v-data-table > .v-data-table__wrapper > table > tfoot > tr > th {
  font-size: 1.3em !important;
}
</style>
