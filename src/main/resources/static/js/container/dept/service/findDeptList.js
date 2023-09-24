import {handleException, serverError} from "../../issue/service/IssueService.js";
import {deptDeleteClickEvent} from "../event/DeptEvent.js";


const fetchDataByMidDeptList = () => {
    const deptElement = document.getElementById('dept-high-select');
    const deptId = deptElement.value;

    fetch('/admin/dept/find/type/' + deptId, {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    })
        .then(res => {
            if (res.ok) {
                return res.json();
            } else {
                res.json().then(errorData => {
                    handleException(errorData)
                });
            }
        })
        .then(data => {
            const listItem = document.getElementById('dept-mid-select');
            listItem.innerHTML = '';

            data.forEach(item => {
                const itemOptionElement = document.createElement('option');
                listItem.appendChild(itemOptionElement)
                itemOptionElement.text = item.deptName
                itemOptionElement.value = item.deptId
            });
        })
        .catch(error => {
            serverError(error);
        });
}

const fetchDataByDeptChart = (deptId) => {
    fetch('/admin/dept/find/tree/' + deptId, {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    })
        .then(res => {
            if (res.ok) {
                return res.json();
            } else {
                res.json().then(errorData => {
                    handleException(errorData)
                });
            }
        })
        .then(data => {
            const listItem = document.getElementById('deptChartBody');
            listItem.innerHTML = '';

            data.forEach(item => {
                const itemTrElement = document.createElement('tr');
                const itemDeptIdElement = document.createElement('td');
                const itemDeptPathElement = document.createElement('td');
                const itemDeptActionElement = document.createElement('td');

                listItem.appendChild(itemTrElement);
                itemTrElement.setAttribute("data-dept-id", item.deptId)
                itemTrElement.setAttribute("data-dept-tree", item.deptPath)

                itemDeptIdElement.textContent = item.deptId;
                itemTrElement.appendChild(itemDeptIdElement);

                itemDeptPathElement.textContent = item.deptPath;
                itemTrElement.appendChild(itemDeptPathElement);

                itemDeptActionElement.textContent = "삭제";
                itemDeptActionElement.id = "delete-dept-button";

                itemTrElement.appendChild(itemDeptActionElement);
            });

            deptDeleteClickEvent();
        })
        .catch(error => {
            serverError(error);
        });
}

export {fetchDataByMidDeptList, fetchDataByDeptChart}