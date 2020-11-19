<template>
  <div id="app">
    <div class="top-title">
      <div class="kore">
      <h4>Korepetycje</h4>
      </div>
          <input type="text" v-model="search" placeholder="szukaj przedmiotu"/>
    </div>
    <div class="content">
      <div class="left-section">
        <ul class="collection">
          <li v-for="(teacher, i) in filteredTeachers" v-bind:key="`${i}-${teacher.id}`"
              class="collection-item avatar z-depth-2">
            <div class="left">
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
            <div class="description">
              <p>{{ teacher.Description }}</p>
            </div>
            <div class="right">
              <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>
              <div class="prices">
                <div class="price">{{ teacher.price }} złoty</div>
                <div class="price2"> za godzinę</div>
              </div>
              <div class="buttons">
                <a class="waves-effect waves-light btn">Rezerwuj lekcje</a>
                <a class="waves-effect waves-light btn">Wyślij wiadomość</a>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div class="right-section">
        <div class="promoted">
          Promowoanes
        </div>

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
.top-title{
  display: grid;
  grid-template-columns: 300px 1fr 500px 1fr 1fr;
}
.content {
  display: flex;
}
.kore{
  grid-column: 2 / 3;
}
.left-section{
  width:66%;
}

.skills {
  margin-top: 20px;
}

.title {
  font-size: 55px;

}
.collection li {
  margin: 0 6px 10px;
  font-weight: 600;
}
li {
  display: flex;
}

.left {
  width: 15%;
}

.right {
  width: 20%;
}

.description {
  width: 65%;
  padding: 10px 40px 10px;
}

.prices {
  text-align: center;
}

.buttons {
  text-align: center;
  font-weight:400;
}

.price {
  margin-top: 30px;
  font-size: 30px;
  text-align: right;
}

.price2 {
  text-align: right;
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