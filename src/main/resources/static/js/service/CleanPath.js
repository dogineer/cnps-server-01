function cleanPath(path) {
    let index = path.indexOf("mam");

    if (index !== -1) {
        let result = path.slice(index + 3);
        console.log(result);
        return result;
    } else {
        console.log("문자열에 'mam'이 포함되어 있지 않습니다.");
    }
}