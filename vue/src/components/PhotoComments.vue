// INFO NEEDED:
// PLEASE PASS IN INDIVIDUAL PHOTO OBJECT
<template>
  <div>
    <!-- Modal Button -->
    <button
      type="button"
      class="btn btn-dark"
      data-toggle="modal"
      :data-target="'#' + 'exampleModal' + photo.photo_id"
      @click="commentsByPhoto(photo.photo_id)"
    >
      <span>
        <i class="far fa-comments"></i>
      </span>
    </button>
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
          <div class="modal-header bg-dark">
            <h5 class="modal-title" id="exampleModalLabel">Comments</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <!-- Add new comment -->
          <textarea
            v-model="text"
            type="text"
            id="comment-upload"
            placeholder="Leave a comment..."
            maxlength="250"
            required
            @keyup.enter="submitComment"
          />
          <button @click="saveUpload()" class="btn btn-success">SEND</button>
          <div v-for="comment in allCommentsOnPhoto" :key="comment.comment_id" class="modal-body">
            <button
              class="btn btn-danger float-right"
              v-if="$store.state.user.id == comment.user_id || $store.state.user.authorities[0].name === 'ROLE_ADMIN'"
              @click="deleteComment(comment.comment_id)"
            >X</button>
            <div class="d-flex justify-content-between">
              <b>
                <Username v-bind:photo="comment" />
              </b>
              {{comment.datetime}}
            </div>
            "{{comment.text}}"
            <br />
            <!-- Add Comment form -->
            <form></form>
          </div>
          <div class="modal-footer bg-dark">
            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Username from "../components/Username";

export default {
  name: "photoComments",
  components: {
    Username,
  },
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
      var url = "http://tegrambackendservice-env.eba-fudtugjs.us-east-2.elasticbeanstalk.com/" + id + "/comments";
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
      fetch("http://tegrambackendservice-env.eba-fudtugjs.us-east-2.elasticbeanstalk.com/addComment/", {
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
      // window.location.reload();
      this.$router.push("/");
    },

    deleteComment(id) {
      var url = "http://tegrambackendservice-env.eba-fudtugjs.us-east-2.elasticbeanstalk.com/comments/" + id;
      fetch(url, {
        method: "delete",
      });
      // window.location.reload();
      this.$router.push("/");
    },
  },
};
</script>

<style scoped>
#exampleModalLabel {
  color: white;
}
</style>