import {
    onLoadProgramTeamChartDataFetch,
    programAddClickEvent, programDeleteClickEvent,
    teamAddSelectEvent
} from "../../container/program/event/ProgramEvent.js";

document.addEventListener('DOMContentLoaded', () => {
    teamAddSelectEvent();
    onLoadProgramTeamChartDataFetch();

    programAddClickEvent();
    programDeleteClickEvent();
});