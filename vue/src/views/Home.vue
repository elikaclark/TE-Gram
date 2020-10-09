<template>
  <div class="container">
    <div class="row posts">
      <div class="col-sm-12 col-md-4 post-box" v-for="photo in photos" :key="photo.photo_id">
        <router-link :to="'/profile/'+photo.user_id"> -_- {{photo.user_id}} </router-link>
        <div>
          <img
            class="postImg imgHover"
            :src="photo.photo_src"
            alt
            data-toggle="modal"
            data-target="#picModal"
          />
          <button type="button" class="btn btn-danger">Likes: {{photo.likes}}</button>
          <button type="button" class="btn btn-primary">Comments</button>
          <div>
            "{{photo.description}}"
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "test",
  data() {
    return {
      photos: [],
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
  height: 500px;
}
</style>