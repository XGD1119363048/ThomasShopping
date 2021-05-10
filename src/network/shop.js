import { request } from "./request";

export function addShop(userName, shopName) {
    return request({
        url: '/addShop',
        method: 'post',
        data: {
            userName: userName,
            shopName: shopName
        }
    })
}

export function updateShop(userName, shopName) {
    return request({
        url: '/updateShop',
        method: 'post',
        data: {
            userName: userName,
            shopName: shopName
        }
    })
}

export function getShopById(shopId) {
    return request({
        url: '/getShopById',
        params: {
            shopId: shopId
        }
    })
}

export function getAllShops() {
    return request({
        url: '/getAllShops'
    })
}

export function getShopByUserName(userName) {
    return request({
        url: '/getShopByUserName',
        params: {
            userName: userName
        }
    })
}

export function getShopByShopName(shopName) {
    return request({
        url: '/getShopByShopName',
        params: {
            shopName: shopName
        }
    })
}