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
          <div v-bind:class="{past: element.Data < todayDate}" class="collapsible-header">
            {{ element.przedmiot }} - {{ element.Data }}
          </div>
          <div class="collapsible-body">
            <div class="row">
              <span class="col s3">Prowadzący zajęcia: {{ element.nauczyciel_id }}</span>
              <div class="col s2 offset-l7">
                Cena: {{ element.Cena }}
              </div>
              <div class="col s12">
                Data: {{ element.Data }}<br>
                Czas trwania: {{ element.Godzina_startu }} - {{ element.Godzina_konca }}
              </div>
            </div>
          </div>
        </li>
        <li v-for="(element, i) in pastFilteredHistory" v-bind:key="`${i}-${element.id}`">
          <div class="past collapsible-header">
            {{ element.przedmiot }} - {{ element.Data }}
          </div>
          <div class="collapsible-body">
            <div class="row">
              <span class="col s3">Prowadzący zajęcia: {{ element.nauczyciel_id }}</span>
              <div class="col s2 offset-l7">
                Cena: {{ element.Cena }}
              </div>
              <div class="col s12">
                Data: {{ element.Data }}<br>
                Czas trwania: {{ element.Godzina_startu }} - {{ element.Godzina_konca }}
              </div>
              <a v-on:click="tutorToRate = element"
                 class="waves-effect waves-light btn modal-trigger col s2 offset-l10"
                 href="#modal1">Zostaw opinię</a>
            </div>
          </div>
        </li>
      </ul>


      <ul v-else class="collapsible popout">
        <li v-for="(element, i) in filteredHistoryT" v-bind:key="`${i}-${element.id}`">
          <div class="collapsible-header">{{ element.przedmiot }} - {{ element.Data }}</div>
          <div class="collapsible-body">
            <div class="row">
              <span class="col s3">Prowadzący zajęcia: {{ element.nauczyciel_id }}</span>
              <div class="col s2 offset-l7">
                Cena: {{ element.Cena }}
              </div>
              <div class="col s12">
                Data: {{ element.Data }}<br>
                Czas trwania: {{ element.Godzina_startu }} - {{ element.Godzina_konca }}
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
              <span class="col s3">Prowadzący zajęcia: {{ element.nauczyciel_id }}</span>
              <div class="col s2 offset-l7">
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
        <h4>Pozostaw opinię dla {{ tutor_id }}</h4>

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
        <a v-on:click="addComment" href="#!" class="modal-close waves-effect waves-green btn-flat">Potwierdź</a>
      </div>
    </div>
  </div>
</template>

<script>

import data from "@/tempKorki.JSON";
import instance from "@/server";
import headers from "@/headers";

// let url = '/history/' + localStorage.id;
let urlHistory = '/studentHistory/1';
let urlOpinion = '/addOpinion';
export default {
  name: "LessonHistory",
  components: {},
  data() {
    return {
      componentName: 'StudentHistory',
      lessonsHistory: data,
      filteredHistory: [],
      filteredHistoryT: [],
      pastFilteredHistory: [],
      pastFilteredHistoryT: [],
      student: true,
      todayDate: "",
      maxStars: 5,
      stars: 0,
      tutorToRate: null,
      description: null,
    }
  },
  created() {
    instance.get(urlHistory)
    .then((response) => {
      console.log(response.data);
    });
    this.todayDate = this.$moment().format("DD-MM-YYYY").toString();
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
    console.log(this.todayDate);
    console.log(this.lessonsHistory[2].Data);
    console.log(this.$moment(this.todayDate).isAfter(this.lessonsHistory[2].Data))

    for (let i = 0; i < this.lessonsHistory.length; i++) {
      if (this.lessonsHistory[i].uczen_id === localStorage.id) {
        if (!this.$moment(this.todayDate).isAfter(this.lessonsHistory[i].Data)) {
          this.pastFilteredHistory.push(this.lessonsHistory[i]);
        } else {
          this.filteredHistory.push(this.lessonsHistory[i]);
        }
      }
    }
    for (let i = 0; i < this.lessonsHistory.length; i++) {
      if (this.lessonsHistory[i].nauczyciel_id === localStorage.id) {
        this.filteredHistoryT.push(this.lessonsHistory[i]);
      }
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
        "tutor": this.tutorToRate,
      }
      var json = JSON.stringify(values);
      console.log(json);
      instance.post(urlOpinion, json, {headers: headers})
      .then((response) => {
        console.log(response);
      });
    },
  }
}
</script>

<style scoped>
#app {
  padding-top: 120px;
  min-height: 79vh;
}

.past {
  background-color: lightgrey;
}

.switch-wrap {
  height: 50px;
  width: 250px;
  border: 2px solid rgb(85, 214, 170);
  border-radius: 20px;
  text-align: center;
}

.student, .teacher {
  height: 100%;
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