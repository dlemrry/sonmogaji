<template>
  <b-container>
    <router-view> </router-view>
    <Chatt />
  </b-container>
</template>

<script>
import SessionMain1 from "../../components/session/sessionmain1/SessionMain1";
import Chatt from "./SessionChat.vue";
export default {
  name: "SessionMain",
  components: {
    Chatt,
  },
  created() {
    console.log('sessionMain')
  },
  
};
</script>
