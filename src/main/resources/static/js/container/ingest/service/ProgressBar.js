const updateProgress = (progressbar, progressPercent, value) => {
    progressbar.style.width = value + "%";
    progressPercent.textContent = value + "%";
}

const complete = (progressPercent) => {
    progressPercent.textContent = "완료";
    alert("[+] 업로드가 완료되었습니다.");
    location.reload();
}

export {updateProgress, complete};