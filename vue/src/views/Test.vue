<template>
  <div>
    <PostPhoto :photos = "photos"/>
    <UserName />
  </div>
</template>

<script>
import PostPhoto from "../components/PostPhoto";
import UserName from "../components/Username"
export default {
  name: "test",
  components: {
    PostPhoto,
    UserName
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