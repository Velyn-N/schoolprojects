var canvas;
var context;
var canvasMaxWidth;
var canvasMaxHeight;

var webSocket;
var webSocketConnected = false;

$(document).ready(function() {
	initWebsocket();
	initCanvas();
});

function initCanvas() {
    canvas = document.querySelector("#canvas");
    if (canvas.getContext) {
        context = canvas.getContext('2d');
		canvasMaxWidth = canvas.width;
		canvasMaxHeight = canvas.height;
		$("#widthDisplay").text(canvasMaxWidth);
		$("#heightDisplay").text(canvasMaxHeight);
    }
}

function clearCanvas() {
	context.clearRect(0, 0, canvasMaxWidth, canvasMaxHeight);
}

function initWebsocket() {
	if (!webSocketConnected) {
		webSocket = new WebSocket("ws://" + location.host + "/webConsole");
		
		webSocket.onopen = function() {
			connected = true;
		};
		
		webSocket.onmessage = function(m) {
			let elem = $("#outputMessages");
			elem.html(m.data + '<br>...<br><span class="invisible">...</span>');
			elem.scrollTop(elem.prop("scrollHeight"));
		};
	}
}

function displayError(msg) {
	$("#errorMessages").text(msg);
}

function query(selector) {
	return document.querySelector(selector);
}

function clearConsoleServer() {
	$.ajax({
		type: "POST",
		url: "/webConsoleActions/clear"
	});
}



//------- LINE

function getLineInfo() {
	let p1x = query("#line-p1-x").value;
	let p1y = query("#line-p1-y").value;
	let p2x = query("#line-p2-x").value;
	let p2y = query("#line-p2-y").value;
	
	return {
		point1: {
			x: p1x,
			y: p1y
		},
		point2: {
			x: p2x,
			y: p2y
		}
	};
}

function drawLine(line) {
	let p1x = line.point1.x;
	let p1y = line.point1.y;
	let p2x = line.point2.x;
	let p2y = line.point2.y;
	
	context.beginPath();
	context.moveTo(p1x, p1y);
	context.lineTo(p2x, p2y);
	context.stroke();
}

function sendLineToServer(line) {
	$.ajax({
		type: "POST",
		url: "/shapes/line",
		data: JSON.stringify(line),
		contentType: "application/json"
	});
}



//------- RECTANGLE

function getRectInfo() {
	let p1x = query("#rect-p1-x").value;
	let p1y = query("#rect-p1-y").value;
	let p2x = query("#rect-p2-x").value;
	let p2y = query("#rect-p2-y").value;
	let p3x = query("#rect-p3-x").value;
	let p3y = query("#rect-p3-y").value;
	let p4x = query("#rect-p4-x").value;
	let p4y = query("#rect-p4-y").value;
	
	return {
		point1: {
			x: p1x,
			y: p1y
		},
		point2: {
			x: p2x,
			y: p2y
		},
		point3: {
			x: p3x,
			y: p3y
		},
		point4: {
			x: p4x,
			y: p4y
		}
	};
}

function drawRect(rect) {
	let p1x = rect.point1.x;
	let p1y = rect.point1.y;
	let p2x = rect.point2.x;
	let p2y = rect.point2.y;
	let p3x = rect.point3.x;
	let p3y = rect.point3.y;
	let p4x = rect.point4.x;
	let p4y = rect.point4.y;
	
	context.beginPath();
	context.moveTo(p1x, p1y);
	context.lineTo(p2x, p2y);
	context.lineTo(p3x, p3y);
	context.lineTo(p4x, p4y);
	context.lineTo(p1x, p1y);
	context.stroke();
}

function sendRectToServer(line) {
	$.ajax({
		type: "POST",
		url: "/shapes/rectangle",
		data: JSON.stringify(line),
		contentType: "application/json"
	});
}



//------- CIRCLE

function getCircleInfo() {
	let p1x = query("#circle-p1-x").value;
	let p1y = query("#circle-p1-y").value;
	let rad = query("#circle-radius").value;
	
	return {
		middlePoint: {
			x: p1x,
			y: p1y
		},
		radius: rad
	};
}

function drawCircle(circle) {
	let p1x = circle.middlePoint.x;
	let p1y = circle.middlePoint.y;
	let rad = circle.radius;
	
	context.beginPath();
	context.arc(p1x, p1y, rad, 0, 2 * Math.PI);
	context.stroke();
}

function sendCircleToServer(circle) {
	$.ajax({
		type: "POST",
		url: "/shapes/circle",
		data: JSON.stringify(circle),
		contentType: "application/json"
	});
}