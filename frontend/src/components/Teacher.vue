<template>
  <div id="app">
    <div class="container">
      <div>
        <div class="col s12 m12 l12">
          <img src="../assets/books.png" alt="" class="circle">
          <span class="title"><b>{{ tutorFirstName }}</b> -</span> <span
            v-for="(value,index) in subjects"
            v-bind:key="index"><b>{{ value.subjectName }} </b></span>
          <a
              class="waves-effect btn modal-trigger bookButton"
              href="#modal1">Zapisz się</a>
        </div>
      </div>
      <div class="description col s12 m12 l9">
        <p>{{ description }}
        </p>
      </div>
      <div class="bottom-section row">
        <div class="comments col s12 l9">
          <h4>Opinie:</h4>
          <div v-for="(opinion,index) in opinions" v-bind:key="index"
               class="z-depth-2 opinion-wrapper">
            <div class="comment">
              {{ opinion.comment }}
            </div>
            <div class="divider"></div>
            <div class="stars">
              <ul class="list center">
                <li :key="star" v-for="star in maxStars"
                    :class="{ 'active-star': star <= opinion.rating }"
                    class="star">
                  <i class="material-icons star">star</i>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="col s12 m12 l3">
          <h4>Lokalizacja:</h4>
          <p>
            <b>Miasto: </b>{{ tutorAddress.city }}
          </p>
          <p>
            <b>Miejsce zajęć: </b>u korepetytora / on-line
          </p>
          <div class="border">
            <GmapMap
                :center="position"
                :zoom="12"
                style="width: 100%; height: 450px;"
            >
              <GmapMarker
                  :position="coordinates"
                  :clickable="true"
                  :icon="{ url: ('http://maps.google.com/mapfiles/kml/paddle/blu-blank.png'),
                            scaledSize: {width: 35, height: 35},
                          }"
                  @click="openWindowStudent"

              />
              <GmapMarker
                  :position="position"
                  :clickable="true"
                  :icon="{ url: ('http://maps.google.com/mapfiles/kml/paddle/ylw-blank.png'),

                            scaledSize: {width: 35, height: 35},
                          }"

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
                <b> {{ tutorFirstName }} - {{ tutorAddress.street }} </b>
              </gmap-info-window>
            </GmapMap>
          </div>
        </div>
      </div>
    </div>
    <div id="modal1" class="modal">
      <div class="modal-content">
        <h3>Wybierz dzień, a następnie czas trwania zajęć:</h3>
        <h5>Dostępność korepetytora:</h5>
        {{ scheduleHoursStart }} - {{ scheduleHoursEnd }}
        <p>
          <label for="date">Data</label>
          <input
              id="date"
              v-model="date"
              type="text"
              name="date"
          >
        </p>
        <div class="row">
        <div v-for="(hour,index1) in hours"
             v-bind:key="index1" class="col s12">
          <div class="col 6">
          <label>
            <input v-model="radioStart" name="group1" type="radio" v-bind:value="hour"  checked/>
            <span>{{ hour }}</span>
          </label>
          </div>
          <div class="col 6">
          <label>
          <input v-model="radioEnd" name="group2" type="radio" v-bind:value="hour" checked/>
          <span>{{ hour }}</span>
          </label>
          </div>
          <div class="center-align">
          </div>
        </div>



        </div>
      </div>
      <div class="modal-footer">
        <a v-on:click="requestLesson" href="#!"
           class="modal-close waves-effect btn-flat">Potwierdź</a>
      </div>
    </div>
  </div>
</template>

<script>
import instance from "@/server";
import headers from "@/headers";

let url = "/addPrivateLesson";
export default {
  name: "Teacher",
  data() {
    return {
      date: null,
      tutorId: null,
      tutor: null,
      tutorUser: null,
      tutorFirstName: null,
      scheduleHoursStart: null,
      scheduleHoursEnd: null,
      week: ["poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziela"],
      scheduleDays: [false, false, false, false, false, false, false],
      hours: [],
      subjects: [],
      opinions: [],
      description: "",
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
        street: "",
      },
      maxStars: 5,
      stars: 0,
      iconSize: 0,
      value: [],
      radioStart: 0,
      radioEnd: 0,
    }
  },
  created() {
    document.addEventListener('DOMContentLoaded', function () {
      var elems = document.querySelectorAll('.modal');

      // eslint-disable-next-line no-unused-vars,no-undef
      var instances = M.Modal.init(elems, 1);
    });

    document.addEventListener('DOMContentLoaded', function () {
      var elems = document.querySelectorAll('.datepicker');
      // eslint-disable-next-line no-undef,no-unused-vars
      var instances = M.Datepicker.init(elems, 1);
    });

    this.$getLocation({})
    .then(coordinates => {
      this.coordinates = coordinates;
    })
    .catch(error => alert(error));
    this.tutorId = this.$route.params.id;
    instance.get('/getLessons/' + this.tutorId)
    .then((response) => {
      this.tutor = response.data;
      this.tutorUser = response.data.user;
      this.description = response.data.user.description;
      this.opinions = response.data.opinions;
      this.tutorFirstName = response.data.user.firstName;
      this.schedule = response.data.user.schedule;
      this.scheduleHoursStart = this.timeToDecimal(this.schedule.startTime);
      this.scheduleHoursEnd = this.timeToDecimal(this.schedule.endTime);
      for (var i = this.scheduleHoursStart; i <= this.scheduleHoursEnd; i++) {
        this.hours.push(i);
      }
      this.value.push(this.scheduleHoursStart);
      this.value.push(this.scheduleHoursEnd);
      if (this.schedule.monday === true) {
        this.scheduleDays[0] = true
      }
      if (this.schedule.tuesday === true) {
        this.scheduleDays[1] = true
      }
      if (this.schedule.wednesday === true) {
        this.scheduleDays[2] = true
      }
      if (this.schedule.thursday === true) {
        this.scheduleDays[3] = true
      }
      if (this.schedule.friday === true) {
        this.scheduleDays[4] = true
      }
      if (this.schedule.saturday === true) {
        this.scheduleDays[5] = true
      }
      if (this.schedule.sunday === true) {
        this.scheduleDays[6] = true
      }
      console.log(this.schedule);
      this.subjects = response.data.user.userSubjects;
      console.log(this.tutorUser);
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
    timeToDecimal(t) {
      var arr = t.split(':');
      var dec = parseInt((arr[1] / 6) * 10, 10);

      return parseFloat(parseInt(arr[0], 10) + '.' + (dec < 10 ? '0' : '') + dec);
    },
    openWindowStudent() {
      this.window_open_student = true
    },
    openWindowTutor() {
      this.window_open_tutor = true
    },
    requestLesson() {
      var type = window.location.hash.substr(1);
      var subjectId = null;
      for (var i = 0; i < this.subjects.length; i++) {
        if (this.subjects[i].subjectName === type) {
          subjectId = this.subjects[i].subjectId;
        }
      }
      var dt = new Date(this.date);
      console.log("getDay() : " + dt.getDay());
      console.log(this.radioStart);
      console.log(this.radioEnd);
      let values = {
        "privateLessonDate": this.date,
        "privateLessonStartHour": this.value[0] + ":00",
        "privateLessonEndHour": this.value[1] + ":00",
        "tutorId": this.tutorId,
        "studentId": localStorage.id,
        "subjectId": subjectId,
      }
      var json = JSON.stringify(values);
      console.log(json);
      instance.post(url, json, {headers: headers})
      .then((response) => {
        console.log(response);
      });
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
  padding-top: 50px;
}

.opinion-wrapper {
  padding: 20px;
  margin-bottom: 30px;
}

.circle {
  height: 50px;
}

.bookButton {
  margin-left: 30px;
}

.title {
  font-size: 21px;
}

.description {
  padding: 10px 40px 10px;
  font-weight: 400;
}

.star {
  display: inline-block;
}

.divider {
  margin: 10px 0 10px;
}

.active-star {
  color: gold;
}
</style>