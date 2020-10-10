//INFO NEEDED
//PLEASE PASS IN PHOTO INFORMATION

<template>
  <div id="app">
    <button
      v-if="$store.state.user.id == $route.params.id"
      type="button"
      class="btn btn-warning"
      data-toggle="modal"
      :data-target="'#'+'editModal'+post.photo_id"
    >Edit</button>
    <br />
    {{post.description}}
    <!-- Modal -->
    <div
      class="modal fade"
      :id="'editModal'+post.photo_id"
      tabindex="-1"
      role="dialog"
      aria-labelledby="editModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editModalLabel">Edit</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <img :src="post.photo_src" style="width:100%" />

            <form>
              <!-- <img v-bind:src="output_src"> -->
              <input v-model="description" type="text" />
              <button @click.prevent="editUpload()" type="submit">Confirm Changes</button>
            </form>

            <button @click="deleteUpload(post.photo_id)" type="submit">DELETE POST</button>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
    <!-- image upload code -->
  </div>
</template>

<script>
import CloudinaryService from "@/services/CloudinaryService";

export default {
  name: "edit",
  mounted() {
    this.photo_id = this.postId;
  },
  props: {
    postId: Object,
  },
  data() {
    return {
      ready: false,
      data_url: "",
      output_src: "",

      photo_id: "",
      description: "",
      upload_src: "",
    };
  },
  methods: {
    checkImageStatus(evt) {
      const fileName = evt.target.files[0];
      const reader = new FileReader();

      reader.readAsDataURL(fileName);
      reader.onload = () => {
        this.data_url = reader.result;
        this.output_src = this.data_url;
        this.ready = true;
      };
    },
    uploadImage() {
      const formData = new FormData();

      formData.append("file", this.data_url);
      CloudinaryService.newImage(formData)
        .then((response) => response.json())
        .then((jsonData) => {
          if (jsonData.secure_url !== "") {
            console.log(jsonData.secure_url);
            this.upload_src = jsonData.secure_url;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    editUpload() {
      fetch("http://localhost:8080/editPhoto", {
        method: "put",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          photo_id: this.photo_id,
          description: this.description,
          photo_src: this.upload_src,
          likes: 1,
          user_id: this.$store.state.user.id,
          profile: false,
        }),
      }).then((response) => {
        console.log(response);
        // window.location.reload();
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

<style>
</style>