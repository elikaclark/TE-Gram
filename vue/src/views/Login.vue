<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-12 col-lg-6 picBox">
        <img
          class="loginPic"
          src="https://images.unsplash.com/photo-1452587925148-ce544e77e70d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1934&q=80"
          alt=""
        />
      </div>
      <div
        class="form-box col-sm-12 col-lg-6 d-flex justify-content-center align-items-center"
      >
        <div id="login" class="text-center">
          <form class="form-signin" @submit.prevent="login">
            <h1>☕ TE Gram</h1>
            <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
            <div
              class="alert alert-danger"
              role="alert"
              v-if="invalidCredentials"
            >
              Invalid username and password!
            </div>
            <div
              class="alert alert-success"
              role="alert"
              v-if="this.$route.query.registration"
            >
              Thank you for registering, please sign in.
            </div>
            <div class="d-flex justify-content-center inputBox">
              <label for="username" class="sr-only">Username</label>
              <input
                type="text"
                id="username"
                class="form-control"
                placeholder="Username"
                v-model="user.username"
                required
                autofocus
              />
            </div>
            <div class="d-flex justify-content-center inputBox">
              <label for="password" class="sr-only">Password</label>
              <input
                type="password"
                id="password"
                class="form-control"
                placeholder="Password"
                v-model="user.password"
                required
              />
            </div>
            <router-link :to="{ name: 'register' }"
              >Need an account?</router-link
            >
            <button class="btn btn-success" type="submit">Sign in</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
.picBox {
  margin-bottom: 50px;
}

.form-box {
  height: 405px;
  border-radius: 15px;
  border: 2px gray solid;
  padding-left: 0%;
  padding-right: 0%;
}

.loginPic {
  height: 405px;
  width: 100%;
  border-radius: 15px;
}
.row {
  margin-top: 20%;
}

.inputBox {
  margin: 10px;
}

@media (max-width: 990px) {
  .form-box {
    background-image: url("https://images.unsplash.com/photo-1452587925148-ce544e77e70d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1934&q=80");
    background-size: 720px 500px;
    background-repeat: no-repeat;
  }

  .form-control {
    width: 80%;
  }

  .form-signin {
    width: 300px;
    height: 300px;
    background: rgba(250, 250, 250, 0.7);
    border-radius: 30px;
  }
  .picBox {
    display: none;
    order: 2;
  }
}
</style>
