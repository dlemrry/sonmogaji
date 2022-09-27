<template>
  <div id="app">
    <HeaderNavBar id="header" />
    <router-view id="section" />
  </div>
</template>

<script>
import HeaderNavBar from "@/components/layout/HeaderNavBar.vue";
export default {
  name: "App",
  components: {
    HeaderNavBar,
  },
};
</script>

<style>
@import "@/assets/css/main.css";
</style>
