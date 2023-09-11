import {handleFormData} from "../../module/handleFormData.js";

document.addEventListener('DOMContentLoaded', () => {
    const teamButtons = document.querySelectorAll('#team-body #form-button');

    teamButtons.forEach(function (button) {
        button.addEventListener('click', (e) => {
            handleTeamFormClick(e);
        });
    });
});

const handleTeamFormClick = (e) => {
    const clickedElement = e.target.parentElement;
    const formId = clickedElement.id;

    switch (formId) {
        case "team-add-form":
            handleFormData(clickedElement, '/admin/team/add', "프로그램 추가");
            break;
        case "team-delete-form":
            handleFormData(clickedElement, '/admin/team/delete', "프로그램 삭제");
            break;
        default:
            console.log(formId);
    }
}