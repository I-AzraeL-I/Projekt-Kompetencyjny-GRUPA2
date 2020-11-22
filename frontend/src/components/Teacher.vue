<template>
  <div id="app">
    <div class="contener">
      <div class="content">
        <div class="left-section">
          <div class="left">
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
          <div class="description">
            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem
              Ipsum has been the industry's standard dummy text ever since the 1500s, when an
              unknown printer took a galley of type and scrambled it to make a type specimen book.
              It has survived not only five centuries, but also the leap into electronic
              typesetting, remaining essentially unchanged. It was popularised in the 1960s with the
              release of Letraset sheets c</p>
          </div>
          <div class="right">
            <div class="prices">
              <div class="price">60 złoty</div>
              <div class="price2"> na godzinę</div>
            </div>
          </div>

        </div>

      </div>
      <div class="extraDescription">

        <div class="capabilities">
          <h4>Zakres</h4>
          <ol>
            <li>Szkoła Podstawowa</li>
            <li>Liceum</li>
            <li>Pomoc do matury</li>
            <li>Studia</li>
          </ol>
        </div>
        <div class="achievements">
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
</template>

<script>
export default {
  name: "Teacher"
}
document.addEventListener('DOMContentLoaded', function () {
  var elems = document.querySelectorAll('.collapsible');
  // eslint-disable-next-line no-unused-vars,no-undef
  var instances = M.Collapsible.init(elems, 1);
});
</script>

<style scoped>
#app {
  padding-top: 100px;
}
.contener {
  padding-left: 100px;
}
.available, .content, .extraDescription {
  margin: 25px 0 25px;
}
.content {
  display: flex;
}

.extraDescription {
  width: 66%;
  display: flex;
}

.capabilities, .achievements {
  width: 50%;
}

.left-section {
  width: 66%;
  display: flex;
}

.skills {
  margin-top: 20px;
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

.circle {
  height: 50px;
}

.right {
  width: 20%;
}

.title {
  font-size: 21px;
}

.description {
  width: 65%;
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