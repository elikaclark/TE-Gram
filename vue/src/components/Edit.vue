//INFO NEEDED
//PLEASE PASS IN PHOTO INFORMATION

<template>
  <div id="app">
    <button
      v-if="$store.state.user.authorities[0].name === 'ROLE_ADMIN' || $store.state.user.id == $route.params.id"
      type="button"
      class="btn btn-info"
      data-toggle="modal"
      :data-target="'#'+'editModal'+photo.photo_id"
    >
      <i class="far fa-edit"></i>
    </button>
    <br />
    <!-- Modal -->
    <div
      class="modal fade"
      :id="'editModal'+photo.photo_id"
      tabindex="-1"
      role="dialog"
      aria-labelledby="editModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header bg-dark" id="banner">
            <h5 class="modal-title" id="editModalLabel">Edit</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <img :src="photo.photo_src" style="width:100%" />

            <form>
              <!-- <img v-bind:src="output_src"> -->
              <textarea v-model="description" type="text" placeholder="New Description" />
            </form>

            <div class="d-flex justify-content-between">
              <button class="btn btn-success" @click="editUpload()" type="submit">Confirm Changes</button>
              <button class="btn btn-danger" @click="deleteUpload(photo.photo_id)" type="submit">DELETE POST</button>
            </div>
          </div>
          <div class="modal-footer bg-dark">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
    <!-- image upload code -->
  </div>
</template>

<script>
export default {
  name: "edit",
  props: {
    photo: Object,
  },
  data() {
    return {
      description: "",
    };
  },
  methods: {
    editUpload() {
      fetch("http://localhost:8080/editPhoto", {
        method: "put",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          photo_id: this.photo.photo_id,
          description: this.description,
          photo_src: this.photo.photo_src,
          likes: this.photo.likes,
          user_id: this.$store.state.user.id,
          profile: false,
        }),
      }).then((response) => {
        console.log(response);
        window.location.reload();
      });
    },
    deleteUpload(id) {
      var url = "http://localhost:8080/photos/" + id;
      fetch(url, {
        method: "delete",
      });
      window.location.reload();
    },
  },
};
</script>

<style scoped>
.btn {
  border-radius: 0px !important;
}
textarea {
  width: 100%;
}
#banner{
  color:white;
}
</style>