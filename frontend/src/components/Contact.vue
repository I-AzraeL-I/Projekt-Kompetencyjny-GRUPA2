<template>
  <div id="app">
    <div class="container">
      <h3>Informacje kontaktowe</h3>
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
import {
  email,
  FormField,
  FormValidation,
  maxLength,
  minLength, pattern,
  required
} from '@asigloo/vue-dynamic-forms';
let url = '/profile/' + localStorage.id + '/subjects';
export default {
  name: "Contact",
  data() {
    return {
      picked: 'student',
      GETRequestResult: '',
      testForm: {
        id: 'test-form',
        fields: [
          new FormField({
            type: 'email',
            label: 'Email',
            name: 'emailAddress',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
              new FormValidation(email, 'Nieprawidłowy adres email'),
            ],
          }),
          new FormField({
            type: 'text',
            label: 'numer telefonu',
            name: 'phoneNumber',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
              new FormValidation(minLength(9), 'Numer telefonu musi składać się z 9 cyfr'),
              new FormValidation(maxLength(9), 'Numer telefonu musi składać się z 9 cyfr'),
              new FormValidation(
                  pattern('^\\d+$',),
                  'Niepoprawny numer telefonu',
              ),
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