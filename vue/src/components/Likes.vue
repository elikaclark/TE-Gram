<template>
  <div>
    <button class="btn btn-danger">
      <span>
        <i class="far fa-heart"></i>
        {{photo.likes}}
      </span>
    </button>
  </div>
</template>

<script>
export default {
  name: "likes",
  props: {
    photo: Object
  }
};
</script>

<style>
</style>