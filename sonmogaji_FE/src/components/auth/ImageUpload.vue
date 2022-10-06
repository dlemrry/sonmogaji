<template>
  <div id="image-upload" class="d-flex justify-content-center">
    <div class="d-inline text-center">
      <h1 class="display-4 fw-semibold my-4">각서 이미지를 검증합니다.</h1>
      <button @click="sendTransaction">누럴용</button>
      <Dropzone
        id="dropzone"
        ref="dropzone"
        class="w-50 mx-auto rounded"
        :options="dropOptions"
        :useCustomSlot="true"
        style="border: 0px; background-color: transparent"
        @vdropzone-complete="verifyResponse"
      >
        <img src="@/assets/icons/image-upload.png" class="w-100" />
      </Dropzone>
      <div>
        <p class="h4 my-3" style="color: #f16b51">
          * 주의사항 : 화질 변경 시 트랜잭션 해시가 인식 불가능 할 수도
          있습니다.
        </p>
        <p class="h3 fw-light mt-3">
          손모가지가 발급한 각서 원본 이미지를 업로드하세요!<br />
          블록체인 상에 존재하는 지 확인할 수 있습니다.
        </p>
      </div>
    </div>
    <b-modal
      id="modal"
      ref="modal"
      :title="modalData.title"
      @hidden="hideModal()"
    >
      <div v-if="modalData.isSuccessed == true">
        <div>
          <img src="@/assets/icons/success.png" />
        </div>
      </div>
      <div v-else>
        <div>
          <img src="@/assets/icons/fail.png" />
        </div>
      </div>
    </b-modal>
  </div>
</template>
<script>
import { mapState } from "vuex";
import Dropzone from "vue2-dropzone";
import "vue2-dropzone/dist/vue2Dropzone.min.css";

const Web3 = require("web3");
const rpcURL = "https://j7a3081.p.ssafy.io";
const web3 = new Web3(rpcURL);

const fake = {
  txAddress: "sdfsdhjflk",
  txTitle: "소고기 각서",
  txContent: "취직한 사람 소고기 사세요",
  imageTitle: "추억사진",
  imageUrl: "s3.image",
  imageIsSecret: "false",
  txIsSecret: "false",
  txCreateDate: "null",
  txExpDate: "null",
  txNftUrl: "null",
  signees: "abc",
};

export default {
  name: "ImageUpload",
  components: {
    Dropzone,
  },
  computed: {
    ...mapState("userStore", ["account"]),
  },
  data() {
    return {
      imageFile: null,
      dropOptions: {
        url: "/api/transaction/verify", // 파일을 업로드할 서버 주소 url.
        maxFiles: 1, // 업로드 파일수
        acceptedFiles: ".jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF", // 이미지 파일 포맷만 허용
      },
      modalData: {
        isSuccessed: true,
        hash: "",
        title: "제목",
      },
    };
  },
  methods: {
    showModal(hash) {
      // hash 값으로 모달에 띄울 데이터를 받아온다.
      this.$bvModal.show("modal");
      this.modalData.hash = hash;
      this.modalData.title = "^___^";
    },
    hideModal() {
      // this.rotateList();
    },
    sendTransaction() {
      // /* */
      const types = ["string", "string", "string"];
      const values = ["Hello", "World", "Web3"];
      const abi = web3.eth.abi.encodeParameters(types, values);
      // console.log(abi);
      // console.log(web3.eth.abi.decodeParameters(types, abi));
      // const memorandumTypes = new Array(11).fill("string");
      // const memorandumValues = Object.keys(fake).map(function (key) { return fake[key]; });
      // const abi = web3.eth.abi.encodeParameters(memorandumTypes, memorandumValues);
      const txParams = {
        nonce: "0x00", // ignored by MetaMask
        gasPrice: "0x00", // customizable by user during MetaMask confirmation.
        gas: "0x00", // customizable by user during MetaMask confirmation.
        to: this.account, // Required except during contract publications.
        from: this.account, // must match user's active address.
        value: "0x00", // Only required to send ether to the recipient from the initiating external account.
        data: abi, // Optional, but used for defining smart contract creation and interaction.
        chainId: "0x569", // Used to prevent transaction reuse across blockchains. Auto-filled by MetaMask.
      };

      // web3.eth.sendRawTransaction(transactionParameters).then(console.log);
      window.ethereum
        .request({
          method: "eth_sendTransaction",
          params: [txParams],
        })
        .then(console.log);
    },
    async verifyResponse(response) {
      //const txHash = response.xhr.response;
      const txHash =
        "0xd4a74f48cfd477605b38ed3f67ac58417714dac91c5bb34c243777d7d353e426";
      console.log(txHash);
      /* web3.js 조회 로직 구현*/

      web3.eth.getTransaction(txHash).then((data) => {
        if (data == null) {
          this.isSuccessed = false;
          this.showModal();
        } else {
          console.log(data);
          // 00. 트랜잭션 json을 txValues로 값만 추출한다.
          this.showModal();
        }
      });
    },
  },
};
</script>
<style scoped>
#image-upload {
  width: 100vw;
  height: 100vh;
}
</style>
