import {AuthContoller} from "../container/auth/controller/AuthContoller.js";
import {IssueController} from "../container/issue/controller/IssueController.js";
import {deptSelectEvent} from "../container/dept/event/DeptEvent.js";

document.addEventListener('DOMContentLoaded', () => {
    deptSelectEvent();

    const signupForm = document.getElementById("signup-form");
    const signUpButton = signupForm.querySelector('#form-button');

    signUpButton.addEventListener('click', () => {
        AuthContoller.signup(signupForm);
    });


    const loginForm = document.getElementById("login-form");
    const loginButton = loginForm.querySelector("#form-button");
    const inputAccount = loginForm.querySelector("#account");
    const rememberMeCheckbox = loginForm.querySelector("#rememberMe");
    const savedAccount = localStorage.getItem("savedAccount");

    if (savedAccount) {
        inputAccount.value = savedAccount;
        rememberMeCheckbox.checked = true;
    }

    loginButton.addEventListener('click', (e) => {
        e.preventDefault();
        AuthContoller.login(loginForm);
        AuthContoller.rememberMeAccount(rememberMeCheckbox.checked, inputAccount.value);
    });

    const issueForm = document.getElementById("errorIssue");
    const issueClose = issueForm.querySelector('#issue-close');
    issueClose.addEventListener('click', (e) => {
        IssueController.errorModalClose(issueForm)
    });

    issueForm.addEventListener('click', () => {
        IssueController.errorModalClose(issueForm)
    });
});



