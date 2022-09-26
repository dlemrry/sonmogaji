<template>
  <b-container>
    <b-row>
      <b-col cols="9"><router-view> </router-view></b-col>
      <b-col cols="3"> <Chatt :roomId="roomId" /></b-col
    ></b-row>
  </b-container>
</template>

<script>
import SessionMain1 from "../../components/session/sessionmain1/SessionMain1";
import Chatt from "./SessionChat.vue";

import { mapState, mapActions } from "vuex";
import { map } from 'sockjs-client/lib/transport-list';
export default {
  name: "SessionMain",
  data() {
    return {
      ...mapState(["roomId","nickname"]),
    };
  },
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
