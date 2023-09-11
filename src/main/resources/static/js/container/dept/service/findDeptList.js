document.addEventListener('DOMContentLoaded', () => {
    const deptButtons = document.querySelectorAll('#dept-high-select');
    fetchDataByMidDeptList();

    deptButtons.forEach(function (button) {
        button.addEventListener('change', (e) => {
            fetchDataByMidDeptList();
        });
    });
});

function fetchDataByMidDeptList() {
    const deptElement = document.getElementById('dept-high-select');
    const deptId = deptElement.value;

    fetch('/admin/dept/find/type/' + deptId, {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    })
        .then(function (response) {
            if (response.ok) {
                console.log('GET success. fetchDataByMidDeptList');
                return response.json();
            }
            throw new Error('GET failed.');
        })
        .then(data => {
            const listItem = document.getElementById('dept-mid-select');
            listItem.innerHTML = '';

            data.forEach(item => {
                const itemOptionElement = document.createElement('option');
                listItem.appendChild(itemOptionElement)
                itemOptionElement.text = item.name
                itemOptionElement.value = item.id
            });
        })
        .catch(error => {
            console.error(error);
        });
}

function deptChartLoad() {
    const selectElement = document.getElementById('deptChartSelect');
    selectElement.addEventListener('change', fetchDataByDeptChart);
    fetchDataByDeptChart();
}

function fetchDataByDeptChart() {
    const deptChart = document.getElementById('deptChartSelect');
    const deptId = deptChart.value;

    fetch('/admin/dept/find/tree/' + deptId, {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    })
        .then(function (response) {
            if (response.ok) {
                console.log('GET success. deptChartList');
                return response.json();
            }
            throw new Error('GET failed.');
        })
        .then(data => {
            const listItem = document.getElementById('deptChartBody');
            listItem.innerHTML = '';

            data.forEach(item => {
                const itemTrElement = document.createElement('tr');
                const itemTd1Element = document.createElement('td');
                const itemTd2Element = document.createElement('td');

                listItem.appendChild(itemTrElement);

                itemTd1Element.textContent = item;
                itemTrElement.appendChild(itemTd1Element);

                itemTd2Element.textContent = "삭제";
                itemTd2Element.id = "delete-dept-button";
                itemTrElement.appendChild(itemTd2Element);
            });
        })
        .catch(error => {
            console.error(error);
        });
}