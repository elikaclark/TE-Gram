<template>
  <div>
    <button
      type="button"
      class="btn btn-primary"
      data-toggle="modal"
      :data-target="'#' + 'exampleModal' + photo.photo_id"
      @click="commentsByPhoto(photo.photo_id)"
    >Comments</button>
    {{allCommentsOnPhoto}}
    <!-- Modal -->
    <div
      class="modal fade"
      :id="'exampleModal' + photo.photo_id"
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
          <div
            v-for="comment in allCommentsOnPhoto"
            :key="comment.comment_id"
            class="modal-body"
          >User: {{comment.user_id}}
          "{{comment.text}}"
          {{comment.datetime}} <br>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "photoComments",
  mounted: {

  },
  data() {
    return {
      allCommentsOnPhoto: "",
    };
  },
  props: {
    photo: Object,
  },
  methods: {
    commentsByPhoto(id) {
      var url = "http://localhost:8080/" + id + "/comments";
      fetch(url, {
        method: "GET",
      })
        .then((response) => {
          return response.json();
        })
        .then((jsonBody) => {
          this.allCommentsOnPhoto = jsonBody;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style>
</style>