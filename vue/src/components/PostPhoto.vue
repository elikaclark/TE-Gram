//BIND PHOTOS AS PHOTOS

<template>
  <div>
    <div class="row posts">
      <div class="col-sm-12 col-md-4 post-box" v-for="photo in photos" :key="photo.photo_id">
        <div class="d-flex justify-content-between bg-dark">
          <router-link :to="'/profile/'+photo.user_id" id="postUsername">
            <span class="d-flex">
              <button class="btn btn-dark">
                <i class="far fa-user"></i>
              </button>
              <span class="d-flex align-items-center">
                <Username :photo="photo" />
              </span>
            </span>
          </router-link>
          <Edit :photo="photo" />
        </div>
        <div>
          <div>
            <img
              class="postImg imgHover"
              :src="photo.photo_src"
              alt
              data-toggle="modal"
              :data-target="'#'+'picModal'+photo.photo_id"
            />
          </div>

          <!-- Modal -->
          <div
            class="modal fade"
            :id="'picModal' + photo.photo_id"
            tabindex="-1"
            role="dialog"
            aria-labelledby="picModalTitle"
            aria-hidden="true"
          >
            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
              <div class="modal-content">
                <div class="modal-header bg-dark">
                  <!-- Username -->
                  <h5 class="modal-title" id="exampleModalLongTitle">
                    <Username :photo="photo" id="postUsername" />
                  </h5>
                  <!-- Close button -->
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <img class="modalImg" :src="photo.photo_src" alt />
                  <div class="col align-self-center">"{{photo.description}}"</div>
                </div>

                <div class="modal-footer bg-dark">
                  <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
            <!-- . -->
          </div>
          <!-- buttons -->
          <div class="d-flex bg-dark">
            <div class="p-2">
              <Likes :photo="photo" />
            </div>
            <div class="p-2">
              <PhotoComments :photo="photo" />
            </div>
            <div class="ml-auto p-2">
              <Favorite :photo="photo" />
            </div>
          </div>
          <!-- . -->
          <div class="bg-dark" id="description">
            <b>
              <Username :photo="photo" />
            </b>
            "{{photo.description|limit(100)}}"
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Favorite from "../components/Favorite";
import PhotoComments from "../components/PhotoComments";
import Likes from "../components/Likes";
import Edit from "../components/Edit";
import Username from "../components/Username";

export default {
  components: {
    Favorite,
    PhotoComments,
    Likes,
    Edit,
    Username,
  },
  props: {
    photos: Array,
  },
  filters: {
    limit: function(value) {
      if (value.length > 100) {
        value = value.substring(0, 97) + '...';
      }
      return value
    }
  }
};
</script>

<style scoped>
#description {
  color: white;
  padding: 3%;
  height: 130px
}
#postUsername {
  color: white;
}

.imgHover:hover {
  opacity: 0.7;
  cursor: pointer;
}
.postImg {
  width: 100%;
  height: 400px;
  object-fit: cover;
}

.modalImg {
  width: 100%;
  height: auto;
}

.p-2 {
  padding: 0rem !important;
}

.close {
  color: white;
}
</style>