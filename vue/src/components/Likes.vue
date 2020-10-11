<template>
  <div>
    <div v-if="liked==false">
      <button @click="flip" type="button" class="btn btn-dark">
        <span>
          <i class="far fa-heart"></i>
        </span>
      </button>
    </div>
    <div v-if="liked==true">
      <button @click="flip" type="button" class="btn btn-danger">
        <span>
          <i class="fas fa-heart"></i>
        </span>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "likes",
  props: {
    photo: Object,
  },
  data() {
    return {
      liked: false,
    };
  },
  methods: {
    flip() {
      this.liked = !this.liked;
    },
  },
};
</script>

<style scoped>
.btn {
  border-radius: 0px !important;
}
</style>