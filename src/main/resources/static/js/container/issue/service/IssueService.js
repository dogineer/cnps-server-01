const errorModalElement = () => {
    return document.getElementById("errorIssue");
}

const serverError = (error) => {
    console.error('데이터 전송 중 오류가 발생했습니다.', error);

    const errorData = {
        errorCode: "SERVER ERROR",
        errorMessage: "서버에 오류가 발생했습니다."
    };

    handleException(errorData)
}

const handleException = (errorData) => {
    const errorCode = errorData.errorCode;
    const errorMessage = errorData.errorMessage;

    console.log("[!] ErrorCode: " + errorCode)
    console.log("[!] Message: " + errorMessage)

    modalErrorDataSetup(errorModalElement(), errorCode, errorMessage)
    modalOpen(errorModalElement())
}

const modalErrorDataSetup = (errorModalElement, errorCode, errorMessage) => {
    const modalErrorCode = errorModalElement.querySelector('#issue-code')
    const modalErrorMessage = errorModalElement.querySelector('#issue-message');

    modalErrorCode.innerHTML = errorCode;
    modalErrorMessage.innerHTML = errorMessage;
}

const modalOpen = (errorModalElement) => {
    errorModalElement.style.display = "block"
}

export {handleException, serverError}