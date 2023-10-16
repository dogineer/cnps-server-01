import{ User } from "../dto/UserDto.js";

export class UserAccessService {
    static getUserAccount(event) {
        const user = new User(event.target.parentElement.parentElement);
        return user.account;
    }

    static userAccess(event) {
        const account = this.getUserAccount(event);

        if (confirm(account + " 해당 유저를 승인합니까?")) {
            fetch(`/s1/api/admin/user/access/apply/${account}`, {
                method: 'PUT',
            })
                .then(data => {
                    alert("유저를 승인합니다.");
                    console.log('승인.', data);
                    location.reload();
                })
                .catch((error) => {
                    alert("ERROR");
                    console.error(error);
                });제
        } else {
            alert("취소하였습니다.");
        }
    }
}