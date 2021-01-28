<template>
  <div id="app">
    <div class="contener row">
      <div class="teacher-subjects col s12 m5">
        <h3>Wybierz swoje przedmioty:</h3>
        <ul class="collection">
          <li v-for="(subject, i) in subjects" v-bind:key="`${i}-${subject.id}`"
              class="collection-item row">
            <div class="col s10">{{ subject.subjectName }}</div>
            <label class="align">
              <input v-model="subjects[i].chosen" type="checkbox" value="name"/>
              <span></span>
            </label>
          </li>
        </ul>
        <div class="center-align">
          <button type="submit" v-on:click="updateSubjects"
                  class="waves-effect waves-light btn signButton">
            Zapisz
          </button>
        </div>
      </div>
      <div class="description col s12 m5 offset-m1">
        <h3>Wybierz godziny i dni swoich zajęć:</h3>
        <ul class="day-picker">
          <li v-for="(day, i) in week" v-bind:key="`${i}`"
          >
            <div class="col s10">{{ day }}</div>
            <label class="align">
              <input v-model="weekPicked[i]" type="checkbox" value="name"/>
              <span></span>
            </label>
          </li>
        </ul>
        <vue-material-range-slider
            :min="9"
            :max="18"
            :thumbLabel="true"

            v-model="value"
        />
        <div class="center-align">
          <button type="submit" v-on:click="updateHours"
                  class="waves-effect waves-light btn signButton">
            Zapisz
          </button>
        </div>
      </div>
    </div>


  </div>
</template>
<script>
import instance from "@/server";
import headers from "@/headers";

let url = '/profil/' + localStorage.id + '/subjects';
let urlHours = '/profil/' + localStorage.id + "/schedule";
export default {
  name: "SubjectTab",
  data() {
    return {
      subjects: null,
      subjectsOriginal: null,
      schedule: null,
      scheduleOriginal: null,
      week: ["poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziela"],
      weekPicked: [false, false, false, false, false, false, false],
      value: [9, 10],
    }
  },
  created() {
    instance.get(url)
    .then((response) => {
      this.subjects = response.data;
      this.subjectsOriginal = JSON.parse(JSON.stringify(response.data));
    })
    instance.get(urlHours)
    .then((response) => {
      console.log(response.data);
      this.schedule = response.data;
      if(this.schedule.monday === true)
        this.weekPicked[0] = true
      if(this.schedule.tuesday === true)
        this.weekPicked[1] = true
      if(this.schedule.wednesday === true)
        this.weekPicked[2] = true
      if(this.schedule.thursday === true)
        this.weekPicked[3] = true
      if(this.schedule.friday === true)
        this.weekPicked[4] = true
      if(this.schedule.saturday === true)
        this.weekPicked[5] = true
      if(this.schedule.sunday === true)
        this.weekPicked[6] = true
    })

  },
  methods: {
    updateSubjects() {
      for (let i = 0; i < this.subjects.length; i++) {
        if (this.subjects[i].chosen !== this.subjectsOriginal[i].chosen) {
          var value = {
            "subjectId": this.subjects[i].subjectId,
            "subjectName": this.subjects[i].subjectName,
            "chosen": this.subjects[i].chosen
          }
          let json = JSON.stringify(value);
          console.log(json);
          instance.put(url, json, {headers: headers}).then((response) => {
            console.log(response);
          }).then(this.$toast.success('Zapisano zmiany.', {
            position: 'top'
          }));
        }
      }
    },
    updateHours() {
      var value = {
        "startTime": this.value[0] + ":00",
        "endTime": this.value[1] + ":00",
        "monday": this.weekPicked[0],
        "tuesday": this.weekPicked[1],
        "wednesday": this.weekPicked[2],
        "thursday": this.weekPicked[3],
        "friday": this.weekPicked[4],
        "saturday": this.weekPicked[5],
        "sunday": this.weekPicked[6],
      }
      let json = JSON.stringify(value);
      instance.post(urlHours, json, {headers: headers}).then((response) => {
        console.log(response);
      }).then(this.$toast.success('Zapisano zmiany.', {
        position: 'top'
      }));

    }
  }
}
</script>

<style scoped>

.collection {
  height: 369px;
  overflow-y: scroll;
}


.btn {
  background-color: rgb(51, 51, 51);
}

.btn:hover {
  background-color: rgb(85, 214, 170);
}
</style>

