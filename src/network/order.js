import { request } from "./request"

export function addOrder(userName, productId, quatity, status, type) {
    return request({
        url: '/addOrder',
        method: 'post',
        data: {
            userName: userName,
            productId: productId,
            quatity: quatity,
            status: status,
            type: type
        }
    })
}

export function getOrderById(orderId) {
    return request({
        url: '/getOrderById',
        method: 'get',
        params: {
            orderId: orderId
        }
    })
}


export function deleteProductInOrder(userName, productId, count) {
    return request({
        url: '/deleteProductInOrder',
        method: 'post',
        data: {
            userName: userName,
            productId: productId,
            count: count
        }
    })
}

export function addProductInOrder(userName, productId, count) {
    return request({
        url: '/addProductInOrder',
        method: 'post',
        data: {
            userName: userName,
            productId: productId,
            count: count
        }
    })
}

export function getOrderByUser(userName) {
    return request({
        url: '/getOrderByUser',
        params: {
            userName: userName
        }
    })
}

export function getOrderByShop(shopId) {
    return request({
        url: '/getOrderByShop',
        params: {
            shopId: shopId
        }
    })
}

/*支付订单*/
export function payOrder(useCoin, userName) {
    return request({
        url: '/payOrder',
        method: 'post',
        data: {
            useCoin: useCoin,
            userName: userName
        }
    })
}

/*取消订单*/
export function cancelOrder(orderId) {
    return request({
        url: '/cancelOrder',
        method: 'post',
        data: {
            orderId: orderId
        }
    })
}
