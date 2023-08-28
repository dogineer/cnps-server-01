import {ClipPreviewService} from "../service/ClipPreviewService.js";

document.addEventListener('DOMContentLoaded', () => {
    const clipPreviewBtn = document.querySelectorAll('#clip-preview-btn');

    clipPreviewBtn.forEach(function (button) {
        button.addEventListener('click', function (event) {
            ClipPreviewService.clipPreview(event);
        });
    });

    const modalDialogElement = document.querySelector("#clipPreview .modal-dialog");
    modalDialogElement.classList.add("modal-xl");

    const modalBodyElement = modalDialogElement.querySelector(".modal-body");
    modalBodyElement.style.padding = "0";
})

const modalElement = document.getElementById('clipPreview');

modalElement.addEventListener('hidden.bs.modal', () => {
    const clipPreviewBodyElement = document.getElementById("clip-preview-body");
    ClipPreviewService.removeVideoElement(clipPreviewBodyElement)
});