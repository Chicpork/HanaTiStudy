/**
 * sends a request to the specified url from a form. this will change the window location.
 * @param {string} path the path to send the post request to
 * @param {object} params the paramiters to add to the url
 * @param {string} [method=post] the method to use on the form
 */
function post(path, params, method) {
  const methodType = method || 'post'; // Set method to post by default if not specified.

  const form = document.createElement('form');
  form.setAttribute('method', methodType);
  form.setAttribute('action', path);

  Object.keys(params).forEach((key) => {
    if (Object.prototype.hasOwnProperty.call(params, key)) { //
      const hiddenField = document.createElement('input');
      hiddenField.setAttribute('type', 'hidden');
      hiddenField.setAttribute('name', key);
      hiddenField.setAttribute('value', params[key]);

      form.appendChild(hiddenField);
    }
  });
  document.body.appendChild(form);
  form.submit();
}

function sendArticleColNum() {
  const articleColNum = document.getElementById('articleColNum').value;
  const urls = window.location.href.split('/');
  post(`/board/${urls[urls.length - 1]}`, { maxArticleColNum: articleColNum });
}

function sendArticleId(e) {
  const articleIdSrc = e.target.parentElement.children[0].textContent.trim();
  const pageNumSrc = e.target.parentElement.children[1].textContent.trim();
  const urls = window.location.href.split('/');
  const query = urls[urls.length - 1].split('?')[1];
  if (query === undefined) {
    post('/board/post.jsp', { articleId: articleIdSrc, pageNum: pageNumSrc });
  } else {
    post(`/board/post.jsp?${query}`, { articleId: articleIdSrc, pageNum: pageNumSrc });
  }
}

function makeLinkArticle() {
  const trTags = document.getElementsByClassName('article');
  for (let i = 0; i < trTags.length; i += 1) {
    const trTag = trTags[i];
    trTag.onclick = sendArticleId;
  }
}

function eventRegist() {
  document.getElementById('articleColNum').onchange = sendArticleColNum;
}

function init() {
  makeLinkArticle();
}

window.onload = () => {
  init();
  eventRegist();
};
