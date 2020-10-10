// INFO NEEDED:
// PLEASE PASS IN INDIVIDUAL PHOTO OBJECT
<template>
  <div>
    <!-- Modal Button -->
    <button
      type="button"
      class="btn btn-primary"
      data-toggle="modal"
      :data-target="'#' + 'exampleModal' + photo.photo_id"
      @click="commentsByPhoto(photo.photo_id)"
    ><span><i class="far fa-comments"></i></span></button>
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
          <!-- Add new comment -->
          <input
            v-model="text"
            type="text"
            id="comment-upload"
            placeholder="Leave a comment..."
            maxlength="250"
            required
            @keyup.enter="submitComment"
            name
            id1
          />
          <button @click="saveUpload()">SEND</button>
          <div v-for="comment in allCommentsOnPhoto" :key="comment.comment_id" class="modal-body">
            User: {{comment.user_id}}
            "{{comment.text}}"
            {{comment.datetime}}
            <br />
            <!-- Add Comment form -->
            <form>
              <button
                v-if="$store.state.user.id == comment.user_id || $store.state.user.role == 'ROLE_ADMIN'"
                @click="deleteComment(comment.comment_id)"
              >Delete Comment</button>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "photoComments",
  data() {
    return {
      allCommentsOnPhoto: "",
      text: "",
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
    saveUpload() {
      fetch("http://localhost:8080/addComment/", {
        method: "post",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          text: this.text,
          user_id: this.$store.state.user.id,
          likes: 1,
          photo_id: this.photo.photo_id,
        }),
      }).then((response) => {
        console.log(response);
      });
      window.location.reload();
    },

    deleteComment(id) {
      var url = "http://localhost:8080/comments/" + id;
      fetch(url, {
        method: "delete",
      });
    },
  },
};
</script>

<style>
</style>