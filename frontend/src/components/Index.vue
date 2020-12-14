<template>
  <div id="app">
    <div v-for="(text,index) in displayedText" v-bind:key="text" class="row">
      <div class="col s12 m6 l6">
        <div>
          <a href="http://www.freepik.com">
            <img id="photo" :src="text.link" alt="Designed by pch.vector / Freepik">
          </a>
        </div>
      </div>
      <div class="col s12 m6 l6">
        <div class="txt">
          <h3>{{ text.header }}</h3>
          <p>{{ text.text }}</p>
        </div>
        <div class="btn-link">
          <router-link v-if="index===0" to="korepetycje"><a class="waves-effect waves-light btn">Przeglądaj oferty <i
              class="material-icons left">
            arrow_forward</i></a></router-link>
          <router-link v-if="index===1" to="rejestracja"><a class="waves-effect waves-light btn">Zarejestruj się <i
              class="material-icons left">
            arrow_forward</i></a></router-link>
          <router-link v-if="index===2" to="logowanie"><a class="waves-effect waves-light btn">Zaloguj się <i
              class="material-icons left">
            arrow_forward</i></a></router-link>
          <router-view/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import data from '@/textIndex.JSON'

export default {
  name: "Index",
  data() {
    return {
      displayedText: data,

    }
  }
}
</script>

<style scoped>

#app {
  /*background-image: url(//unsplash.it/1000/1000);*/

  background-blend-mode: multiply;
  background-size: cover;
  place-items: center;
  border: 0;
  font-family: "Charlie Text", sans-serif;
  font-size: 14px;
  font-weight: 400;
  line-height: 1.5;
  color: rgb(55, 138, 110);
  text-align: center;
  box-sizing: border-box;
  padding-top: 66px;
}

p {
  font-size: 20px;
  letter-spacing: 0.5px;
}
h3 {
  letter-spacing: -1.25px;
}
.txt {
  padding: 0 150px 0 150px;
  text-align: left;
  display: inline-block;
}

.btn-link {
  padding-top:50px;

}

#photo {

  height: 350px;
  display: inline-block;
}

.row {
  text-align: center;
  padding: 100px 0 100px;
}

.col {
  display: inline-block;
  text-align: center;
  height: 100%;
}

@media only screen and (max-width: 1400px) {
  .txt {
    padding: 0 50px 50px 50px;
  }

  .img {
    margin: 50px;
  }
}
</style>