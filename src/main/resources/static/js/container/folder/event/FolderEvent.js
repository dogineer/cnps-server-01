import {FolderController} from "../controller/FolderController.js";
import {IngestController} from "../../ingest/controller/IngestController.js";

const folderSelectClickEvent = (nodeElement) => {
    const folderAnchorElements = nodeElement.querySelector('.folder-anchor');
    const folderId = folderAnchorElements.id;

    folderAnchorElements.addEventListener('click', () => {
        console.log("[Fetch] 해당 폴(F_" + folderId + ")더의 클립 데이터를 가져옵니다.")
        FolderController.clickFolderFetchData(folderAnchorElements, folderId);
    });
}

const folderRightClickEvent = (nodeElement) => {
    const folderAnchorElements = nodeElement.querySelector('.folder-anchor');
    const folderId = folderAnchorElements.id;

    folderAnchorElements.addEventListener('mousedown', (e) => {
        if (e.button === 2) {
            console.log("[Click] 해당 폴더(F_" + folderId + ")를 우클릭 하였습니다.");
            FolderController.showFolderActionMenu(folderId)
        }
    });
}

const folderArrowClickEvent = (nodeElement) => {
    const folderAnchorElements = nodeElement.querySelector('.folder-anchor');
    const folderArrowMenuElement = nodeElement.querySelector('.arrow-menu');
    const folderImgElement = nodeElement.querySelector('.folder-img');

    folderArrowMenuElement.addEventListener('click', () => {
        const folderId = folderAnchorElements.id
        const action = folderArrowMenuElement.classList.contains("right")

        console.log("[Fetch] 해당 폴더(F_" + folderId + ")의 자손 폴더를 가져옵니다.");
        FolderController.changeFolderImageAndArrow(folderArrowMenuElement, folderImgElement, action)
        FolderController.clickFolderArrow(folderAnchorElements, folderId)
    })
}

const folderArrowToggleEvent = (nodeElement) => {
    const folderAnchorElement = nodeElement.querySelector('.folder-anchor');
    const folderArrowMenuElement = nodeElement.querySelector('.arrow-menu');
    const folderImgElement = nodeElement.querySelector('.folder-img');
    const folderId = folderAnchorElement.id

    folderArrowMenuElement.addEventListener('click', () => {

        const action = folderArrowMenuElement.classList.contains("right")

        console.log("[Fetch] 해당 폴더(F_" + folderId + ")의 자손 폴더를 가져옵니다.");
        FolderController.changeFolderImageAndArrow(folderArrowMenuElement, folderImgElement, action)
        FolderController.clickFolderToggleSelect(folderAnchorElement, folderId)
    })

    folderAnchorElement.addEventListener('click', () => {
        IngestController.selectFolder(folderAnchorElement);
    })
}

const folderCreateClickEvent = (nodeElement) => {
    const folderAnchorElements = nodeElement.querySelector('.folder-anchor');

    const folderDto = {
        folderId: folderAnchorElements.id,
        folderProgramId: folderAnchorElements.getAttribute("data-program-id")
    }

    const createFolderElement = nodeElement.querySelector('.f-add');

    createFolderElement.addEventListener('click', () => {
        const newFolderElement = document.getElementById("newFolder");
        FolderController.createFolderModalShow(folderDto, newFolderElement);

        const createFolderForm = document.getElementById("form-new-folder");
        const createFolderBtn = createFolderForm.querySelector("#create-folder-btn")

        createFolderBtn.addEventListener('click', (e) => {
            console.log("[Click] 해당 폴더(F_" + folderDto.folderId + ")에 대한 자손 풀더를 생성합니다.");
            FolderController.sendFormDataToCreate(createFolderForm, e);
        })
    });
}

const folderDeleteClickEvent = (nodeElement) => {
    const folderAnchorElements = nodeElement.querySelector('.folder-anchor');

    const folderDto = {
        folderId: folderAnchorElements.id,
        folderName: folderAnchorElements.getAttribute("data-folder-name"),
        folderProgramId: folderAnchorElements.getAttribute("data-program-id")
    }

    const deleteFolderElement = nodeElement.querySelector('.f-del');


    deleteFolderElement.addEventListener('click', () => {
        console.log("[Click] 해당 폴더(F_" + folderDto.folderId + ")를 삭제합니다.");
        FolderController.sendFormDataToDelete(folderDto);
    });
}

const folderRootCreateClickEvent = () => {

    const folderDto = {
        folderId: 1,
        folderProgramId: account_program_id
    }

    const newFolderElement = document.getElementById("newFolder");
    FolderController.createFolderModalShow(folderDto, newFolderElement);

    const createFolderForm = document.getElementById("form-new-folder");
    const createFolderBtn = createFolderForm.querySelector("#create-folder-btn")

    createFolderBtn.addEventListener('click', (e) => {
        console.log("[Click] 해당 폴더(F_" + folderDto.folderId + ")에 대한 자손 풀더를 생성합니다.");
        FolderController.sendFormDataToCreate(createFolderForm, e);
        e.stopPropagation();
    })
}

export {
    folderSelectClickEvent,
    folderRightClickEvent,
    folderArrowClickEvent,
    folderCreateClickEvent,
    folderDeleteClickEvent,
    folderArrowToggleEvent,
    folderRootCreateClickEvent
};