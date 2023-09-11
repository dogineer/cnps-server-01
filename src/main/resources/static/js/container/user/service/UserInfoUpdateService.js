function userInfoUpdateService() {
    const form = document.getElementById('update-userInfo');
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    fetch('/admin/user/account-info/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                console.log(data)
                console.log('폼 데이터가 성공적으로 전송되었습니다.');
                alert("정보를 수정했습니다.")
                location.replace("/");
            } else {
                alert("서버 응답이 실패했습니다.");
                console.error('서버 응답이 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('데이터 전송 중 오류가 발생했습니다:', error);
            alert("정보 수정에 오류가 발생했습니다.");
        });
}