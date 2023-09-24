import {ProgramController} from "../controller/ProgramController.js";

const teamAddSelectEvent = () => {
    const programTypeBtn = document.querySelectorAll('#program-type');
    ProgramController.findProgramTypeList();

    programTypeBtn.forEach((button) => {
        button.addEventListener('change', () => {
            console.log("[Fetch] 유형 선택으로 인한 프로그램 선택에 맞는 데이터를 패치합니다.")
            ProgramController.findProgramTypeList();
        });
    })
}

const onLoadProgramTeamChartDataFetch = () => {
    ProgramController.programTeamChartFetch();
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

export {teamAddSelectEvent, onLoadProgramTeamChartDataFetch, programAddClickEvent, programDeleteClickEvent};
