
/*
javascript中制作滚动代码的常用属性
页可见区域宽： document.body.clientWidth;
网页可见区域高： document.body.clientHeight;
网页可见区域宽： document.body.offsetWidth   (包括边线的宽);
网页可见区域高： document.body.offsetHeight (包括边线的宽);
网页正文全文宽： document.body.scrollWidth;
网页正文全文高： document.body.scrollHeight;
网页被卷去的高： document.body.scrollTop;
网页被卷去的左： document.body.scrollLeft;
网页正文部分上： window.screenTop;
网页正文部分左： window.screenLeft;
屏幕分辨率的高： window.screen.height;
屏幕分辨率的宽： window.screen.width;
屏幕可用工作区高度： window.screen.availHeight;
* */
export function scroll2top() {
    let acceleration = 0.1
    let time = 100
    let x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0
    if (document.documentElement) {
        x1 = document.documentElement.scrollLeft || 0
        y1 = document.documentElement.scrollTop || 0
    }
    if (document.body) {
        x2 = document.body.scrollLeft || 0
        y2 = document.body.scrollTop || 0
    }
    x3 = window.scrollX || 0
    y3 = window.scrollY || 0
    let x = Math.max(x1, Math.max(x2, x3))
    let y = Math.max(y1, Math.max(y2, y3))
    let speed = 1 + acceleration
    window.scrollTo(Math.floor(x / speed), Math.floor(y / speed))
    if (x > 0 || y > 0) {
        let invokeFunction = scroll2top( acceleration , time )
        window.setTimeout(invokeFunction, time)
    }
}
export function scroll2Bottom() {
    let acceleration = 0.1
    let time = 100
    let x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0
    if (document.documentElement) {
        x1 = document.documentElement.scrollLeft || 0
        y1 = document.documentElement.scrollTop || 0
    }
    if (document.body) {
        x2 = document.body.scrollLeft || 0
        y2 = document.body.scrollTop || 0
    }
    x3 = window.scrollX || 0
    y3 = window.scrollY || 0

    let x = Math.min(x1, Math.min(x2, x3))
    let y = Math.min(y1, Math.min(y2, y3))
    let speed = 1 + acceleration
    window.scrollTo(Math.floor(-x / speed), Math.floor(-y / speed))
    if (x > 0 || y > 0) {
        let invokeFunction = scroll2top( acceleration , time )
        window.setTimeout(invokeFunction, time)
    }
}