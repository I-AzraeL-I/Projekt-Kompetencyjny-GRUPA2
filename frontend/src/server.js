const axios = require('axios');
const SERVER_URL = 'http://localhost:8080';
const instance = axios.create({
  baseURL: SERVER_URL,
  timeout: 1000,
})
export default instance;
