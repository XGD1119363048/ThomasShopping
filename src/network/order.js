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
        params: {
            orderId: orderId
        }
    })
}


export function deleteProductInOrder(orderId, productId, count) {
    return request({
        url: '/deleteProductInOrder',
        method: 'post',
        data: {
            orderId: orderId,
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
export function payOrder(useCoin, orderId) {
    return request({
        url: '/payOrder',
        params: {
            useCoin: useCoin,
            orderId: orderId
        }
    })
}

/*取消订单*/
export function payOrder(orderId) {
    return request({
        url: '/payOrder',
        params: {
            orderId: orderId
        }
    })
}

