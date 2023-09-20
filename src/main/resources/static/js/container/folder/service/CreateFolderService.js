import {handleException, serverError} from "../../issue/service/IssueService.js";

const folderCreate = (formData) => {
    fetch('/folder/create/' , {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(formData)
    })
        .then(res => {
            if (res.ok) {
                console.log("[Fetch] 새로운 폴더 '" + formData.name + "'를 생성합니다.")
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
    fetch('/folder/delete/' + folderId, {
        method: 'DELETE',
    }).then(res => {
            if (res.ok) {
                console.log("[Fetch] 새로운 폴더 '" + folderId + "'를 삭제합니다.")
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
