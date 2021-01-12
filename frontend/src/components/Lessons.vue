<template>
  <div id="app">
    <div class="container">
      <div class="top-title row">

        <input type="text" v-model="search" placeholder="szukaj przedmiotu"
               class="searchBar col s12 m10 l3 offset-m1 offset-l1"/>
      </div>
      <div class="content row">
        <div class="left-section col s12 m12 l12">
          <ul class="collection">
            <li v-for="(lesson, i) in filteredLessons" v-bind:key="`${i}-${lesson.userId}`"
                class="collection-item avatar z-depth-2 row">
              <div class="left col s12 m2 l2">
                <img src="../assets/books.png" alt="" class="circle">
                <span class="title">{{ lesson.firstName }}</span>
                <p> {{ lesson.city }}
                </p>
                <ul class="skills">
                  <li>{{ lesson.subjectName }}</li>
                </ul>
              </div>
              <div class="description col s12 m7 l7">
                <p>{{ lesson.description }}</p>
              </div>
              <div class="right col s12 m3 l3">
                <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>
                <div class="buttons">

                  <router-link :to="{ name: 'nauczyciel', params: { tutor: lesson }}"><a
                      class="waves-effect waves-light btn messageButton">Wyślij wiadomość</a>
                  </router-link>
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="right-section col s12 m12 l5">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// const source ="";'
import instance from "../server.js"

export default {
  name: "Lessons",

  data() {
    return {
      lessons: [],
      search: '',
    }
  },
  computed: {
    filteredLessons: function () {
      return this.lessons.filter((lesson) => {
        if (lesson.subjectName.toLowerCase().match(this.search.toLocaleLowerCase())) {
          return lesson;
        }
      });
    }
  },
  created() {
    instance.get('/getLessons')
    .then((response) => {
      this.lessons = response.data;
    })
  }
}
</script>

<style scoped>
#app {
  padding-top: 120px;
  min-height: 79vh;
}

@media only screen and (max-width: 1400px) {
  .messageButton {
    height: 50px;
    line-height: inherit;
  }
}

.searchBar {
  grid-column: 2 / 3;
}


.skills {
  margin-top: 20px;
}


.collection li {
  margin: 0 6px 10px;
  font-weight: 600;
}


.description {
  padding: 10px 40px 10px;
  font-weight: 400;
}

.buttons {
  text-align: center;
  font-weight: 400;
  margin-top: 30px;
}


.buttons a {
  margin-top: 2px;
  margin-bottom: 2px;
}

</style>