<template>
  <div id="app">
    <div v-for="text in displayedText" v-bind:key="text" class="row">
      <div class="col s12 m6 l6">
        <div class="img1">
        </div>
      </div>
      <div  class="col s12 m6 l6">
        <div class="txt">
          <h4>{{ text.header }}</h4>
          <p>{{ text.text }}</p>
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
  background-color: ghostwhite;
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
  padding-top:66px;
}

p {
  font-size: 22px;
}

.txt {
  padding: 0 150px 0 150px;
  text-align: left;
  display: inline-block;
}

.img {
  display: inline-block;
}

.img1 {
  background-color: #42b983;
  width: 400px;
  height: 300px;
  display: inline-block;
  border-radius: 45px;
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