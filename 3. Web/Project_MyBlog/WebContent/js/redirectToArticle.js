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

function sendArticleId(e) {
  const articleIdSrc = e.target.parentElement.children[0].textContent.trim();
  post('/board/post.jsp', { articleId: articleIdSrc, pageNum: 1 });
}

function makeLinkArticle() {
  const trTags = document.getElementsByClassName('article');
  for (let i = 0; i < trTags.length; i += 1) {
    const trTag = trTags[i];
    trTag.onclick = sendArticleId;
  }
}

function init() {
  makeLinkArticle();
}

window.onload = () => {
  init();
};
