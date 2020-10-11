<template>
  <div class="container">
    <div
      v-if="$route.params.id == this.$store.state.user.id || $store.state.user.authorities[0].name === 'ROLE_ADMIN'"
    >
      SETTINGS
      {{users}}
    </div>
    <div v-else>NOT AUTHORIZED</div>
  </div>
</template>

<script>
export default {
  name: "settings",
  data() {
    return {
      users: [],
    };
  },
  mounted: function () {
    var url = "http://localhost:8080/user/" + this.$route.params.id;
    fetch(url, {
      method: "get",
    }).then((response) => {
        return response.json();
    }).then((jsonBody) => {
        this.users = jsonBody;
    })
  },
};
</script>

<style>
</style>