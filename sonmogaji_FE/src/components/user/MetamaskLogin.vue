<template>
    <div id="metamask-login" class="d-flex justify-content-center">
        <div class="d-inline text-center">
            <h1 class="display-4 fw-semibold my-5">지갑을 연결하세요</h1>
            <p class="h2 fw-light my-3">만약 지갑이 없다면, Metamask에서 지갑을 생성해보세요.</p>
            <img src="@/assets/icons/metamask.png" class="w-50"/>
            <div class="d-flex justify-content-center text-center">
                <b-button class="mx-2" @click="login" style="background-color: #223359; border: 0px; font-size: 24px;">지갑 연결하기</b-button>
                <b-button class="mx-2" style="background-color: #777777; border: 0px; font-size: 24px;">뒤로 가기</b-button>
            </div>
        </div>
    </div>
</template>
<script>
import { mapState, mapMutations, mapActions } from "vuex";

const userStore = "userStore";

export default{
    name: "MetamaskLogin",
    computed: {
    ...mapState(userStore, ["isLoggedIn", "account"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGGED_IN", "SET_ACCOUNT", "SET_IS_METAMASK_SUPPORTED"]),
    ...mapActions(userStore, ["connectWallet", "checkMetamaskSupported", "switchNetwork"]),
    async login() {
      this.SET_IS_LOGGED_IN(true);
      this.SET_ACCOUNT("0xasdfhjakjk23h4jkshjsdkfjshfjk");
      this.$router.push({name: "home"});
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
    console.log(this.isLoggedIn);
  },
};
</script>
<style scoped>
#metamask-login {
    width: 100vw;
    height: 100vh;
}
</style>
