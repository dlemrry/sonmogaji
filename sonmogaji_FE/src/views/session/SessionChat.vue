<template>
  <b-container>
    <main id="app">
      <section ref="chatArea" class="chat-area">
        <p
          v-for="message in chatmessages"
          :key="message"
          class="message"
          :class="{
            'message-out': message.author === 'you',
            'message-in': message.author !== 'you',
          }"
        >
          {{ message.body }}
        </p>
      </section>

      <section class="chat-inputs">
        <form @submit.prevent="sendMessage()" id="person2-form">
          <b-row>
            <b-col cols="9"
              ><input
                v-model="chatinput"
                id="person2-input"
                type="text"
                placeholder="Type your message"
            /></b-col>
            <b-col cols="3"><button type="submit">Send</button></b-col>
          </b-row>
        </form>
      </section>
    </main>
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
  props: {
    roomId: String,
  },
  data() {
    return {
      chatinput: "",
    };
  },
  methods: {
    ...mapActions(["chat"]),
    sendMessage() {
      this.chat(this.chatinput);
    },
  },
};
</script>

<style>
.headline {
  text-align: center;
  font-weight: 100;
  color: white;
}
.chat-area {
  /*   border: 1px solid #ccc; */
  background: white;
  height: 50vh;
  padding: 1em;
  overflow: auto;
  max-width: 350px;
  margin: 0 auto 2em auto;
  box-shadow: 2px 2px 5px 2px rgba(0, 0, 0, 0.3);
}
.message {
  width: 45%;
  border-radius: 10px;
  padding: 0.5em;
  /*   margin-bottom: .5em; */
  font-size: 0.8em;
}
.message-out {
  background: #407fff;
  color: white;
  margin-left: 50%;
}
.message-in {
  background: #f1f0f0;
  color: black;
}
.chat-inputs {
  display: flex;
  justify-content: space-between;
}
#person2-input {
  padding: 0.5em;
}
</style>
