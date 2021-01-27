<template>
  <div id="app">

    <section class="content">
      <div class="containerr row">
        <div class="event col s12 m3">
          <h2>Profil użytkownika</h2>
          <ul class="collection">
            <li
                @click="toggleClass"
                v-on:click="componentName='BasicData'" class="collection-item current"><i
                class="material-icons">account_circle</i> Dane
            </li>
            <li @click="toggleClass"
                v-on:click="componentName='SubjectTab'" class="collection-item"><i
                class="material-icons">library_books</i> Przedmioty
            </li>
            <li @click="toggleClass"
                v-on:click="componentName='Contact'" class="collection-item"><i
                class="material-icons">local_phone</i> Kontakt
            </li>
            <li @click="toggleClass"
                v-on:click="componentName='Address'" class="collection-item"><i
                class="material-icons">home</i> Adres
            </li>
            <li @click="toggleClass"
                v-on:click="componentName='Settings'" class="collection-item"><i
                class="material-icons">settings</i> Ustawienia
            </li>
          </ul>
        </div>
        <div class="sessions col s12 m7 offset-m1">
          <component v-bind:is="componentName"></component>
        </div>
      </div>
    </section>

  </div>
</template>

<script>

import BasicData from "@/components/ProfilePage/BasicData";
import SubjectTab from "@/components/ProfilePage/SubjectTab";
import Contact from "@/components/ProfilePage/Contact";
import Address from "@/components/ProfilePage/Address";
import Settings from "@/components/ProfilePage/Settings";

export default {
  name: "Profil",
  components: {
    BasicData,
    SubjectTab,
    Settings,
    Contact,
    Address
  },
  data() {
    return {
      componentName: 'BasicData',
      isActive: false,
    }
  },
  methods: {
    toggleClass(event) {
      var el = document.querySelector(".collection");
      var len = document.querySelectorAll(".collection-item").length;
      for (var i = 0; i < len; i++) {
        el.getElementsByTagName("li")[i].classList.remove("current");
      }
      event.target.classList.add("current");
    }
  }

}

</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: rgb(55, 138, 110);
}

.content {
  padding: 70px;
}

@media only screen and (min-width: 1400px) {
  .content {
    height: 79.3vh;
  }
}

li:hover {
  background-color: rgb(85, 214, 170);
  color: white;
  cursor: pointer;
}

.current {
  color: rgb(85, 214, 170);
}

.collection {
  border-style: none;
}

.circle {
  height: 50px;
}
</style>