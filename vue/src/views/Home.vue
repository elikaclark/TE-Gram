<template>
  <div class="container">
    <div class="row posts">
      <div class="col-sm-12 col-md-4 post-box" v-for="photo in photos" :key="photo.photo_id">
        <router-link :to="'/profile/'+photo.user_id"> -_- {{photo.user_id}} </router-link>
        <div>
          <img
            class="postImg imgHover"
            :src="photo.photo_src"
            alt
            data-toggle="modal"
            data-target="#picModal"
          />
          <button type="button" class="btn btn-danger">Likes: {{photo.likes}}</button>
          <!-- . -->
          <PhotoComments v-bind:photo="photo"/>
          <!-- . -->
          <button 
          v-if="!inFavorites(photo.photo_id)" type="button" class="btn btn-info" @click.prevent="addToFavorite(photo.photo_id)">
            Favorite
          </button>
          <button 
          v-else type="button" class="btn btn-info" @click.prevent="deleteFromFavorite(photo.photo_id)">
            Unfavorite
          </button>
          <div>
            "{{photo.description}}"
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PhotoComments from '../components/PhotoComments.vue'

export default {
  name: "test",
  components: {
    PhotoComments
  },
  data() {
    return {
      photos: [],
      userFavPhotos: []
    };
  },
  methods: {
    checkUserFavorites(){

      fetch("http://localhost:8080/" + this.$store.state.user.id + "/favorites",{
      method: "GET"
      }).then(response => response.json())
      .then(json =>{
        this.userFavPhotos = json;
      }).catch(err =>{
        console.log(err);
      });

    },
    inFavorites(photoId){
        let isFav = false;
        this.userFavPhotos.forEach(photo =>{
          if(photo.photo_id == photoId){
            isFav = true;
          }
        });
        return isFav;
    },
    addToFavorite(photo_id){
  
      fetch("http://localhost:8080/addFavorite",{
        method: "POST",
        headers:{
          'content-type': 'application/json'
        },
        body: JSON.stringify({
          user_id: this.$store.state.user.id,
          photo_id: photo_id
        })
      }).then(response =>{
        if(response.status === 201){
          this.checkUserFavorites();
        }
      }).catch(err =>{
        console.log(err);
      })

    },
    deleteFromFavorite(photo_id){

      fetch("http://localhost:8080/deleteFavorite",{
        method: "DELETE",
        headers:{
          'content-type': 'application/json'
        },
        body: JSON.stringify({
          user_id: this.$store.state.user.id,
          photo_id: photo_id
        })
      }).then(response =>{
        if(response.status === 204){
          this.checkUserFavorites();
        }
      }).catch(err =>{
        console.log(err);
      })
    },
      showCommentsByPhoto(photoId) {
    this.$router.push({ name: 'commentByPhoto',
     params: { id: photoId } });

    }
    
  },
  mounted: function () {
    fetch("http://localhost:8080/photos", {
      method: "get",
    })
      .then((response) => {
        return response.json();
      })
      .then((jsonBody) => {
        this.photos = jsonBody.reverse();
      });
      
      // updates favorite buttons
      this.checkUserFavorites();
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
  height: 500px;
}
</style>