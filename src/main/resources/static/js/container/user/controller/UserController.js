import {UserAccessService} from "../service/UserAccessService.js";
import {UserDeleteService} from '../service/UserDeleteService.js';
import {UserDetailOpenService} from '../service/UserDetailOpenService.js';

document.addEventListener('DOMContentLoaded', () => {
    var userAccessBtns = document.querySelectorAll('#user-access-btn');
    var userLeaveBtns = document.querySelectorAll('#user-leave-btn');
    var userDeleteBtns = document.querySelectorAll('#user-delete-btn');
    var userDetailOpenBtn = document.querySelectorAll('#user-detail-management');

    userAccessBtns.forEach(function (button) {
        button.addEventListener('click', function (e) {
            UserAccessService.userAccess(e);
            e.stopPropagation();
        });
    });

    userLeaveBtns.forEach(function (button) {
        button.addEventListener('click', function (e) {
            UserDeleteService.userLeave(e);
            e.stopPropagation();
        });
    });

    userDeleteBtns.forEach(function (button) {
        button.addEventListener('click', function (e) {
            UserDeleteService.userDelete(e);
            e.stopPropagation();
        });
    });

    userDetailOpenBtn.forEach(function (button) {
        button.addEventListener('click', function (e) {
            UserDetailOpenService.userDetailOpen(e)
            e.stopPropagation();
        });
    });
});