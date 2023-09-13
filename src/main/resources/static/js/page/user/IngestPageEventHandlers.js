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

        folderAnchorElements.addEventListener('click', () => {
            FolderController.currentFolder(folderAnchorElements)

            const folderImgElement = {
                folderOpen: folderAnchorElements.querySelector('img[alt="folder_close"]'),
                folderClose: folderAnchorElements.querySelector('img[alt="folder_open"]')
            };

            FolderController.changeFolderImage(folderImgElement.folderOpen, '/img/folder_open.svg', 'folder_open')
            FolderController.changeFolderImage(folderImgElement.folderClose, '/img/folder_close.svg', 'folder_close')

            const folderId = folderAnchorElements.id
            console.log("click folder = " + folderId)
            FolderController.clickFolderToggleSelect(folderId);
        })

        current.addEventListener('mousedown', (e) => {
            const folderAnchorElements = current.querySelector('.folder-anchor');
            const folderId = folderAnchorElements.id
            FolderController.showCreateFolderForm(e, folderId)
        });
    })
})
