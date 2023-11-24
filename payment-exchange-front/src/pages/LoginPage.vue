<template>
  <v-app id="inspire">
    <v-container fluid fill-height>
      <v-layout align-center justify-center>
        <v-flex xs12 sm8 md4>
          <v-card class="elevation-12">
            <v-toolbar dark color="primary">
              <v-toolbar-title>Login</v-toolbar-title>
            </v-toolbar>
            <v-card-text>
              <v-form
                ref="loginForm"
                v-model="loginForm"
                lazy-validation>
                <v-text-field
                  v-model="login.username"
                  :rules="[rules.required]"
                  outlined
                  prepend-inner-icon="person"
                  name="username"
                  label="Username"
                  type="text"
                  class="zoom1"
                ></v-text-field>
                <v-text-field
                  id="password"
                  v-model="login.password"
                  :rules="[rules.required]"
                  outlined
                  prepend-inner-icon="lock"
                  name="password"
                  label="Password"
                  type="password"
                  :append-icon="value ? 'visibility' : 'visibility_off'"
                  @click:append="() => (value = !value)"
                  :type="value ? 'password' : 'text'"
                  class="zoom1"
                ></v-text-field>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn x-large color="primary" to="/" @click.prevent="loginRequest">Login</v-btn>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>
</template>
<script>

export default {
  data() {
    return {
      loginResponse: {
        responseMessage: null,
        token: null,
        userId: null,
        username: null,
        fullName: null,
        roleId: null
      },
      errorResp:{
        errorCode:null,
        errorMessage:null,
      },
      login: {
        username: 'nikola',
        password: 'Nikola123'
      },
      value: String,
      loginForm: true,
      rules: {
        required: value => !!value || 'Required.'
      }
    };
  },
  methods: {
    loginRequest() {
      if (this.validForm()) {

        const payload = {
          username: this.login.username,
          password: this.login.password,
        }

        console.log(payload);

        fetch("api/login", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(payload),
        }).then(async (response) => {
          if (!response.ok) {
            this.errorResp = await response.json();
            this.pushErrorNotification(this.errorResp.errorMessage);
          } else {
            this.loginResponse = await response.json()
            console.log(this.loginResponse)
            this.SetToken();
            this.RedirectToMainPage();
          }
        });
      }
    },
    SetToken() {
      sessionStorage.setItem('user-token', this.loginResponse.token)
      const currentDatetime = new Date();
      currentDatetime.setMinutes(currentDatetime.getMinutes() + 20);
      sessionStorage.setItem('token-duration', currentDatetime.toString())
      sessionStorage.setItem('user-id', this.loginResponse.userId)
      sessionStorage.setItem('username', this.loginResponse.username)
      sessionStorage.setItem('full-name', this.loginResponse.fullName)
      sessionStorage.setItem('role-id', this.loginResponse.roleId)
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
    RedirectToMainPage() {
      this.$router.push({name: 'Dashboard'})
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
    validForm() {
      return this.$refs.loginForm.validate()
    },
  }
};
</script>
<style>

.zoom1.v-input input {
  font-size: 1.3em;
}
.zoom1.v-input label {
  font-size: 1.3em;
}
</style>
