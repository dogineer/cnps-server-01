import{ User } from "../dto/UserDto.js";

export class UserAccessService {
    static getUserAccount(event) {
        const user = new User(event.target.parentElement.parentElement);
        return user.account;
    }

    static userAccess(event) {
        const account = this.getUserAccount(event);

        if (confirm(account + " 해당 유저를 승인합니까?")) {
            fetch(`/admin/user/access/apply/${account}`, {
                method: 'PUT',
            })
                .then(data => {
                    console.log('승인.', data);
                })
                .catch((error) => {
                    alert("ERROR");
                    console.error(error);
                });
        } else {
            alert("취소하였습니다.");
        }
    }
}