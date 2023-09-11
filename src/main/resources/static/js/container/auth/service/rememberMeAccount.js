export const rememberMeAccount = () => {
    const rememberMe = document.getElementById("rememberMe").checked;

    if (rememberMe) {
        const account = this.querySelector("#account").value;
        localStorage.setItem("savedAccount", account);
    } else {
        localStorage.removeItem("savedAccount");
    }
};