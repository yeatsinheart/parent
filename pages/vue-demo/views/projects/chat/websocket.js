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


var reader = {
    readAs: function(type,blob,cb){
        console.log(blob)
        var r = new FileReader();
        r.onloadend = function(){
            if(typeof(cb) === 'function') {
                cb.call(r,r.result);
            }
        }
        try{
            r['readAs'+type](blob);
        }catch(e){}
    }
}

function parseBlob(blob){
    var shortVar, intVar, stringVar;
    reader.readAs('ArrayBuffer',blob.slice(0,blob.size),function(arr){
        shortVar = (new Int8Array(arr));
        for(let i=0;i<shortVar.length;i++){
            shortVar[i]=~shortVar[i];
        }
        var string = new TextDecoder().decode(shortVar);
        console.log(string);

    });

   /* reader.readAs('ArrayBuffer',blob.slice(0,blob.size),function(arr){
        intVar = (new Int32Array(arr));
        console.log(intVar);
    });
*/
    reader.readAs('Text',blob.slice(0,blob.size,'text/plain;charset=UTF-8'),function(result){
        stringVar = result;
        console.log(stringVar);
    });
}

function CreateConnect(url, startIM,userId,nick,openHandler,responsehadler, errhadler) {
// 验证浏览器是否支持WebSocket协议
    if (g_ws == null) {
        try {
            g_ws = new WebSocket(url);
            // 监听消息
            g_ws.onmessage = function (event) {
                //valueLabel.innerHTML+ = event.data;
                if (event.data !== "pong") {

                    let buffer = event.data;
                    if(buffer instanceof Blob){
                        buffer.arrayBuffer().then(arr=>{
                            let shortVar = (new Int8Array(arr));
                            for(let i=0;i<shortVar.length;i++){
                                shortVar[i]=~shortVar[i];
                            }
                            let string = new TextDecoder("utf-8").decode(shortVar);
                            responsehadler(string);
                        });
                    }
                    //let txt = new TextDecoder("utf-8").decode(new Uint8Array(buffer))
                   /* console.log(txt)
                    responsehadler(event.data);*/
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
                        "nick":nick,
                        "from": userId
                    };
                    sendMsg(JSON.stringify(joinChatMsg));
                    openHandler();
                }
                heartCheck.start();
            };
            g_ws.onerror = function (event) {
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
            let blob = new Blob([txtstr], { type: "text/plain" });
            g_ws.send(blob);
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
    timeout: 5000,
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
