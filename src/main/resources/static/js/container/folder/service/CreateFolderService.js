import {handleException, serverError} from "../../issue/service/IssueService.js";

const folderCreate = (formData) => {
    fetch('/s1/api/folder/create/' , {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(formData)
    })
        .then(res => {
            if (res.ok) {
                console.log("[Fetch] 새로운 폴더(F_" + formData.folderName + ")를 생성합니다.")
                location.reload()
            } else {
                res.json().then(errorData => {
                    handleException(errorData)
                });
            }
        })
        .catch(error => {
            serverError(error);
        });
}

const folderDelete = (folderId) => {
    fetch('/s1/api/folder/delete/' + folderId, {
        method: 'DELETE',
    }).then(res => {
            if (res.ok) {
                console.log("[Fetch] 폴더(F_" + folderId + ")를 삭제합니다.")
                location.reload()
            } else {
                res.json().then(errorData => {
                    handleException(errorData)
                });
            }
        })
        .catch(error => {
            serverError(error);
        });
}

export {folderCreate, folderDelete}
