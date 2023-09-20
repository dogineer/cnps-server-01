import {ClipController} from "../../container/clip/controller/ClipController.js";

import {
    folderArrowClickEvent,
    folderCreateClickEvent,
    folderDeleteClickEvent,
    folderRightClickEvent,
    folderRootCreateClickEvent,
    folderSelectClickEvent
} from "../../container/folder/event/FolderEvent.js";

import {
    clipPreviewButtonEvent,
    importPremiereEvent,
    modalStyleChangeEvent
} from "../../container/clip/event/ClipEvent.js";

document.addEventListener('DOMContentLoaded', () => {

    modalStyleChangeEvent();

    const clipPreviewButtonElement = document.querySelectorAll('#clip-preview-btn');
    clipPreviewButtonElement.forEach((current) => {
        clipPreviewButtonEvent(current);
    })

    const clipImportForPremiereElement = document.querySelectorAll('#clip');
    clipImportForPremiereElement.forEach((current) => {
        importPremiereEvent(current);
    })

    const folderNodeElement = document.querySelectorAll('.folder-node');
    folderNodeElement.forEach((current) => {
        folderSelectClickEvent(current);
        folderRightClickEvent(current);
        folderArrowClickEvent(current);
        folderCreateClickEvent(current);
        folderDeleteClickEvent(current);
    })

    const folderAddElement = document.getElementById('folder-add');
    folderAddElement.addEventListener('click', () => {
        folderRootCreateClickEvent(account_team_id);
    })

    const modalElement = document.getElementById('clipPreview');
    modalElement.addEventListener('hidden.bs.modal', () => {
        const clipPreviewBodyElement = document.getElementById("clip-preview-body");
        ClipController.removeVideoElement(clipPreviewBodyElement);
    });
});

