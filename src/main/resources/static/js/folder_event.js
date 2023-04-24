function toggleFolder(folder) {
  var folderP = document.getElementById('F'+folder);

    if (folderP.style.display === 'none' || folderP.style.display === '') {
      folderP.style.display = 'block';

      fetch('/folder/show/' + folder)
        .then(response => response.json())
        .then(data => {
          data.forEach(item => {
            var listItem = document.createElement('li');
            listItem.textContent = `${item.name}`;
            listItem.id = `F${item.id}`;

            var Info = document.getElementById(`F${folder}`);
            Info.appendChild(listItem);
          });
        })
        .catch(error => console.log(error, "fetch 에러!"));
    } else {
      folderP.style.display = 'none';

      var Info = document.getElementById(`F${folder}`);

      if (Info) {
        while (Info.firstChild) {
          Info.removeChild(Info.firstChild);
        }
      }
    }
}