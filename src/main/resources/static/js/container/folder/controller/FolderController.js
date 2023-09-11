import {folderToggle} from "../service/ToggleFolder.js";
import {fetchFolderData} from "../service/fetchFolderData.js";

export class FolderController {
    static currentFolder(target) {
        const allAnchorElements = document.querySelectorAll(".folder-anchor")
        allAnchorElements.forEach((element) => {
            element.setAttribute('aria-current', 'false')
        })

        target.setAttribute('aria-current', 'true')
    }

    static changeFolderImage(image, src, alt) {
        if (image) {
            image.src = src;
            image.alt = alt;
        }
    }

    static clickFolderToggle(folderId) {
        folderToggle(folderId);
        fetchFolderData(folderId);
    }

    static showCreateFolderForm(e, folderId) {
        if (e.button === 2) {
            const folderMenu = document.getElementById('folder_create_input_' + folderId);

            if (folderMenu.style.display === 'none' || folderMenu.style.display === '') {
                folderMenu.style.display = 'block';
            } else {
                folderMenu.style.display = 'none';
            }
        }
    }

    static createFolder(folderId) {

    }
}