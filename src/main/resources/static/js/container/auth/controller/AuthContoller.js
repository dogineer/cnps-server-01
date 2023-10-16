import {loginService} from "../service/loginService.js";
import {signup} from "../service/signUpService.js";
import {rememberMeAccountService} from "../service/rememberMeAccount.js";
import {validateFormData} from "../../module/validateFormData.js";
import {updateService} from "../service/updateService.js";

export class AuthContoller {
    static login(loginForm) {
        const formData = validateFormData(loginForm);
        loginService(formData);
    }

    static signup(signupForm) {
        const formData = validateFormData(signupForm);
        signup(formData);
    }

    static rememberMeAccount(isChecked, account) {
        rememberMeAccountService(isChecked, account);
    }

    static logout() {
        if (confirm('로그아웃 하시겠습니까?')) {
            fetch("/s1/api/auth/logout", {
                method: 'POST'
            }).then(() => location.replace("/"));
        }
    }

    static programUpdate(programUpdateForm) {
        const endPoint = '/s1/api/user/program/update'
        const formData = validateFormData(programUpdateForm);
        console.log(formData)
        updateService(formData, endPoint);
    }

    static passwordUpdate(passwordChangeForm) {
        const endPoint = '/s1/api/user/password/update';
        const formData = validateFormData(passwordChangeForm);
        updateService(formData, endPoint);
    }
}