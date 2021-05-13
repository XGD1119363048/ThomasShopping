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
<<<<<<< HEAD
=======
        method: 'get',
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
        params: {
            orderId: orderId
        }
    })
}


<<<<<<< HEAD
export function deleteProductInOrder(orderId, productId, count) {
=======
export function deleteProductInOrder(userName, productId, count) {
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
    return request({
        url: '/deleteProductInOrder',
        method: 'post',
        data: {
<<<<<<< HEAD
            orderId: orderId,
=======
            userName: userName,
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
            productId: productId,
            count: count
        }
    })
}

<<<<<<< HEAD
export function addProductInOrder(orderId, productId, count) {
=======
export function addProductInOrder(userName, productId, count) {
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
    return request({
        url: '/addProductInOrder',
        method: 'post',
        data: {
<<<<<<< HEAD
            orderId: orderId,
=======
            userName: userName,
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
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
<<<<<<< HEAD
/*支付订单*/
export function payOrder(useCoin, orderId) {
    return request({
        url: '/payOrder',
        params: {
            useCoin: useCoin,
            orderId: orderId
=======

/*支付订单*/
export function payOrder(useCoin, userName) {
    return request({
        url: '/payOrder',
        method: 'post',
        data: {
            useCoin: useCoin,
            userName: userName
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
        }
    })
}

/*取消订单*/
<<<<<<< HEAD
export function payOrder(orderId) {
    return request({
        url: '/payOrder',
=======
export function cancelOrder(orderId) {
    return request({
        url: '/cancelOrder',
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
        params: {
            orderId: orderId
        }
    })
}
<<<<<<< HEAD

=======
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
