<template>
  <div id="image-upload" class="d-flex justify-content-center">
    <div class="d-inline text-center">
      <h1 class="display-4 fw-semibold my-4">각서 이미지를 검증합니다.</h1>
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
          * 주의사항 : 화질 변경 시 트랜잭션 해시가 인식 불가능 할 수도 있습니다.
        </p>
        <p class="h3 fw-light mt-3">
          손모가지가 발급한 각서 원본 이미지를 업로드하세요!<br />
          블록체인 상에 존재하는 지 확인할 수 있습니다.
        </p>
      </div>
    </div>
    <b-modal id="modal" ref="modal" :title="modalData.title" @hidden="hideModal()">
      <div v-if="modalData.isSuccessed == true">
        <div>
          <img src="@/assets/icons/success.png" />
        </div>
        <div>
          해당 각서는 <b>블록체인</b>에 보관된, <br />
          꼭 지켜져야 할 <b>약속</b>이 맞습니다.
        </div>
        <div>
          <img :src="modalData.imageFile" alt="" />
        </div>
        <div>
          {{ modalData.createDate }}
          {{ modalData.hash }}
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
import { getMemoryImage } from "@/api/index.js";

const Web3 = require("web3");
const rpcURL = "https://j7a3081.p.ssafy.io";
const web3 = new Web3(rpcURL);
const userStore = "userStore";

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
    ...mapState(userStore, ["account"]),
  },
  data() {
    return {
      dropOptions: {
        url: "/api/transaction/verify", // 파일을 업로드할 서버 주소 url.
        maxFiles: 1, // 업로드 파일수
        acceptedFiles: ".jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF", // 이미지 파일 포맷만 허용
        addRemoveLinks: true,
        dictRemoveFile: "삭제",
      },
      modalData: {
        isSuccessed: true,
        imageFile: null,
        blockchain: "Hyperledger Besu",
        createDate: "",
        hash: "",
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
    async verifyResponse(response) {
      const txHash = response.xhr.response;
      // const txHash =        "0xd4a74f48cfd477605b38ed3f67ac58417714dac91c5bb34c243777d7d353e426";
      console.log(txHash);
      /* web3.js 조회 로직 구현*/

      // 01. 해시값으로 블록체인 네트워크에서 해당 트랜잭션을 가져온다.
      web3.eth
        .getTransaction(txHash)
        .then((data) => {
          // 02. 만약 온 데이터가 없다면, 해당 해시에 대한 트랜잭션이 없는 것으로, false 화면을 출력한다.
          if (data == null) {
            this.modalData.isSuccessed = false;
            this.showModal();
          }
          // 03. 만약 온 데이터가 있다면 파싱해서 modal에 들어갈 데이터를 채운다.
          else {
            const decodedData = web3.eth.abi.decodeParameters(["string"], data.input)[0];
            const parsedData = decodedData.split(" ");
            this.modalData.isSuccessed = true;
            // getMemoryImage(
            //   txHash,
            //   (response) => {
            //     console.log(response);
            //     this.modalData.imageFile = require(`${response.data.base64Str}`);
            //   },
            //   (error) => {
            //     console.log(error);
            //     this.modalData.imageFile = "";
            //   },
            // );
            this.modalData.imageFile = parsedData[3];
            this.modalData.createDate = parsedData[6];
            this.modalData.hash = txHash;
            this.showModal();
          }
        })
        // 04. 만약 에러가 발생했다면, 해당 해시에 대한 트랜잭션이 없는 것으로, false 화면을 출력한다.
        .catch((error) => {
          this.modalData.isSuccessed = false;
          this.showModal();
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
