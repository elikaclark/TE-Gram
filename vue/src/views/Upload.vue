<template>
  <div id="app">
    {{upload_src}}
    <div class="container">
      <img v-bind:src="output_src" alt />
    </div>
    <!-- image upload code -->
    <form @submit.prevent="uploadImage($event)" @change="checkImageStatus($event)">
      <input type="file" id="file-upload" name="filename" />
      <button type="submit" :disabled="!ready">Upload</button>
      <!-- <img v-bind:src="output_src"> -->
      <input v-model="description" type="text" name id />
      <button @click="saveUpload()">FULL SEND</button>
    </form>
  </div>
</template>

<script>
import CloudinaryService from "@/services/CloudinaryService";

export default {
  name: "upload",
  data() {
    return {
      ready: false,
      data_url: "",
      output_src:
        "https://images.unsplash.com/photo-1534278931827-8a259344abe7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80",
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

<style scoped>
img {
  width: 100%;
}
</style>