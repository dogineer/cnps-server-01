import {ClipController} from "../../clip/controller/ClipController.js";

const clearClipTrField = () => {
    const elements = document.getElementById('clipListBody')

    if (elements !== null) {
        elements.replaceChildren()
    }

    return elements
}

const generateThumbnailUrl = (ingestAt, clipUuid) => {
    const currentProtocol = window.location.protocol;
    const filename = ingestAt + '/' + clipUuid + '.jpg';
    return currentProtocol + '/clip/thumbnail?filename=' + filename;
}

const createClipData = (parentElement, item, index) => {
    const tr = document.createElement('tr')

    tr.id = 'clip'
    tr.className = 'clip-edit'
    tr.setAttribute('data-clip-id', item.clip_id)
    tr.setAttribute('data-clip-title', item.file_name)
    tr.setAttribute('data-clip-data', item.ingest_at)
    tr.setAttribute('data-clip-uuid', item.clip_uuid)
    tr.setAttribute('data-clip-path', item.file_path)
    // tr.setAttribute('onclick', 'import_res(this)')

    const count = document.createElement('td')
    const preview = document.createElement('td')
    const teamId = document.createElement('td')
    const teamName = document.createElement('td')
    const folderName = document.createElement('td')
    const fileName = document.createElement('td')
    const filePath = document.createElement('td')
    const fileFormat = document.createElement('td')
    const fileSize = document.createElement('td')

    const previewAtag = document.createElement('a')
    const previewThumbnail = document.createElement('img')

    tr.appendChild(count)
    tr.appendChild(preview)
    tr.appendChild(teamId)
    tr.appendChild(teamName)
    tr.appendChild(folderName)
    tr.appendChild(fileName)
    tr.appendChild(filePath)
    tr.appendChild(fileFormat)
    tr.appendChild(fileSize)

    parentElement.appendChild(tr)

    count.className = 'clip-count'
    count.innerText = index + 1

    preview.className = 'clip-preview'
    preview.appendChild(previewAtag)
    previewAtag.setAttribute("type", "button")
    previewAtag.setAttribute("id", "clip-preview-btn")
    previewAtag.addEventListener('click', (e) => {
        ClipController.showPreview(e);
    })

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

    const spanFilePath = document.createElement('span')

    filePath.appendChild(spanFilePath)
    spanFilePath.className = 'clip-file_path'
    spanFilePath.innerText = item.file_path

    fileFormat.className = 'clip-file_format'
    fileFormat.innerText = item.format_long_name

    fileSize.className = 'clip-file_size'
    fileSize.innerText = item.size
}

export const fetchFolderData = (folderId) => {
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
            const parentElement = clearClipTrField()

            item.forEach((item, index) => {
                createClipData(parentElement, item, index)
            })
        })
        .catch(error => {
            console.log(error, "폴더 정보를 가져오지 못했습니다.")
            alert('폴더 정보를 가져오지 못했습니다.');
        });
}