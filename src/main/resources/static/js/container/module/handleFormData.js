import {validateFormData} from "./validateFormData.js";

export const handleFormData = (formElement, endpoint, dataType) => {
    const formData = validateFormData(formElement);

    if (confirm(JSON.stringify(formData) + `${dataType}을(를) 추가하시겠습니까?`)) {
        fetch(endpoint, {
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
                    console.error('서버 응답이 실패했습니다.');
                    alert("서버 응답이 실패했습니다.");
                }
            })
            .catch(error => {
                console.error('데이터 전송 중 오류가 발생했습니다:', error);
                alert(`${dataType}을(를) 추가하는 과정에서 문제가 발생했습니다.`);
            });
    } else {
        alert("취소하였습니다.");
    }
}