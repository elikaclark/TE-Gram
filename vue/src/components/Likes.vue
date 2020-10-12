//PASS IN PHOTO INFO

<template>
  <div>
    <button
      v-if="!isLiked(photo.photo_id) && liked==false"
      type="button"
      class="btn btn-dark"
      @click.prevent="addLike(photo.photo_id), flip()"
    >
      <span>
        <i class="far fa-heart"></i>
        {{photo.likes}}
      </span>
    </button>
    <button
      v-else
      type="button"
      class="btn btn-danger"
      @click.prevent="deleteLike(photo.photo_id), flip()"
    >
      <span>
        <i class="fas fa-heart"></i>
        {{photo.likes}}
      </span>
    </button>
  </div>
</template>

<script>
export default {
  name: "likes",
  props: {
    photo: Object,
  },
  data() {
    return {
      liked: false,
      photo_id: "",
      user_id: "",
    };
  },
  mounted: function () {
    this.isliked();
  },
  methods: {
    flip() {
      this.liked = !this.liked;
    },
    isLiked(photoId) {
      let isLiked = false;
      let userFavPhotos = [];
      fetch("http://localhost:8080/likes/photo/" + photoId, {
        method: "get",
      }).then((response) => (this.userFavPhotos = response.json()));

      userFavPhotos.forEach((photo) => {
        if (photo.photo_id == photoId) {
          isLiked = true;
        }
      });
      return isLiked;
    },
    addLike() {
      //mounted?
      this.photo_id = this.photo.photo_id;
      this.user_id = this.photo.user_id;
      fetch("http://localhost:8080/addLike/", {
        method: "post",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          photo_id: this.photo_id,
          user_id: this.user_id,
        }),
      })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteLike() {
      //mounted?
      this.photo_id = this.photo.photo_id;
      this.user_id = this.photo.user_id;
      fetch("http://localhost:8080/deleteLike/", {
        method: "delete",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          photo_id: this.photo_id,
          user_id: this.user_id,
        }),
      })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
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