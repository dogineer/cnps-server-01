import {handleException} from "../../issue/service/IssueService.js";

export const loginService = (formData) => {
    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(res => {
            if (res.ok) {
                location.reload()
            } else {
                res.json().then(errorData => {
                    handleException(errorData)
                });
            }
        })
        .catch(error => {
            console.error('데이터 전송 중 오류가 발생했습니다.', error);

            const errorData = {
                errorCode: "SERVER ERROR",
                errorMessage: "서버에 오류가 발생했습니다."
            };

            handleException(errorData)
        });
}
