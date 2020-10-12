<template>
  <div class="container">
    <div class="text-center">
      <h1>Explore</h1>
    </div>

    <PostPhoto :photos="photos" />
  </div>
</template>

<script>
//imported lodash
import _ from "lodash";
import PostPhoto from "../components/PostPhoto";

export default {
  data() {
    return {
      photos: [],
    };
  },
  components: {
    PostPhoto,
  },
  mounted: function () {
    fetch("http://localhost:8080/photos", {
      method: "get",
    })
      .then((response) => {
        return response.json();
      })
      .then((jsonBody) => {
        //lodash shuffles the array
        this.photos = _.shuffle(jsonBody);
      });
  },
};
</script>

<style>
</style>