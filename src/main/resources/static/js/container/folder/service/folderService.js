import {FolderController} from "../controller/FolderController.js";
import {ClipController} from "../../clip/controller/ClipController.js";
import {handleException, serverError} from "../../issue/service/IssueService.js";

export {currentFolder, fetchDataForFolder, folderToggleSelector}

const currentFolder = (target) => {
    const allAnchorElements = document.querySelectorAll(".folder-anchor")
    allAnchorElements.forEach((element) => {
        element.setAttribute('aria-current', 'false')
    })

    target.setAttribute('aria-current', 'true')
}

const createFolderElements = (parentFolderElement, childrenFolderId, childrenFolderName) => {
    const folderNodeContainerElement = document.createElement('div');
    folderNodeContainerElement.className = 'folder-node pd-l-1';
    folderNodeContainerElement.id = "N_" + childrenFolderId;

    const folderTree = document.createElement('div');
    folderTree.className = "folder-tree";

    const folderArrowMenuElement = document.createElement('div');
    folderArrowMenuElement.className = "arrow-menu right"

    const folderAnchorElement = document.createElement('a');
    folderAnchorElement.className = 'folder-anchor'
    folderAnchorElement.setAttribute("aria-current", "false")
    folderAnchorElement.id = childrenFolderId;

    const folderNameElement = document.createElement('span')
    folderNameElement.textContent = childrenFolderName;

    const folderImg = document.createElement('i');
    folderImg.className = "folder-img f-close";

    const folderActionMenu = document.createElement('div');
    folderActionMenu.id = "FA_" + childrenFolderId;
    folderActionMenu.className = "folder-action";
    folderActionMenu.style = "display: none;";

    const actionCreateButton = document.createElement('div');
    actionCreateButton.className = "folder_create_btn"
    actionCreateButton.innerText = "폴더 생성";

    const actionDeleteButton = document.createElement('div');
    actionDeleteButton.className = "folder_delete_btn"
    actionDeleteButton.innerText = "폴더 삭제";

    const folderChildrenElement = document.createElement('div');
    folderChildrenElement.id = "F_" + childrenFolderId
    folderChildrenElement.className = "folder-children slide-none";

    parentFolderElement.append(folderNodeContainerElement);
    folderNodeContainerElement.append(folderTree);

    folderActionMenu.append(actionCreateButton);
    folderActionMenu.append(actionDeleteButton);

    folderTree.append(folderArrowMenuElement);
    folderTree.append(folderAnchorElement);

    folderAnchorElement.append(folderImg);
    folderAnchorElement.append(childrenFolderName);

    folderNodeContainerElement.append(folderActionMenu);
    folderNodeContainerElement.append(folderChildrenElement);

    return {folderNodeContainerElement, folderArrowMenuElement, folderAnchorElement, folderImg}
}

const fetchDataForFolder = (folderId) => {
    const parentFolderElement = document.getElementById('F_' + folderId);

    if (parentFolderElement) {
        if (parentFolderElement.classList.contains('slide-none')) {
            parentFolderElement.classList.remove('slide-none');
            parentFolderElement.classList.add('slide-down');

            fetch('/folder/show/' + folderId)
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
                        const childrenFolderId = `${item.id}`;
                        const childrenFolderName = `${item.name}`;
                        console.log("[create] 부모 폴더(" + parentFolderElement.id + ")에 대한 자손 폴더(" + childrenFolderId + ")를 생성합니다.")

                        const folderElement = createFolderElements(parentFolderElement, childrenFolderId, childrenFolderName);
                        const folderNodeContainerElement = folderElement.folderNodeContainerElement;
                        const folderArrowMenuElement = folderElement.folderArrowMenuElement;
                        const folderAnchorElement = folderElement.folderAnchorElement;
                        const folderImg = folderElement.folderImg;

                        folderAnchorElement.addEventListener('mousedown', (e) => {
                            if (e.button === 2) {
                                const folderAnchorElements = folderNodeContainerElement.querySelector('.folder-anchor');
                                const folderId = folderAnchorElements.id;
                                console.log("[Click] 해당 폴더를 우클릭 하였습니다. folderId = " + folderId);
                                FolderController.showFolderActionMenu(folderId)
                            }
                        });

                        folderArrowMenuElement.addEventListener('click', () => {
                            console.log("[Fetch] 해당 폴더의 자손 폴더를 가져옵니다. folderId = " + childrenFolderId)
                            const action = folderArrowMenuElement.classList.contains("right")
                            FolderController.changeFolderImageAndArrow(folderArrowMenuElement, folderImg, action)
                            FolderController.clickFolderArrow(folderAnchorElement, childrenFolderId)
                        })

                        folderAnchorElement.addEventListener('click', () => {
                            console.log("[Fetch] 해당 폴더의 클립 데이터를 가져옵니다. folderId = " + childrenFolderId)
                            FolderController.clickFolderFetchData(folderAnchorElement, childrenFolderId);

                            const modalElement = document.getElementById('clipPreview');
                            modalElement.addEventListener('hidden.bs.modal', () => {
                                const clipPreviewBodyElement = document.getElementById("clip-preview-body");
                                ClipController.removeVideoElement(clipPreviewBodyElement);
                            });
                        })
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

            fetch('/folder/show/' + folder)
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
                        const childrenFolderId = `${item.id}`;
                        const childrenFolderName = `${item.name}`;
                        console.log("[create] 부모 폴더(" + parentFolderElement.id + ")에 대한 자손 폴더(" + childrenFolderId + ")를 생성합니다.")

                        const folderElement = createFolderElements(parentFolderElement, childrenFolderId, childrenFolderName);
                        const folderArrowMenuElement = folderElement.folderArrowMenuElement;
                        const folderAnchorElement = folderElement.folderAnchorElement;
                        const folderImg = folderElement.folderImg;

                        folderArrowMenuElement.addEventListener('click', () => {
                            console.log("[Fetch] 해당 폴더의 자손 폴더를 가져옵니다. folderId = " + childrenFolderId)
                            const action = folderArrowMenuElement.classList.contains("right")

                            FolderController.changeFolderImageAndArrow(folderArrowMenuElement, folderImg, action)
                            FolderController.clickFolderToggleSelect(folderAnchorElement, childrenFolderId)
                        })

                        folderAnchorElement.addEventListener('click', () => {
                            const folderSelects = document.querySelector("#ingestRequest-form select[name='folder']");
                            if (folderSelects) {
                                for (let i = 0; i < folderSelects.length; i++) {
                                    const option = folderSelects.options[i];
                                    if (option.value === childrenFolderId) {
                                        console.log("폴더 선택 : " + childrenFolderId)
                                        option.selected = true;
                                        break;
                                    }
                                }
                            }
                        })
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
