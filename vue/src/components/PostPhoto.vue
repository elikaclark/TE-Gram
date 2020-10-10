//BIND PHOTOS AS PHOTOS

<template>
  <div>
    <div class="row posts">
      <div class="col-sm-12 col-md-4 post-box" v-for="photo in photos" :key="photo.photo_id">
        <div class="d-flex justify-content-between">
          <router-link :to="'/profile/'+photo.user_id">
            <Username :photo="photo" />
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
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLongTitle">🧍 {{photo.user_id}}</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <img class="modalImg" :src="photo.photo_src" alt />
                  {{photo.description}}
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
            <!-- . -->
          </div>
          <!-- buttons -->
          <div class="d-flex">
            <div class="p-2">
              <Likes />
            </div>
            <div class="p-2">
              <PhotoComments :photo="photo" />
            </div>
            <div class="ml-auto p-2">
              <Favorite :photo="photo" />
            </div>
          </div>
          <!-- . -->
          <div>
            <b>User: {{photo.user_id}}:</b>
            "{{photo.description}}"
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
    photos: Object,
  },
};
</script>

<style scoped>
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
</style>