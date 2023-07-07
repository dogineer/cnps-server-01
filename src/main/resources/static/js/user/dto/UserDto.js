export class User {
    constructor(element) {
        this.element = element;
        this.account = this.element.getAttribute('data-user-account');
    }
}