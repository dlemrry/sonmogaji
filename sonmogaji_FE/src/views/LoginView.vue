<template>
  <div>
    <div v-if="!isLoggedIn">
      <h1 @click="login" v-if="isMetamaskSupported">지갑 연결하기</h1>
      <h1 v-else href="https://metamask.io/">메타마스크를 설치하세요!</h1>
    </div>
    <div v-else>
      {{ account }}
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
const userStore = "userStore";

export default {
  name: "LoginView",
  computed: {
    ...mapState(userStore, ["isLoggedIn", "account"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGGED_IN", "SET_ACCOUNT", "SET_IS_METAMASK_SUPPORTED"]),
    ...mapActions(userStore, ["connectWallet", "checkMetamaskSupported"]),
    login() {
      this.connectWallet().then(this.$router.push({ name: "home" }));
    },
  },
  data() {
    return {
      isMetamaskSupported: false,
    };
  },
  created() {
    this.isMetamaskSupported = typeof window.ethereum !== "undefined";
    this.SET_IS_METAMASK_SUPPORTED(this.isMetamaskSupported);
  },
  mounted() {
    this.console.log(this.isLoggedIn);
  },
};
</script>
<style></style>
