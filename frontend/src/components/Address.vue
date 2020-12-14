<template>
  <div id="app">
    <div class="container">
      <h3>Adres zamieszkania</h3>
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
let url = '/profil/' + localStorage.id + '/address';
export default {
  name: "Address",
  data() {
    return {
      GETRequestResult: '',
      testForm: {
        id: 'test-form',
        fields: [
          new FormField({
            type: 'text',
            placeholder: 'Miasto',
            name: 'city',
            validations: [
              new FormValidation(required, 'To pole jest wymagane')
            ]
          }),
          new FormField({
            type: 'text',
            placeholder: 'Ulica',
            name: 'street',
            validations: [
              new FormValidation(required, 'To pole jest wymagane')
            ]
          }),
        ],
      },
    };
  },
  created() {
    instance.get(url)
    .then((response) => {
      console.log(response);
      this.GETRequestResult = response.data;
      console.log(this.GETRequestResult);
    })
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