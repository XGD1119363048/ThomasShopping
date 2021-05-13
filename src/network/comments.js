import { request } from "./request";

export function addComment(content, nickname, productId) {
    return request({
        url: '/addComment',
        method: 'post',
        data: {
            content: content,
            nickname: nickname,
            productId: productId
        }
    })
}

export function replyComment(commentId, reply) {
    return request({
        url: '/replyComment',
        method: 'post',
        data: {
            commentId: commentId,
            reply: reply
        }
    })
}


<<<<<<< HEAD
/*获取全部评论*/
export function getComments() {
    return request({
        url: '/getComments'
    })
}


=======
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
export function deleteCommentById(commentId) {
    return request({
        url: '/deleteCommentById',
        params: {
            commentId: commentId
        }
    })
}


<<<<<<< HEAD
/*获取某商品全部评论 */
export function getComments(productId) {
    return request({
        url: '/getComments',
=======

/*获取全部评论*/
export function getComments() {
    return request({
        url: '/getComments'
    })
}


/*获取某商品全部评论 */
export function getCommentsByProduct(productId) {
    return request({
        url: '/getCommentsByProduct',
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
        params: {
            productId: productId
        }
    })
}

