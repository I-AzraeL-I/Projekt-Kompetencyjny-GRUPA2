<template>
  <div id="app">
    <div class="container">
      <div class="switch-wrap row">
        <div class="student  col s6 selected">
          <a @click="toggleClass"
             href="#uczeń" class="fill-div valign-wrapper">Uczeń</a>
        </div>
        <div class="teacher valign-wrapper col s6">
          <a @click="toggleClass"
             href="#nauczyciel" class="fill-div valign-wrapper">Nauczyciel</a>
        </div>

      </div>

      <ul v-if="student" class="collapsible popout">
        <li v-for="(element, i) in filteredHistory" v-bind:key="`${i}-${element.id}`">
          <div class="collapsible-header">
            <b> {{ element.subject.subjectName }} - {{ element.privateLessonDate }} </b>
          </div>
          <div class="collapsible-body">
            <div class="row">
              <span class="col s4">Prowadzący zajęcia: {{ element.tutorFirstName }}
                {{ element.tutorLastName }}</span>
              <div class="col s1 offset-l7">
                Cena: {{ element.price }} zł
              </div>
              <div class="col s12">
                Data: {{ element.privateLessonDate }}<br>
                Czas trwania: {{ element.privateLessonStartHour }} -
                {{ element.privateLessonEndHour }}
              </div>
              <div v-show="element.acceptance === 2">
                <div class="col s4 offset-l6 canceled-text-info">
                  <b>Termin został odrzucony, wybierz inny termin zajęć.</b>
                </div>
                <router-link :to="'/nauczyciel/'+1"><a
                    class="waves-effect waves-light btn messageButton col s2">Wyślij wiadomość</a>
                </router-link>
              </div>
            </div>
          </div>
        </li>
        <li v-for="(element, i) in pastFilteredHistory" v-bind:key="`${i}-${element.id}`">
          <div class="past collapsible-header">
            <b> {{ element.subject.subjectName }} - {{ element.privateLessonDate }} </b>
          </div>
          <div class="collapsible-body">
            <div class="row">
              <span class="col s4">Prowadzący zajęcia: {{ element.tutorFirstName }}
                {{ element.tutorLastName }}</span>
              <div class="col s1 offset-l7">
                Cena: {{ element.price }} zł
              </div>
              <div class="col s12">
                Data: {{ element.privateLessonDate }}<br>
                Czas trwania: {{ element.privateLessonStartHour }} -
                {{ element.privateLessonEndHour }}
              </div>
              <a v-on:click="tutorToRate = element.tutorId"
                 class="waves-effect btn modal-trigger col s2 offset-l10"
                 href="#modal1">Zostaw opinię</a>
            </div>
          </div>
        </li>
      </ul>


      <ul v-else class="collapsible popout">
        <li v-for="(element, i) in filteredHistoryT" v-bind:key="`${i}-${element.id}`">

          <div class="collapsible-header row left-align">
            <div class="col s3">
            <b>{{ element.subject.subjectName }} -
            {{ element.privateLessonDate }}</b>
            </div>
            <div class="col s1 offset-s8">
              <i class="material-icons new-notification">new_releases</i>
            </div>
          </div>

          <div class="collapsible-body">
            <div class="row">
              <span class="col s4">Uczeń: {{ element.studentFirstName }}
                {{ element.studentLastName }}</span>
              <div class="col s1 offset-l7">
                Cena: {{ element.price }} zł
              </div>
              <div class="col s12">
                Data: {{ element.privateLessonDate }}<br>
                Czas trwania: {{ element.privateLessonStartHour }} -
                {{ element.privateLessonEndHour }}
              </div>
              <div class="col s1 offset-l10">
                <a v-on:click="confirm()" class="btn-floating btn-large green waves-effect waves-light"><i
                    class="material-icons">check</i></a>
              </div>
              <div class="col s1">
                <a class="btn-floating btn-large red waves-effect waves-light"><i
                    class="material-icons">clear</i></a>
              </div>
            </div>
          </div>
        </li>
        <li v-for="(element, i) in pastFilteredHistoryT" v-bind:key="`${i}-${element.id}`">
          <div class="past collapsible-header">
            {{ element.przedmiot }} - {{ element.Data }}
          </div>
          <div class="collapsible-body">
            <div class="row">
              <span class="col s4">Prowadzący zajęcia: {{ element.nauczyciel_id }}</span>
              <div class="col s1 offset-l7">
                Cena: {{ element.Cena }}
              </div>
              <div class="col s12">
                Data: {{ element.Data }}<br>
                Czas trwania: {{ element.Godzina_startu }} - {{ element.Godzina_konca }}
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>

    <div id="modal1" class="modal">
      <div class="modal-content">
        <h4>Twoja opinia</h4>

        <label for="description">Komentarz</label>
        <textarea id="description"
                  v-model="description"
                  class="materialize-textarea"
        ></textarea>
        <br>
        <ul class="list center">
          <li :key="star" v-for="star in maxStars" :class="{ 'active-star': star <= stars }"
              @click="rate(star)" class="star">
            <i class="material-icons star">star</i>
          </li>
        </ul>
      </div>
      <div class="modal-footer">
        <a v-on:click="addComment" href="#!" class="modal-close waves-effect btn-flat">Potwierdź</a>
      </div>
    </div>
  </div>
</template>

<script>

import instance from "@/server";
import headers from "@/headers";

let urlStudentHistory = '/studentHistory/' + localStorage.id;
let urlTutorHistory = '/tutorHistory/' + localStorage.id;
let urlOpinion = '/addOpinion';
let urlConfirmLesson = '/confirmLesson';
export default {
  name: "LessonHistory",
  components: {},
  data() {
    return {
      userId: localStorage.id,
      componentName: 'StudentHistory',
      historyStudent: [],
      historyTutor: [],
      filteredHistory: [],
      filteredHistoryT: [],
      student: true,
      todayDate: "",
      maxStars: 5,
      stars: 0,
      tutorName: "",
      tutorToRateName:"",
      tutorToRate: null,
      description: null,
      link: null,
      lesson: null,
      temp: [],

    }
  },
  created() {
    instance.get(urlStudentHistory)
    .then((response) => {
      this.historyStudent = response.data;
    });

    instance.get(urlTutorHistory)
    .then((response) => {
      this.historyTutor = response.data;
    });

    document.addEventListener('DOMContentLoaded', function () {
      var elems = document.querySelectorAll('.collapsible');
      // eslint-disable-next-line no-unused-vars,no-undef
      var instances = M.Collapsible.init(elems, 1);
    });
    document.addEventListener('DOMContentLoaded', function () {
      var elems = document.querySelectorAll('.modal');
      // eslint-disable-next-line no-unused-vars,no-undef
      var instances = M.Modal.init(elems, 1);
    });


  },
  computed: {
    pastFilteredHistory: function () {
      return this.historyStudent.filter((lesson) => {
          if (!this.$moment(lesson.privateLessonDate).isAfter()) {
            console.log(lesson.privateLessonDate);

            return lesson;
          } else {
            this.filteredHistory.push(lesson);
          }
      });
    },
    pastFilteredHistoryT: function () {
      return this.historyTutor.filter((lesson) => {
          if (!this.$moment(lesson.privateLessonDate).isAfter()) {
            return lesson;
          } else {
            this.filteredHistoryT.push(lesson);
          }
      });
    }

  },
  methods: {
    toggleClass(event) {
      var el = document.querySelector(".switch-wrap");
      for (var i = 0; i < 2; i++) {
        var e = el.getElementsByTagName("div")[i];
        e.classList.remove("selected");
        (e.firstChild.style.color = 'black');
      }
      event.target.parentElement.classList.add("selected");
      event.target.style.color = 'white';
      this.student = !this.student;
    },
    rate(star) {
      if (
          typeof star === 'number' &&
          star <= this.maxStars &&
          star >= 0
      ) {
        this.stars = this.stars === star ? star - 1 : star
      }
    },
    addComment() {
      let values = {
        "comment": this.description,
        "rating": this.stars,
        "tutorId": this.tutorToRate,
      }
      var json = JSON.stringify(values);
      instance.post(urlOpinion, json, {headers: headers})
      .then((response) => {
        console.log(response);
      });
    },
    confirm() {
      let values = {
        "link": this.link,
        "lesson": this.lesson,
      }
      var json = JSON.stringify(values);
      instance.post(urlConfirmLesson, json, {headers: headers})
      .then((response) => {
        console.log(response);
      });
    }
  }
}
</script>

<style scoped>
#app {
  padding-top: 120px;
  min-height: 79vh;
}

.past {
  background-color: #e0e0e0;
}

.green {
  background-color: green;
}

.canceled {
  background-color: #ff867c;
}

.canceled-text-info {
  color: #ff867c;
}

.switch-wrap {
  height: 50px;
  width: 250px;
  border: 2px solid rgb(85, 214, 170);
  border-radius: 20px;
  text-align: center;
}

.btn {
  background-color: rgb(51,51,51);
}

.btn:hover {
  background-color: rgb(85, 214, 170);
}

.student, .teacher {
  height: 100%;
}
.new-notification {
  color:orange;
}
.student {
  border-bottom-left-radius: 15px;
  border-top-left-radius: 15px;
}

.teacher {
  border-bottom-right-radius: 15px;
  border-top-right-radius: 15px;
}

a.fill-div {
  height: 100%;
  width: 100%;
  text-decoration: none;
  color: black;
}

.teacher {
  border-left: 2px solid rgb(85, 214, 170);
}

.selected {
  background-color: rgb(85, 214, 170);
  color: white;
}

.list {
  margin: 0 0 5px 0;
  padding: 0;
  list-style-type: none;
}

.star {
  display: inline-block;
}

.active-star {
  color: gold;
}
</style>