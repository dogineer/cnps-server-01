document.addEventListener('DOMContentLoaded', () => {
    const savedAccount = localStorage.getItem("savedAccount");
    const accountInput = document.querySelector(".form-login #account");
    const rememberMeCheckbox = document.getElementById("rememberMe");

    if (savedAccount) {
        accountInput.value = savedAccount;
        rememberMeCheckbox.checked = true;
    }

    document.querySelector('.form-login')
        .addEventListener('submit', function () {
            const rememberMe = document.getElementById("rememberMe").checked;

            if (rememberMe) {
                const account = this.querySelector("#account").value;
                localStorage.setItem("savedAccount", account);
            } else {
                localStorage.removeItem("savedAccount");
            }
        });
});