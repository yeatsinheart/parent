/**获取场所*/
function getWhere() {
    let list = [];
    list.push({"img": "kefu.jpeg", "name": "客服", "type": "customer", "area": "cus", "id": 1});
    list.push({"img": "who.jpg", "name": "系统群", "type": "group", "area": "cus", "id": 2});
    for (let i = 10; i < 20; i++) {
        list.push({"img": "who.jpg", "name": "私聊" + i, "type": "friend", "area": "pri", "id": i});
    }
    return list;
}

/**关注场所消息*/
function listen() {
    let list = getWhere();
    let places = "";
    for (let i = 0; i < list.length; i++) {
        let place = list[i]['id'];
        if (list[i]['type'] !== "group") {
            place = Math.min(list[i]['id'], userId) + "@" + Math.max(list[i]['id'], userId);
        }
        places += place + ",";
    }
    places = places.substr(0, places.length - 1);
    let joinGroup = {
        "action": "joinimgroup",
        "where": places
    };
    return joinGroup;
}

var activePlace;

/**打开场所*/
function active(who, userId) {
    activePlace = who['id'];
    let msg = document.querySelector('#chat_msg');
    let place = who['id'];
    if (list[i]['type'] !== "group") {
        place = Math.min(who['id'], userId) + "@" + Math.max(who['id'], userId);
    }
    msg.innerHTML = '<div id="chat_msg_who" style="border-bottom: 1px solid rgba(0,0,0,0.2);">' +
        '               <div class="goback" onclick="backToContactor()"></div> <img style="padding-left: 28px;width: 74px;' +
        '            height: 46px;' +
        '            border-radius: 100%;' +
        '            vertical-align: middle;" src="' + who['img'] + '"><span style="padding-left: 5px">' + who['name'] + '</span>' +
        '        </div>';
    msg.innerHTML += '' +
        '<div class="historyarea" id="historyarea">' +
        '</div>';
    msg.innerHTML += '<div class="sendarea">' +
        '            <div class="input" contenteditable id="messageId" name="message" placeholder="输入消息"></div>' +
        '            <div>' +
        '                <button id="sendButton" onClick=javascript:SendMsg("' + place + '") style="height: 30px">Send</button>' +
        '            </div>' +
        '        </div>'
    msg.setAttribute("style", " display: -webkit-flex;display: flex;");
}

/**是否激活*/
function isActive(where) {
    if (activePlace && activePlace === where) {
        return true;
    } else return false;
}

/**新消息*/
function newMsg(data, userId) {
    let obj = eval('(' + data + ')')
    if (isActive(obj['where'])) {
        let msg = document.querySelector('#historyarea');
        msg.innerHTML += renderMsg(obj, userId);
        renderMsg(obj, userId);
    } else {
    }
}