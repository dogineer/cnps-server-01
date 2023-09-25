import {IssueController} from "../container/issue/controller/IssueController.js";
import {AuthContoller} from "../container/auth/controller/AuthContoller.js";
import {DarkMode} from "../container/module/DarkMode.js";

document.addEventListener('DOMContentLoaded', () => {
    window.oncontextmenu = () => {
        return false;
    };

    const programUpdateForm = document.getElementById("program-update-form");
    const programUpdateButton = programUpdateForm.querySelector("#update-button");

    programUpdateButton.addEventListener('click', (e) => {
        e.preventDefault();
        AuthContoller.programUpdate(programUpdateForm);
    });

    const passwordChangeForm = document.getElementById("password-update-form");
    const passwordUpdateButton = passwordChangeForm.querySelector("#update-button");

    passwordUpdateButton.addEventListener('click', (e) => {
        e.preventDefault();
        AuthContoller.passwordUpdate(passwordChangeForm);
    });

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


    const currentUrl = window.location.pathname;
    const sidebarLinks = document.querySelectorAll('.sidebar-link');

    sidebarLinks.forEach(link => {
        const linkUrl = link.getAttribute('href');

        if (linkUrl === currentUrl) {
            link.classList.add("current")
            link.setAttribute('aria-current', 'page');

            const collapseElement = link.closest('.collapse');
            if (collapseElement) {
                collapseElement.classList.add('show');
            }
        }
    });


    const issueForm = document.getElementById("errorIssue");
    const issueClose = issueForm.querySelector('#issue-close');
    issueClose.addEventListener('click', () => {
        IssueController.errorModalClose(issueForm)
    });

    issueForm.addEventListener('click', () => {
        IssueController.errorModalClose(issueForm)
    });
});



