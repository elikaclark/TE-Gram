<template>
  <div class="container main">
    <div class="row">
      <div class="col-sm-12 col-lg-6 picBox">
        <img
          class="loginPic"
          src="https://i.pinimg.com/originals/0a/f4/ce/0af4ce7d0f95c36448c86b67589342fe.gif"
          alt=""
        />
      </div>
      <div
        class="form-box col-sm-12 col-lg-6 d-flex justify-content-center align-items-center"
      >
        <div id="register" class="text-center">
          <form class="form-register" @submit.prevent="register">
            <h1>☕ TE Gram</h1>
            <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
            <div
              class="alert alert-danger"
              role="alert"
              v-if="registrationErrors"
            >
              {{ registrationErrorMsg }}
            </div>
            <label for="username" class="sr-only">Username</label>
            <div class="inputBox">
              <div class="d-flex justify-content-center inputBox">
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
              <label for="password" class="sr-only">Password</label>
              <div class="d-flex justify-content-center inputBox">
              <input
                type="password"
                id="password"
                class="form-control"
                placeholder="Password"
                v-model="user.password"
                required
              />
              </div>
              <div class="d-flex justify-content-center inputBox">
              <input
                type="password"
                id="confirmPassword"
                class="form-control"
                placeholder="Confirm Password"
                v-model="user.confirmPassword"
                required
              />
              </div>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">
              Create Account
            </button>
            <router-link :to="{ name: 'login' }">Already have an account?</router-link>
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
  height: 700px;
  border-radius: 15px;
  border: 2px gray solid;
  padding-left: 0%;
  padding-right: 0%;
}

.loginPic {
  height: 700px;
  width: 100%;
  border-radius: 15px;
}
.row {
  margin-top: 20%;
}

.inputBox {
  margin: 10px 0;
}

@media (max-width: 990px) {
  .form-box {
    background-image: url("https://i.pinimg.com/originals/0a/f4/ce/0af4ce7d0f95c36448c86b67589342fe.gif");
    background-size: cover;
    background-repeat: no-repeat;
  }

  .form-control {
    width: 90%;
  }

  .form-register {
    background-color: rgba(250, 250, 250, 0.5);
    border-radius: 20px;
    padding: 30px;
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

@media (max-width: 575px) {
  .form-box {
    position: absolute;
    top: -10%;
    height: 110%;
    background-size: cover;
    border-radius: 0px;
  }
}
</style>
