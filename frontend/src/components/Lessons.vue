<template>
  <div id="app">
    <div class="top-title row">

          <input type="text" v-model="search" placeholder="szukaj przedmiotu" class="searchBar col s12 m10 l3 offset-m1 offset-l1"/>
    </div>
    <div class="content row">
      <div class="left-section col s12 m12 l7">
        <ul class="collection">
          <li v-for="(teacher, i) in filteredTeachers" v-bind:key="`${i}-${teacher.id}`"
              class="collection-item avatar z-depth-2 row">
            <div class="left col s12 m2 l2">
              <img src="../assets/books.png" alt="" class="circle">
              <span class="title">{{ teacher.name }}</span>
              <p> {{ teacher.City }}
              </p>
              <ul class="skills">
                <li>{{ teacher.skills[0] }}</li>
                <li>{{ teacher.skills[1] }}</li>
                <li>{{ teacher.skills[2] }}</li>
              </ul>
            </div>
            <div class="description col s12 m7 l7">
              <p>{{ teacher.Description }}</p>
            </div>
            <div class="right col s12 m3 l3">
              <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>
              <div class="prices">
                <div class="price">{{ teacher.price }} złoty</div>
                <div class="price2"> na godzinę</div>
              </div>
              <div class="buttons">
                <a class="waves-effect waves-light btn">Wyślij wiadomość</a>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div class="right-section col s12 m12 l5">
      </div>
    </div>
  </div>
</template>

<script>
// const source ="";'
import data from "@/teachers.JSON"

export default {
  name: "Lessons",

  data() {
    return {
      teachers: data,
      search: ''
    }
  },
  computed: {
    filteredTeachers: function() {
      return this.teachers.filter((teacher)=> {
        for (const skill of teacher.skills) {
          if(skill.toLowerCase().match(this.search.toLocaleLowerCase()))
          return teacher;
      }
      });
    }
  }
  // created () {
  //   alert(source);
  //   fetch(source)
  //     .then(response => {
  //       return response.json();
  //     })
  //   .then(teachers => {
  //     this.teachers = teachers;
  //   })
  // }
}
</script>

<style scoped>
#app {
  padding-top: 60px;
}

.searchBar{
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

.prices {
  text-align: center;
  font-weight: 400;
}

.buttons {
  text-align: center;
  font-weight:400;
  margin-top: 30px;
}

.price {
  margin-top: 30px;
  font-size: 30px;

}

.price2 {

}

.buttons a {
  margin-top: 2px;
  margin-bottom: 2px;
}
.promoted {
  background-color: #42b983;
  width: 300px;
  height: 300px;
  border-radius: 45px;
}
</style>