function sendInfo() {
  document.getElementById('registForm').submit();
}

function eventRegist() {
  document.getElementById('registMe').onclick = sendInfo;
}

window.onload = () => {
  eventRegist();
};
