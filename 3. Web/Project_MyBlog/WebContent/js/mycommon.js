function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  const expires = `expires=${d.toUTCString()}`;
  document.cookie = `${cname}=${cvalue};${expires};path=/`;
}

function saveIdCookie() {
  if (document.getElementById('saveId').checked) {
    setCookie('saveId', document.getElementById('userId').value, 30);
  } else {
    setCookie('saveId', '', -1);
  }
}

function eventRegist() {
  if (document.getElementById('login-submit') !== null) {
    document.getElementById('login-submit').onclick = saveIdCookie;
  }
}

function changeColor() {
  const pathname = window.location.pathname.split('/');
  const name = pathname[1];
  if (name.length === 0) {
    document.getElementById('home').classList.toggle('active');
  } else if (name === 'board') {
    document.getElementById('freeboard').classList.toggle('active');
  } else if (name === 'guest') {
    document.getElementById('guestbook').classList.toggle('active');
  }
}

window.onload = (() => {
  eventRegist();
  changeColor();
});
