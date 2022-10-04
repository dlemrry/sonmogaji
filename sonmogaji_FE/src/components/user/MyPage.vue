<template>
  <div id="my-page">
    <!--유저 정보-->
    <div class="d-flex px-5 pt-5">
      <div id="user-info" class="bg-cyan shadow-lg rounded w-50 p-4">
        <p class="h2 text-white fw-semibold mb-3">나의 손모가지</p>
        <div>
          <div class="d-flex align-items-center mb-2">
            <img src="@/assets/icons/ether-round.png" alt="ether" class="small-icon" />
            <p class="h5 text-white mx-2 fw-light">ETH (Hyperledger Besu)</p>
          </div>
          <div class="d-flex align-items-center mb-2">
            <img src="@/assets/icons/metamask-round.png" alt="ether" class="small-icon" />
            <a class="h5 text-white mx-2 fw-light">{{ account }}</a>
          </div>
        </div>
      </div>
    </div>

    <!--각서 목록-->
    <div class="row p-5">
      <b-card
        v-for="item in myList"
        :key="item.hash"
        :title="item.title"
        img-src="https://picsum.photos/400/300"
        img-alt="추억사진"
        img-top
        class="mx-2 my-4 col-6 col-md-4 col-lg-3"
      >
        <b-card-text>
          <ul>
            <li>{{ item.timestamp }}</li>
            <li>{{ item.hash }}</li>
          </ul>
        </b-card-text>
      </b-card>
    </div>
  </div>
</template>
<script>
import { mapState, mapMutations, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "MyPage",
  computed: {
    ...mapState(userStore, ["isLoggedIn", "account"]),
  },
  data() {
    return {
      myList: [
        {
          title: "각서 제목1",
          timestamp: "2022.10.03",
          hash: "hash1",
        },
        {
          title: "각서 제목2",
          timestamp: "2022.10.03",
          hash: "hash2",
        },
        {
          title: "각서 제목3",
          timestamp: "2022.10.03",
          hash: "hash3",
        },
        {
          title: "각서 제목4",
          timestamp: "2022.10.03",
          hash: "hash4",
        },
        {
          title: "각서 제목5",
          timestamp: "2022.10.03",
          hash: "hash5",
        },
        {
          title: "각서 제목6",
          timestamp: "2022.10.03",
          hash: "hash6",
        },
      ],
    };
  },
};
</script>
<style scoped>
#my-page {
  min-width: 100vw;
  min-height: 100vh;
}
.small-icon {
  width: 30px;
  height: 30px;
}
</style>
