<template>
  <div id="app">
  <date-picker v-model="date"></date-picker>
  </div>
</template>

<script>

export default {
  name: "datePicker",
  data() {
    return {
      date: ''
    }
  }
}
</script>

<style scoped>

</style>