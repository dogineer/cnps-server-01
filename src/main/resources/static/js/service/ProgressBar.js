document.addEventListener("DOMContentLoaded", () => {
    const progressbar = document.querySelector("#progress-gauge");
    const progressPercent = document.querySelector(".progress-percent");
    const form = document.querySelector("#ingestRequest-form");

    function updateProgress(value) {
        progressbar.style.width = value + "%";
        progressPercent.textContent = value + "%";
    }

    function complete() {
        progressPercent.textContent = "완료";
        alert("업로드가 완료되었습니다.");
        location.reload();
    }

    form.addEventListener("submit", (e) => {
        e.preventDefault();

        const formData = new FormData(form);
        const xhr = new XMLHttpRequest();

        xhr.open("POST", "/media/ingest/add");

        xhr.upload.addEventListener("progress", (e) => {
            if (e.lengthComputable) {
                const percentComplete = (e.loaded / e.total) * 100;
                const formattedPercent = percentComplete.toFixed(2);

                updateProgress(formattedPercent);
            }
        });

        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    complete();
                }
            }
        };

        xhr.send(formData);
    });
});