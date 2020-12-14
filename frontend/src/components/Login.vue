<template>
  <div id="app">
    <div class="container row">
      <div class="register-form z-depth-3 col s12 m12 l4 offset-l4 row">
        <div class="zio"><h1>LOGO</h1></div>
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
    <Map></Map>
  </div>

</template>

<script>

import Map from '@/components/Map.vue'
import {FormField, FormValidation, required, email} from '@asigloo/vue-dynamic-forms';
import instance from"../server.js"
import headers from"../headers.js"

export default {
  name: "Login",
  components: {
    Map,
  },
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
      let json = JSON.stringify(values,['emailAddress','plainPassword']);
      console.log(json);
      instance.post('/login', json, {headers:headers})
      .then((response) => {
        console.log(response);
      })
    }
  }
}
</script>

<style scoped>
.container {
  text-align: center;
  padding-top: 150px;

}

.zio {
  margin-bottom: 75px;
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