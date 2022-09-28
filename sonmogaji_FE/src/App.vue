<template>
  <div id="app">
    <HeaderNavBar id="header" />
    <RouterView id="section" />
    <FooterBar></FooterBar>
  </div>
</template>

<script>
import HeaderNavBar from "@/components/layout/HeaderNavBar.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
export default {
  name: "App",
  components: {
    HeaderNavBar,
    FooterBar,
  },
};
</script>

<style>
@import "@/assets/css/main.css";
</style>
