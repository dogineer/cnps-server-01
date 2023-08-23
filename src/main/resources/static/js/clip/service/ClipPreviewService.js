export class ClipPreviewService {
    static clipPreview(event) {
        const clip = event.target.closest("#clip");

        if (!clip) {
            console.error("clip 요소를 찾을 수 없음");
            return;
        }

        const clipId = clip.getAttribute("data-clip-id");
        const clipTitle = clip.getAttribute("data-clip-title");
        const clipPath = clip.getAttribute("data-clip-path");

        this.openClipModal(clipId, clipTitle, clipPath);
    }

    static openClipModal(clipId, clipTitle, clipPath) {
        const modal = new bootstrap.Modal(document.getElementById("clipPreview"));
        const modalDialogElement = document.querySelector("#clipPreview .modal-dialog");
        modalDialogElement.classList.add("modal-xl");

        const clipPreviewBodyElement = document.getElementById("clip-preview-body");
        this.removeVideoElement(clipPreviewBodyElement);
        this.setModalContent(clipPreviewBodyElement, clipTitle, clipId, clipPath);

        modal.show();
    }

    static setModalContent(clipPreviewBodyElement, clipTitle, clipId, clipPath) {
        const titleElement = document.getElementById("clip-title");
        const idElement = document.getElementById("clip-id");

        titleElement.innerText = clipTitle;
        idElement.innerText = clipId;

        const newClipPreviewElement = document.createElement("video");
        newClipPreviewElement.setAttribute("controls", "");

        const newSourceElement = document.createElement("source");
        newSourceElement.setAttribute("id", "clip-source");

        const queryParams = new URLSearchParams({filename: clipPath});
        const encodedClipPath = queryParams.toString();

        newSourceElement.setAttribute(
            "src",
            `http://localhost:8080/clip/display?${encodedClipPath}`
        );
        newSourceElement.setAttribute("type", "video/mp4");

        newClipPreviewElement.appendChild(newSourceElement);
        clipPreviewBodyElement.appendChild(newClipPreviewElement);
    }

    static removeVideoElement(clipPreviewBodyElement) {
        const existingVideoElement = clipPreviewBodyElement.querySelector("video");
        if (existingVideoElement) {
            clipPreviewBodyElement.removeChild(existingVideoElement);
        }
    }
}