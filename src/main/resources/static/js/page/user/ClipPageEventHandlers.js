import {ClipController} from "../../container/clip/controller/ClipController.js";
import {FolderController} from "../../container/folder/controller/FolderController.js";

document.addEventListener('DOMContentLoaded', () => {

    window.oncontextmenu = () => {
        return false;
    };

    const clipPreviewBtn = document.querySelectorAll('#clip-preview-btn');
    clipPreviewBtn.forEach(function (button) {
        button.addEventListener('click', function (e) {
            ClipController.showPreview(e);
        });
    });

    const modalDialogElement = document.querySelector("#clipPreview .modal-dialog");
    modalDialogElement.classList.add("modal-xl");

    const modalBodyElement = modalDialogElement.querySelector(".modal-body");
    modalBodyElement.style.padding = "0";

    // const searchButton = document.getElementById('search-button');
    // searchButton.addEventListener('click', handleSearch);

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
            FolderController.clickFolderToggle(folderId);
        })

        current.addEventListener('mousedown', (e) => {
            const folderAnchorElements = current.querySelector('.folder-anchor');
            const folderId = folderAnchorElements.id
            FolderController.showCreateFolderForm(e, folderId)
        });
    })
});

const modalElement = document.getElementById('clipPreview');

modalElement.addEventListener('hidden.bs.modal', () => {
    const clipPreviewBodyElement = document.getElementById("clip-preview-body");
    ClipController.removeVideoElement(clipPreviewBodyElement);
});