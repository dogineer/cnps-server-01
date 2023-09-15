import {setupWebSocket} from "../service/WebSocketService.js";
import {complete, updateProgress} from "../service/ProgressBar.js";
import {setupDragAndDrop} from "../service/DragAndDrop.js";
import {currentFolder} from "../../folder/service/folderService.js";

export class IngestController {
    static async webSocket() {
        await setupWebSocket();
    }

    static ingestRequset() {
        const progressbar = document.querySelector("#progress-gauge");
        const progressPercent = document.querySelector(".progress-percent");
        const form = document.querySelector("#ingestRequest-form");

        form.addEventListener("submit", (e) => {
            e.preventDefault();

            const formData = new FormData(form);
            const xhr = new XMLHttpRequest();

            xhr.open("POST", "/ingest/add");

            xhr.upload.addEventListener("progress", (e) => {
                if (e.lengthComputable) {
                    const percentComplete = (e.loaded / e.total) * 100;
                    const formattedPercent = percentComplete.toFixed(2);

                    updateProgress(progressbar, progressPercent, formattedPercent);
                }
            });

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        complete(progressPercent);
                    }
                }
            };

            xhr.send(formData);
        });
    }

    static dragAndDrop() {
        setupDragAndDrop();
    }

    static selectFolder(folderAnchorElements) {
        currentFolder(folderAnchorElements);

        const folderId = folderAnchorElements.id
        const folderSelects = document.querySelector("#ingestRequest-form select[name='folder']");
        if (folderSelects) {
            for (let i = 0; i < folderSelects.length; i++) {
                const option = folderSelects.options[i];
                if (option.value === folderId) {
                    console.log("[Select] 폴더 선택 : " + folderId)
                    option.selected = true;
                    break;
                }
            }
        }
    }
}