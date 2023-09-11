import {handleException} from "../../issue/service/IssueService.js"
import {validateFormData} from "../../module/validateFormData.js";

export const signup = () => {
    const form = document.getElementById('signup-form');
    const formData = validateFormData(form)

    fetch('auth/signup', {
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
                res.json()
                    .then(errorData => {
                        handleException(errorData)
                        console.error('서버 응답이 실패했습니다.');
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
