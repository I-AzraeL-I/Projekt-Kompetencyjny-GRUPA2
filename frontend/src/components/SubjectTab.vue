<template>
  <div id="app">
    <div class="contener row">
      <div class="teacher-subjects col s12 m5">
      <h3>Wybierz swoje przedmioty:</h3>
        <ul class="collection">
         <li v-for="(subject, i) in subjects" v-bind:key="`${i}-${subject.id}`"
              class="collection-item row">
            <div class="col s10">{{subject.name}}</div>
            <label class="align">
              <input v-model="subjects[i].isChecked" type="checkbox" value="name"/>
              <span></span>
            </label>
          </li>
        </ul>
      </div>
      <div class="description col s12 m7">
        <h5>Przedmioty wybrane z listy zostaną dodane do przedmiotów, które
          i pojawią się na liście naszych ofert.</h5>
      </div>
    </div>
    <button type="submit" v-on:click="updateSubjects" class="waves-effect waves-light btn signButton">
      Zapisz
    </button>
  </div>
</template>

<script>
import data from "@/subjects.JSON"
import instance from "@/server";
import headers from "@/headers";
let url = '/profile/' + localStorage.id + '/subjects';
//import {instance,headers} from '../App.vue'
export default {
  name: "SubjectTab",
  data () {
    return {
      selected: [],
      subjects: data,
    }
  },
  created() {
    instance.get(url)
    .then((response) => {
      console.log(response);
      this.GETRequestResult = response.data;
      console.log(this.GETRequestResult);
    })
  },
  methods: {
    updateSubjects() {
      let json = JSON.stringify(this.subjects);
      instance.post(url, json, {headers:headers})
      .then((response) => {
        console.log(response);
      })
    }
  }
}
</script>

<style scoped>

.collection {
  height:369px;
  overflow-y:scroll;
}
.description {
  margin-top:20px;
}
</style>