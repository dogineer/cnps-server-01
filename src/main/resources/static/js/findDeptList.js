function deptChartLoad() {
    const selectElement = document.getElementById('deptChartSelect');
    selectElement.addEventListener('change', fetchDataByTeam);
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
                var itemElement = document.createElement('tr');
                itemElement.textContent = item;
                listItem.appendChild(itemElement);
            });
        })
        .catch(error => {
            console.error(error);
        });
}