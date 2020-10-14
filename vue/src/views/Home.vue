<template>
  <div class="container">
    <div class="text-center">
      <h1>Welcome, {{$store.state.user.name}}</h1>
    </div>
    <PostPhoto :photos="photos" />
  </div>
</template>

<script>
import PostPhoto from "../components/PostPhoto";
export default {
  name: "home",
  components: {
    PostPhoto,
  },
  data() {
    return {
      photos: [],
    };
  },
  mounted: function () {
    fetch("http://localhost:8080/photos", {
      method: "get",
    })
      .then((response) => {
        return response.json();
      })
      .then((jsonBody) => {
        this.photos = jsonBody.reverse();
      });
  },
};
</script>

<style>
</style>