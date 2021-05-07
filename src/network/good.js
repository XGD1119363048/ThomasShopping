import {request} from "./request"

export function getHomeMultidata() {
  return request({
    url: ''
  })
}

export function getHomeGoods(count) {
  return request({
    url: '/getProductByCount',
    params: {
      count
    }
  })
}

export function getProductById(id) {
  return request({
    url: '/getProductById',
    params: {
      productId: id
    }
  })
}
