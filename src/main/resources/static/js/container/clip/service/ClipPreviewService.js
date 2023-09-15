export class ClipPreviewService {
    static clipPreview(event) {
        const clip = event.target.closest("#clip");

        if (!clip) {
            console.error("[!] clip 요소를 찾을 수 없음");
            return;
        }

        const clipId = clip.getAttribute("data-clip-id");
        const clipTitle = clip.getAttribute("data-clip-title");
        const clipPath = clip.getAttribute("data-clip-path");

        this.openClipModal(clipId, clipTitle, clipPath);
    }

    static openClipModal(clipId, clipTitle, clipPath) {
        console.log("[+] 클립을 재생합니다.")
        console.log("Clip ID: " + clipId)
        console.log("Clip TITLE: " + clipTitle)

        const modal = new bootstrap.Modal(document.getElementById("clipPreview"));
        const clipPreviewBodyElement = document.getElementById("clip-preview-body");

        this.setModalTitle(clipTitle)
        this.removeVideoElement(clipPreviewBodyElement);
        this.setModalContent(clipPreviewBodyElement, clipPath);

        modal.show();
    }

    static setModalTitle(clipTitle){
        const clipPreviewElement = document.getElementById("clipPreview");
        const modalTitle = clipPreviewElement.querySelector('#ModalLabel')
        modalTitle.innerHTML = clipTitle;
    }

    static setModalContent(clipPreviewBodyElement, clipPath) {
        const newClipPreviewElement = document.createElement("video");
        newClipPreviewElement.setAttribute("id", "player");
        newClipPreviewElement.setAttribute("class", "video-js")

        const queryParams = new URLSearchParams({filename: clipPath});
        const encodedClipPath = queryParams.toString();

        clipPreviewBodyElement.appendChild(newClipPreviewElement);

        const currentProtocol = window.location.protocol;
        const currentHost = window.location.host;
        const currentHostWithProtocol = currentProtocol + "//" + currentHost;

        videojs(document.querySelector('#player'), {
            sources: {
                src: currentHostWithProtocol + `/clip/streaming?${encodedClipPath}`,
                type: ''
            },
            controls: true,
            autoplay: true,
            preload: 'auto',
            playbackRates: [0.5, 1, 1.5, 2],
            controlBar: {
                skipButtons: {
                    forward: 5,
                    backward: 5
                },
            }
        });
    }

    static removeVideoElement(clipPreviewBodyElement) {
        const existingVideoElement = clipPreviewBodyElement.querySelector(".video-js");
        if (existingVideoElement) {
            clipPreviewBodyElement.removeChild(existingVideoElement);
        }
    }
}