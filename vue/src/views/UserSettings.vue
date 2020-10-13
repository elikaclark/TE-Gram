<template>
  <div class="container">
    <div
      v-if="
        $route.params.id == this.$store.state.user.id ||
        $store.state.user.authorities[0].name === 'ROLE_ADMIN'
      "
    >
      <div class="row">
        <div class="col-md-4">
          <div class="card">
            <h2 class="header bg-dark">Change Username</h2>
            <div class="d-flex justify-content-between">
              <label>Email:</label>
              {{ $store.state.user.username }}
            </div>

            <form class="ui form" @submit.prevent="editUser">
              <div class="d-flex justify-content-between">
                <label>Current name:</label>
                {{ $store.state.user.name }}
              </div>
              <div class="d-flex justify-content-between">
                <label>New name:</label>
                <input type="text" v-model="newUser.name" name="name" />
              </div>
              <br />
              <div class="d-flex float-right">
                <button
                  :disabled="newUser.name.length == 0"
                  @click="editUser(), userNameFlip()"
                  class="btn btn-warning"
                >Update Username</button>
              </div>

              <div v-if="userNameSuccess">Successfully updated username!</div>
            </form>
          </div>
        </div>
        <div class="col-md-5">
          <!-- PASSWORD FORM -->
          <div class="card">
            <h2 class="header bg-dark">Change Password</h2>
            <form>
              <div class="d-flex justify-content-between">
                <label>Old Password:</label>
                <input type="password" v-model="oldPass" name="oldPass" />
              </div>
              <br />
              <div class="d-flex justify-content-between">
                <label>New Password:</label>
                <input
                  type="password"
                  v-model="newPass"
                  name="newPass"
                  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                  title="Must contain at least one number and
                 one uppercase and lowercase letter, and at least 8 or more characters"
                  required
                />
              </div>
              <br />
              <div class="d-flex justify-content-between">
                <label>Confirm New Password:</label>
                <input
                  type="password"
                  v-model="newPassCon"
                  name="newPassCon"
                  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                  title="Must contain at least one number and
                 one uppercase and lowercase letter, and at least 8 or more characters"
                />
              </div>
              <br />
              <div class="bg-dark">
                <button
                  :disabled="(newPassCon.length == 0)"
                  class="btn btn-warning float-right"
                  @click="changePassword()"
                >Update Password</button>
              </div>
            </form>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <h2 class="header bg-dark">DELETE USER</h2>
            <form>
              <div class>
                <div>Type email to confirm deletion: {{$store.state.user.username}}</div>
                <input type="text" v-model="deleteConfirm" name="delete" id="deleteConfirm" />
              </div>
              <div class="bg-dark">
                <button
                  class="btn btn-danger float-right"
                  :disabled="deleteConfirm != $store.state.user.username"
                  @click="deleteUser"
                >DELETE USER</button>
              </div>
            </form>
          </div>
        </div>
      </div>
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
      userNameSuccess: false,
      newUser: {
        id: this.$store.state.user.id,
        username: this.$store.state.user.username,
        name: "",
      },
      oldPass: "",
      newPass: "",
      newPassCon: "",
      deleteConfirm: "",
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
    userNameFlip() {
      this.userNameSuccess = true;
    },
    editUser() {
      console.log("sending");
      fetch("http://localhost:8080/users/" + this.$route.params.id, {
        method: "put",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.newUser),
      }).then((response) => {
        console.log(response);
      });
    },
    changePassword() {
      var passwords = [this.oldPass, this.newPass];
      console.log(passwords);
      fetch(
        "http://localhost:8080/users/" + this.$route.params.id + "/password",
        {
          method: "put",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          body: JSON.stringify(passwords),
        }
      ).then((response) => {
        console.log(response);
      });
      this.vm.$forceUpdate();
    },
    deleteUser() {
      fetch("http://localhost:8080/users/" + this.$route.params.id, {
        method: "delete",
      }).then(this.$router.push("/logout"));
    },
  },
};
</script>

<style scoped>
#deleteConfirm {
  width: 100%;
}
.header {
  color: white;
}
</style>