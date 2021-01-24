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
      <div class="description col s12 m7">
        <h5>Przedmioty wybrane z listy zostaną dodane do przedmiotów, które
          i pojawią się na liście naszych ofert.</h5>
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
export default {
  name: "SubjectTab",
  data() {
    return {
      subjects: null,
      subjectsOriginal: null,
    }
  },
  created() {
    instance.get(url)
    .then((response) => {
      this.subjects = response.data;
      this.subjectsOriginal = JSON.parse(JSON.stringify(response.data));
    })
  },
  methods: {
    updateSubjects() {
      for (let i = 0; i < this.subjects.length; i++) {
        if (this.subjects[i].chosen !== this.subjectsOriginal[i].chosen) {
          var value = {
            "subjectId":this.subjects[i].subjectId,
            "subjectName":this.subjects[i].subjectName,
            "chosen":this.subjects[i].chosen
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
    }
  }
}
</script>

<style scoped>

.collection {
  height: 369px;
  overflow-y: scroll;
}

.description {
  margin-top: 20px;
}
</style>