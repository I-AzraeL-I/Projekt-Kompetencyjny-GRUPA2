<template>
  <div id ="app">
      <h1>Twoja Lokalizacja:</h1>
      <p>{{ coordinates.lat }} Szerokosc, {{ coordinates.lng }} Dlugosc</p>
      <GmapMap
          :center="coordinates"
          :zoom="11"
          style="width: 600px; height: 400px; margin: 32px"
      ></GmapMap>
  </div>
</template>

<script>
export default {
  data() {
    return {
      coordinates: {
        lat: 0,
        lng: 0
      }
    }
  },
  created() {
    // get coordinates from browser
    this.$getLocation({})
    .then(coordinates => {
      this.coordinates = coordinates;
    })
    .catch(error => alert(error));
  }
}
</script>

<style scoped>


</style>