export const rememberMeAccountService = (isChecked, account) => {
    if (isChecked) {
        localStorage.setItem("savedAccount", account);
    } else {
        localStorage.removeItem("savedAccount");
    }
};