document.addEventListener('DOMContentLoaded', () => {
    const darkModeSetting = localStorage.getItem('darkMode');
    const toggleSwitch = document.getElementById('toggleSwitch');

    if (darkModeSetting === 'enabled') {
        toggleSwitch.classList.toggle('enabled');
        setBlackColors();
    } else {
        setWhiteColors();
    }
});

function setBlackColors() {
    document.documentElement.style.setProperty('--cnps-background-color', '#1c1c1c');
    document.documentElement.style.setProperty('--cnps-black', '#c0bebe');
    document.documentElement.style.setProperty('--cnps-real-white', '#212121');
    document.documentElement.style.setProperty('--cnps-white', '#262626');
    document.documentElement.style.setProperty('--cnps-heavy-white', '#2a2a2a');
    document.documentElement.style.setProperty('--cnps-red', 'orangered');
    document.documentElement.style.setProperty('--cnps-dark-red', '#e13c00');
    document.documentElement.style.setProperty('--cnps-sidebar', '#262626');
    document.documentElement.style.setProperty('--cnps-black-shadow', '#F2F2F219');

    localStorage.setItem('darkMode', 'enabled');
}
// #e4e6ed
function setWhiteColors() {
    document.documentElement.style.setProperty('--cnps-background-color', '#e4e6ed');
    document.documentElement.style.setProperty('--cnps-black', '#2F3336');
    document.documentElement.style.setProperty('--cnps-real-white', '#FFFFFF');
    document.documentElement.style.setProperty('--cnps-white', '#F2F2F2');
    document.documentElement.style.setProperty('--cnps-heavy-white', '#f0f2f6');
    document.documentElement.style.setProperty('--cnps-red', 'orangered');
    document.documentElement.style.setProperty('--cnps-dark-red', '#e13c00');
    document.documentElement.style.setProperty('--cnps-sidebar', 'linear-gradient(60deg, #29323c 0%, #485563 100%)');
    document.documentElement.style.setProperty('--cnps-black-shadow', '#2F33367C');

    localStorage.setItem('darkMode', 'disabled');
}

function toggleDarkMode() {
    const toggleSwitch = document.getElementById('toggleSwitch');
    const darkModeEnabled = document.documentElement.style.getPropertyValue('--cnps-background-color') === '#1c1c1c';
    toggleSwitch.classList.toggle('enabled');

    if (darkModeEnabled) {
        setWhiteColors();
    } else {
        setBlackColors();
    }
}

function sidebarToggle() {
  var sidebar = document.getElementsByClassName('sidebar')[0];
  sidebar.classList.toggle('collapsed');
}