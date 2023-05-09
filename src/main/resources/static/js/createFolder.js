function createFolder(folderId) {

    const folderName = document.querySelector('input[name="folder_name"]').value;
    console.log("create folder", folderName, folderId);

    if (folderName === "" || folderId === null){
        alert("값이 정상적으로 입력 되지 않았습니다.")
    } else {
        const folderDto = {
            name: folderName,
            p_id: folderId
        };

        fetch('folder/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(folderDto)})
            .then(data => {
                console.log('폴더 DB에 저장되었습니다. :', data);
            }).catch((error) => {
                console.error('데이터 값이 잘못됐습니다. :', error);
            });
    }
}