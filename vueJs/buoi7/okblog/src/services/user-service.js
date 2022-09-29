import axios from 'axios'
import authHeader from '../services/auth-hreader'

const API_URL = 'http://localhost:8080/api/'

class UserService {
  getPublicContent () {
    return axios.get(API_URL + 'list')
  }

  getUserBoard () {
    return axios.get(API_URL + 'list', { headers: authHeader() })
  }

  getModeratorBoard () {
    return axios.get(API_URL + 'blog/create', { headers: authHeader() })
  }

  getAdminBoard () {
    return axios.get(API_URL + 'blog/create', { headers: authHeader() })
  }
}

export default new UserService()
