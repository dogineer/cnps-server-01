export const changeCount = () => {
    const limit = 50;
    let currentPage = parseInt(document.getElementById("pageCount").textContent);
    let currentCount = (currentPage - 1) * limit + 1;

    const pageCounts = document.querySelectorAll("#count");
    pageCounts.forEach(pageCount => {
        pageCount.innerHTML = currentCount++;
    });
};