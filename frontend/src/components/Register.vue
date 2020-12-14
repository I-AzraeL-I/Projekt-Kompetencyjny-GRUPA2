<template>
  <div class="container row">
    <div class="register-form z-depth-3 col s12 m12 l4 offset-l4 row">
      <div class="zio"><h1>LOGO</h1></div>
      <dynamic-form class="dynamic-form col s10 offset-s1"
                    v-bind:id="testForm.id"
                    v-bind:fields="testForm.fields"
                    @change="checkPassword"
                    @submit="addUser"

      />
      <div id="pmatch" class="col s12 error"></div>
      <button type="submit" :form="testForm.id" class="waves-effect waves-light btn signButton">
        Zarejestruj
      </button>
    </div>
  </div>
</template>

<script>
import instance from"../server.js"
import headers from"../headers.js"
import {
  FormField,
  FormValidation,
  pattern,
  required,
  email,
  minLength,
  maxLength
} from '@asigloo/vue-dynamic-forms';
import '@asigloo/vue-dynamic-forms/src/styles/themes/default.scss';

export default {
  name: "Register",
  data() {
    return {
      testForm: {
        id: 'test-form',
        fields: [
          new FormField({
            type: 'text',
            placeholder: 'Imię',
            name: 'firstName',
            validations: [
              new FormValidation(required, 'To pole jest wymagane')
            ]
          }),
          new FormField({
            type: 'text',
            placeholder: 'Nazwisko',
            name: 'lastName',
            validations: [
              new FormValidation(required, 'To pole jest wymagane')
            ]
          }),
          new FormField({
            type: 'text',
            placeholder: 'Data urodzenia rrrr-mm-dd',
            name: 'birthDate',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
              new FormValidation(
                  pattern(
                      '^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$',
                  ),
                  'Niepoprawna data, poprawny format - rrrr-mm-dd.',
              ),
            ]
          }),
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
            type: 'text',
            placeholder: 'Numer telefonu',
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
          new FormField({
            type: 'password',
            placeholder: 'Hasło',
            name: 'plainPassword',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
              new FormValidation(minLength(8), 'Hasło musi się składać z conajmniej 8 znaków.'),
              // new FormValidation(
              //     pattern(
              //         '^(?=.*[a-z])(?=.*[A-Z])(?=.*d)(?=.*[#$^+=!*()@%&]).{8,16}$',
              //     ),
              //     'Hasło musi zawierać conajmniej 1 wielką literę, 1 małą literę, 1 cyfrę, 1 znak specjalny i mieć od 8 do 16 znaków.',
              // ),
            ],
          }),
          new FormField({
            type: 'password',
            placeholder: 'Potwierdź Hasło',
            name: 'plainPassword2',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
            ],
          }),
        ],
      },
    };
  },
  methods: {
    addUser(values) {
      if (values.plainPassword === values.plainPassword2) {
        let json = JSON.stringify(values, ['firstName','lastName','birthDate','city','street','emailAddress','phoneNumber','plainPassword']);
        console.log(json);
        instance.post('/addUser', json, {headers:headers})
        .then((response) => {
          window.localStorage.setItem('id',response.data);
        }).then(this.$router.push({name: "profil"}));
      }
    },
    checkPassword(values) {
      if (values.plainPassword && values.plainPassword2) {
        if (values.plainPassword !== values.plainPassword2) {
          document.querySelector('#pmatch').innerHTML = 'Hasła różnią się od siebie';

        } else {
          document.querySelector('#pmatch').innerHTML = '';
        }
      }
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

.error {
  color: red;
}
</style>