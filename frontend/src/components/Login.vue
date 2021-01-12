<template>
  <div id="app">
    <div class="container row">
      <div class="register-form z-depth-3 col s12 m12 l4 offset-l4 row">
        <div class="login-wrapper"><h1><img id="logo" src="../assets/korki.png"></h1></div>
        <dynamic-form class="dynamic-form col s10 offset-s1"
                      v-bind:id="testForm.id"
                      v-bind:fields="testForm.fields"
                      @submit="login"

        />
        <button type="submit" :form="testForm.id" class="waves-effect waves-light btn signButton">
          Zaloguj
        </button>
      </div>

    </div>
  </div>

</template>

<script>

import {FormField, FormValidation, required, email} from '@asigloo/vue-dynamic-forms';
import instance from "../server.js"
import headers from "../headers.js"

export default {
  name: "Login",
  data() {
    return {
      picked: 'one',
      testForm: {
        id: 'test-form',
        fields: [
          new FormField({
            type: 'email',
            placeholder: 'Email',
            name: 'emailAddress',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
              new FormValidation(email, 'Nieprawidłowy adres email'),
            ],
          }),
          new FormField({
            type: 'password',
            placeholder: 'Hasło',
            name: 'plainPassword',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
            ],
          }),

        ],
      },
    };
  },
  methods: {
    login(values) {
      let json = JSON.stringify(values);
      console.log(json);
      instance.post('/login', json, {headers: headers})
      .then((response) => {
        this.$router.push({name: "profil"});
        this.$router.go({name: "profil"});
        window.localStorage.setItem('id', response.data);
        // eslint-disable-next-line no-unused-vars
      }).catch(error => (this.$toast.error('\nNiepoprawny login lub hasło.', {
        position: 'top'
      })));
    }
  },
  computed: {
    mapConfig() {
      return {
        center: {lat: 0, lng: 0}
      }
    },
  },
}
</script>

<style scoped>
.container {
  text-align: center;
  padding-top: 150px;

}

.login-wrapper {
  margin-bottom: 75px;
}

#logo {
  width: 100%;
}

.register-form {

  margin-bottom: 100px;
  grid-column: 2 / 3;
  background-color: rgb(85, 214, 170);
  border-radius: 10px 10px 30px 30px;
  padding: 0 30px 20px;

}

.signButton {
  margin-bottom: 20px;
}
</style>