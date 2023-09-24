import {DeptController} from "../controller/DeptController.js";

const lowDeptAddTypeSelectEvent = () => {
    const deptButtons = document.querySelectorAll('#dept-high-select');
    DeptController.findDeptTypeList();

    deptButtons.forEach((button) => {
        button.addEventListener('change', (e) => {
            console.log("[Fetch] 현재 부서 타입을 변경합니다.")
            DeptController.findDeptTypeList();
        });
    });
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

    deptDeleteBtn.forEach((button)=>{
        button.addEventListener('click', (e) => {
            DeptController.deptDeleteAction(e)
        })
    })
}

export {lowDeptAddTypeSelectEvent, deptAddClickEvent, deptDeleteClickEvent, onLoadDataFetch};