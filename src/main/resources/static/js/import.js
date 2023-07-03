function evalScript(script, callback) {
    new CSInterface().evalScript(script, callback);
}

function import_res(element) {
    if (element) {
        var file_path = element.getAttribute("data-clip-path");
        importFile(file_path);
    } else {
        alert("element is undefined")
    }

}

function importFile(file_path) {
    if (file_path) {
        var script = "$._PPP_.importCustomFiles('" + file_path + "')";

        evalScript(script);
    } else {
        console.error("file_path is null or undefined");
    }

}