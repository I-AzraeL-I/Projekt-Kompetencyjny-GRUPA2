<template>
  <div id="app">
    <div class="container">
      <h3>Adres zamieszkania</h3>
      <div class="form">
        <form
            id="form"
            @submit="updateValues"
        >
          <p>
            <label for="city">Miasto</label>
            <input
                id="city"
                v-model="city"
                type="text"
                name="city"
            >
          </p>

          <p>
            <label for="street">Ulica</label>
            <input
                id="street"
                v-model="street"
                type="text"
                name="street"
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
import instance from"../server.js"
import headers from"../headers.js"
let url = '/profil/' + localStorage.id + '/address';
export default {
  name: "Address",
  data() {
    return {
      city: null,
      street: null
    };
  },
  created() {
    instance.get(url)
    .then((response) => {
      console.log(response.data);
      this.city = response.data.city;
      this.street = response.data.street;
    })
  },
  methods: {
    updateValues() {
      event.preventDefault();
      let values = {
        "city": this.city,
        "street": this.street
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