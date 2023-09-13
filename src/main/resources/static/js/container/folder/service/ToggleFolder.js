import {fetchFolderData} from "./fetchFolderData.js";
import {FolderController} from "../controller/FolderController.js";

export {folderToggle, folderToggleSelector}

const folderToggle = (folder) => {
    const folderP = document.getElementById('F' + folder);

    if (folderP) {
        if (folderP.classList.contains('slide-none')) {
            folderP.classList.remove('slide-none');
            folderP.classList.add('slide-down');

            fetch('/folder/show/' + folder)
                .then(response => response.json())
                .then(data => {
                    data.forEach(item => {
                        const folderNode = document.createElement('div');
                        folderNode.className = 'folder-node pd-l-1';
                        folderNode.id = `${item.id}`;

                        const folderAnchor = document.createElement('a');
                        folderAnchor.className = 'folder-anchor'
                        folderAnchor.setAttribute("aria-current", "false")
                        folderAnchor.id = `${item.id}`;

                        const folderName = document.createElement('span')
                        folderName.textContent = `${item.name}`

                        const folderImg = document.createElement('img');
                        folderImg.src = '/img/folder_close.svg'
                        folderImg.alt = 'folder_close'

                        const childrenFoldersField = document.getElementById(`F${folder}`);
                        childrenFoldersField.appendChild(folderNode);
                        folderNode.append(folderAnchor)
                        folderAnchor.appendChild(folderImg);
                        folderAnchor.appendChild(folderName);

                        folderAnchor.addEventListener('click', () => {
                            console.log("click children folder = " + `${item.id}`)
                            FolderController.currentFolder(folderAnchor)
                            fetchFolderData(`${item.id}`);
                        })
                    });
                })
                .catch(error => console.log(error, "fetch 에러!"));
        } else {
            folderP.classList.remove('slide-down');
            folderP.classList.add('slide-none')

            setTimeout(() => {
                while (folderP.firstChild) {
                    folderP.removeChild(folderP.firstChild);
                }
            }, 400);
        }

    } else {
        console.log("하위 폴더 필드를 찾을 수 없습니다.")
    }
}

const folderToggleSelector = (folder) => {
    const folderP = document.getElementById('F' + folder);

    if (folderP) {
        if (folderP.classList.contains('slide-none')) {
            folderP.classList.remove('slide-none');
            folderP.classList.add('slide-down');

            fetch('/folder/show/' + folder)
                .then(response => response.json())
                .then(data => {
                    data.forEach(item => {
                        const folderSelectNode = document.createElement('div');
                        folderSelectNode.className = 'folder-node pd-l-1';
                        folderSelectNode.id = `${item.id}`;

                        const folderSelectAnchor = document.createElement('a');
                        folderSelectAnchor.className = 'folder-anchor'
                        folderSelectAnchor.setAttribute("aria-current", "false")
                        folderSelectAnchor.id = `${item.id}`;

                        const folderName = document.createElement('span')
                        folderName.textContent = `${item.name}`

                        const folderImg = document.createElement('img');
                        folderImg.src = '/img/folder_close.svg'
                        folderImg.alt = 'folder_close'

                        const childrenFoldersField = document.getElementById(`F${folder}`);
                        childrenFoldersField.appendChild(folderSelectNode);
                        folderSelectNode.append(folderSelectAnchor)
                        folderSelectAnchor.appendChild(folderImg);
                        folderSelectAnchor.appendChild(folderName);

                        folderSelectAnchor.addEventListener('click', () => {
                            console.log("click children folder = " + `${item.id}`)
                            FolderController.currentFolder(folderSelectAnchor)
                            const folderSelects = document.querySelector("#ingestRequest-form select[name='folder']");

                            if (folderSelects) {
                                for (let i = 0; i < folderSelects.length; i++) {
                                    const option = folderSelects.options[i];
                                    if (option.value === `${item.id}`) {
                                        option.selected = true;
                                        break;
                                    }
                                }
                            }
                        })
                    });
                })
                .catch(error => console.log(error, "fetch 에러!"));
        } else {
            folderP.classList.remove('slide-down');
            folderP.classList.add('slide-none')

            setTimeout(() => {
                while (folderP.firstChild) {
                    folderP.removeChild(folderP.firstChild);
                }
            }, 400);
        }

    } else {
        console.log("하위 폴더 필드를 찾을 수 없습니다.")
    }
}
