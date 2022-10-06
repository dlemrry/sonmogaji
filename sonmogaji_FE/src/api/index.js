import axios from "axios";
const api = axios.create();

async function getMemoryImage(txHash, success, fail) {
  await api.get(`/api/transaction/${txHash}/img`).then(success).catch(fail);
  return;
}

export { getMemoryImage };
