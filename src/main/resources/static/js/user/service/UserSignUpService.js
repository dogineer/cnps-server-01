function signup() {
    const form = document.getElementById('signup-form');
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    fetch('auth/signup', {
        method: 'POST',
        headers: { 
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                console.log(data)
                console.log('폼 데이터가 성공적으로 전송되었습니다.');
                alert("회원 가입을 마쳤습니다. 관리자의 승인을 기다려주새요.")
                location.replace("/");
            } else {
                console.error('서버 응답이 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('데이터 전송 중 오류가 발생했습니다:', error);
            alert("가입에 오류가 생겼습니다.");
        });
}