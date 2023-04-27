function access(account) {
    if(confirm(account+" 해당 유저를 승인합니까?") === true){
        fetch('/admin/user/access/apply/' + account, {
            method: 'PUT',
           }).then(data => {
                console.log('승인.', data);
            }).catch((error) => {
                alert("ERROR");
                console.error(error);
            });
    } else {
        alert("취소하였습니다.");
    }
}


function deleteUser(account) {
    if(confirm(account+" 삭제하시겠습니까?") === true){
        fetch('/admin/user/delete/' + account, {
            method: 'PUT',
           }).then(data => {
                alert("직원을 삭제합니다.");
                console.log('관리자 확인.', data);
            }).catch((error) => {
                alert("ERROR");
                console.error(error);
            });
    } else {
        alert("취소하였습니다.");
    }
}

