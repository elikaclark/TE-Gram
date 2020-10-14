<template>
  <div class="container">
    <header class="text-center">
      <h1 v-if="this.$route.params.id == this.$store.state.user.id">
        {{this.$store.state.user.name}}'s Profile
      </h1>
      <h1 v-else>
        {{users.name}}'s Profile
      </h1>
    </header>
    <!-- Post -->
    <PostPhoto :photos="photos" />
  </div>
</template>

<script>
import PostPhoto from "../components/PostPhoto";

export default {
  name: "profile",
  data() {
    return {
      photos: "",
      users: ""
    };
  },
  components: {
    PostPhoto,
  },
  created: function () {
    var url = "http://tegrambackendservice-env.eba-fudtugjs.us-east-2.elasticbeanstalk.com/user/" + this.$route.params.id;
    fetch(url, {
      method: "get",
    })
      .then((response) => {
        return response.json();
      })
      .then((jsonBody) => {
        this.users = jsonBody;
      });
  },
  mounted: function () {
    var url = "http://tegrambackendservice-env.eba-fudtugjs.us-east-2.elasticbeanstalk.com/photos/users/" + this.$route.params.id;
    fetch(url, {
      method: "get",
    })
      .then((response) => {
        return response.json();
      })
      .then((jsonBody) => {
        this.photos = jsonBody.reverse();
        console.log(this.photos[0]);
      });
  },
  updated: function () {
    var url = "http://tegrambackendservice-env.eba-fudtugjs.us-east-2.elasticbeanstalk.com/photos/users/" + this.$route.params.id;
    fetch(url, {
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