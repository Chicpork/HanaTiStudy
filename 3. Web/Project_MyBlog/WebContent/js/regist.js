function post(path, params, method) {
  const methodType = method || 'post'; // Set method to post by default if not specified.
  const checkId = document.getElementById('checkId').contentWindow;
  const form = document.createElement('form');
  form.setAttribute('method', methodType);
  form.setAttribute('action', path);

  Object.keys(params).forEach((key) => {
    if (Object.prototype.hasOwnProperty.call(params, key)) {
      const hiddenField = document.createElement('input');
      hiddenField.setAttribute('type', 'hidden');
      hiddenField.setAttribute('name', key);
      hiddenField.setAttribute('value', params[key]);

      form.appendChild(hiddenField);
    }
  });
  checkId.document.body.appendChild(form);
  checkId.document.body.lastChild.submit();
}

function sendId() {
  const idSrc = document.getElementById('id').value;
  post('', { id: idSrc }, 'get');
}

function isValid(event) {
  const input = event.target;
  const inputValue = input.value;
  const inputName = input.name;
  const fname = `isValid${inputName.slice(0, 1).toUpperCase() + inputName.slice(1)}`;
  if (inputValue.length === 0) {
    input.nextElementSibling.textContent = '값을 입력해주세요!';
    input.nextElementSibling.style.display = 'inline-block';
    input.focus();
  } else if (input.name === 'passwd2') {
    if (document.getElementById('passwd').value === inputValue) {
      input.nextElementSibling.style.display = 'none';
    } else {
      input.nextElementSibling.textContent = '비밀번호가 다릅니다.';
      input.nextElementSibling.style.display = 'inline-block';
      input.focus();
    }
  } else if (window[fname](inputValue)) {
    input.nextElementSibling.style.display = 'none';
  } else {
    input.nextElementSibling.textContent = '입력 형식에 맞춰 입력해주세요.';
    input.nextElementSibling.style.display = 'inline-block';
    input.focus();
  }
}

function sendInfo() {
  document.getElementById('registForm').submit();
}

function eventRegist() {
  document.getElementById('registMe').onclick = sendInfo;
  document.getElementById('id').onchange = sendId;
  const inputs = document.getElementsByClassName('user-info-form')[0].getElementsByTagName('input');
  for (let i = 1; i < inputs.length; i += 1) {
    inputs[i].onkeyup = isValid;
  }
}

window.onload = () => {
  eventRegist();
};
