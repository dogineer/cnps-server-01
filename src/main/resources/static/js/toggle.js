window.oncontextmenu = function () {
  return false;
};

function sidebarToggle(){
    var sidebar = document.getElementsByClassName('sidebar')[0];
    sidebar.classList.toggle('collapsed');
}

function openRightClickMenu(event, folder){
  if (event.button === 2) {
    var folderMenu = document.getElementById(folder);
    if (folderMenu.style.display === 'none' || folderMenu.style.display === '') {
      folderMenu.style.display = 'block';
    } else {
      folderMenu.style.display = 'none';
    }
  }
}

function getClipElementById(id_name) {
  return document.getElementById(`clip-${id_name}`);
}

function clearClipFields() {
  const clipFields =
      [
          'ingest_name',
          'team_id',
          'team_name',
          'folder_name',
          'file_name',
          'file_path',
          'file_format',
          'file_size'
      ];

  clipFields.forEach((id_name) => {
    getClipElementById(id_name).innerHTML = "";
  });
}

function clickFolder(folderId){
  fetch('/folder/select/' + folderId,{
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
      })
      .then(function(response){
            if(response.ok){
                console.log('GET success. folder id = ', folderId);
                return response.json()
            }
            throw new Error('GET failed.');
            })
      .then(item => {
        console.log(item)
        clearClipFields()

        item.forEach(item => {
            getClipElementById('ingest_name').innerHTML = item.ingest_name;
            getClipElementById('team_id').innerHTML = item.team_id;
            getClipElementById('team_name').innerHTML = item.team_name;
            getClipElementById('folder_name').innerHTML = item.folder_name;
            getClipElementById('file_name').innerHTML = item.file_name;
            getClipElementById('file_path').innerHTML = item.file_path;
            getClipElementById('file_format').innerHTML = item.format_long_name;
            getClipElementById('file_size').innerHTML = item.file_size;
        })
      })
  .catch(error => {
      console.log(error, "폴더 정보를 가져오지 못했습니다.")
      alert('폴더 정보를 가져오지 못했습니다.');
  });
}

function folderToggle(folder) {
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
            listAnchor.setAttribute('onclick',`clickFolder(${item.id})`)


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

