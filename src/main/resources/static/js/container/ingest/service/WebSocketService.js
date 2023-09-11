async function initWebSocket() {
    const currentProtocol = window.location.protocol;
    const websocketProtocol = currentProtocol === "https:" ? "ws" : "ws";
    const serverURL = (await getServerURL()).replace(/^http/, websocketProtocol);
    return serverURL + '/websocket';
}

export async function setupWebSocket() {
    const SOCKET_URL = await initWebSocket();
    console.log(SOCKET_URL)
    const SOCKET = new WebSocket(SOCKET_URL);

    SOCKET.addEventListener('open', () => {
        console.log("server02 연결이 열렸습니다.");
    });

    SOCKET.addEventListener('message', (message) => {
        const {percentage, ingestId} = JSON.parse(message.data);
        updateProgress(percentage, ingestId);
    });

    SOCKET.addEventListener('close', () => {
        console.log("server02 연결이 닫혔습니다.");
    });
}

async function getServerURL() {
    try {
        const response = await fetch('/clip/get-server02-url');
        if (response.ok) {
            return await response.text();
        } else {
            console.error('\n' + '서버 URL을 가져오지 못했습니다.');
        }
    } catch (error) {
        console.error('서버 URL을 가져오는 중 에러 발생:', error);
    }
    return null;
}

function updateProgress(percentage, ingestId) {
    const ingestPerventageElement = document.getElementById("#P" + ingestId);
    if (ingestPerventageElement) {
        ingestPerventageElement.textContent = `진행률: ${percentage}%`;
    }
}
