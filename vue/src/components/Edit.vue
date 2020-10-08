<template>
  <div id="app">
    <div class="container">
      <img style="width: 100%" v-bind:src="output_src" alt />
    </div>
    <!-- image upload code -->
    <form @submit.prevent="uploadImage($event)" @change="checkImageStatus($event)">
      <input type="file" id="file-upload" name="filename" />
      <button type="submit" :disabled="!ready">Upload</button>
      <!-- <img v-bind:src="output_src"> -->
      <input v-model="description" type="text" name id />
      <button @click="saveUpload()" type="submit">FULL SEND</button>
    </form>
  </div>
</template>

<script>
import CloudinaryService from "@/services/CloudinaryService";

export default {
  name: "edit",
  data() {
    return {
      ready: false,
      data_url: "",
      output_src:
        "https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
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
    saveUpload() {
      fetch("http://localhost:8080/addPhoto/", {
        method: "post",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          description: this.description,
          photo_src: this.upload_src,
          likes: 1,
          user_id: 3,
          profile: false,
        }),
      }).then((response) => {
        console.log(response);
      });
    },
  },
};
</script>

<style>
</style>