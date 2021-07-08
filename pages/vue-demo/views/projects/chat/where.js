/**获取场所*/
function getWhere(){
    let list = [];
    for (let i = 0; i < 1; i++) {
        let who = {"img": "kefu.jpeg", "name": "客服","type":"customer", "area":"cus","id": i};
        list.push(who);
    }
    for (let i = 0; i < 1; i++) {
        let who = {"img": "who.jpg", "name": "群组","type":"group", "area":"sys", "id": i};
        list.push(who);
    }
    for (let i = 0; i < 1; i++) {
        let who = {"img": "who.jpg", "name": "私聊","type":"friend", "area":"pri", "id": i};
        list.push(who);
    }
    return list;
}

/**是否激活*/
function isActive(){}
/**新消息*/
function newMsg(){}