import {
    deptAddClickEvent,
    lowDeptAddTypeSelectEvent,
    onLoadDataFetch
} from "../../container/dept/event/DeptEvent.js";

document.addEventListener('DOMContentLoaded', () => {
    onLoadDataFetch();
    lowDeptAddTypeSelectEvent();
    deptAddClickEvent();
});