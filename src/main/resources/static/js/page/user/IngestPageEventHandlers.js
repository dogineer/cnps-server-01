import {IngestController} from "../../container/ingest/controller/IngestController.js";
import {folderArrowToggleEvent} from "../../container/folder/event/FolderEvent.js";
import {changeCount} from "../../container/module/changeCount.js";

document.addEventListener('DOMContentLoaded', () => {
    changeCount();

    const modalDialogElement = document.querySelector("#IngestRequest .modal-dialog");
    modalDialogElement.classList.add("modal-xl");

    IngestController.webSocket().then(() => {
        console.log('웹 소켓 설정이 성공적으로 완료되었습니다.');
    }).catch((error) => {
        console.error('에러 발생:', error);
    });

    IngestController.ingestRequset();
    IngestController.dragAndDrop();

    const folderNodeElement = document.querySelectorAll('.folder-node');
    folderNodeElement.forEach((current) => {
        folderArrowToggleEvent(current);
    })
})
