function renderMsg(msg, myself) {
    if (msg['from'] == myself) {
        return meMsg(msg);
    }
    if (msg['from'] == 0) {
        return sysMsg(msg);
    }
    if (msg['from'] != 0) {
        return otherMsg(msg);
    }
}
function gettime(timestamp){
    let now = new Date();
    let date = new Date(timestamp);
    let Y = now.getFullYear()==date.getFullYear()?"":date.getFullYear()+"-";

    let M = now.getMonth()==date.getMonth() && now.getDate()==date.getDate()?"":(date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    let D = now.getMonth()==date.getMonth() && now.getDate()==date.getDate()?"":date.getDate() + ' ';

    let h = date.getHours() + ':';
    let m = date.getMinutes() + ':';
    let s = date.getSeconds();
    return Y+M+D+h+m+s;
}
function meMsg(msg) {
    return '                <div class="me">' +
        '                    <div class="detail">' +
        '                        <div style="text-align: right">'+gettime(msg["timestamp"])+' 我</div>' +
        msg['data']+
        '                    </div>' +
        '                </div><div class="claer"></div>';
}

function otherMsg(msg) {
    return '                 <div class="speaker">' +
        '                    <div class="photo"><img src="who.jpg"></div>' +
        '                    <div class="detail">' +
        '                        <div class="name">'+msg["nick"]+' '+gettime(msg['timestamp'])+'</div>' +
        msg['data']+
        '                    </div>' +
        '                </div>';
}

function sysMsg(msg) {
    return '<div style="text-align: center;margin-top: 14px;">系统提示</div>';
}