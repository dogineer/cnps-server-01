function handleSearch() {
    var searchElement = document.getElementById('search-word').value;
    var elements = document.getElementsByClassName('clip-ingest_name');
    var result = document.getElementById('search-results');
    var regex = new RegExp(searchElement, 'i');

    result.innerHTML = '';

    for (var i = 0; i < elements.length; i++) {
        var element = elements[i];
        var text = element.textContent || element.innerText;

        if (regex.test(text)) {
            var resultItem = document.createElement('div');
            resultItem.textContent = text;

            result.appendChild(resultItem);
        }
    }

    console.log('검색 결과 : \n' + result.innerText)
}