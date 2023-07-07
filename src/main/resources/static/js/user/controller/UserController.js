import {UserAccessService} from '../service/UserAccessService.js';
import {UserDeleteService} from '../service/UserDeleteService.js';

document.addEventListener('DOMContentLoaded', () => {
    var userAccessBtns = document.querySelectorAll('#user #user-access-btn');
    userAccessBtns.forEach(function (button) {
        button.addEventListener('click', function (event) {
            UserAccessService.userAccess(event);
        });
    });

    var userDeleteBtns = document.querySelectorAll('#user #user-delete-btn');
    userDeleteBtns.forEach(function (button) {
        button.addEventListener('click', function (event) {
            UserDeleteService.userDelete(event);
        });
    });
});