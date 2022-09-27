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
export default {
  name: "ChooseRoll",
  components: {},
  computed:{
    ...mapState(["roll"]),
    ...mapGetters(["getRoll"]),
  },
  created() {},
  mounted() {
    console.log("ChooseRoll");
  },
  data() {
    return {
     
    };
  },
  methods: {
    ...mapActions(["enterRoll"]),
    chooseSignee(){
        this.enterRoll("signee");
        this.$router.push({name:"session"})
    },
    chooseObserver(){
        this.enterRoll("observer")
        this.$router.push({name:"session"})
    }
  },
};
</script>
