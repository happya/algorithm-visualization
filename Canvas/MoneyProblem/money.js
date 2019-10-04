var WINDOW_WIDTH, WINDOW_HEIGHT, RADIUS;
var MARGIN_LEFT, MARGIN_TOP;
var timer;
var canvas, context;
var money, w;

function init() {
  WINDOW_WIDTH = document.body.clientWidth;;
  WINDOW_HEIGHT = document.body.clientHeight;
  RADIUS =  Math.round(WINDOW_WIDTH * 4 / 5 / 108) - 1;;
  MARGIN_TOP = Math.round(WINDOW_HEIGHT / 5);
  MARGIN_LEFT = Math.round(WINDOW_WIDTH / 10);

  canvas = document.getElementById('canvas');
  context = canvas.getContext('2d');
  canvas.width = WINDOW_WIDTH;
  canvas.height = WINDOW_HEIGHT;
  context.clearRect(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
  
}
function initMoney() {
  money = new Array(100);
  for(var i=0;i<money.length;i++){
    money[i] = 100;
  }
  w = Math.floor((WINDOW_WIDTH - MARGIN_LEFT*2) / money.length);
}

window.onload = function () {
  init();
  initMoney();
  
  // render(context);
  if(timer) {
    clearInterval(timer);
  }
  timer=setInterval(() => {
    render(context);
    update(context);
  }, 40);
  
}

function render(ctx) {
  // 对矩形区域刷新
  ctx.clearRect(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
  
  for(var i=0;i<money.length;i++) {
    drawMoney(ctx,i);
  }
}

function update(ctx) {
  
  for(var k=0;k<50;k++) {
    for(var i=0;i<100;i++) {
        var j = Math.floor(Math.random() * money.length);
        money[i] -= 1;
        money[j] += 1;
    }
  }
  money.sort(sortMoney);
  for(var i=0;i<money.length;i++){
    drawMoney(ctx,i);
  }
}

function drawMoney(ctx,index) {
  
  if(money[index]>0) {
    ctx.beginPath();
    ctx.moveTo(index*w + 1 + MARGIN_LEFT, parseInt(WINDOW_HEIGHT/2)-money[index]);
    ctx.lineTo(index*w + w + MARGIN_LEFT, parseInt(WINDOW_HEIGHT/2)-money[index]);
    ctx.lineTo(index*w + w + MARGIN_LEFT, parseInt(WINDOW_HEIGHT/2));
    ctx.lineTo(index*w + 1 + MARGIN_LEFT, parseInt(WINDOW_HEIGHT/2));
    ctx.closePath();  
  
    ctx.fillStyle = 'rgb(0, 102, 153)';
    ctx.fill();
    
  }
  else if(money[index]<0) {
    ctx.beginPath();
    ctx.moveTo(index*w + 1 + MARGIN_LEFT, parseInt(WINDOW_HEIGHT/2));
    ctx.lineTo(index*w + w + MARGIN_LEFT, parseInt(WINDOW_HEIGHT/2));
    ctx.lineTo(index*w + w + MARGIN_LEFT, parseInt(WINDOW_HEIGHT/2)-money[index]);
    ctx.lineTo(index*w + 1 + MARGIN_LEFT, parseInt(WINDOW_HEIGHT/2)-money[index]);
    ctx.closePath(); 
    ctx.fillStyle = 'rgb(255, 153, 102)';
    ctx.fill();
  }  
}
function sortMoney(a,b) {
  return a-b;
}