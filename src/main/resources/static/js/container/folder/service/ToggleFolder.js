import {clipPreviewContoller} from "../../container/clip/controller/ClipController.js"

window.oncontextmenu = function () {
    return false;
};

export function folderToggle(folder) {
    const folderP = document.getElementById('F' + folder);

    if (folderP) {
        if (folderP.style.display === 'none' || folderP.style.display === '') {
            folderP.style.display = 'block';
        } else {
            folderP.style.display = 'none';
        }

        var Info = document.getElementById(`F${folder}`);

        if (Info) {
            while (Info.firstChild) {
                Info.removeChild(Info.firstChild);
            }
        }

        fetch('/folder/show/' + folder)
            .then(response => response.json())
            .then(data => {
                data.forEach(item => {
                    var listItem = document.createElement('li');
                    listItem.ariaLevel = '2'
                    listItem.ariaLabel = `${item.id}` + '_anchor'
                    listItem.id = `${item.id}`;
                    listItem.className = 'folder-node pd-l-1'

                    var listFolder = document.createElement('img');
                    listFolder.src = '/img/folder_close.svg'
                    listFolder.alt = 'folder'

                    var listAnchor = document.createElement('a');
                    listAnchor.setAttribute("aria-current", "false")
                    listAnchor.textContent = `${item.name}`
                    listAnchor.id = `${item.id}`;
                    listAnchor.className = 'folder-anchor'

                    var folderItem = document.createElement('div');
                    folderItem.className = 'folder-item-container'
                    listAnchor.appendChild(folderItem)

                    var Info = document.getElementById(`F${folder}`);
                    Info.appendChild(listItem);

                    var Anchor = document.getElementById(`${item.id}`);
                    Anchor.appendChild(listFolder);
                    Anchor.appendChild(listAnchor);
                });
            })
            .catch(error => console.log(error, "fetch 에러!"));

        console.log("[ show folders ]")
        clickFolder(folder);
    } else {
        console.log("[ show folders ]")
        clickFolder(folder);
    }
}

function clearClipTrField() {
    const elements = document.getElementById('clipListBody')

    if (elements !== null) {
        elements.replaceChildren()
    }

    return elements
}

function clickFolder(folderId) {
    fetch('/folder/select/' + folderId, {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    })
        .then(function (response) {
            if (response.ok) {
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

                tr.id = 'clip'
                tr.className = 'clip-edit'
                tr.setAttribute('data-clip-id', item.clip_id)
                tr.setAttribute('data-clip-title', item.file_name)
                tr.setAttribute('data-clip-path', item.file_path)
                tr.setAttribute('data-clip-data', item.ingest_at)
                tr.setAttribute('data-clip-uuid', item.clip_uuid)
                tr.setAttribute('onclick', 'import_res(this)')

                var count = document.createElement('td')
                var preview = document.createElement('td')
                var teamId = document.createElement('td')
                var teamName = document.createElement('td')
                var folderName = document.createElement('td')
                var fileName = document.createElement('td')
                var filePath = document.createElement('td')
                var fileFormat = document.createElement('td')
                var fileSize = document.createElement('td')

                var previewAtag = document.createElement('a')
                var previewThumbnail = document.createElement('img')

                tr.appendChild(count)
                tr.appendChild(preview)
                tr.appendChild(teamId)
                tr.appendChild(teamName)
                tr.appendChild(folderName)
                tr.appendChild(fileName)
                tr.appendChild(filePath)
                tr.appendChild(fileFormat)
                tr.appendChild(fileSize)

                count.className = 'clip-count'
                count.innerText = index + 1

                preview.className = 'clip-preview'
                preview.appendChild(previewAtag)
                previewAtag.setAttribute("type", "button")
                previewAtag.setAttribute("id", "clip-preview-btn")

                previewAtag.appendChild(previewThumbnail)
                previewThumbnail.setAttribute("class", "thumbnail")
                previewThumbnail.setAttribute("src", generateThumbnailUrl(item.ingest_at, item.clip_uuid))
                previewThumbnail.setAttribute("alt", "thumbnail")

                teamId.className = 'clip-team_id'
                teamId.innerText = 'T' + item.team_id

                teamName.className = 'clip-team_name'
                teamName.innerText = item.team_name

                folderName.className = 'clip-folder_name'
                folderName.innerText = item.folder_name

                fileName.className = 'clip-file_name'
                fileName.innerText = item.file_name

                var spanFilePath = document.createElement('span')

                filePath.appendChild(spanFilePath)
                spanFilePath.className = 'clip-file_path'
                spanFilePath.innerText = item.file_path

                fileFormat.className = 'clip-file_format'
                fileFormat.innerText = item.format_long_name

                fileSize.className = 'clip-file_size'
                fileSize.innerText = item.size
            })

            clipPreviewContoller();
        })
        .catch(error => {
            console.log(error, "폴더 정보를 가져오지 못했습니다.")
            alert('폴더 정보를 가져오지 못했습니다.');
        });
}

function generateThumbnailUrl(ingestAt, clipUuid) {
    const currentProtocol = window.location.protocol;
    var filename = ingestAt + '/' + clipUuid + '.jpg';
    return currentProtocol + '/clip/thumbnail?filename=' + filename;
}


