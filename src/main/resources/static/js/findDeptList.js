function deptChartLoad() {
    const selectElement = document.getElementById('deptChartSelect');
    selectElement.addEventListener('change', fetchDataByTeam);
    fetchDataByTeam();
}

function fetchDataByTeam() {
    const deptChart = document.getElementById('deptChartSelect');
    const deptId = deptChart.value;

    fetch('/admin/dept/find/' + deptId, {
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
            var listItem = document.getElementById('deptChartBody');
            listItem.innerHTML = '';

            data.forEach(item => {
                var itemTrElement = document.createElement('tr');
                var itemTd1Element = document.createElement('td');
                var itemTd2Element = document.createElement('td');

                listItem.appendChild(itemTrElement);

                itemTd1Element.textContent = item;
                itemTrElement.appendChild(itemTd1Element);

                itemTd2Element.textContent = "삭제";
                itemTrElement.appendChild(itemTd2Element);
            });
        })
        .catch(error => {
            console.error(error);
        });
}