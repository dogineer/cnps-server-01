import {ProgramController} from "../controller/ProgramController.js";

const onLoadProgramChartDataFetch = () => {
    ProgramController.programProgramChartFetch();
}

const programAddClickEvent = () => {
    const programFormButton = document.querySelectorAll("#program-body #form-button");

    programFormButton.forEach((button) => {
        button.addEventListener('click', (e) => {
            ProgramController.programAddAction(e);
        })
    })
}

const programDeleteClickEvent = () => {
    const programDeleteButton = document.querySelectorAll('#delete-program-button');

    programDeleteButton.forEach((button)=>{
        button.addEventListener('click', (e) => {
            ProgramController.programDeleteAction(e);
        })
    })
}

export {onLoadProgramChartDataFetch, programAddClickEvent, programDeleteClickEvent};
