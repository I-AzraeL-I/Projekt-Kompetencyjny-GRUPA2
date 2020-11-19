<template>
  <div class="container">
    <div class="zio"><h1>LOGO</h1></div>
    <div class="register-form">
      <dynamic-form class="dynamic-form"
                    v-bind:id="testForm.id"
                    v-bind:fields="testForm.fields"
                    @submit="printValues"

      />
      <div class="radio-role">
        <p class="role-span">
          <label>
            <input value="one" name="group1" type="radio" v-model="picked"/>
            <span>Uczeń</span>
          </label>
        </p>
        <p class="role-span">
          <label>
            <input value="two" name="group1" type="radio" v-model="picked"/>
            <span class="role-span">Korepetytor</span>
          </label>
        </p>
      </div>
      <button type="submit" :form="testForm.id" class="waves-effect waves-light btn">Zarejestruj
      </button>
    </div>
  </div>
</template>

<script>
import {FormField, FormValidation, pattern, required, email, minLength, maxLength} from '@asigloo/vue-dynamic-forms';
import md5 from 'js-md5'

export default {
  name: "Register",
  data() {
    return {
      picked: 'one',
      testForm: {
        id: 'test-form',
        fields: [
          new FormField({
            type: 'text',
            label: 'Imię',
            name: 'name',
            validations: [
              new FormValidation(required, 'To pole jest wymagane')
            ]
          }),
          new FormField({
            type: 'text',
            label: 'Nazwisko',
            name: 'surname',
            validations: [
              new FormValidation(required, 'To pole jest wymagane')
            ]
          }),
          new FormField({
            type: 'email',
            label: 'Email',
            name: 'email',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
              new FormValidation(email, 'Format of email is incorrect'),
            ],
          }),
          new FormField({
            type: 'password',
            label: 'Hasło',
            name: 'password',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
              // new FormValidation(
              //     pattern(
              //         '^(?=.*[a-z])(?=.*[A-Z])(?=.*d)(?=.*[#$^+=!*()@%&]).{8,16}$',
              //     ),
              //     'Hasło musi zawierać conajmniej 1 wielką literę, 1 małą literę, 1 cyfrę, 1 znak specjalny i mieć od 8 do 16 znaków.',
              // ),
            ],
          }),
          new FormField({
            type: 'text',
            label: 'Miasto',
            name: 'city',
            validations: [
              new FormValidation(required, 'To pole jest wymagane')
            ]
          }),
          new FormField({
            type: 'text',
            label: 'Ulica',
            name: 'street',
            validations: [
              new FormValidation(required, 'To pole jest wymagane')
            ]
          }),
          new FormField({
            type: 'text',
            label: 'Numer telefonu',
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
  methods: {
    printValues(values) {
      console.log(this.picked);
      values.password = md5(values.password);
      var json = JSON.stringify(values);
      console.log(json);
      console.log(values);
    }
  }
}
</script>

<style scoped>
.container {
  display: grid;
  grid-template-columns: 1fr 500px 1fr;
}

.zio {
  margin: 150px 0 100px;
  grid-column: 2/3;
}

.register-form {

  margin-bottom: 100px;
  grid-column: 2 / 3;
  text-align: center;

}

.dynamic-form {
  margin-bottom: 10px;
}

.radio-role {
  margin-bottom: 10px;
}

.role-span {
  text-align: left;
  /*padding-left: 20px;*/
}

</style>