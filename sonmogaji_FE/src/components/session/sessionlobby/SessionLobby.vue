<template>
  <b-container>
    <b-row>
      <b-col>
        <b-row></b-row>
        <b-row></b-row>
      </b-col>
      <b-col>
        <b-button variant="success" @click="start()">시작</b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "SessionLobby",
  components: {},
  created() {
    console.log("sessionLobby");
  },
  data() {
    return {};
  },
  methods: {
    start(){
      console.log('시작')
      this.$router.push({name:"sessionMain1"})
    }
  },
};
</script>
