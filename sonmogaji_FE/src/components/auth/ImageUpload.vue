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
        @vdropzone-upload-progress="uploadProgress"
        @vdropzone-success="uploadSuccess"
        @vdropzone-canceled="uploadFailed"
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
import Dropzone from "vue2-dropzone";
import "vue2-dropzone/dist/vue2Dropzone.min.css";

export default {
  name: "ImageUpload",
  components: {
    Dropzone,
  },
  data() {
    return {
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
    uploadProgress(file, progress, bytesSent) {
      console.log("파일 업로드 진행...", progress);
      console.log(file);
      console.log(bytesSent);
    },
    uploadSuccess(file, response) {
      this.isSuccessed = true;
      this.showModal();
      console.log(response);
    },
    uploadFailed(file, response) {
      this.isSuccessed = false;
      this.hideModal();
      console.log(response);
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
