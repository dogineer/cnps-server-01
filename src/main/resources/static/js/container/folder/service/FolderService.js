import {handleException, serverError} from "../../issue/service/IssueService.js";
import {
    folderArrowClickEvent,
    folderCreateClickEvent,
    folderDeleteClickEvent,
    folderRightClickEvent,
    folderSelectClickEvent,
    folderArrowToggleEvent
} from "../event/FolderEvent.js";

export {currentFolder, fetchDataForFolder, folderToggleSelector}

const currentFolder = (target) => {
    const allAnchorElements = document.querySelectorAll(".folder-anchor")
    allAnchorElements.forEach((element) => {
        element.setAttribute('aria-current', 'false')
    })

    target.setAttribute('aria-current', 'true')
}

const createFolderElements = (parentFolderElement, childrenFolder) => {
    const childrenFolderId = childrenFolder.folderId;
    const childrenFolderName = childrenFolder.folderName;
    const childrenFolderProgramId = childrenFolder.folderProgramId;

    const folderNodeElement = document.createElement('div');
    folderNodeElement.className = 'folder-node pd-l-1';
    folderNodeElement.id = "N_" + childrenFolderId;

    const folderTree = document.createElement('div');
    folderTree.className = "folder-tree";

    const folderArrowMenuElement = document.createElement('div');
    folderArrowMenuElement.className = "arrow-menu right"

    const folderAnchorElement = document.createElement('a');
    folderAnchorElement.className = 'folder-anchor'
    folderAnchorElement.setAttribute("aria-current", "false")
    folderAnchorElement.setAttribute("data-folder-name", childrenFolderName)
    folderAnchorElement.setAttribute("data-program-id", childrenFolderProgramId)
    folderAnchorElement.id = childrenFolderId;

    const folderNameElement = document.createElement('span')
    folderNameElement.textContent = childrenFolderName;

    const folderImg = document.createElement('i');
    folderImg.className = "folder-img f-close";

    const folderActionMenu = document.createElement('div');
    folderActionMenu.id = "FA_" + childrenFolderId;
    folderActionMenu.className = "folder-action";
    folderActionMenu.style = "display: none;";

    const actionCreateButton = document.createElement('i');
    actionCreateButton.className = "folder-img f-add"

    const actionDeleteButton = document.createElement('i');
    actionDeleteButton.className = "folder-img f-del"

    const folderChildrenElement = document.createElement('div');
    folderChildrenElement.id = "F_" + childrenFolderId
    folderChildrenElement.className = "folder-children slide-none";

    parentFolderElement.append(folderNodeElement);
    folderNodeElement.append(folderTree);

    folderActionMenu.append(actionCreateButton);
    folderActionMenu.append(actionDeleteButton);

    folderTree.append(folderArrowMenuElement);
    folderTree.append(folderAnchorElement);

    folderAnchorElement.append(folderImg);
    folderAnchorElement.append(childrenFolderName);

    folderAnchorElement.append(folderActionMenu);
    folderNodeElement.append(folderChildrenElement);

    return folderNodeElement;
}

const fetchDataForFolder = (folderId) => {
    const parentFolderElement = document.getElementById('F_' + folderId);

    if (parentFolderElement) {
        if (parentFolderElement.classList.contains('slide-none')) {
            parentFolderElement.classList.remove('slide-none');
            parentFolderElement.classList.add('slide-down');

            fetch('/s1/api/folder/show/' + folderId)
                .then(res => {
                    if (res.ok) {
                        return res.json();
                    } else {
                        res.json().then(errorData => {
                            handleException(errorData)
                        });
                    }
                })
                .then(data => {
                    data.forEach(item => {
                        const childrenFolder = {
                            folderId: `${item.folderId}`,
                            folderName: `${item.folderName}`,
                            folderProgramId: `${item.programId}`
                        }

                        console.log("[create] 부모 폴더(" + parentFolderElement.id + ")에 대한 자손 폴더(F_" + childrenFolder.folderId + ")를 생성합니다.")

                        const folderNodeElement = createFolderElements(parentFolderElement, childrenFolder);
                        folderRightClickEvent(folderNodeElement);
                        folderArrowClickEvent(folderNodeElement);
                        folderSelectClickEvent(folderNodeElement);
                        folderCreateClickEvent(folderNodeElement);
                        folderDeleteClickEvent(folderNodeElement);
                    });
                })
                .catch(error => {
                    serverError(error);
                });
        } else {
            parentFolderElement.classList.remove('slide-down');
            parentFolderElement.classList.add('slide-none')

            setTimeout(() => {
                while (parentFolderElement.firstChild) {
                    parentFolderElement.removeChild(parentFolderElement.firstChild);
                }
            }, 400);
        }
    } else {
        console.log("[!] 하위 폴더 필드를 찾을 수 없습니다.")
    }
}

const folderToggleSelector = (folder) => {
    const parentFolderElement = document.getElementById('F_' + folder);

    if (parentFolderElement) {
        if (parentFolderElement.classList.contains('slide-none')) {
            parentFolderElement.classList.remove('slide-none');
            parentFolderElement.classList.add('slide-down');

            fetch('/s1/api/folder/show/' + folder)
                .then(res => {
                    if (res.ok) {
                        return res.json();
                    } else {
                        res.json().then(errorData => {
                            handleException(errorData)
                        });
                    }
                })
                .then(data => {
                    data.forEach(item => {
                        const childrenFolder = {
                            folderId: `${item.folderId}`,
                            folderName: `${item.folderName}`,
                            folderProgramId: `${item.programId}`
                        }

                        console.log("[create] 부모 폴더(" + parentFolderElement.id + ")에 대한 자손 폴더(F_" + childrenFolder.folderId + ")를 생성합니다.")

                        const folderNodeElement = createFolderElements(parentFolderElement, childrenFolder);
                        folderArrowToggleEvent(folderNodeElement);
                    });
                })
                .catch(error => {
                    serverError(error);
                });
        } else {
            parentFolderElement.classList.remove('slide-down');
            parentFolderElement.classList.add('slide-none')

            setTimeout(() => {
                while (parentFolderElement.firstChild) {
                    parentFolderElement.removeChild(parentFolderElement.firstChild);
                }
            }, 400);
        }
    } else {
        console.log("[!] 하위 폴더 필드를 찾을 수 없습니다.")
    }
}
