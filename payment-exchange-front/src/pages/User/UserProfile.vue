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
    <v-row no-gutters height="100%">
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">

          <v-dialog v-model="dialogConfirm" max-width="500px">
            <v-card>
              <v-card-title class="text-h5">Are you sure you want to update data?</v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="closeConfirm">Cancel</v-btn>
                <v-btn color="primary" @click="editUser">OK</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <v-dialog v-model="dialogUpdatePassword" max-width="500px">
            <v-card>
              <v-card-title class="text-h5">Update Password</v-card-title>
              <v-divider/>
              <v-card-text  class="pa-6 pt-5 mb-1">
                <v-form
                  ref="UpdatePasswordForm"
                  v-model="updatePasswordForm"
                  lazy-validation>
                <v-spacer/>
                <v-row no-gutters class="d-flex justify-center align-center">

                    <v-col
                      cols="12"
                      sm="8"
                      md="8"
                    >
                      <v-text-field
                        v-model="updatePasswordRequest.oldPassword"
                        label="Old Password"
                        :rules="[rules.required]"
                        outlined
                        class="zoom1"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                <v-row no-gutters class="d-flex justify-center align-center">
                    <v-col
                      cols="12"
                      sm="8"
                      md="8"
                    >
                      <v-text-field
                        v-model="updatePasswordRequest.newPassword"
                        label="New Password"
                        :rules="[rules.required, rules.different]"
                        outlined
                        class="zoom1"
                        :append-icon="value ? 'visibility' : 'visibility_off'"
                        @click:append="() => (value = !value)"
                        :type="value ? 'password' : 'text'"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" x-large @click="closeUpdatePasswordDialog">Cancel</v-btn>
                <v-btn color="primary" x-large @click="updatePassword">Update</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>

        </template>
      </v-menu>

    </v-row>
    <v-card
      class="mx-3 mb-1 rounded-lg"
      elevation="2"
      outlined
      height="100%">
      <v-card-title>
        <h1 class="page-title">User profile</h1>
      </v-card-title>
      <v-divider/>
      <v-card-text>
        <v-form
          ref="UserProfileForm"
          lazy-validation>
          <v-row>

            <v-col
              cols="3"
              sm="2"
              md="4"
            >
              <v-text-field
                v-model="userProfile.userId"
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
                v-model="userProfile.username"
                label="Username"
                :rules="[rules.required]"
                prepend-inner-icon="mdi-account"
                outlined
                class="zoom1"
                @change="makeChange = true"
              ></v-text-field>
            </v-col>
            <v-col
              cols="12"
              sm="6"
              md="4"
            >

              <template>

                <v-btn color="success" block x-large @click="showUpdatePasswordDialog">Update password</v-btn>
              </template>
              <!--
                            <v-text-field
                              v-model="userProfile.password"
                              label="Password"
                              :rules="[rules.required]"
                              prepend-inner-icon="mdi-lock"
                              outlined
                            ></v-text-field>-->
            </v-col>
            <v-col
              cols="12"
              sm="6"
              md="4"
            >
              <v-select
                v-model="userProfile.roleId"
                label="Role"
                :disabled="checkRole(1)"
                required
                :rules="[rules.required]"
                :items="roles"
                item-value="roleId"
                :item-text="getSelectRoleText"
                outlined
                class="zoom1"
                @change="test"
              ></v-select>
            </v-col>
            <v-col
              cols="12"
              sm="6"
              md="4"
            >
              <v-text-field
                v-model="userProfile.firstName"
                :rules="[rules.required]"
                label="First Name"
                outlined
                class="zoom1"
                @change="makeChange = true"
              ></v-text-field>
            </v-col>
            <v-col
              cols="12"
              sm="6"
              md="4"
            >
              <v-text-field
                v-model="userProfile.lastName"
                :rules="[rules.required]"
                label="Last Name"
                outlined
                class="zoom1"
                @change="makeChange = true"
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
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="userProfile.dateOfBirth"
                    label="Date of birth"
                    :rules="[rules.required]"
                    prepend-inner-icon="mdi-calendar"
                    readonly
                    outlined
                    v-bind="attrs"
                    v-on="on"
                    class="zoom1"
                    @change="makeChange = true"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="userProfile.dateOfBirth"
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
                v-model="userProfile.phoneNumber"
                :rules="[rules.required]"
                label="Phone number"
                prepend-inner-icon="mdi-phone"
                outlined
                class="zoom1"
                @change="makeChange = true"
              ></v-text-field>
            </v-col>
            <v-col
              cols="12"
              sm="6"
              md="4"
            >
              <v-text-field
                v-model="userProfile.address"
                :rules="[rules.required]"
                label="Address"
                prepend-inner-icon="mdi-map-marker"
                outlined
                class="zoom1"
                @change="makeChange = true"
              ></v-text-field>
            </v-col>
            <v-col
              cols="12"
              sm="6"
              md="4"
            >
              <v-text-field
                v-model="userProfile.city"
                :rules="[rules.required]"
                label="City"
                prepend-inner-icon="mdi-city"
                outlined
                class="zoom1"
                @change="makeChange = true"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          color="primary"
          v-show="this.makeChange"
          @click="showConfirm"
        >
          Update Data
        </v-btn>
<!--        <v-btn
          color="primary"
          @click="validForm"
        >
          Validate
        </v-btn>-->
      </v-card-actions>
    </v-card>

  </v-container>
</template>
<script>


export default {
  name: "UserProfile",
  data() {
    return {
      overlay: false,
      userProfile: {
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
      value: false,
      menu2: false,
      makeChange: false,
      dialogConfirm: false,
      dialogUpdatePassword: false,
      updatePasswordForm: true,
      updatePasswordRequest: {
        oldPassword: null,
        newPassword: null,
      },
      roles: [{
        roleId: null,
        roleName: null,
      }],
      rules: {
        required: value => !!value || 'Required field',
        different: value => (!!value && value !== this.updatePasswordRequest.oldPassword )|| 'New password has same as old',
        counter: value => value.length <= 20 || 'Max 20 characters',
        email: value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Invalid e-mail.'
        }
      }
    }
  },
  watch: {
    dialogUpdatePassword(val) {
      val || this.closeUpdatePasswordDialog()
    },
    dialogConfirm(val) {
      val || this.closeConfirm()
    },
  },
  created() {
    this.onLoad()
  },
  mounted() {
    this.onLoad()
  },
  methods: {
    async onLoad() {
      this.overlay = true;

      this.userProfile = await fetch('/api/user/getUserPersonalInfo/' + sessionStorage.getItem('user-id'), {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());

      this.roles = await fetch('/api/user/getRoles', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());

      setTimeout(() => {
        this.overlay = false;
      });
    },
    checkRole(item){
      const role = sessionStorage.getItem('role-id');
      return role > item;
    },
    getSelectRoleText(item) {
      return `${item.roleId} - ${item.roleName}`;
    },
    getRoleTextTable(id) {
      const ro = this.roles.find(d => d.roleId === id)
      return `${ro.roleId} - ${ro.roleName}`
    },
    validForm() {
      return this.$refs.UserProfileForm.validate()
    },
    validUpdatePasswordForm() {
      return this.$refs.UpdatePasswordForm.validate()
    },
    showConfirm() {
      this.dialogConfirm = true;
    },
    closeConfirm() {
      this.dialogConfirm = false;
    },
    showUpdatePasswordDialog() {
      this.dialogUpdatePassword = true;
    },
    closeUpdatePasswordDialog() {
      this.updatePasswordRequest.oldPassword = null;
      this.updatePasswordRequest.newPassword = null;
      this.$refs.UpdatePasswordForm.reset()
      this.value = false;
      this.dialogUpdatePassword = false;
    },
    test() {
      console.log(this.userProfile.roleId)
    },
    initialize() {
      this.onLoad()
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
    editUser() {

      console.log(this.editedUser)
      if (this.validForm()) {

        const payload = {
          userId: this.userProfile.userId,
          username: this.userProfile.username,
          password: null,
          roleId: this.userProfile.roleId,
          flActive: true,
          firstName: this.userProfile.firstName,
          lastName: this.userProfile.lastName,
          dateOfBirth: this.userProfile.dateOfBirth,
          phoneNumber: this.userProfile.phoneNumber,
          address: this.userProfile.address,
          city: this.userProfile.city
        }

        console.log(payload);

        const data = new FormData();
        //data.append( "json", JSON.stringify( payload ) );

        fetch("api/user/edit", {
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
            this.closeConfirm();
            this.initialize();
            throw new Error('Something went wrong');

          } else {
            this.pushSuccessNotification("The user was edited");

            this.makeChange = false;
            this.closeConfirm();
            this.initialize();
          }
        });
      }
    },
    updatePassword() {

      console.log(this.editedUser)
      if (this.validUpdatePasswordForm()) {

        const payload = {
          oldPassword: this.updatePasswordRequest.oldPassword,
          newPassword: this.updatePasswordRequest.newPassword,
        }

        console.log(payload);

        fetch("api/user/updatePassword", {
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
            this.closeUpdatePasswordDialog();
            this.initialize();
            throw new Error('Something went wrong');

          } else {
            this.pushSuccessNotification("The password was updated");
            this.closeUpdatePasswordDialog();
            this.initialize();
          }
        });
      }
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

.zoom1.v-select input {

  font-size: 1.3em !important;
}

v-list.v-text-field input {

  font-size: 1.3em !important;
}

</style>
