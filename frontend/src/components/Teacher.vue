<template>
  <div id="app">
    <div class="containerr">
      <div class="content row">
        <div class="left-section col s12 m12 l7">
          <div class="left col s12 m2 l2">
            <img src="../assets/books.png" alt="" class="circle">
            <span class="title">Adrian Kozłowski</span>
            <p> Adr
            </p>
            <ul class="skills">
              <li>Adr</li>
              <li>Adr</li>
              <li>Adr</li>
            </ul>
          </div>
          <div class="description col s12 m7 l7">
            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem
              Ipsum has been the industry's standard dummy text ever since the 1500s, when an
              unknown printer took a galley of type and scrambled it to make a type specimen book.
              It has survived not only five centuries, but also the leap into electronic
              typesetting, remaining essentially unchanged. It was popularised in the 1960s with the
              release of Letraset sheets c</p>
          </div>
          <div class="right col s12 m3 l3">
            <div class="prices">
              <div class="price">60 złoty</div>
              <div class="price2"> na godzinę</div>
            </div>
          </div>

        </div>
        <div class="right-section col s12 m12 l12">
          <div class="extraDescription row">
            <div class="capabilities col s12 m6">
              <h4>Zakres</h4>
              <ol>
                <li>Szkoła Podstawowa</li>
                <li>Liceum</li>
                <li>Pomoc do matury</li>
                <li>Studia</li>
              </ol>
            </div>
            <div class="achievements col s12 m6">
              <h4>Doświadczenie</h4>
              <ol>
                <li>Native Speaker</li>
                <li>Native Speaker</li>
                <li>Native Speaker</li>
              </ol>

            </div>
          </div>
          <div class="available">
            <h4>Dni dostępne w tygodniu</h4>
            <ul>
              <li>
                Poniedziałek
              </li>
              <li>
                Wtorek
              </li>
              <li>
                Sroda
              </li>
              <li>
                Czwartek
              </li>
              <li>
                Piątek
              </li>
            </ul>
          </div>
          <div class="comments">
            <h4>Opinie:</h4>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Teacher"
}
</script>

<style scoped>
#app {
}

.available, .content, .extraDescription {
  margin: 25px 0 25px;
}

.containerr {
  padding: 70px;
}

.extraDescription {
  width: 66%;
  display: flex;
}

.skills {
  margin-top: 20px;
}

.circle {
  height: 50px;
}


.title {
  font-size: 21px;
}

.description {
  padding: 10px 40px 10px;
  font-weight: 400;
}

.prices {
  text-align: center;
  font-weight: 400;
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

</style>