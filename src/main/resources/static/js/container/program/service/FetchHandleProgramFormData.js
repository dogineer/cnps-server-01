import {validateFormData} from "../../module/validateFormData.js";
import {handleException, serverError} from "../../issue/service/IssueService.js";

const postHandleFormData = (formElement, dataType) => {
    const formData = validateFormData(formElement);

    if (confirm(JSON.stringify(formData) + `${dataType}을(를) 추가하시겠습니까?`)) {
        fetch('/admin/program/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(res => {
                if (res.ok) {
                    console.log(formData);
                    console.log('폼 데이터가 성공적으로 전송되었습니다.');
                    alert(`${dataType}을(를) 추가하였습니다.`);
                } else {
                    res.json().then(errorData => {
                        handleException(errorData)
                    });
                }
            })
            .catch(error => {
                serverError(error);
            });
    } else {
        alert("취소하였습니다.");
    }
}

const deleteHandleFormData = (program) => {
    const programAlart = program.programPath + `(` + program.programId + `)`;

    if (confirm(programAlart + `)를 삭제 하시겠습니까?`)) {
        fetch('/admin/program/delete/' + program.programId, {
            method: 'DELETE',
        }).then(res => {
            if (res.ok) {
                alert(programAlart + `을(를) 삭제하였습니다.`);
                location.reload();
            } else {
                res.json().then(errorData => {
                    handleException(errorData)
                });
            }
        })
            .catch(error => {
                serverError(error);
            });
    } else {
        alert("취소하였습니다.");
    }
}

export {postHandleFormData, deleteHandleFormData}