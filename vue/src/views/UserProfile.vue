<template>
  <div class="container">
    <header>
      <div class="row justify-content-center align-items-center">
        <div class="col-sm-3 col-md-3 d-flex justify-content-center align-items-center">
          <img
            class="userPic"
            src="https://images.unsplash.com/photo-1534278931827-8a259344abe7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80"
            alt
          />
          <span>
            <b>UserName</b>
          </span>
        </div>
        <div class="col-sm-3 col-md-3 d-flex text-center">
          <span>POSTS:#</span>
        </div>
        <div class="col-sm-3 col-md-3 d-flex text-center">
          <span>Followers:#</span>
        </div>
        <div class="col-sm-3 col-md-3 d-flex text-center">
          <span>Following:#</span>
        </div>
      </div>
    </header>
    <!-- Post -->
    <div class="row posts">
      <div class="col-sm-12 col-md-4 post-box" v-for="post in posts" v-bind:key="post.photo_id">
        <div class="postUser">🧍 {{post.user_id}}</div>
        <div>
          <img
            class="postImg imgHover"
            :src="post.photo_src"
            alt
            data-toggle="modal"
            :data-target="'#'+'picModal'+post.photo_id"
          />
          <br />
          <!-- picModal -->
          <div
            class="modal fade"
            :id="'picModal' + post.photo_id"
            tabindex="-1"
            role="dialog"
            aria-labelledby="picModalTitle"
            aria-hidden="true"
          >
            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLongTitle">🧍 {{post.user_id}}</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <img class="postImg" :src="post.photo_src" alt />
                  {{post.description}}
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
            <!-- . -->
          </div>
          <button type="button" class="btn btn-danger">Like</button>
          <!--Comment Modal-->
          <button
            type="button"
            class="btn btn-primary"
            data-toggle="modal"
            data-target="#exampleModal"
          >Comments</button>

          <!-- Modal -->
          <div
            class="modal fade"
            id="exampleModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
          >
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">Comments</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">...</div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary">Save changes</button>
                </div>
              </div>
            </div>
          </div>
          <!-- . -->
          <button v-if="$store.state.user.id == $route.params.id"
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
                  <Edit v-bind:postId="post.photo_id" />
                  <button @click="deleteUpload(post.photo_id)" type="submit">DELETE POST</button>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CloudinaryService from "@/services/CloudinaryService";
import Edit from "../components/Edit";

export default {
  name: "profile",
  mounted: function () {
    var url = "http://localhost:8080/photos/users/" + this.$route.params.id;
    console.log(url);
    fetch(url, {
      method: "get",
    })
      .then((response) => {
        return response.json();
      })
      .then((jsonBody) => {
        this.posts = jsonBody;
      });
  },
  components: {
    Edit,
  },
  data() {
    return {
      posts: "",
      ready: false,
      data_url: "",
      output_src: "",
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
          }
        })
        .catch((err) => {
          console.log(err);
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
  height: auto;
}
.postIcons {
  font-size: 2rem;
}
.userPic {
  width: 100%;
  height: auto;
  border-radius: 100%;
}

@media (min-width: 750px) {
  .imgHover:hover {
    cursor: pointer;
    opacity: 0.8;
  }
}

@media (max-width: 750px) {
  #picModal {
    opacity: 1;
  }
}
</style>