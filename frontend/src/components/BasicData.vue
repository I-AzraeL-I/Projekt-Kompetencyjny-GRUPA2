<template>
  <div id="app">
    <div class="container">
      <h3>Informacje o Tobie</h3>
      <div class="form">
        <form
            id="form"
            @submit="updateValues"
        >
          <p>
            <label for="name">Imię</label>
            <input
                id="name"
                v-model="name"
                type="text"
                name="name"
            >
          </p>

          <p>
            <label for="LastName">Nazwisko</label>
            <input
                id="LastName"
                v-model="lastName"
                type="text"
                name="LastName"
            >
          </p>
          <p>
            <label for="birthDate">Data Urodzenia</label>
            <input
                id="birthDate"
                v-model="birthDate"
                type="text"
                name="birthDate"
            >
          </p>
          <p>
            <label for="description">Opis</label>
            <textarea id="description"
                      v-model="description"
                      class="materialize-textarea"
            ></textarea>
          </p>
          <p>
            <input
                type="submit"
                value="Submit"
            >
          </p>

        </form>
      </div>
    </div>
  </div>
</template>

<script>
import instance from "../server.js"
import headers from "../headers.js"

let url = '/profil/' + localStorage.id + '/data';
export default {
  name: "BasicData",
  data() {
    return {
      errors: [],
      name: null,
      lastName: null,
      description: null,
      birthDate: null,
    };
  },
  created() {
    instance.get(url)
    .then((response) => {
      console.log(response.data);
      this.name = response.data.firstName;
      this.lastName = response.data.lastName;
      this.birthDate = response.data.birthDate;
      this.description = response.data.description;
    })
  },
  methods: {
    updateValues() {
      event.preventDefault();
      let values = {
        "userId": localStorage.id,
        "firstName": this.name,
        "lastName": this.lastName,
        "birthDate": this.birthDate,
        "description": this.description
      }
      var json = JSON.stringify(values);
      console.log(json);
      instance.post(url, json, {headers: headers})
      .then((response) => {
        console.log(response);
      })
    },
  }
}
</script>

<style scoped>

</style>