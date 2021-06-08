import { request } from "./request";


export function addUser(userName, password, address, phoneNumber, gender, age, birthday, email, status, coin, balance) {
  return request({
    url: '/addUser',
    method: 'post',
    data: {
      userName: userName,
      password: password,
      address: address,
      phoneNumber: phoneNumber,
      gender: gender,
      age: age,
      birthday: birthday,
      email: email,
      status: status,
      coin: coin,
      balance: balance
    }
  })
}

export function updateUser(userName, password, address, phoneNumber, gender, age, birthday, email, status, coin) {
  return request({
    url: '/updateUser',
    method: 'post',
    data: {
      userName: userName,
      password: password,
      address: address,
      phoneNumber: phoneNumber,
      gender: gender,
      age: age,
      birthday: birthday,
      email: email,
      status: status,
      coin: coin
    }
  })
}

export function queryUser(userName) {
  return request({
    url: '/queryUser',
    params: {
      userName: userName
    }
  })
}

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

export function addBalance(username, balance) {
  return request({
    url: '/addBalance',
    method: 'post',
    data: {
      userName: username,
      balance: balance
    }
  })
}








