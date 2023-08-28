async function getServerURL() {
  try {
    const response = await fetch('/clip/get-server02-url');
    if (response.ok) {
      return await response.text();
    } else {
      console.error('\n' + '서버 URL을 가져오지 못했습니다.');
    }
  } catch (error) {
    console.error('서버 URL을 가져오는 중 에러 발생 :', error);
  }
  return null;
}

async function initWebSocket() {
  const currentProtocol = window.location.protocol;
  const websocketProtocol = currentProtocol === "https:" ? "wss" : "ws";
  const websocketURL = (await getServerURL()).replace(/^http/, websocketProtocol) + '/websocket';

  if (websocketURL) {
    const socket = new WebSocket(websocketURL);

    socket.onopen = function () {
      console.log("server02 연결이 열렸습니다.");
    };

    socket.onmessage = function (event) {
      var json = JSON.parse(event.data);

      var percentage = json.percentage;
      var ingestId = json.ingestId;

      updateProgress(percentage, ingestId);
    };

    socket.onclose = function (event) {
      console.log("server02 연결이 닫혔습니다.");
    };
  }
}

function updateProgress(percentage, ingestId) {
  var ingestNumber = document.getElementById("P" + ingestId);
  if (ingestNumber) {
    ingestNumber.textContent = "진행률: " + percentage + "%";
  }
}

window.addEventListener('load', initWebSocket);
