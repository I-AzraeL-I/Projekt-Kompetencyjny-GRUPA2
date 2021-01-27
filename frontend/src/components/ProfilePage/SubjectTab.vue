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
        <div class="input-field col s12">
          <select>
            <optgroup label="team 1">
              <option value="1">Option 1</option>
              <option value="2">Option 2</option>
            </optgroup>
            <optgroup label="team 2">
              <option value="3">Option 3</option>
              <option value="4">Option 4</option>
            </optgroup>
          </select>
          <label>Optgroups</label>
        </div>
      </div>
    </div>
    <button type="submit" v-on:click="updateSubjects"
            class="waves-effect waves-light btn signButton">
      Zapisz
    </button>
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
      week : ["poniedziałek","wtorek","środa","czwartek","piątek","sobota","niedziela"],
      weekPicked : [false,false,false,false,false,false,false],
      hours : [
          "8:00",
          "9:00",
          "10:00",
          "11:00",
          "12:00",
          "13:00",
          "14:00",
          "15:00",
          "16:00",
          "17:00",
          "18:00",
          "19:00",
      ]
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
      this.scheduleOriginal = JSON.parse(JSON.stringify(this.schedule));
    })
    document.addEventListener('DOMContentLoaded', function() {
      var elems = document.querySelectorAll('select');
      // eslint-disable-next-line no-unused-vars,no-undef
      var instances = M.FormSelect.init(elems, 1);
    });

  },
  methods: {
    updateSubjects() {
      console.log(this.weekPicked);
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
            "startTime": "17:00",
            "endTime": "18:00",
            "monday": true,
            "tuesday": true,
            "wednesday": true,
            "thursday": true,
            "friday": true,
            "saturday": true,
            "sunday": true,
          }
          let json = JSON.stringify(value);
          console.log(json);
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

