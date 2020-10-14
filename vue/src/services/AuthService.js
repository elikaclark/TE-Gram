import axios from 'axios';

const http = axios.create({
  baseURL: "http://tegrambackendservice-env.eba-fudtugjs.us-east-2.elasticbeanstalk.com"
});

export default {
  login(user) {
    return http.post('/login', user)
  },

  register(user) {
    return http.post('/register', user)
  },
}
