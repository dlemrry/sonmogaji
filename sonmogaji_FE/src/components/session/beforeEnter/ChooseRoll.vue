<template>
  <b-container>
    <b-row>
      <b-col>
        <b-card
          @click="chooseSignee()"
          title="참여자"
          :img-src="require('../../../assets/user.png')"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem"
          class="mb-2"
        >
          <b-card-text> 각서에 직접 이름을 올리고 서명합니다. </b-card-text>
        </b-card>
      </b-col>
      <b-col>
        <b-card
          @click="chooseObserver()"
          title="관전자"
          :img-src="require('../../../assets/usergroup.png')"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem"
          class="mb-2"
        >
          <b-card-text> 강 건너 불구경 하러 왔습니다. </b-card-text>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
import axios from "axios";
export default {
  name: "ChooseRoll",
  components: {},
  computed: {
    ...mapState(["roll"]),
    ...mapGetters(["getRoll", "getSenderNickName", "getRoomCode"]),
  },
  created() {},
  mounted() {
    console.log("ChooseRoll");
  },
  data() {
    return {};
  },
  methods: {
    ...mapActions(["enterRoll", "enterRoomCode"]),
    chooseSignee() {
      console.log(this.getRoomCode)
      console.log(this.getSenderNickName)
      axios
        .post("http://localhost:8080/api/room/isAvail", {
          roomCode: this.getRoomCode,
          senderNickName: this.getSenderNickName,
        })
        .then((response) => {
          console.log(response.data);
          this.enterRoll("signee");
          

          this.$router.push({ name: "session" });
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {});
    },
    chooseObserver() {
     axios
        .post("http://localhost:8080/api/room/isAvail", {
          roomCode: this.getRoomCode,
          senderNickName: this.getSenderNickName,
        })
        .then((response) => {
          console.log(response.data);
          this.enterRoll("observer");
          

          this.$router.push({ name: "session" });
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {});
    },
  },
};
</script>
