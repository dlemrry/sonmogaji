<template>
  <b-container>
    <div class="message-form ld-over">
      <small class="text-muted">@{{  }}</small>
      <b-form @submit.prevent="onSubmit" class="ld-over" v-bind:class="{}">
        <div class="ld ld-ring ld-spin"></div>
        <b-alert variant="danger" :show="hasError">{{  }} </b-alert>
        <b-form-group>
          <b-form-input
            id="message-input"
            type="text"
            v-model="message"
            placeholder="Enter Message"
            autocomplete="off"
            required
          >
          </b-form-input>
        </b-form-group>
        <div class="clearfix">
          <b-button type="submit" variant="primary" class="float-right"> Send </b-button>
        </div>
      </b-form>
    </div>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "SessionChat",
  components: {},
  computed: { ...mapState(["chatmessages", "stomp"]) },
  created() {},
  mounted() {
    console.log("sessionChat");
  },
  data() {
    return {};
  },
  methods: {
    ...mapActions(["chat"]),
  },
};
</script>
