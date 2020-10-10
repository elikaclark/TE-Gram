//BIND PHOTOS AS PHOTOS

<template>
  <div>
    <div class="row posts">
      <div class="col-sm-12 col-md-4 post-box" v-for="photo in photos" :key="photo.photo_id">
        <router-link :to="'/profile/'+photo.user_id">User: {{photo.user_id}}</router-link>
        <div>
          <img
            class="postImg imgHover"
            :src="photo.photo_src"
            alt
            data-toggle="modal"
            :data-target="'#'+'picModal'+photo.photo_id"
          />
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
                  <img class="postImg" :src="photo.photo_src" alt />
                  {{photo.description}}
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
            <!-- . -->
          </div>
          <!-- buttons -->
          <div class="d-flex justify-content-between">
            <Likes />
            <PhotoComments :photo="photo" />
            <Edit :photo="photo"/>
            <Favorite :photo="photo" />
          </div>
          <!-- . -->
          <div>User: {{photo.user_id}}: "{{photo.description}}"</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Favorite from "../components/Favorite"
import PhotoComments from "../components/PhotoComments"
import Likes from "../components/Likes"
import Edit from "../components/Edit"

export default {
  components: {
    Favorite,
    PhotoComments,
    Likes,
    Edit
  },
  props: {
    photos: Object
  },
  mounted: function () {
    // updates favorite buttons
    this.checkUserFavorites();
  },
};
</script>

<style>
</style>