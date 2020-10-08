<template>
  <div id="app">
    {{upload_src}}
    <div class="container">


     
    </div>
    <form>
    <!-- <form @submit.prevent="uploadImage($event)" @change="checkImageStatus($event)"> 
        <form @submit.prevent="uploadComment($event)" >-->
    <!-- image upload code -->
    

      <!-- <img v-bind:src="output_src"> -->
      <input v-model="text" type="text"
       id="comment-upload"
                    placeholder="Leave a comment..."
                    maxlength="250"
                    required
                    @keyup.enter="submitComment" name id1 />
      <button @click="saveUpload()">SEND</button>
      <button @click="deleteComment()">Delete Comment</button>

    </form>
  </div>
</template>

<script>



export default {
  name: "comment",
  data() {
    return {
      text: ""
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
          photo_id: 3
          
        }),
      }).then((response) => {
        console.log(response);
      });
    },

    deleteComment(){
       fetch("http://localhost:8080/comments/2/",  {
           method: "delete"

       })
    }
  },
};
</script>

<style scoped>
img {
  width: 100%;
}
</style>