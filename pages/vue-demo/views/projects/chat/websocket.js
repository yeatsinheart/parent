var WebSocket = WebSocket || window.WebSocket || window.MozWebSocket;
// tips: WebSocket 调试脚本
if (!WebSocket) {
    alert("WebSocket not supported by this browser!");
}
var tt;
var lockReconnect = false;//避免重复连接
var g_ws = null;

function reconnect(url) {
    if (lockReconnect) {
        return;
    }
    lockReconnect = true;
    //没连接上会一直重连，设置延迟避免请求过多
    tt && clearTimeout(tt);
    tt = setTimeout(function () {
        CreateConnect(url);
        lockReconnect = false;
    }, 4000);
}

function CreateConnect(url, startIM,userId,responsehadler, errhadler) {
// 验证浏览器是否支持WebSocket协议
    if (g_ws == null) {
        try {
            g_ws = new WebSocket(url);
            // 监听消息
            g_ws.onmessage = function (event) {
                //valueLabel.innerHTML+ = event.data;
                if (event.data !== "pong") {
                    responsehadler(event.data);
                } else {
                    //心跳检测重置
                    heartCheck.start();
                }
            };
            g_ws.onclose = function (event) {
                g_ws = null;
            };
            g_ws.onopen = function (event) {
                //心跳检测重置
                if(startIM){
                    let joinChatMsg = {
                        "action": "startim",
                        "from": userId
                    };
                    sendMsg(JSON.stringify(joinChatMsg))
                }
                heartCheck.start();
            };
            g_ws.onerror = function (event) {
                console.log(event)
                errhadler("onerror(), Socket 发生错误!");
            };
        } catch (e) {
            console.log(e)
            g_ws = null;
        }
    }
}

function sendMsg(txtstr) {
    try {
        if (g_ws != null && g_ws.readyState === g_ws.OPEN) {
            g_ws.send(txtstr);
        }
    } catch (e) {
        console.log(e)
    }
}

// 关闭连接
function CloseConnect() {
    console.log("CloseConnect() 主动关闭");
    if (g_ws != null) {
        g_ws.close();
    }
}

//心跳检测
let heartCheck = {
    timeout: 160000,
    timeoutObj: null,
    pong: function () {
        let self = this;
        setTimeout(sendMsg("ping"), self.timeout);
    },
    ping: function () {
        let self = this;
        setTimeout(sendMsg("ping"), self.timeout)
    },
    interval: function () {
        let self = this;
        self.ping();
        //setTimeout(self.interval(self.timeout),self.timeout);
    },
    start: function () {
        let self = this;
        //清除之前的任务
        this.timeoutObj && clearTimeout(this.timeoutObj);
        this.timeoutObj = setTimeout(function () {
                //触发定时执行
                self.interval();
            }
            , self.timeout);
    }
};
