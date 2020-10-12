//INFO REQUIRED
//BIND TO PHOTO IN PHOTOS

<template>
  <div>
    <button
      v-if="!inLikes(photo.photo_id)"
      type="button"
      class="btn btn-dark"
      @click.prevent="addLike(photo.photo_id), likes+=1"
    >
      <span>
        <i class="far fa-heart"></i>
        {{likes}}
      </span>
    </button>
    <button
      v-else
      type="button"
      class="btn btn-danger"
      @click.prevent="unLike(photo.photo_id), likes-=1"
    >
      <span>
        <i class="fas fa-heart"></i>
        {{likes}}
      </span>
    </button>
  </div>
</template>

<script>
export default {
  name: "Likes",
  props: {
    photo: Object,
  },
  data() {
    return {
      photos: [],
      likedPhotos: [],
      likes: this.photo.likes,
    };
  },
  mounted: function () {
    this.checkUserFavorites();
  },
  methods: {
    checkUserFavorites() {
      fetch("http://localhost:8080/" + this.$store.state.user.id + "/likes", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((json) => {
          this.likedPhotos = json;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    inLikes(photoId) {
      let isFav = false;
      this.likedPhotos.forEach((photo) => {
        if (photo.photo_id == photoId) {
          isFav = true;
        }
      });
      return isFav;
    },
    addLike(photo_id) {
      fetch("http://localhost:8080/addLike", {
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
    unLike(photo_id) {
      fetch("http://localhost:8080/deleteLike", {
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

<style scoped>
.btn {
  border-radius: 0px !important;
}
</style>