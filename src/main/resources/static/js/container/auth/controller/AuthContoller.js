import {loginService} from "../service/loginService.js";
import {signup} from "../service/signUpService.js";
import {rememberMeAccountService} from "../service/rememberMeAccount.js";
import {validateFormData} from "../../module/validateFormData.js";

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
        fetch("/auth/logout", {
            method: 'POST'
        }).then(() => location.replace("/"));
    }

}