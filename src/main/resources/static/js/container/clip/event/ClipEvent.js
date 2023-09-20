import {ClipController} from "../controller/ClipController.js";

const modalStyleChangeEvent = () => {
    const modalDialogElement = document.querySelector("#clipPreview .modal-dialog");
    const modalHeaderElement = document.querySelector("#clipPreview .modal-header");
    modalDialogElement.classList.add("modal-xl");
    modalHeaderElement.classList.remove("modal-header")
    modalHeaderElement.classList.add("c-modal-header")

    const modalBodyElement = modalDialogElement.querySelector(".modal-body");
    modalBodyElement.style.padding = "0";
}

const clipPreviewButtonEvent = (buttonElement) => {
    buttonElement.addEventListener('click', (e) => {
        const trElement = e.target.closest('tr');

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
}

const importPremiereEvent = (buttonElement) => {
    buttonElement.addEventListener('click', () => {
        console.log("[Import] 클립 선택")
        console.log("클립 ID: " + buttonElement.getAttribute("data-clip-id"))
        console.log("클립 PATH: " + buttonElement.getAttribute("data-clip-path"))

        ClipController.importPremierPro(buttonElement);
    });
}

export {modalStyleChangeEvent, clipPreviewButtonEvent, importPremiereEvent};