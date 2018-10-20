function init() {
  const pathname = window.location.pathname.split('/');
  const name = pathname[1];
  console.log('name :', name);
  if (name.length === 0) {
    document.getElementById('home').classList.toggle('active');
  } else if (name === 'board') {
    document.getElementById('freeboard').classList.toggle('active');
  } else if (name === 'guest') {
    document.getElementById('guestbook').classList.toggle('active');
  }
}

window.onload = (() => {
  init();
});
