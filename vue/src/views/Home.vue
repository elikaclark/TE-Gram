<template>
  <div class="container">
    <h1>FEED</h1>
    <p v-for="photo in photos" :key="photo.photo_id">
      <b>User: {{photo.user_id}}</b>
      <br>
      <img :src="photo.photo_src" alt="">
      <br>
      Description: {{photo.description}}
      <br>
      Likes: {{photo.likes}}
    </p>
  </div>
</template>

<script>
export default {
  name: "test",
  data() {
    return {
      photos: []
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
        this.photos = jsonBody;
      });
  }
};
</script>

<style scoped>

</style>