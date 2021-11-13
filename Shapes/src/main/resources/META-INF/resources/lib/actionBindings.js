$("#clearCanvas").on("click", function() {
	clearCanvas();
});

$("#newLine").on("click", function() {
	let line = getLineInfo();
	drawLine(line);
	sendLineToServer(line);
});

$("#newRect").on("click", function() {
	let rect = getRectInfo();
	drawRect(rect);
	sendRectToServer(rect);
});

$("#newCircle").on("click", function() {
	let circle = getCircleInfo();
	drawCircle(circle);
	sendCircleToServer(circle);
});

$("#clearConsole").on("click", function() {
	clearConsoleServer();
});