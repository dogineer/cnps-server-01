const errorModalElement = () => {
    return document.getElementById("errorIssue");
}

export function handleException(errorData) {
    const errorCode = errorData.errorCode;
    const errorMessage = errorData.errorMessage;

    console.log("ErrorCode: " + errorCode)
    console.log("Message: " + errorMessage)

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