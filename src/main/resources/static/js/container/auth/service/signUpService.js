import {handleException, serverError} from "../../issue/service/IssueService.js"
import {validateFormData} from "../../module/validateFormData.js";

export const signup = () => {
    const form = document.getElementById('signup-form');
    const formData = validateFormData(form)

    fetch('/s1/api/user/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(res => {
            if (res.ok) {
                console.log('폼 데이터가 성공적으로 전송되었습니다.');
                alert("회원 가입을 마쳤습니다. 관리자의 승인을 기다려주새요.")
                location.replace("/");
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
