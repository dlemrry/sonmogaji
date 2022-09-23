<template>
  <b-container>
    <b-row>
      <b-col><router-view> </router-view></b-col> <b-col> <Chatt /></b-col
    ></b-row>
  </b-container>
</template>

<script>
import SessionMain1 from "../../components/session/sessionmain1/SessionMain1";
import Chatt from "./SessionChat.vue";

import { mapState, mapActions } from "vuex";
export default {
  name: "SessionMain",
  components: {
    Chatt,
  },
  created() {
    console.log("sessionMain");
    this.stompConnect();
  },
  methods: {
    ...mapActions(["stompConnect"]),
  },
};
</script>
