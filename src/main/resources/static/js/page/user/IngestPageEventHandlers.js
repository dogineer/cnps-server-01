import {IngestController} from "../../container/ingest/controller/IngestController.js";
import {FolderController} from "../../container/folder/controller/FolderController.js";

document.addEventListener('DOMContentLoaded', () => {
    const modalDialogElement = document.querySelector("#IngestRequest .modal-dialog");
    modalDialogElement.classList.add("modal-xl");

    IngestController.webSocket().then(() => {
        console.log('웹 소켓 설정이 성공적으로 완료되었습니다.');
    }).catch((error) => {
        console.error('에러 발생:', error);
    });

    IngestController.ingestRequset();
    IngestController.dragAndDrop();

    const folderContainerElement = document.querySelectorAll('.folder-node');
    folderContainerElement.forEach((current) => {
        const folderAnchorElements = current.querySelector('.folder-anchor');
        const folderArrowMenuElement = current.querySelector('.arrow-menu');
        const folderImgElement = current.querySelector('.folder-img');

        folderArrowMenuElement.addEventListener('click', () => {
            const folderId = folderAnchorElements.id
            const action = folderArrowMenuElement.classList.contains("right")

            console.log("[Fetch] 해당 폴더의 자손 폴더를 가져옵니다. folderId = " + folderId)
            FolderController.changeFolderImageAndArrow(folderArrowMenuElement, folderImgElement, action)
            FolderController.clickFolderToggleSelect(folderAnchorElements, folderId);
        })

        folderAnchorElements.addEventListener('click', () => {
            IngestController.selectFolder(folderAnchorElements)
        })
    })
})
