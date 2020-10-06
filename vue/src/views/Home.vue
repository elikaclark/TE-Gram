<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <p>{{holder}}</p>
  </div>
</template>

<script>
export default {
  name: "home", 
  data() {
    return {
      holder: []
    }
  },
  mounted: function() {
    fetch("https://opentdb.com/api.php?amount=10&category=15&type=multiple", {
      method: "get",
    }).then((response) => {
      return response.json();
    }).then((jsonData) => {
      this.holder = jsonData.results;
    })
  }
};
</script>
