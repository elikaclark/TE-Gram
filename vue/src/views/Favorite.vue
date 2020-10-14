<template>
  <div>
    <div class="text-center">
      <h1>Favorites</h1>
    </div>

    <div class="container">
      <PostPhotos :photos="userFavPhotos" />
    </div>
  </div>
</template>

<script>
import PostPhotos from "../components/PostPhoto";
export default {
  name: "favorites",

  data() {
    return {
      userFavPhotos: [],
    };
  },
  components: {
    PostPhotos,
  },
  methods: {
    checkUserFavorites() {
      fetch(
        "http://tegrambackendservice-env.eba-fudtugjs.us-east-2.elasticbeanstalk.com/" + this.$store.state.user.id + "/favorites",
        {
          method: "GET",
        }
      )
        .then((response) => response.json())
        .then((json) => {
          this.userFavPhotos = json;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    inFavorites(photoId) {
      let isFav = false;
      this.userFavPhotos.forEach((photo) => {
        if (photo.photo_id == photoId) {
          isFav = true;
        }
      });
      return isFav;
    },
    addToFavorite(photo_id) {
      fetch("http://tegrambackendservice-env.eba-fudtugjs.us-east-2.elasticbeanstalk.com/addFavorite", {
        method: "POST",
        headers: {
          "content-type": "application/json",
        },
        body: JSON.stringify({
          user_id: this.$store.state.user.id,
          photo_id: photo_id,
        }),
      })
        .then((response) => {
          if (response.status === 201) {
            this.checkUserFavorites();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteFromFavorite(photo_id) {
      fetch("http://tegrambackendservice-env.eba-fudtugjs.us-east-2.elasticbeanstalk.com/deleteFavorite", {
        method: "DELETE",
        headers: {
          "content-type": "application/json",
        },
        body: JSON.stringify({
          user_id: this.$store.state.user.id,
          photo_id: photo_id,
        }),
      })
        .then((response) => {
          if (response.status === 204) {
            this.checkUserFavorites();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mounted: function () {
    // updates favorite buttons
    this.checkUserFavorites();
  },
};
</script>

<style>
.posts {
  display: flex;
  flex-wrap: wrap;
}

.post-box {

  padding: 2%;
}

.postImg {
  width: 100%;
  height: auto;
}

</style>