<template>
  <div class="welcome-message">
    <img src="~@/static/icon.png" alt="icon" />
    <h2>欢迎使用V-IM系统</h2>
    <Search class="search-box" @showChat="showChat"></Search>
  </div>
</template>
<script>
import Search from "./search.vue";

export default {
  name: "welcome",
  components: {
    Search
  },
  methods: {
    showChat: function() {
      console.log("搜索");
    }
  }
};
</script>

<style scoped lang="scss">
.welcome-message {
  display: table-cell !important;
  height: 100%;
  text-align: center;
  vertical-align: middle;
  & > img {
    width: 12rem;
    margin-top: 10rem;
  }
}
</style>
