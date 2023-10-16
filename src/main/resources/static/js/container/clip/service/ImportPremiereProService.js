import {handleException} from "../../issue/service/IssueService.js";

function evalScript(script, callback) {
    new CSInterface().evalScript(script, callback);
}

export const import_res = (element) => {
    if (element) {
        const filePath = element.getAttribute("data-clip-path");
        checkFileExistence(filePath);
    } else {
        const errorData = {
            errorCode: "NOT ELEMENT",
            errorMessage: "요소가 없습니다."
        };

        handleException(errorData);
    }

}

const checkFileExistence = (filePath) => {
    const result = "/Volumes/mediabuddies.kro.kr" + filePath;

    fetch(`/s1/api/clip/check/file?filePath=${encodeURIComponent(filePath)}`)
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
            const errorData = {
                errorCode: "NOT CLIP IMPORT",
                errorMessage: "프리미어에서 사용해주세요."
            };

            handleException(errorData)
        });
}