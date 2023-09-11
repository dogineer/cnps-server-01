document.addEventListener("DOMContentLoaded", () => {
    const fileInput = document.getElementById("file-input");
    const fileListDisplay = document.getElementById("file-list");
    const fileUpload = document.querySelector(".file-upload");

    fileUpload.addEventListener("dragenter", (e) => {
        e.preventDefault();
        fileUpload.style.backgroundColor = "#007bff";
    });

    fileUpload.addEventListener("dragleave", () => {
        fileUpload.style.backgroundColor = "#f3f3f3";
    });

    fileUpload.addEventListener("dragover", (e) => {
        e.preventDefault();
    });

    fileUpload.addEventListener("drop", (e) => {
        e.preventDefault();
        fileUpload.style.borderColor = "#ccc";

        const files = e.dataTransfer.files;
        if (files.length > 0) {
            const fileNamesContainer = document.getElementById("file-list");
            fileNamesContainer.innerHTML = "";

            for (const file of files) {
                const span = document.createElement("div");
                span.textContent = file.name;
                fileNamesContainer.appendChild(span);
            }
        } else {
            fileListDisplay.textContent = "";
        }
    });

    fileInput.addEventListener("change", () => {
        const files = fileInput.files;

        if (files.length > 0) {
            const fileNamesContainer = document.getElementById("file-list");
            fileNamesContainer.innerHTML = "";

            for (const file of files) {
                const span = document.createElement("div");
                span.textContent = file.name;
                fileNamesContainer.appendChild(span);
            }
        } else {
            fileListDisplay.textContent = "";
        }
    });
});