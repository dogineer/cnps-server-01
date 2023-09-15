import {currentFolder, fetchDataForFolder, folderToggleSelector} from "../service/folderService.js";
import {fetchFolderDataForClipView} from "../service/fetchFolderData.js";
import {sandFolderData} from "../service/createFolderService.js";

export class FolderController {
    static clickFolderArrow(folderAnchorElements, folderId) {
        currentFolder(folderAnchorElements)
        fetchDataForFolder(folderId);
    }

    static clickFolderToggleSelect(folderAnchorElements, folderId) {
        currentFolder(folderAnchorElements)
        folderToggleSelector(folderId);
    }

    static clickFolderFetchData(folderAnchorElements, folderId) {
        currentFolder(folderAnchorElements)
        fetchFolderDataForClipView(folderId);
    }

    static changeFolderImageAndArrow(folderArrowMenuElement, folderImgElement, action) {
        if (action) {
            folderArrowMenuElement.classList.remove("right")
            folderArrowMenuElement.classList.add("down")

            folderImgElement.classList.remove("f-close")
            folderImgElement.classList.add("f-open")
        } else {
            folderArrowMenuElement.classList.remove("down")
            folderArrowMenuElement.classList.add("right")

            folderImgElement.classList.remove("f-open")
            folderImgElement.classList.add("f-close")
        }
    }

    static showFolderActionMenu(folderId) {
        const folderActionMenu = document.getElementById('FA_' + folderId);

            if (folderActionMenu.style.display === 'none' || folderActionMenu.style.display === '') {
                folderActionMenu.style.display = 'block';
            } else {
                folderActionMenu.style.display = 'none';
            }
    }

    static createFolder(folderId) {
        sandFolderData(folderId);
    }
}