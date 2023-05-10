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

function getElementsByClassName(id_name) {
  return document.getElementsByClassName(`clip-${id_name}`);
}

// function clearClipFields() {
//   const clipFields =
//       [
//           'count',
//           'ingest_name',
//           'team_id',
//           'team_name',
//           'folder_name',
//           'file_name',
//           'file_path',
//           'file_format',
//           'file_size'
//       ];
//
//   clipFields.forEach((id_name) => {
//     const elements = getElementsByClassName(id_name);
//
//     for (let i = 0; i < elements.length; i++) {
//       // elements[i].innerHTML = '';
//         elements[i].remove()
//     }
//   });
// }

function clearClipTrField(){
    const elements = document.getElementById('clipListBody')

    if (elements !== null){
        elements.replaceChildren()
    }

    return elements
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
        var parent = clearClipTrField()

        item.forEach((item, index) => {
          var tr = document.createElement('tr')
          parent.appendChild(tr)

          tr.className = 'clip-edit'
          tr.setAttribute('data-clip-id',    item.clip_id)
          tr.setAttribute('data-clip-title', item.file_name)
          tr.setAttribute('onClick', 'evalScript(\'$._PPP_.importCustomFiles("' + item.file_path + '")\')')

          var count = document.createElement('td')
          var preview = document.createElement('td')
          var ingestName = document.createElement('td')
          var teamId = document.createElement('td')
          var teamName = document.createElement('td')
          var folderName = document.createElement('td')
          var fileName = document.createElement('td')
          var filePath = document.createElement('td')
          var fileFormat = document.createElement('td')
          var fileSize = document.createElement('td')

          var previewAtag = document.createElement('a')
          var previewSpanIcon = document.createElement('span')

          tr.appendChild(count)
          tr.appendChild(preview)
          tr.appendChild(ingestName)
          tr.appendChild(teamId)
          tr.appendChild(teamName)
          tr.appendChild(folderName)
          tr.appendChild(fileName)
          tr.appendChild(filePath)
          tr.appendChild(fileFormat)
          tr.appendChild(fileSize)

          count.className = 'clip-count'
          count.innerText = index+1

          preview.className = 'clip-preview'
          preview.appendChild(previewAtag)
          previewAtag.setAttribute("type", "button")
          previewAtag.setAttribute("data-clip-id", item.clip_id)
          previewAtag.setAttribute("data-clip-title", item.file_name)
          previewAtag.setAttribute("data-clip-path", item.file_path)
          previewAtag.setAttribute("onclick","clipPreview(this)")

          previewAtag.appendChild(previewSpanIcon)
          previewSpanIcon.setAttribute("class", "span-icon material-symbols-outlined")
          previewSpanIcon.innerText = "youtube_activity"

          ingestName.className = 'clip-ingest_name'
          ingestName.innerText = item.ingest_name

          teamId.className = 'clip-team_id'
          teamId.innerText = item.team_id

          teamName.className = 'clip-team_name'
          teamName.innerText = item.team_name

          folderName.className = 'clip-folder_name'
          folderName.innerText = item.folder_name

          fileName.className = 'clip-file_name'
          fileName.innerText = item.file_name

          filePath.className = 'clip-file_path'
          filePath.innerText = item.file_path

          fileFormat.className = 'clip-file_format'
          fileFormat.innerText = item.format_long_name

          fileSize.className = 'clip-file_size'
          fileSize.innerText = item.size
        })
      })
  .catch(error => {
      console.log(error, "폴더 정보를 가져오지 못했습니다.")
      alert('폴더 정보를 가져오지 못했습니다.');
  });
}

