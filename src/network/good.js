import { request } from "./request"

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

/* 添加商品 */
export function addProduct(shopName, name, description, price, stock, categoryId, categoryChildId, imageAddress) {
  return request({
    url: '/addProduct',
    method: 'post',
    data: {
      shopName: shopName,
      name: name,
      description: description,
      price: price,
      stock: stock,
      categoryId: categoryId,
      categoryChildId: categoryChildId,
      imageAddress: imageAddress
    }
  })
}

/*更新商品*/
// export function addProduct(id, shopName, name, description, price, stock, categoryId, categoryChildId, imageAddress) {
//   return request({
//     url: '/addProduct',
//     method: 'post',
//     data: {
//       id: id,
//       shopName: shopName,
//       name: name,
//       description: description,
//       price: price,
//       stock: stock,
//       categoryId: categoryId,
//       categoryChildId: categoryChildId,
//       imageAddress: imageAddress
//     }
//   })
// }

export function getProductsByShopName(shopName) {
  return request({
    url: '/getProductsByShopName',
    params: {
      shopName: shopName
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

export function getProductByCount(count) {
  return request({
    url: '/getProductByCount',
    params: {
      count: count
    }
  })
}

export function getProductByCategoryChildId(categoryChildId) {
  return request({
    url: '/getProductByCategoryChildId',
    params: {
      categoryChildId: categoryChildId
    }
  })
}

export function getProductByCategoryId(categoryId) {
  return request({
    url: '/getProductByCategoryId',
    params: {
      categoryId: categoryId
    }
  })
}

export function deleteProductById(productId) {
  return request({
    url: '/deleteProductById',
    params: {
      productId: productId
    }
  })
}

