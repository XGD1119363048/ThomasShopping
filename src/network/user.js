import {request} from "./request";

export function checkUser(username, password) {
  return request({
    url: '/checkUser',
    method: 'post',
    data: {
      userName: username,
      password: password
    }
  })
}
