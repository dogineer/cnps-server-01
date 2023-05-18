var socket = new WebSocket("ws://localhost:8081/websocket");

socket.onopen = function () {
  console.log("WebSocket 연결이 열렸습니다.");
};

socket.onmessage = function (event) {
  var json = JSON.parse(event.data);
  console.log(json);

  var percentage = json.percentage;
  var ingestId = json.ingestId;

  updateProgress(percentage, ingestId);
};

socket.onclose = function (event) {
  console.log("WebSocket 연결이 닫혔습니다.");
};

function updateProgress(percentage, ingestId) {
  var ingestNumber = document.getElementById("#P"+ingestId)
      ingestNumber.textContent = "진행률: " + percentage + "%";
}