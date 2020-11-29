<template>
  <div id="app">
    <div class="container">
      <h3>Informacje o Tobie</h3>
      <div class="register-form">
        <dynamic-form class="dynamic-form "
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
          <p class="role-span">
            <label>
              <input value="three" name="group1" type="radio" v-model="picked"/>
              <span class="role-span">Oba</span>
            </label>
          </p>
        </div>
        <button type="submit" :form="testForm.id" class="waves-effect waves-light btn signButton">
          Zapisz
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import {FormField, FormValidation, required} from '@asigloo/vue-dynamic-forms';

export default {
  name: "BasicData",
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
              new FormValidation(required, 'To pole jest wymagane'),
            ],
          }),
          new FormField({
            type: 'text',
            label: 'Nazwisko',
            name: 'surname',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
            ],
          }),
          new FormField({
            type: 'textarea',
            label: 'Opis',
            name: 'description',
          }),

        ],
      },
    };
  },
  methods: {
    printValues(values) {
      console.log(this.picked);
      var json = JSON.stringify(values);
      console.log(json);
      console.log(values);
    }
  }
}
</script>

<style scoped>

</style>