export class DarkMode {
    static setBlackColors() {
        document.documentElement.style.setProperty('--cnps-background-color', '#2E3440');
        document.documentElement.style.setProperty('--cnps-black', '#FFFFFF');
        document.documentElement.style.setProperty('--cnps-real-white', '#4C566A');
        document.documentElement.style.setProperty('--cnps-white', '#262626');
        document.documentElement.style.setProperty('--cnps-heavy-white', '#3B4252');
        document.documentElement.style.setProperty('--cnps-red', '#BF616A');
        document.documentElement.style.setProperty('--cnps-dark-red', '#BF616A');
        document.documentElement.style.setProperty('--cnps-sidebar', '#3B4252');
        document.documentElement.style.setProperty('--cnps-black-shadow', '#353535');
        document.documentElement.style.setProperty('--cnps-hover', '#c0bebe');
    }

// #e4e6ed
    static setWhiteColors() {
        document.documentElement.style.setProperty('--cnps-background-color', '#E5E9F0');
        document.documentElement.style.setProperty('--cnps-black', '#2E3440');
        document.documentElement.style.setProperty('--cnps-real-white', '#FFFFFF');
        document.documentElement.style.setProperty('--cnps-white', '#E5E9F0');
        document.documentElement.style.setProperty('--cnps-heavy-white', '#ECEFF4');
        document.documentElement.style.setProperty('--cnps-red', '#BF616A');
        document.documentElement.style.setProperty('--cnps-dark-red', '#BF616A');
        document.documentElement.style.setProperty('--cnps-sidebar', '#4C566A');
        document.documentElement.style.setProperty('--cnps-black-shadow', '#E5E9F0');
        document.documentElement.style.setProperty('--cnps-hover', '#4C566A');
    }

    static darkModeValidate() {
        const darkModeSetting = localStorage.getItem('darkMode');
        const toggleSwitch = document.getElementById('toggleSwitch');

        if (darkModeSetting === 'enabled') {
            toggleSwitch.classList.add('enabled');
            this.setBlackColors();
        } else if (darkModeSetting === 'disabled') {
            toggleSwitch.classList.remove('enabled');
            this.setWhiteColors();
        }
    }

    static toggle() {
        const toggleSwitch = document.getElementById('toggleSwitch');

        if (toggleSwitch.classList.contains('enabled' )) {
            localStorage.setItem('darkMode', 'disabled');
        } else {
            localStorage.setItem('darkMode', 'enabled');
        }

        this.darkModeValidate();
    }
}
