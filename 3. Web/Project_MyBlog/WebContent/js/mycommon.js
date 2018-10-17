function init() {
  const pathname = window.location.pathname.split('/');
  let filename = pathname[pathname.length - 1];
  if (filename.length === 0) {
    document.getElementById('home').parentElement.classList.toggle('active');
  } else {
    filename = filename.slice(0, filename.length - 4);
    document.getElementById(filename).parentElement.classList.toggle('active');
  }
}

window.addEventListener('DOMContentLoaded', () => {
  init();
});
