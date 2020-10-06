<template>
  <!-- https://www.w3schools.com/bootstrap4/tryit.asp?filename=trybs_navbar_collapse -->
  <div id="app">
    <div id="nav">
      <nav
        v-if="$store.state.token != ''"
        class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-end fixed-top"
      >
        <router-link v-bind:to="{ name: 'home' }">☕TE GRAM</router-link>
        <button class="btn btn-success ml-auto mr-1">New Post</button>
        <router-link
          class="btn btn-danger ml-auto mr-1"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div
          class="collapse navbar-collapse flex-grow-0"
          id="navbarSupportedContent"
        >
          <ul class="navbar-nav text-right">
            <li class="nav-item active">
              <router-link v-bind:to="{ name: '#' }">Profile</router-link>
            </li>
            <li class="nav-item active">
              <router-link v-bind:to="{ name: '#' }">Favorites</router-link>
            </li>
          </ul>
        </div>
      </nav>
      <br>
      <br>
      <br>
    </div>

    <router-view />
  </div>
</template>
