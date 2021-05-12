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


export function deleteCommentById(commentId) {
    return request({
        url: '/deleteCommentById',
        params: {
            commentId: commentId
        }
    })
}



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
        params: {
            productId: productId
        }
    })
}

