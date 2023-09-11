import {IngestController} from "../../container/ingest/controller/IngestController.js";

document.addEventListener('DOMContentLoaded', () => {
    IngestController.webSocket().then(() => {
        console.log('웹 소켓 설정이 성공적으로 완료되었습니다.');
    }).catch((error) => {
        console.error('에러 발생:', error);
    });

    IngestController.ingestRequset();
    IngestController.dragAndDrop();
})
