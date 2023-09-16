import {User} from "../dto/UserDto.js";

function fetchDataSelectOptions(url, data, targetElement, id) {
    return fetch(url)
        .then(res => res.json())
        .catch((error) => {
            alert("ERROR");
            console.error(error);
        });
}

export class UserDetailOpenService {
    static getUserAccount(e) {
        const user = new User(e.target.parentElement);
        return user.account;
    }

    static userDetailOpen(e) {
        const account = this.getUserAccount(e);
        console.log(account)

        if (account === null) {
            throw new Error('account가 null 데이터 입니다.');
        }

        if (confirm(account + "의 정보를 수정하기 위해 열람하시겠습니까?")) {
            fetch(`/admin/user/account-info/${account}`, {
                method: 'GET',
            })
                .then(res => res.json())
                .then(data => {
                    const modal = new bootstrap.Modal(document.getElementById('AdminServiceMemberInfo'));

                    const userAccount = document.getElementById('user-account');
                    const userName = document.getElementById('user-name');
                    const userPhone = document.getElementById('user-phone');
                    const userRank = document.getElementById('user-rank');
                    const userDept = document.getElementById('user-dept');
                    const userTeam = document.getElementById('user-team');
                    const userBirth = document.getElementById('user-birth');
                    const userGender = document.getElementById('user-gender');
                    const userEmail = document.getElementById('user-email');
                    const userJoinedAt = document.getElementById('user-joined_at');
                    const userApprovedAt = document.getElementById('user-approved_at');

                    [userDept, userTeam, userRank].forEach(element => {
                        while (element.firstChild) {
                            element.removeChild(element.firstChild);
                        }
                    });

                    fetchDataSelectOptions('/admin/dept/list/all')
                        .then(list => {
                            list.forEach(item => {
                                const option = document.createElement('option');
                                option.value = item.deptId;
                                option.textContent = item.deptName;
                                userDept.appendChild(option);

                                if (item.deptId === data.deptId) {
                                    option.selected = true;
                                }
                            });
                        });

                    fetchDataSelectOptions('/admin/rank/list')
                        .then(list => {
                            list.forEach(item => {
                                const option = document.createElement('option');
                                option.value = item.rankId;
                                option.textContent = item.rankName;
                                userRank.appendChild(option);

                                if (item.rankId === data.rankId) {
                                    option.selected = true;
                                }
                            });
                        });

                    fetchDataSelectOptions('/team/list')
                        .then(list => {
                            list.forEach(item => {
                                const option = document.createElement('option');
                                option.value = item.teamId;
                                option.textContent = item.teamName;
                                userTeam.appendChild(option);

                                if (item.teamId === data.teamId) {
                                    option.selected = true;
                                }
                            });
                        });

                    userAccount.value = data.account;
                    userName.value = data.name;
                    userPhone.value = data.phone
                    userBirth.textContent = data.birth;
                    userGender.value = data.gender;
                    userEmail.value = data.email;
                    userJoinedAt.textContent = data.joined_at
                    userApprovedAt.textContent = data.approved_at

                    modal.show();

                    console.log('유저의 정보를 불러옵니다.\n', data);
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