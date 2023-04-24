function toggleFolder(folder) {
  var folderP = document.getElementById('F'+folder);

    if (folderP.style.display === 'none' || folderP.style.display === '') {
      folderP.style.display = 'block';

      fetch('/folder/show/' + folder)
        .then(response => response.json())
        .then(data => {
          data.forEach(item => {
            var listItem = document.createElement('li');
            listItem.ariaLevel = '2'
            listItem.ariaLabel =  `${item.id}`+'_anchor'
            listItem.id = `${item.id}`;
            listItem.className = 'folder-node'

            var listFolder = document.createElement('i');
            listFolder.textContent = 'folder';
            listFolder.className = 'inline-icon material-symbols-outlined'

            var listAnchor = document.createElement('a');
            listAnchor.ariaLabel =  `${item.id}`+'_anchor'
            listAnchor.textContent = `${item.name}`
            listAnchor.id = `F${item.id}`;
            listAnchor.className = 'folder-anchor'

            var Info = document.getElementById(`F${folder}`);
            Info.appendChild(listItem);

            var Anchor = document.getElementById(`${item.id}`);
            Anchor.appendChild(listFolder);
            Anchor.appendChild(listAnchor);
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