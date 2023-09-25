import {handleException, serverError} from "../../issue/service/IssueService.js";
import {programDeleteClickEvent} from "../event/ProgramEvent.js";

const fetchProgramType = () => {
    const programElement = document.getElementById('program-type');
    const programId = programElement.value;

    fetch('/admin/program/find/program/' + programId, {
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
            const listItem = document.getElementById('program');
            listItem.innerHTML = '';

            data.forEach(item => {
                const itemOptionElement = document.createElement('option');
                listItem.appendChild(itemOptionElement)
                itemOptionElement.text = item.programName
                itemOptionElement.value = item.programId
            });
        })
        .catch(error => {
            serverError(error);
        });
}

const fetchDataByProgramChart = (programId) => {
    fetch('/admin/program/find/tree/' + programId, {
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
            const listItem = document.getElementById('program-chart-body');
            listItem.innerHTML = '';

            data.forEach(item => {
                const itemTrElement = document.createElement('tr');
                const itemProgramIdElement = document.createElement('td');
                const itemProgramPathElement = document.createElement('td');
                const itemProgramActionElement = document.createElement('td');

                listItem.appendChild(itemTrElement);
                itemTrElement.setAttribute("data-program-id", item.programId)
                itemTrElement.setAttribute("data-program-tree", item.programPath)

                itemProgramIdElement.textContent = item.programId;
                itemTrElement.appendChild(itemProgramIdElement);

                itemProgramPathElement.textContent = item.programPath;
                itemTrElement.appendChild(itemProgramPathElement);

                itemProgramActionElement.textContent = "삭제";
                itemProgramActionElement.id = "delete-program-button";

                itemTrElement.appendChild(itemProgramActionElement);
            });

            programDeleteClickEvent();
        })
        .catch(error => {
            serverError(error);
        });
}

export {fetchProgramType, fetchDataByProgramChart}