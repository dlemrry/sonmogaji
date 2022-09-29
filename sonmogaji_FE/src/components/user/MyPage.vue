<template>
    <div id="my-page">
        <!--유저 정보-->
        <div>
            <img src="@/assets/icons/ethereum.png" alt="ether" class="my-5">
        </div>    

        <!--각서 목록-->
    </div>
</template>
<script>
import { mapState, mapMutations, mapActions } from "vuex";

const userStore = "userStore";

export default{
    name: "MyPage",
    computed: {
    ...mapState(userStore, ["isLoggedIn", "account"]),
  },
};
</script>
<style scoped>
    #my-page {
        width: 100vw;
        height: 100vh;
    }
</style>
