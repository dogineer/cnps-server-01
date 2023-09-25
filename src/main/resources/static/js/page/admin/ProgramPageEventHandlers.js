import {
    onLoadProgramChartDataFetch,
    programAddClickEvent, programDeleteClickEvent,
} from "../../container/program/event/ProgramEvent.js";

document.addEventListener('DOMContentLoaded', () => {
    onLoadProgramChartDataFetch();
    programAddClickEvent();
    programDeleteClickEvent();
});