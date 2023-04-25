function createFolder(name, p_id) {
    console.log("create folder", name, p_id);

    const folderDto = {
        name: name,
        p_id: p_id
    };

    console.log("folderDto \n", folderDto);

    fetch('folder/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(folderDto)})
        .then(response => response.json())
        .then(data => {
            console.log('성공이요:', data);
        }).catch((error) => {
            console.error('나가죽으세여ㅛ:', error);
        });
}