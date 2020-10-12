<template>
  <div class="container">
    <h2 class="ui dividing header">Edit Profile</h2>

    <form class="ui form" @submit.prevent="editUser">
      <div class="field">
        <label>Current name</label>
        {{ $store.state.user.name }}
      </div>

      <div class="field">
        <label>New name</label>
        <input type="text" v-model="newUser.name" name="name" />
      </div>

      <div class="field">
        <label>Email</label>
        {{ $store.state.user.username }}
      </div>

      <div class="field">
        <label>New Email</label>
        <input type="email" v-model="newUser.username" name="userName" />
      </div>

      <button class="ui button primary">
        Update profile
      </button>
    </form>
    <div
      v-if="
        $route.params.id == this.$store.state.user.id ||
        $store.state.user.authorities[0].name === 'ROLE_ADMIN'
      "
    >
      SETTINGS
      {{ users }}
    </div>
    <div v-else>NOT AUTHORIZED</div>
    <Username />
  </div>
</template>

<script>
import Username from "../components/Username";

export default {
  name: "settings",
  components: {
    Username,
  },
  data() {
    return {
      users: [],
      newUser: {
        id: this.$store.state.user.id,
      },
    };
  },
  mounted: function () {
    var url = "http://localhost:8080/user/" + this.$route.params.id;
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
  methods: {
    editUser() {
      console.log('sending');
      fetch("http://localhost:8080/users/" + this.$route.params.id, {
        method: "put",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.newUser),
      }).then((response) => {
        console.log(response);
        window.location.reload();
      });
    },
  },
};
</script>

<style>
</style>