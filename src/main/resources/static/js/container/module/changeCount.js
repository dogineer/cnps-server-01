export const changeCount = () => {
    const url = new URL(window.location.href);
    const limit = parseInt(url.searchParams.get('limit')) || 50;
    let currentPage = parseInt(document.getElementById("pageCount").textContent);
    let currentCount = (currentPage - 1) * limit + 1;

    const pageCounts = document.querySelectorAll("#count");
    pageCounts.forEach(pageCount => {
        pageCount.innerHTML = currentCount++;
    });
};