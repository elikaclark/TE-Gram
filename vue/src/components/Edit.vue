//INFO NEEDED
//PLEASE PASS IN PHOTO INFORMATION

<template>
  <div id="app">
    <!-- image upload code -->
    <form>
      <!-- <img v-bind:src="output_src"> -->
      <input v-model="description" type="text" name id />
      <button @click.prevent="editUpload()" type="submit">Confirm Changes</button>
    </form>
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
    postId: Object
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
  },
};
</script>

<style>
</style>