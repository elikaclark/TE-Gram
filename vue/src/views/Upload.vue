<template>
  <div id="app">
    <div class="text-center">
      <h1>UPLOAD A PHOTO</h1>
    </div>

    <div class="container">
      <div class="row">
        <div class="offset-lg-2 col-lg-8">
          <img v-bind:src="output_src" style="width: auto; height: 500px"/>
          <form @submit.prevent="uploadImage($event)" @change="checkImageStatus($event)">
            <div class="d-flex justify-content-center">
              <input type="file" id="file-upload" name="filename" />
              <button type="submit" :disabled="!ready">Upload</button>
            </div>
            <div v-if="upload_src.length > 0" class="d-flex justify-content-center">
              <textarea v-model="description" type="text" placeholder="Add a description!" />
              <button class="btn btn-success" @click="saveUpload()" type="submit">UPLOAD</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- image upload code -->
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
      output_src: "https://media2.giphy.com/media/5z0cCz0Xl8rjbU83Kc/giphy.gif",
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
          likes: 0,
          user_id: this.$store.state.user.id,
          profile: false,
        }),
      }).then((response) => {
        console.log(response);
        //redirects to homepage
        this.$router.push("/");
      });
    },
  },
};
</script>

<style scoped>
img {
  width: 100%;
}
textarea {
  width: 100%;
}
</style>