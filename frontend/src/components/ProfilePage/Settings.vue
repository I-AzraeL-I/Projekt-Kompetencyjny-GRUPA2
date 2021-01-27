<template>
  <div id="app">
    <div class="container">
      <h3>Zmiana hasła</h3>
      <form
          id="form"
          @submit="updateValues"
      >
        <p>
          <label for="plainPassword">Nowe hasło</label>
          <input
              id="plainPassword"
              v-model="plainPassword"
              type="password"
              name="plainPassword"
          >
        </p>
        <p>
          <label for="password2">Potwierdź nowe hasło</label>
          <input
              id="password2"
              v-model="password2"
              type="password"
              name="birthDate"
          >
        </p>
        <button type="submit" class="waves-effect waves-light btn signButton">
          Zapisz
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import instance from "../../server.js"
import headers from "../../headers.js"

let url = '/profil/' + localStorage.id + '/settings';
export default {
  name: "Settings",
  data() {
    return {
      paswordOld: null,
      plainPassword: null,
      password2: null
    };
  },
  methods: {
    updateValues() {
      event.preventDefault();
      if (this.plainPassword === this.password2) {
        let value = {"plainPassword": this.plainPassword}
        let json = JSON.stringify(value);
        instance.post(url, json, {headers: headers})
        .then((response) => {
          console.log(response);
        }).catch(error => alert(error.message));
      }
    }
  }
}
</script>

<style scoped>
.btn {

  background-color: rgb(51, 51, 51);
}

.btn:hover {
  background-color: rgb(85, 214, 170);
}
</style>