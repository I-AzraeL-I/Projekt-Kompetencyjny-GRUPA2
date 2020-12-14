<template>
  <div id="app">
    <div class="container">
      <h3>Informacje kontaktowe</h3>
      <div class="form">
        <form
            id="form"
            @submit="updateValues"
        >
          <p>
            <label for="emailAddress">Email</label>
            <input
                id="emailAddress"
                v-model="emailAddress"
                type="text"
                name="emailAddress"
            >
          </p>

          <p>
            <label for="phoneNumber">Numer telefonu</label>
            <input
                id="phoneNumber"
                v-model="phoneNumber"
                type="text"
                name="phoneNumber"
            >
          </p>
          <button type="submit" class="waves-effect waves-light btn signButton">
            Zapisz
          </button>

        </form>
      </div>
    </div>
  </div>
</template>

<script>
import instance from "../server.js"
import headers from "../headers.js"
let url = '/profil/' + localStorage.id + '/contact';
export default {
  name: "Contact",
  data() {
    return {
      emailAddress: null,
      phoneNumber: null
    };
  },
  created() {
    instance.get(url)
    .then((response) => {
      console.log(response.data);
      this.emailAddress = response.data.emailAddress;
      this.phoneNumber = response.data.phoneNumber;
    })
  },
  methods: {
    updateValues() {
      event.preventDefault();
      let values = {
        "emailAddress": this.emailAddress,
        "phoneNumber": this.phoneNumber
      }
      let json = JSON.stringify(values);
      console.log(json);
      instance.post(url, json, {headers: headers})
      .then((response) => {
        console.log(response);
      }).then(this.$toast.success('Zapisano zmiany.', {
        position: 'top'
      }));
    }
  }
}
</script>

<style scoped>

</style>