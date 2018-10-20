function init() {
  const pathname = window.location.pathname.split('/');
  const name = pathname[1];
  if (name.length === 0) {
    document.getElementById('home').parentElement.classList.toggle('active');
  } else if (name === 'board') {
    document.getElementById('freeboard').parentElement.classList.toggle('active');
  } else if (name === 'guest') {
    document.getElementById('guestbook').parentElement.classList.toggle('active');
  }
}

window.addEventListener('DOMContentLoaded', () => {
  init();
});
