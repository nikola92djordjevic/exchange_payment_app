<template>
  <v-app id="inspire">


    <v-btn color="primary" @click.prevent="TestMethod" >Test button</v-btn>


  </v-app>
</template>
<script>

export default {
  data() {
    return {
      loginResponse:{
        responseMessage:'',
        token:'',
        userId:'',
        username:'',
        fullName:''
      },
      login:{
        username:'nikola92',
        password:'Nikola123'
      },
      rules: {
        required: value => !!value || 'Required.'
      }
    };
  },
  methods: {
    loginRequest() {
      fetch("api/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: this.login.username,
          password: this.login.password,
        }),
      }).then(res => res.json())
        .then(da => this.loginResponse = da)
        .then(data => console.log(this.loginResponse.token))
        .then(d => this.SetToken())
        .then(r => this.RedirectToMainPage());

      this.$router.push({ name: 'Overview' })
    },
    SetToken() {
      sessionStorage.setItem('user-token', this.loginResponse.token)
      sessionStorage.setItem('user-id', this.loginResponse.userId)
      sessionStorage.setItem('username', this.loginResponse.username)
      sessionStorage.setItem('full-name', this.loginResponse.fullName)
    },
    RedirectToMainPage() {
      this.$router.push({ name: 'MainPage' })
    },
    TestMethod(){
      var currentDate = new Date();
      console.log(currentDate);

      var currentDateWithFormat = new Date().toJSON().slice(0,10).replace(/-/g,'/');
      console.log(currentDateWithFormat);

      const current = new Date();
      const date = `${current.getDate()}/${current.getMonth()+1}/${current.getFullYear()}`;
      console.log(date);
    }
  }
};
</script>
