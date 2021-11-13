function query(selector) {
	return document.querySelector(selector);
}

function initCanvas() {
    canvas = document.querySelector("#canvas");
    if (canvas.getContext) {
        context = canvas.getContext('2d');
    }
}

function loadOutput() {
	$.ajax({
		url: "rest/messages",
		success: function(data, textStatus, jqXHR) {
			query("#outputMessages").textContent = data;
		}
	});
}