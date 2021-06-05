/**
 * @function windowLoadInit - 劫持 window.onload 的赋值行为，防止覆盖
 * @desc 函数调用前产生的覆盖不可逆转
 * @throw {Any} 所有回调执行完毕之后，会抛出 catch 到的第一个错误
 *   错误将被异步抛出，避免影响初始化函数的继续执行
 * @return {Function}
 */
function addLoadEvent(func) {
    var oldonload = window.onload; //把现在有window.onload事件处理函数的值存入变量oldonload。
    if (typeof window.onload != 'function') { //如果这个处理函数还没有绑定任何函数，就像平时那样把新函数添加给它
        window.onload = func;
    } else { //如果在这个处理函数上已经绑定了一些函数。就把新函数追加到现有指令的末尾
        window.onload = function () {
            oldonload();
            func();
        }
    }
}

var chatOpened = false;

function chatChange() {
    chatOpened = !chatOpened;
    document.querySelector("#chat-icon").innerHTML
}

addLoadEvent(function () {
    //新建一个div元素节点
    let div = document.createElement("div");
    div.setAttribute("id", "chat-icon");
    div.setAttribute("class","have-msg");
    div.setAttribute("style", "text-align:center;position:absolute;bottom:20px;right:20px;width:60px;height:60px;line-height:88px;background-color:#409EFF;border-radius:50%; ");
    div.innerHTML = "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 800 800\" height=\"50%\" width=\"50%\" role=\"img\" fill='#fff'     animation-duration='.25s' animation-name='iconAnimation'>" +
        "<path fill-rule=\"evenodd\" clip-rule=\"evenodd\" d=\"M400 26.2c-193.3 0-350 156.7-350 350 0 136.2 77.9 254.3 191.5 312.1 15.4 8.1 31.4 15.1 48.1 20.8l-16.5 63.5c-2 7.8 5.4 14.7 13 12.1l229.8-77.6c14.6-5.3 28.8-11.6 42.4-18.7C672 630.6 750 512.5 750 376.2c0-193.3-156.7-350-350-350zm211.1 510.7c-10.8 26.5-41.9 77.2-121.5 77.2-79.9 0-110.9-51-121.6-77.4-2.8-6.8 5-13.4 13.8-11.8 76.2 13.7 147.7 13 215.3.3 8.9-1.8 16.8 4.8 14 11.7z\"></path>" +
        "</svg>"
    let closeIcon = "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 800 800\" height=\"50%\" width=\"50%\" role=\"img\" fill='#fff'  animation-duration='.25s' animation-name='iconAnimation'>" +
        "<path fill-rule=\"evenodd\" clip-rule=\"evenodd\" d=\"M466.24042,400.4053l272.927-275.99463c6.94586-8.76452,11.13092-20.00501,11.13092-32.20959 s-4.18506-23.42317-11.21857-32.29724l0.08765,0.10955c-8.76453-6.94588-20.005-11.13094-32.20959-11.13094 c-12.20453,0-23.42316,4.18505-32.29724,11.21858l0.10956-0.08765L401.84311,336.008L125.84851,60.0134 c-8.76452-6.94588-20.00501-11.13094-32.2096-11.13094s-23.42316,4.18506-32.29724,11.21858l0.10955-0.08765 C54.50535,68.77792,50.32029,80.01842,50.32029,92.223s4.18505,23.42317,11.21858,32.29724l-0.08764-0.10956l275.9946,275.99463 L61.45122,673.33234c-6.94588,8.76453-11.13094,20.005-11.13094,32.20959s4.18506,23.42316,11.21858,32.29724l-0.08765-0.1095 c8.19483,7.64703,19.2162,12.33606,31.33314,12.33606c0.83263,0,1.68717-0.02191,2.49789-0.06573h-0.10957 c0.54779,0.02191,1.20512,0.04382,1.86246,0.04382c11.32813,0,21.5388-4.71094,28.79144-12.29224l0.0219-0.02191 l275.99463-272.92703l272.92703,272.92703c7.2746,7.58136,17.48523,12.31415,28.81335,12.31415 c0.65735,0,1.29279-0.02191,1.95013-0.04382h-0.08765c0.72308,0.04382,1.55573,0.06573,2.38831,0.06573 c12.11694,0,23.16022-4.68903,31.37695-12.35797l-0.02185,0.02191c6.94586-8.76447,11.13092-20.005,11.13092-32.20959 c0-12.20453-4.18506-23.42316-11.21857-32.29724l0.08765,0.10956L466.24042,400.4053z\"></path>" +
        "</svg>";
//把div元素节点添加到body元素节点中成为其子节点，但是放在body的现有子节点的最后
    div.addEventListener("click", function (e) {
        callChat();
    })
    document.body.appendChild(div);

})
function chatInHere(data){
    let chat = document.querySelector("#chat");
    if (!chat) {

    }else {
        chat.style.display = 'block';
    }
    dragInBodyArea(chat, document.body);
}

function callChat() {
    let caller = document.querySelector("#chat-icon");
    caller.style.display = 'none';
    let chatPane = document.querySelector("#chat-pane");
    if (!chatPane) {
        let div = document.createElement("div");
        div.setAttribute("id", "chat-pane");
        div.setAttribute("style", "z-index:10000;" +
            "position:absolute;bottom:20px;right:20px;" +
            "width:260px;height:520px;" +
            "border-radius:5px;background-color:#fff;" +
            "border: 1px solid #D9D9D9;" +
            "border-color: rgba(0,0,0,.05);" +
            "background-repeat: no-repeat;" +
            "background-color: #F6F6F6;" +
            "color: #333;");
        let closeDiv = document.createElement("div");
        closeDiv.setAttribute("style", "position:absolute;top:10px;right:10px;");
        closeDiv.innerHTML = "X";
        closeDiv.addEventListener("click", function (e) {
            closeChat();
        })
        div.appendChild(closeDiv);
        document.body.appendChild(div);
        dragInBodyArea(div, document.body);
    }else {
        chatPane.style.display = 'block';
    }
}

function closeChat() {
    let caller = document.querySelector("#chat-icon");
    caller.style.display = 'block';
    let chatPane = document.querySelector("#chat-pane");
    chatPane.style.display = 'none';
}

function closeMsg() {
    let msg = document.querySelector("#chat");
    msg.style.display = 'none';
}

/*
   * js实现div可拖拽 相对body
   * @params target 可以点击拖动的元素
   * @params parent body 必须是position: absolute;
   * 思想：鼠标的clienX/clientY相对值设置为父元素的left/top的相对值
   */
function dragInBodyArea(target) {
    var initX, targetNowX,
        initY, targetNowY,
        dragable = false;
    target.addEventListener("mousedown", function (e) {
        dragable = true;
        initX = e.clientX;
        initY = e.clientY;
        targetNowX = target.style.right.replaceAll('px', "");
        targetNowY = target.style.bottom.replaceAll('px', "");
    }, false);
    target.addEventListener("touchstart", function (e) {
        dragable = true;
        initX = e.clientX;
        initY = e.clientY;
        targetNowX = target.style.right.replaceAll('px', "");
        targetNowY = target.style.bottom.replaceAll('px', "");
    }, false);
    document.addEventListener("mousemove", function (e) {
        if (dragable === true) {
            let nowX = e.clientX,
                nowY = e.clientY,
                disX = nowX - initX,
                disY = nowY - initY;
            target.style.right = parseInt(targetNowX) - disX + "px";
            target.style.bottom = parseInt(targetNowY) - disY + "px";
        }
    });
    document.addEventListener("touchmove", function (e) {
        if (dragable === true) {
            let nowX = e.clientX,
                nowY = e.clientY,
                disX = nowX - initX,
                disY = nowY - initY;
            target.style.right = parseInt(targetNowX) - disX + "px";
            target.style.bottom = parseInt(targetNowY) - disY + "px";
        }
    });
    document.addEventListener("mouseup", function (e) {
        dragable = false;
    }, false);
    document.addEventListener("touchend", function (e) {
        dragable = false;
    }, false);


}
