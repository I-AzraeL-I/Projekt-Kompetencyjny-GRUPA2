<template>
  <div id="app">
    <GmapMap
        :center="coordinates"
        :zoom="12"
        style="width: 600px; height: 400px; margin: 32px"
    >
      <GmapMarker
          :position="position"
          :clickable="true"
          :icon="{ url: ('https://developers.google.com/maps/documentation/javascript/examples/full/images/parking_lot_maps.png')}"
          @click="openWindow"
      />
      <gmap-info-window
          @closeclick="window_open=false"
          :opened="window_open"
          :position="position"
          :options="{
          pixelOffset: {
            width: 0,
            height: -35
          }
        }"
      >
        Andrzej
      </gmap-info-window>
    </GmapMap>
  </div>
</template>

<script>
export default {
  data() {
    return {
      coordinates: {
        lat: 0,
        lng: 0
      },
      position: {
        lat: 0,
        lng: 0
      },
      info: {
        lat: 0,
        lng: 0
      },
      window_open: false
    }
  },
  created() {
    // get coordinates from browser
    this.$getLocation({})
    .then(coordinates => {
      this.coordinates = coordinates;
    })
    .catch(error => alert(error));
    this.$geocoder.setDefaultMode('address');      // this is default
    let addressObj = {
      address_line_1: 'Politechnika Łódzka',
      address_line_2: '',
      city: '',
      state: '',
      zip_code: '',
      country: 'Poland'
    }
    this.$geocoder.send(addressObj, response => {
      console.log(response.results[0].geometry.location);
      this.position = response.results[0].geometry.location;
      this.info = this.position;

    })
  },
  methods: {
    openWindow() {
      this.window_open = true
    }
  }
}
</script>

<style scoped>
</style>