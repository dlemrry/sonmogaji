const userStore = {
  namespaced: true,
  state: {
    isLoggedIn: false,
    isMetamaskSupported: false,
    account: null,
  },
  getters: {
    getIsLoggedin: function (state) {
      return state.isLoggedIn;
    },
    getAccount: function (state) {
      return state.account;
    },
  },
  mutations: {
    SET_IS_LOGGED_IN: (state, isLoggedIn) => {
      state.isLoggedIn = isLoggedIn;
    },
    SET_ACCOUNT: (state, account) => {
      state.isLoggedIn = true;
      state.account = account;
    },
    SET_IS_METAMASK_SUPPORTED: (state, isMetamaskSupported) => {
      state.isMetamaskSupported = isMetamaskSupported;
    },
  },
  actions: {
    async checkMetamaskSupported() {
      this.isMetamaskSupported = typeof window.ethereum !== "undefined";
    },
    async connectWallet() {
      const accounts = await window.ethereum.request({ method: "eth_requestAccounts" });
      this.address = accounts[0];
      this.isLoggedIn = true;
      console.log(this.isLoggedIn);
      this.switchNetwork();
    },
    async switchNetwork() {
      try {
        await window.ethereum.request({
          method: "wallet_switchEthereumChain",
          params: [{ chainId: "0x539" }],
        });
      } catch (error) {
        if (error.code === 4902) {
          try {
            const result = await window.ethereum.request({
              method: "wallet_addEthereumChain",
              params: [
                {
                  chainId: "0x539",
                  chainName: "Ganache",
                  nativeCurrency: {
                    name: "ETH",
                    symbol: "eth",
                    decimals: 18,
                  },
                  rpcUrls: ["https://localhost:8545/"],
                },
              ],
            });
          } catch (addError) {
            console.error("addError : " + addError);
          }
        }
        console.error("error : " + error);
      }
    },
  },
};

export default userStore;
