function updateTimer() {
    var timerInterval = setInterval(updateTimer, 1000);
    var timeElementId = document.getElementById('time');
    var time = 4000;

    function updateTimer() {
      var seconds = Math.floor(time / 1000);
      timeElementId.innerText = seconds + '초';
      time -= 1000;

      if (time < 0) {
        clearInterval(timerInterval);
        window.history.back();
      }
    }
}