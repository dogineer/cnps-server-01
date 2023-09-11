import {IssueController} from "../container/issue/controller/IssueController.js";
import {AuthContoller} from "../container/auth/controller/AuthContoller.js";
import {DarkMode} from "../container/module/DarkMode.js";

document.addEventListener('DOMContentLoaded', () => {
    DarkMode.darkModeValidate();

    const toggleSwitch = document.getElementById('toggleSwitch');
    toggleSwitch.addEventListener('click', () => {
        DarkMode.toggle(toggleSwitch);
    })

    const sidebar = document.getElementsByClassName('sidebar')[0];
    const sidebarToggle = document.querySelector('.sidebar-toggle');
    sidebarToggle.addEventListener('click', () => {
        sidebar.classList.toggle('collapsed');
    })

    const logout = document.getElementById("logout");
    logout.addEventListener('click', () => {
        AuthContoller.logout();
    })

    const issueForm = document.getElementById("errorIssue");
    const issueClose = issueForm.querySelector('#issue-close');
    issueClose.addEventListener('click', () => {
        IssueController.errorModalClose(issueForm)
    });
});



