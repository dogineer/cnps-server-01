function updateTimer() {
    var timerInterval = setInterval(updateTimer, 1000);
    var timeElementId = document.getElementById('time');
    var time = 2000;

    function updateTimer() {
      var seconds = Math.floor(time / 1000);
      timeElementId.innerText = seconds + '초';
      time -= 1000;

      if (time < 0) {
        clearInterval(timerInterval);
        location.replace("/");
      }
    }
}