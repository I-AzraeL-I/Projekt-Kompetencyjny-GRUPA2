<template>
  <div id="app">
    <div class="container">
      <div>
        <div class="col s12 m12 l12">
          <img src="../assets/books.png" alt="" class="circle">
          <span class="title">{{ tutor.user.firstName }} - {{ tutor.user.userSubjects[0].subjectName }}</span>
        </div>
      </div>
      <div class="description col s12 m12 l9">
        <p>{{ tutor.user.description }}</p>
      </div>
      <div class="bottom-section row">
        <div class="comments col s12 l9">
          <h5>OPINIE</h5>
        </div>
        <div class="col s12 m12 l3">
          <h4>Lokalizacja:</h4>
          <p>
          <b>Miasto: </b>{{tutor.city}}
          </p>
          <p>
            <b>Miejsce zajęć: </b>u korepetytora / on-line
          </p>
          <div class="border">
          <GmapMap
              :center="position"
              :zoom="12"
              style="width: 289px; height: 450px;"
          >
            <GmapMarker
                :position="coordinates"
                :clickable="true"
                @click="openWindowStudent"

            />
<!--            :icon="{ url: ('https://developers.google.com/maps/documentation/javascript/examples/full/images/parking_lot_maps.png')}"-->
            <GmapMarker
                :position="position"
                :clickable="true"
                @click="openWindowTutor"
            />
            <gmap-info-window
                @closeclick="window_open_student=false"
                :opened="window_open_student"
                :position="coordinates"
                :options="{
          pixelOffset: {
            width: 0,
            height: -35
          }
        }"
            >
              Ty
            </gmap-info-window>
            <gmap-info-window
                @closeclick="window_open_tutor=false"
                :opened="window_open_tutor"
                :position="position"
                :options="{
          pixelOffset: {
            width: 0,
            height: -35
          }
        }"
            >
            <b>  {{ tutor.user.firstName }} - {{ tutor.user.address.street }} </b>
            </gmap-info-window>
          </GmapMap>
        </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import instance from "@/server";

export default {
  name: "Teacher",
  data() {
    return {
      tutorId: null,
      tutor: null,
      coordinates: {
        lat: 0,
        lng: 0
      },
      position: {
        lat: 0,
        lng: 0
      },
      window_open_tutor: false,
      window_open_student: false,
      tutorAddress: {
        city: "",
        street:"",
      },
    }
  },
  created() {
    this.$getLocation({})
    .then(coordinates => {
      this.coordinates = coordinates;
    })
    .catch(error => alert(error));
    this.tutorId = this.$route.params.tutor.userId;
    // console.log(this.$route.params.tutor);
    instance.get('/getLessons/' + this.tutorId)
    .then((response) => {
      this.tutor = response.data;
      this.tutorAddress = response.data.user.address;
      console.log(this.tutor);
      this.$geocoder.setDefaultMode('address');      // this is default
      let addressObj = {
        address_line_1: this.tutorAddress.street,
        address_line_2: '',
        city: this.tutorAddress.city,
        state: '',
        zip_code: '',
        country: 'Poland'
      }
      this.$geocoder.send(addressObj, response => {
        this.position = response.results[0].geometry.location;
      })
    })

  },
  methods: {
    openWindowStudent() {
      this.window_open_student = true
    },
    openWindowTutor() {
      this.window_open_tutor = true
    }
  }
}
</script>

<style scoped>
#app {
  font-size: 110%;
}

.border {
  border: 5px solid green;
}

.container {
  padding-top: 120px;
}

.bottom-section {
  padding-top: 150px;
}

.circle {
  height: 50px;
}


.title {
  font-size: 21px;
}

.description {
  padding: 10px 40px 10px;
  font-weight: 400;
}

</style>