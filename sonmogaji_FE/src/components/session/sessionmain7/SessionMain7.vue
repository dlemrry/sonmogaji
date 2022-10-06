<template>
  <b-container>
    

    <b-row>
      <b-col>
        <img  id="memorandum" />

      </b-col>
    </b-row>
    <b-row></b-row>
    

    <b-row>
    </b-row>
    
  </b-container>
</template>

<script>
import { mapActions, mapMutations, mapState } from 'vuex';
export default {
  name: "SessionMain7",
  components: {},
  created() {
    
    console.log("sessionMain7");
  },
  computed: {
    ...mapState(["memorandumPreview"]),
    ...mapMutations([""]),
    
  },
  data() {
    return {
    };
  },
  methods: {
    ...mapActions([]),
    toMain7() {
      this.roomNext(7);
    },
    vote6() {
      this.roomVote(6);
    },
    
  },
};
</script>

<style>
.activecircledot {
  margin: auto;
  width: 20px;
  height: 20px;
  background: rgb(0, 191, 216);
  border-radius: 50%;
}
.circledot {
  margin: auto;
  width: 20px;
  height: 20px;
  background: rgb(172, 172, 172);
  border-radius: 50%;
}

.activebar {
  margin: auto;
  width: 100px;
  height: 5px;
  background: rgb(0, 191, 216);
}
.bar {
  margin: auto;
  width: 100px;
  height: 5px;
  background: rgb(172, 172, 172);
}
</style>
