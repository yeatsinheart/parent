//base64图片
var si = localStorage.getItem('start_screen_img');
if (si) {
  let ssi = document.getElementById('start_screen_img')
  if (ssi)
    ssi.src = si
}