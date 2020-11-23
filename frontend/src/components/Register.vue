<template>
  <div class="container row">
    <div class="register-form z-depth-3 col s12 m12 l4 offset-l4">
      <div class="zio"><h1>LOGO</h1></div>
      <dynamic-form class="dynamic-form"
                    v-bind:id="testForm.id"
                    v-bind:fields="testForm.fields"
                    @submit="printValues"

      />
<!--      <div class="radio-role">-->
<!--        <p class="role-span">-->
<!--          <label>-->
<!--            <input value="one" name="group1" type="radio" v-model="picked"/>-->
<!--            <span>Uczeń</span>-->
<!--          </label>-->
<!--        </p>-->
<!--        <p class="role-span">-->
<!--          <label>-->
<!--            <input value="two" name="group1" type="radio" v-model="picked"/>-->
<!--            <span class="role-span">Korepetytor</span>-->
<!--          </label>-->
<!--        </p>-->
<!--      </div>-->
      <button type="submit" :form="testForm.id" class="waves-effect waves-light btn signButton">Zarejestruj
      </button>
    </div>
  </div>
</template>

<script>
import {FormField, FormValidation, pattern, required, email, minLength, maxLength} from '@asigloo/vue-dynamic-forms';
import '@asigloo/vue-dynamic-forms/src/styles/themes/default.scss';
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
            placeholder: 'Imię',
            name: 'name',
            validations: [
              new FormValidation(required, 'To pole jest wymagane')
            ]
          }),
          new FormField({
            type: 'text',
            placeholder: 'Nazwisko',
            name: 'surname',
            validations: [
              new FormValidation(required, 'To pole jest wymagane')
            ]
          }),
          new FormField({
            type: 'email',
            placeholder: 'Email',
            name: 'email',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
              new FormValidation(email, 'Nieprawidłowy adres email'),
            ],
          }),
          new FormField({
            type: 'password',
            placeholder: 'Hasło',
            name: 'password',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
              new FormValidation(
                  pattern(
                      '^(?=.*[a-z])(?=.*[A-Z])(?=.*d)(?=.*[#$^+=!*()@%&]).{8,16}$',
                  ),
                  'Hasło musi zawierać conajmniej 1 wielką literę, 1 małą literę, 1 cyfrę, 1 znak specjalny i mieć od 8 do 16 znaków.',
              ),
            ],
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
            placeholder: 'Adres',
            name: 'street',
            validations: [
              new FormValidation(required, 'To pole jest wymagane')
            ]
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
  text-align: center;
  padding-top:150px;

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