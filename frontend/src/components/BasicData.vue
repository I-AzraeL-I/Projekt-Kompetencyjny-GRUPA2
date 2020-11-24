<template>
<div id="app">
  <div class="container">
    <div class="register-form">
      <div class="zio"><h1>Informacje o Tobie</h1></div>
      <dynamic-form class="dynamic-form"
                    v-bind:id="testForm.id"
                    v-bind:fields="testForm.fields"
                    @submit="printValues"

      />
      <button type="submit" :form="testForm.id" class="waves-effect waves-light btn signButton">Zapisz
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
      testForm: {
        id: 'test-form',
        fields: [
          new FormField({
            type: 'text',
            placeholder: 'Imię',
            name: 'name',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
            ],
          }),
          new FormField({
            type: 'text',
            placeholder: 'Nazwisko',
            name: 'surname',
            validations: [
              new FormValidation(required, 'To pole jest wymagane'),
            ],
          }),
          new FormField({
            type: 'textarea',
            label: 'Opis',
            name: 'description',
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