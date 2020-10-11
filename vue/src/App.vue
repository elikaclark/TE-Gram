<template>
  <!-- https://www.w3schools.com/bootstrap4/tryit.asp?filename=trybs_navbar_collapse -->
  <div id="app">
    <div id="nav">
      <nav
        v-if="$store.state.token != ''"
        class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top justify-content-between"
      >
        <!--Home button-->
        <div>
          <router-link v-bind:to="{ name: 'home' }">
            <i class="fas fa-mug-hot"></i> TE GRAM
          </router-link>
        </div>

        <!-- new post -->
        <div>
          <router-link class="btn btn-success" v-bind:to="{ name: 'upload' }">
            <i class="far fa-plus-square"></i>
          </router-link>
        </div>

        <!-- logout -->
        <span class="d-flex">
          <button
            class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse flex-grow-0" id="navbarSupportedContent">
            <ul class="navbar-nav text-right">
              <li class="nav-item active">
                <router-link :to="'/profile/' + $store.state.user.id">
                  <button class="btn btn-dark">
                    <i class="far fa-user"></i>
                  </button>
                </router-link>
              </li>
              <li class="nav-item active">
                <router-link v-bind:to="{ name: 'favorites' }">
                  <button class="btn btn-dark">
                    <i class="fas fa-star"></i>
                  </button>
                </router-link>
              </li>
            </ul>
          </div>
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