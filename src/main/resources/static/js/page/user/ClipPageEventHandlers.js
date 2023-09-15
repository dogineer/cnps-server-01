import {ClipController} from "../../container/clip/controller/ClipController.js";
import {FolderController} from "../../container/folder/controller/FolderController.js";

document.addEventListener('DOMContentLoaded', () => {

    window.oncontextmenu = () => {
        return false;
    };

    const clipPreviewBtn = document.querySelectorAll('#clip-preview-btn');
    clipPreviewBtn.forEach((button) => {
        button.addEventListener('click', (e) => {
            const trElement = e.target.closest('tr'); // tr 요소 찾기
            if (trElement) {
                const clipId = trElement.getAttribute('data-clip-id');
                const clipPath = trElement.getAttribute('data-clip-path');

                console.log('[Click] 클립 프리뷰 버튼 클릭');
                console.log('클립 ID: ' + clipId);
                console.log('클립 PATH: ' + clipPath);

                ClipController.showPreview(e);
            }
            e.stopPropagation();
        });
    });

    const clipImportForPremiere = document.querySelectorAll('#clip');
    clipImportForPremiere.forEach((element) => {
        element.addEventListener('click', (e) => {
            console.log("[Click] 클립 선택")
            console.log("클립 ID: " + element.getAttribute("data-clip-id"))
            console.log("클립 PATH: " + element.getAttribute("data-clip-path"))

            ClipController.importPremierPro(element);
        })
    })

    const modalDialogElement = document.querySelector("#clipPreview .modal-dialog");
    const modalHeaderElement = document.querySelector("#clipPreview .modal-header");
    modalDialogElement.classList.add("modal-xl");
    modalHeaderElement.classList.remove("modal-header")
    modalHeaderElement.classList.add("c-modal-header")

    const modalBodyElement = modalDialogElement.querySelector(".modal-body");
    modalBodyElement.style.padding = "0";

    const folderContainerElement = document.querySelectorAll('.folder-node');

    folderContainerElement.forEach((current) => {
        const folderAnchorElements = current.querySelector('.folder-anchor');

        folderAnchorElements.addEventListener('click', () => {
            const folderId = folderAnchorElements.id

            console.log("[Fetch] 해당 폴더의 클립 데이터를 가져옵니다. folderId = " + folderId)
            FolderController.clickFolderFetchData(folderAnchorElements, folderId);
        })

        folderAnchorElements.addEventListener('mousedown', (e) => {
            if (e.button === 2) {
                const folderAnchorElements = current.querySelector('.folder-anchor');
                const folderId = folderAnchorElements.id;
                console.log("[Click] 해당 폴더를 우클릭 하였습니다. folderId = " + folderId);
                FolderController.showFolderActionMenu(folderId)
            }
        });
    })

    folderContainerElement.forEach((current) => {
        const folderAnchorElements = current.querySelector('.folder-anchor');
        const folderArrowMenuElement = current.querySelector('.arrow-menu');
        const folderImgElement = current.querySelector('.folder-img');

        folderArrowMenuElement.addEventListener('click', () => {
            const folderId = folderAnchorElements.id
            const action = folderArrowMenuElement.classList.contains("right")

            console.log("[Fetch] 해당 폴더의 자손 폴더를 가져옵니다. folderId = " + folderId)
            FolderController.changeFolderImageAndArrow(folderArrowMenuElement, folderImgElement, action)
            FolderController.clickFolderArrow(folderAnchorElements, folderId)
        })
    })

    const modalElement = document.getElementById('clipPreview');
    modalElement.addEventListener('hidden.bs.modal', () => {
        const clipPreviewBodyElement = document.getElementById("clip-preview-body");
        ClipController.removeVideoElement(clipPreviewBodyElement);
    });
});

