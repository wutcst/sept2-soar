import axios from 'axios'

const Axios = axios.create({
  baseURL: '/mock',
  timeout: 5000
})

// 请求拦截器
Axios.interceptors.request.use(function (config) {
  return config
}, function (error) {
  return Promise.reject(error)
})

// 响应拦截器
Axios.interceptors.response.use(function (res) {
  return res.data
}, function (error) {
  return Promise.reject(error)
})

export default ({ url, method = 'GET', params = {}, data = {}, headers = {} }) => {
  return Axios({
    url,
    method,
    params,
    data,
    headers
  })
}
