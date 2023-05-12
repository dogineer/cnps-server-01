var socket = new WebSocket("ws://localhost:8081/websocket");

socket.onopen = function() {
    console.log("WebSocket 연결이 열렸습니다.");
};

socket.onmessage = function(event) {
    var percentage = parseFloat(event.data);
    // console.log("진행률: " + percentage);

    updateProgress(percentage);
};

socket.onclose = function(event) {
    console.log("WebSocket 연결이 닫혔습니다.");
};

function updateProgress(percentage) {
    document.getElementById("progressText").textContent = "진행률: " + percentage + "%";
}