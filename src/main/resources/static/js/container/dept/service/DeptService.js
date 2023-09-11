import {handleFormData} from "../../module/handleFormData.js";

document.addEventListener('DOMContentLoaded', () => {
    const deptButtons = document.querySelectorAll('#dept-body #form-button');

    deptButtons.forEach(function (button) {
        button.addEventListener('click', (e) => {
            handleDeptFormClick(e);
        });
    });
});

const handleDeptFormClick = (e) => {
    const clickedElement = e.target.parentElement;
    const formId = clickedElement.id;

    switch (formId) {
        case "dept-high-add-form":
            handleFormData(clickedElement, '/admin/dept/high/add', "본부");
            break;
        case "dept-mid-add-form":
            handleFormData(clickedElement, '/admin/dept/mid/add', "부서 유형");
            break;
        case "dept-low-add-form":
            handleFormData(clickedElement, '/admin/dept/low/add', "부서");
            break;
        default:
            console.log(formId);
    }
}