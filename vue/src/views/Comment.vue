<template>
  <div id="app">
    {{upload_src}}
    {{photo_id}}
    <div class="container"></div>
    <form>
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
      <button @click="deleteComment()">Delete Comment</button>
      <button @click="showCommentsByPhoto()">Comments</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "comment",
  data() {
    return {
      text: "",
      photo_id: -1,
      allCommentsOnPhoto: [],
    };
  },
  methods: {
    saveUpload() {
      fetch("http://localhost:8080/addComment/", {
        method: "post",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          text: this.text,
          user_id: 1,
          likes: 1,
          photo_id: 3,
        }),
      }).then((response) => {
        console.log(response);
      });
    },

    deleteComment() {
      fetch("http://localhost:8080/comments/2/", {
        method: "delete",
      });
    },
    commentsByPhoto() {
      fetch("http://localhost:8080/" + this.$router.params.id + "/comments", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((json) => {
          this.allCommentsOnPhoto = json;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    showCommentsByPhoto() {
      fetch("http://localhost:8080/3/comments/", {
        method: "get",
      });
    },
  },
  created() {
    this.photo_id = this.$route.params.id;

    // go to the server endpoint and get all the comments.
    // and store those comments somewhere
  },
};
</script>

<style scoped>
img {
  width: 100%;
}
</style>