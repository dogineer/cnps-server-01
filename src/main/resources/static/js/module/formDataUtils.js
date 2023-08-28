export const validateFormData = (formData) => {
    const formDataObject = new FormData(formData);
    const encodedData = Object.fromEntries(formDataObject.entries());

    for (const key in encodedData) {
        const value = encodedData[key].trim();
        if (value === "" || value === null || value === undefined) {
            alert("입력값이 없습니다.");
            throw new Error("입력값이 없습니다.");
        }
    }

    return encodedData;
}
