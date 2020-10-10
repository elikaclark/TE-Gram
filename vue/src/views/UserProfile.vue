<template>
  <div class="container">
    <header>
      <h1>WELCOME {{$route.params}}"</h1>
    </header>
    <!-- Post -->
    <PostPhoto :photos = "photos"/>
  </div>
</template>

<script>
import PostPhoto from "../components/PostPhoto";

export default {
  name: "profile",
  data() {
    return {
      photos: "",
    };
  },
    components: {
    PostPhoto,
  },
  mounted: function () {
    var url = "http://localhost:8080/photos/users/" + this.$route.params.id;
    console.log(url);
    fetch(url, {
      method: "get",
    })
      .then((response) => {
        return response.json();
      })
      .then((jsonBody) => {
        this.photos = jsonBody;
      });
  },

};
</script>

<style scoped>
.posts {
  display: flex;
  flex-wrap: wrap;
}
.post-box {
  border: gray 1px solid;
  border-radius: 4px;
  padding: 2%;
}
.postImg {
  width: 100%;
  height: auto;
}
.postIcons {
  font-size: 2rem;
}
.userPic {
  width: 100%;
  height: auto;
  border-radius: 100%;
}

@media (min-width: 750px) {
  .imgHover:hover {
    cursor: pointer;
    opacity: 0.8;
  }
}

@media (max-width: 750px) {
  #picModal {
    opacity: 1;
  }
}
</style>