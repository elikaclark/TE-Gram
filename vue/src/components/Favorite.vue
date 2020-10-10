//INFO REQUIRED
//BIND TO PHOTO IN PHOTOS

<template>
  <div>
    <button
      v-if="!inFavorites(photo.photo_id)"
      type="button"
      class="btn btn-info"
      @click.prevent="addToFavorite(photo.photo_id)"
    >Favorite</button>
    <button
      v-else
      type="button"
      class="btn btn-info"
      @click.prevent="deleteFromFavorite(photo.photo_id)"
    >Unfavorite</button>
  </div>
</template>

<script>
export default {
  name: "favorites",
  props: {
    photo: Object,
  },
  data() {
    return {
      photos: [],
      userFavPhotos: [],
    };
  },
  mounted: function () {
    this.checkUserFavorites();
  },
  methods: {
    checkUserFavorites() {
      fetch(
        "http://localhost:8080/" + this.$store.state.user.id + "/favorites",
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
      fetch("http://localhost:8080/addFavorite", {
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
      fetch("http://localhost:8080/deleteFavorite", {
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
};
</script>

<style>
</style>