//PASS IN USER ID 
<template>
  <div>
    {{users.name}}
  </div>
</template>

<script>
export default {
  name: "Username",
  data() {
    return {
      users: "",
    };
  },
  props: {
    photo: Object,
  },
  created: function () {
    var url = "http://localhost:8080/user/" + this.photo.user_id;
    fetch(url, {
      method: "get",
    })
      .then((response) => {
        return response.json();
      })
      .then((jsonBody) => {
        this.users = jsonBody;
      });
  },
};
</script>

<style>
</style>