<template>
  <div class="container">
    <header>
      <div class="row justify-content-center align-items-center">
        <div class="col-sm-3 col-md-3 d-flex justify-content-center align-items-center">
          <img
            class="userPic"
            src="https://images.unsplash.com/photo-1534278931827-8a259344abe7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80"
            alt=""
          />
          <span><b>UserName</b></span>
        </div>
        <div class="col-sm-3 col-md-3 d-flex text-center">
          <span>POSTS:#</span>
        </div>
        <div class="col-sm-3 col-md-3 d-flex text-center">
          <span>Followers:#</span>
        </div>
        <div class="col-sm-3 col-md-3 d-flex text-center">
          <span>Following:#</span>
        </div>
      </div>
    </header>
    <div class="row posts">
      <div class="col-sm-12 col-md-4 post-box">
        <div class="postUser">🧍 Username</div>
        <div>
          <img
            class="postImg"
            src="https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
            alt=""
          />
        </div>
        <div>
          <span class="postIcons">♡</span>
          <span class="postIcons">💬</span>
          <span class="postIcons">✈</span>
        </div>
      </div>
      <div class="col-sm-12 col-md-4 post-box">
        <div class="postUser">🧍 Username</div>
        <div>
          <img
            class="postImg"
            src="https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
            alt=""
          />
        </div>
        <div>
          <span class="postIcons">♡</span>
          <span class="postIcons">💬</span>
          <span class="postIcons">✈</span>
        </div>
      </div>
      <div class="col-sm-12 col-md-4 post-box">
        <div class="postUser">🧍 Username</div>
        <div>
          <img
            class="postImg"
            src="https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
            alt=""
          />
        </div>
        <div>
          <span class="postIcons">♡</span>
          <span class="postIcons">💬</span>
          <span class="postIcons">✈</span>
        </div>
      </div>
      <div class="col-sm-12 col-md-4 post-box">
        <div class="postUser">🧍 Username</div>
        <div>
          <img
            class="postImg"
            src="https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
            alt=""
          />
        </div>
        <div>
          <span class="postIcons">♡</span>
          <span class="postIcons">💬</span>
          <span class="postIcons">✈</span>
        </div>
      </div>
      <div class="col-sm-12 col-md-4 post-box">
        <div class="postUser">🧍 Username</div>
        <div>
          <img
            class="postImg"
            src="https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
            alt=""
          />
        </div>
        <div>
          <span class="postIcons">♡</span>
          <span class="postIcons">💬</span>
          <span class="postIcons">✈</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "profile",
};
</script>

<style>
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
  height: auto;
}
.postIcons {
  font-size: 2rem;
}
.userPic {
  width: 100%;
  height: auto;
  border-radius: 100%;
}
</style>