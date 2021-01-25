<template>
  <footer class="page-footer">
    <div class="container">
      <div class="row">
        <div class="col l6 s12">
          <h5 class="white-text">Korepetycje</h5>
          <router-link :to="{ name: 'korepetycje'}"> <p class="grey-text text-lighten-4">Znajdź korepetytora.</p>
          </router-link>
        </div>
        <div class="col l4 offset-l2 s12">
          <h5 class="white-text">Links</h5>
          <ul>
            <li><a class="grey-text text-lighten-3" href="#!">Link 1</a></li>
            <li><a class="grey-text text-lighten-3" href="#!">Link 2</a></li>
            <li><a class="grey-text text-lighten-3" href="#!">Link 3</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="footer-copyright">
      <div class="container">
        Projekt Kompetencyjny
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "footer"
}
</script>

<style scoped>
.page-footer{
  background: rgb(85, 214, 170);

}
</style>