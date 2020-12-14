<template>
  <div id="app">
    <header class="browser-default">
      <router-link to="/"><img id="logo" src="../assets/books.png"></router-link>
      <div class="browser-default nav-bar">
        <ul v-if="this.id ==='null'">
          <li>
            <router-link to="logowanie">Zaloguj</router-link>
          </li>
          <li>
            <router-link to="rejestracja">Zarejestruj</router-link>
          </li>
        </ul>
        <ul v-else>
          <li>
            <router-link to="profil">Profil</router-link>
          </li>
          <li v-on:click="logOut">
            <router-link to="/">Wyloguj</router-link>
          </li>
        </ul>
      </div>
    </header>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "Header",
  rrr: "rejestracja",
  data: function() {
    return {
      id: 'null',
    }
},
  mounted() {
    if (localStorage.id) {
      this.id = localStorage.id;
    }
    else this.id= 'null';
  },
  methods: {
    logOut: function() {
      localStorage.setItem('id','null');
      this.id='null';
    }
}


}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

:root {
  --backgroundcolor: rgba(85, 214, 170, .54);
}

header {
  background: rgba(85, 214, 170, .77);
  position: fixed;
  text-align: center;
  z-index: 999;
  width: 100%;
  height: 66px;
  display: grid;
  grid-template-columns: 1fr auto minmax(600px, 3fr) 1fr;
}

#logo {
  grid-column: 2 / 3;
  height: 66px;
}

.nav-bar {
  /*background: rgba(85,214,170,.54);*/
  grid-column: 3 / 4;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.nav-bar ul {
  margin: 0;
  padding: 0;
  list-style: none;
  display: flex;
}

.nav-bar li {

  margin-left: 3em;
  margin-bottom: 0;
}

.nav-bar a {
  color: white;
  text-decoration: none;
  font-size: 1.2rem;
  text-transform: uppercase;
  opacity: 1;
  position: relative;
}

.nav-bar a:hover {
  color: black;

}

.nav-bar a::before {
  content: '';
  display: block;
  height: 5px;
  background: black;
  position: absolute;
  bottom: -.50em;
  left: 0;
  right: 0;
  transform: scale(0, 1);
  transition: transform ease-in-out 250ms;
}

.nav-bar a:hover::before {
  transform: scale(1, 1);
}
</style>