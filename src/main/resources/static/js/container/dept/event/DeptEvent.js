import {DeptController} from "../controller/DeptController.js";

const lowDeptAddTypeSelectEvent = () => {
    const deptButtons = document.querySelectorAll('#dept-high-select');
    DeptController.findDeptTypeList();

    deptButtons.forEach((button) => {
        button.addEventListener('change', () => {
            console.log("[Fetch] 현재 부서 타입을 변경합니다.")
            DeptController.findDeptTypeList();
        });
    });
}

const deptSelectEvent = () => {
    const deptHighElement = document.getElementById('dept-high-select');
    const deptMidElement = document.getElementById('dept-mid-select');
    const deptLowElement = document.getElementById('dept-low-select');

    const findDeptLowListAndUpdate = (deptElement, nextDeptElement) => {
        const deptId = deptElement.value;
        return DeptController.findDeptItemAndChange(deptId, nextDeptElement);
    };

    const initDeptSelect = () => {
        findDeptLowListAndUpdate(deptHighElement, deptMidElement)
            .then(() => findDeptLowListAndUpdate(deptMidElement, deptLowElement));
    };

    deptHighElement.addEventListener('change', () => {
        findDeptLowListAndUpdate(deptHighElement, deptMidElement)
            .then(() => findDeptLowListAndUpdate(deptMidElement, deptLowElement));
    });

    deptMidElement.addEventListener('change', () => {
        findDeptLowListAndUpdate(deptMidElement, deptLowElement);
    });

    initDeptSelect();
}

const onLoadDataFetch = () => {
    console.log("[Fetch] 현재 부서 데이터를 패치합니다.")
    DeptController.deptChartFetch();
}

const deptAddClickEvent = () => {
    const deptButtons = document.querySelectorAll('#dept-body #form-button');

    deptButtons.forEach((button) => {
        button.addEventListener('click', (e) => {
            DeptController.deptAddAction(e);
        });
    });
}

const deptDeleteClickEvent = () => {
    const deptDeleteBtn = document.querySelectorAll('#delete-dept-button');

    deptDeleteBtn.forEach((button) => {
        button.addEventListener('click', (e) => {
            DeptController.deptDeleteAction(e)
        })
    })
}

export {lowDeptAddTypeSelectEvent, deptAddClickEvent, deptDeleteClickEvent, onLoadDataFetch, deptSelectEvent};