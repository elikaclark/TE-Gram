<template>
  <div class="container">
    <h1>All Comments</h1>
    <p v-for="comment in comments" :key="comment.comment_id">
      User: {{comment.user_id}}
      <br/>
      "{{comment.text}}"
      <br>
      {{comment.timestamp}}
    </p>
  </div>
</template>

<script>
export default {
  name: "test",
  data() {
    return {
      comments: []
    };
  },
  mounted: function () {
    fetch("http://localhost:8080/comments", {
      method: "get",
    })
      .then((response) => {
        return response.json();
      })
      .then((jsonBody) => {
        this.comments = jsonBody;
      });
  },
  function () {
    console.log("jello")
  }
};
</script>

<style scoped>

</style>