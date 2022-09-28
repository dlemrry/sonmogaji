<template>
  <b-navbar class="px-5">
    <b-navbar-brand href="#">
      <img src="@/assets/logos/logo.png" width="200px" alt="sonmogaji" />
    </b-navbar-brand>
    <b-navbar-nav class="w-100"></b-navbar-nav>
    <b-navbar-nav class="ml-auto">
      <b-nav-item-dropdown v-if="isLoggedIn" dropleft>
        <b-icon icon="person-circle" font-scale="2"></b-icon>
        <b-dropdown-item href="#"> <b-nav-item>로그아웃</b-nav-item></b-dropdown-item>
        <b-dropdown-item href="#">마이페이지</b-dropdown-item>
      </b-nav-item-dropdown>
      <b-nav-item-dropdown v-else dropleft>
        <template #button-content>
          <b-icon icon="list" font-scale="2"></b-icon>
        </template>
        <b-dropdown-item href="#">로그인</b-dropdown-item>
      </b-nav-item-dropdown>
    </b-navbar-nav>
  </b-navbar>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "HeaderNavBar",
  computed: {
    ...mapState(userStore, ["isLoggedIn", "account", "isMetamaskSupported"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGGED_IN", "SET_ACCOUNT"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGGED_IN(false);
      this.SET_ACCOUNT(null);
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>
