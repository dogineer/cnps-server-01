import {fetchDataByProgramChart} from "../service/programTypeList.js";
import {deleteHandleFormData, postHandleFormData} from "../service/FetchHandleProgramFormData.js";


export class ProgramController {
    static programProgramChartFetch() {
        const currentElement = document.getElementById('program-select');
        const programId = currentElement.value;
        fetchDataByProgramChart(programId);

        currentElement.addEventListener('change', () => {
            const currentElement = document.getElementById('program-select');
            const programId = currentElement.value;
            fetchDataByProgramChart(programId);
        })
    }

    static programAddAction(e){
        const clickedElement = e.target.parentElement;
        const formId = clickedElement.id;

        switch (formId) {
            case "program-type-add-form":
                postHandleFormData(clickedElement, "프로그램 유형")
                break;
            case "program-add-form":
                postHandleFormData(clickedElement, "프로그램")
                break;
            default:
                break;
        }
    }

    static programDeleteAction(e) {
        const clickedElement = e.target.parentElement;

        const program = {
            programId: clickedElement.getAttribute('data-program-id'),
            programPath: clickedElement.getAttribute('data-program-tree')
        }

        deleteHandleFormData(program)
    }
}