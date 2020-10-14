<template>
  <div id="app">
    <div class="text-center">
      <h3>Spill the tea!</h3>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-4 col-sm-12">
          <div class="bg-dark header" style="color: white">Upload a picture!</div>
          <div class="d-flex justify-content-between">
            <form @submit.prevent="uploadImage($event)" @change="checkImageStatus($event)">
              <div>
                <input type="file" id="file-upload" name="filename" />
                <br />
                <button
                  @click="isLoading=true"
                  class="btn btn-primary"
                  type="submit"
                  :disabled="!ready"
                >Upload</button>
              </div>
            </form>
          </div>
          <div v-if="upload_src.length > 0" style="padding: 5% 0%">
            <div class="bg-dark header" style="color: white">Add a description!</div>
            <textarea v-model="description" type="text" placeholder="Spill the TE" />
            <br />
            <button class="btn btn-success" @click="saveUpload()" type="submit">Post</button>
          </div>
          <div v-else-if="isLoading==true">
            <img
              src="https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/42641350633939.58d51adc0f159.gif"
            />
          </div>
        </div>
        <div class="col-md-4 col-sm-12">
          <div class="d-flex bg-dark">
            <button class="btn btn-dark">
              <i class="far fa-user"></i>
            </button>
            <span class="d-flex align-items-center" style="color: white">{{$store.state.user.name}}</span>
          </div>
          <div>
            <img class="postImg" v-bind:src="output_src" />
          </div>
          <div class="d-flex bg-dark">
            <div class="p-2">
              <button type="button" class="btn btn-dark">
                <span>
                  <i class="far fa-heart"></i>
                </span>
              </button>
            </div>
            <div class="p-2">
              <button type="button" class="btn btn-dark">
                <span>
                  <i class="far fa-comments"></i>
                </span>
              </button>
            </div>
            <div class="ml-auto p-2">
              <button type="button" class="btn btn-dark">
                <span>
                  <i class="far fa-star"></i>
                </span>
              </button>
            </div>
          </div>
          <div v-if="upload_src.length > 0" class="bg-dark" id="description">
            <span class="d-flex justify-content-between">
              <b>{{$store.state.user.name}}</b>
            </span>
            "{{description|limit(117)}}"
          </div>
        </div>
        <div class="col-md-2"></div>
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
      isLoading: false,
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
  filters: {
    limit: function (value) {
      if (value.length > 120) {
        value = value.substring(0, 117) + "...";
      }
      return value;
    },
  },
};
</script>

<style scoped>
textarea {
  height: 200px;
}

.header {
  padding: 2%;
}
#description {
  color: white;
  padding: 3%;
  height: 130px;
  border-top: 1px rgba(250, 250, 250, 0.8) solid;
}
img {
  width: 100%;
}
textarea {
  width: 100%;
}
.postImg {
  width: 100%;
  height: 400px;
  object-fit: cover;
}
.p-2 {
  padding: 0rem !important;
}
</style>