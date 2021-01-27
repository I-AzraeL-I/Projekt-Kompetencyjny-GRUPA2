<template>
  <div class="container row">
    <div class="register-form z-depth-3 col s12 m6 offset-m3 l4 offset-l4 row">
      <div class="login-wrapper"><h1><img id="logo" src="../assets/korki.png"></h1></div>
      <dynamic-form class="dynamic-form col s10 offset-s1"
                    v-bind:id="testForm.id"
                    v-bind:fields="testForm.fields"
                    @change="checkPassword"
                    @submit="addUser"

      />

      <div id="pmatch" class="col s12 error"></div>
    </div>
    <div class="button-wrapper col s12">
      <button type="submit" :form="testForm.id" class="waves-effect waves-light btn signButton">
        Zarejestruj
      </button>
    </div>
  </div>
</template>

<script>
import instance from "../server.js"
import headers from "../headers.js"
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
        let json = JSON.stringify(values,
            ['firstName', 'lastName', 'birthDate', 'city', 'street', 'emailAddress', 'phoneNumber',
              'plainPassword']);
        let loginJson = JSON.stringify(values, ['emailAddress', 'plainPassword']);
        console.log(loginJson);
        console.log(json);
        instance.post('/addUser', json, {headers: headers})
        .then((response) => {
          instance.post('/login', loginJson, {headers: headers});
          this.$router.push({name: "profil"});
          this.$router.go({name: "profil"});
          window.localStorage.setItem('id', response.data);
          // eslint-disable-next-line no-unused-vars
        }).catch(error => (this.$toast.error(
            '\nIstnieje już użytkownik zarejestrowany na ten adres Email.', {
              position: 'top'
            })));
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

.login-wrapper {
  margin-bottom: 75px;
}

#logo {
  width: 100%;
}

.btn {

  background-color: rgb(51, 51, 51);
}

.btn:hover {
  background-color: rgb(51, 51, 51);
}

.register-form {

  margin-bottom: 40px;
  grid-column: 2 / 3;
  background-color: rgb(85, 214, 170);
  border-radius: 10px 10px 30px 30px;
  padding: 0 30px 20px;

}

.button-wrapper {
  margin-bottom: 50px;
}

.signButton {
  margin-bottom: 20px;
}

.error {
  color: red;
  margin-bottom: 20px;
}
</style>