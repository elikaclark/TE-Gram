<template>
  <div>
    <PostPhoto :photos = "photos"/>
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
      photos: "",
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

    // updates favorite buttons
    this.checkUserFavorites();
  },
};
</script>

<style>
</style>