import {currentFolder, fetchDataForFolder, folderToggleSelector} from "../service/FolderService.js";
import {fetchFolderDataForClipView} from "../service/FetchFolderDataForClipView.js";
import {folderCreate, folderDelete} from "../service/CreateFolderService.js";
import {validateFormData} from "../../module/validateFormData.js";

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

        if (folderActionMenu.style.display === 'none') {
            folderActionMenu.style.display = '';
        } else if (folderActionMenu.style.display === '') {
            folderActionMenu.style.display = 'none';
        }
    }

    static createFolderModalShow(folderDto, newFolderElement) {
        const modal = new bootstrap.Modal(newFolderElement);

        const folderId = folderDto.folderId;
        const folderTeamId = folderDto.folderTeamId

        const currentFolder = newFolderElement.querySelector('#current-folder')
        currentFolder.value = folderId
        const currentFolderTeam = newFolderElement.querySelector('#team_id')
        currentFolderTeam.value = folderTeamId;

        modal.show();
    }

    static sendFormDataToDelete(folderDto) {
        if (confirm(folderDto.folderName + " 폴더를 삭제합니까?")) {
            folderDelete(folderDto.folderId);
        }
    }

    static sendFormDataToCreate (createFolderForm, e) {
        const formData = validateFormData(createFolderForm);
        folderCreate(formData);
    }
}