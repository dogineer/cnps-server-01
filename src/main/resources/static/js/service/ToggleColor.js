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
    document.documentElement.style.setProperty('--cnps-red', 'orangered');
    document.documentElement.style.setProperty('--cnps-dark-red', '#e13c00');
    document.documentElement.style.setProperty('--cnps-sidebar', '#262626');

    localStorage.setItem('darkMode', 'enabled');
}

function setWhiteColors() {
    document.documentElement.style.setProperty('--cnps-background-color', '#F9FAFF');
    document.documentElement.style.setProperty('--cnps-black', '#2F3336');
    document.documentElement.style.setProperty('--cnps-real-white', '#FFFFFF');
    document.documentElement.style.setProperty('--cnps-white', '#F2F2F2');
    document.documentElement.style.setProperty('--cnps-red', 'orangered');
    document.documentElement.style.setProperty('--cnps-dark-red', '#e13c00');
    document.documentElement.style.setProperty('--cnps-sidebar', '#2F3336');

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