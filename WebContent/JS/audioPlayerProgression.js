var c;
var audioplayer;
var ctx;

function init(){
	c = document.getElementById('myCanvas');
	ctx = c.getContext("2d");
	ctx.strokeStyle='white';
	ctx.lineWidth=4;
	ctx.beginPath();
	ctx.arc(100, 75, 50, 0* Math.PI, 0 * Math.PI);
	ctx.stroke();
}

function barreDeProgressionTitreMusical(){
	ctx.clearRect(0, 0, c.width, c.height);
	audioplayer = document.getElementById('player');
	var timer = audioplayer.currentTime;
	var duration = audioplayer.duration;
	cte = (timer/duration)*2;
	ctx.strokeStyle='white';
	ctx.lineWidth=4;
	ctx.beginPath();
	ctx.arc(100, 75, 50, 0* Math.PI, cte * Math.PI);
	ctx.stroke();
}