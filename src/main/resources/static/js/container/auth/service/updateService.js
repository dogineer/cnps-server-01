import {handleException, serverError} from "../../issue/service/IssueService.js";

export const updateService = (formData, endPoint) => {
    fetch(endPoint, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(res => {
            if (res.ok) {
                alert("수정이 완료되었습니다. 로그인 페이지로 돌아갑니다.")
                window.location.replace("/");
            } else {
                res.json().then(errorData => {
                    handleException(errorData)
                });
            }
        })
        .catch(error => {
            console.error('데이터 전송 중 오류가 발생했습니다.', error);
            serverError(error);
        });
}
