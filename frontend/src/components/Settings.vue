<template>
  <div id="app">
    <div class="container">
      <h3>Zmiana hasła</h3>
      <div class="register-form">
        <dynamic-form class="dynamic-form "
                      v-bind:id="testForm.id"
                      v-bind:fields="testForm.fields"
                      @submit="printValues"

        />
        <button type="submit" :form="testForm.id" class="waves-effect waves-light btn signButton">
          Zapisz
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import instance from"../server.js"
import headers from"../headers.js"
import {FormField, FormValidation,  required} from '@asigloo/vue-dynamic-forms';
let url = '/profile/' + localStorage.id + '/subjects';
export default {
  name: "Settings",
  data() {
    return {
      GETRequestResult: '',
      testForm: {
        id: 'test-form',
        fields: [
          new FormField({
            type: 'password',
            label: 'Stare hasło',
            name: 'oldPass',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
            ],
          }),
          new FormField({
            type: 'password',
            label: 'Nowe hasło',
            name: 'password',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
            ],
          }),
          new FormField({
            type: 'password',
            label: 'Potwierdź nowe hasło',
            name: 'password2',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
            ],
          }),

        ],
      },
    };
  },
  methods: {
    printValues(values) {
      var json = JSON.stringify(values);
      console.log(json);
      instance.post(url, json, {headers:headers})
      .then((response) => {
        console.log(response);
      })
    }
  }
}
</script>

<style scoped>

</style>