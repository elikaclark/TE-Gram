<template>
  <!-- https://www.w3schools.com/bootstrap4/tryit.asp?filename=trybs_navbar_collapse -->
  <div id="app">
    <div id="nav">
      <nav
        v-if="$store.state.token != ''"
        class="navbar d-flex navbar-expand-sm bg-dark navbar-dark fixed-top justify-content-between"
      >
        <!--Home button-->
        <div>
          <router-link v-bind:to="{ name: 'home' }" id="logo">
            <i class="fas fa-mug-hot"></i> TE Time
          </router-link>
        </div>

        <!-- new post -->
        <div>
          <router-link id="addButton" class="btn btn-success" v-bind:to="{ name: 'upload' }">
            <i class="far fa-plus-square"></i>
          </router-link>
        </div>
        <span class="d-flex">
          <!-- HAMBURGER -->

          <router-link :to="'/profile/' + $store.state.user.id">
            <button class="btn btn-dark">
              <i class="fas fa-user"></i>
            </button>
          </router-link>

          <router-link :to="{ name: 'explore'}">
            <button class="btn btn-dark">
              <i class="fas fa-compass"></i>
            </button>
          </router-link>

          <router-link v-bind:to="{ name: 'favorites' }">
            <button class="btn btn-dark">
              <i class="fas fa-star"></i>
            </button>
          </router-link>

          <router-link v-bind:to="'/settings/' + $store.state.user.id">
            <button class="btn btn-dark">
              <i class="fas fa-cog"></i>
            </button>
          </router-link>

          <router-link
            class="btn btn-danger"
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
          >
            <i class="fas fa-sign-out-alt"></i>
          </router-link>
        </span>
      </nav>
      <br />
      <br />
      <br />
    </div>
    <router-view />
  </div>
</template>

<style scoped>
nav {
  font-size: 1.5rem;
}
#addButton {
  margin-left: 110%;
}
#logo {
  color: white;
}
</style>