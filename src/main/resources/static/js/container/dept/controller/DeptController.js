import {fetchDataByDeptChart, fetchDataByMidDeptList} from "../service/findDeptList.js";
import {postHandleFormData, deleteHandleFormData} from "../service/FetchHandleFormData.js";

export class DeptController {
    static findDeptTypeList() {
        fetchDataByMidDeptList();
    }

    static deptChartFetch() {
        const currentElement = document.getElementById('deptChartSelect');
        const deptId = currentElement.value;
        fetchDataByDeptChart(deptId);

        currentElement.addEventListener('change', () => {
            const deptChart = document.getElementById('deptChartSelect');
            const changeDeptId = deptChart.value;
            fetchDataByDeptChart(changeDeptId);
        })
    };

    static deptAddAction(e) {
        const clickedElement = e.target.parentElement;
        const formId = clickedElement.id;

        switch (formId) {
            case "dept-high-add-form":
                postHandleFormData(clickedElement, "본부");
                break;
            case "dept-mid-add-form":
                postHandleFormData(clickedElement, "부서 유형");
                break;
            case "dept-low-add-form":
                postHandleFormData(clickedElement, "부서");
                break;
            default:
                break;
        }
    }

    static deptDeleteAction(e) {
        const clickedElement = e.target.parentElement;

        const dept = {
            deptId: clickedElement.getAttribute('data-dept-id'),
            deptPath: clickedElement.getAttribute('data-dept-tree')
        }

        deleteHandleFormData(dept)
    }
}