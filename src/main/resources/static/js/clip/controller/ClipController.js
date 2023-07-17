import {ClipPreviewService} from "../service/ClipPreviewService.js";

document.addEventListener('DOMContentLoaded', () =>{
    const clipPreviewBtn = document.querySelectorAll('#clip-preview-btn');

    clipPreviewBtn.forEach(function (button) {
        button.addEventListener('click', function (event) {
            ClipPreviewService.clipPreview(event);
        });
    });

})