function evalScript(script, callback) {
    new CSInterface().evalScript(script, callback);
}

function import_res(element) {
    if (element) {
        const file_path = element.getAttribute("data-clip-path");
        checkFileExistence(file_path);
    } else {
        alert("요소가 없습니다.")
    }

}

function checkFileExistence(filePath) {
    const result = "/Volumes/mediabuddies.kro.kr" + filePath;

    fetch(`/clip/checkFileExistence?filePath=${encodeURIComponent(filePath)}`)
        .then(res => res.json())
        .then(data => {
            if (data === true) {
                evalScript("$._PPP_.importCustomFiles('" + result + "')");
            } else {
                evalScript("alert('" + data + " \\n ' + '" + filePath + "')");
                evalScript("alert('파일을 찾을 수 없습니다. \\n' + '" + result + "')");
            }
        })
        .catch(() => {
            throw new Error("프리미어에서 사용해주세요.")
        });
}