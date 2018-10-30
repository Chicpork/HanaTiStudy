function post(checkParam, params, method) {
  const methodType = method || 'post'; // Set method to post by default if not specified.
  const checkId = document.getElementById(checkParam).contentDocument;
  const form = document.createElement('form');
  form.setAttribute('method', methodType);
  form.setAttribute('action', '');

  Object.keys(params).forEach((key) => {
    if (Object.prototype.hasOwnProperty.call(params, key)) {
      const hiddenField = document.createElement('input');
      hiddenField.setAttribute('type', 'hidden');
      hiddenField.setAttribute('name', key);
      hiddenField.setAttribute('value', params[key]);

      form.appendChild(hiddenField);
    }
  });
  checkId.body.appendChild(form);
  checkId.body.lastChild.submit();
}

function sendId() {
  const idSrc = document.getElementById('id').value;
  post('checkId', { id: idSrc }, 'get');
}

function sendEmail() {
  const emailSrc = document.getElementById('email').value;
  post('checkEmail', { email: emailSrc }, 'get');
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
  const inputs = document.getElementsByClassName('user-info-form')[0].getElementsByTagName('input');
  if (inputs[0].nextElementSibling.contentWindow.getCheckParam === 'false') {
    inputs[0].focus();
    return;
  }
  for (let i = 1; i < inputs.length - 3; i += 1) {
    const inputName = inputs[i].name;
    const fname = `isValid${inputName.slice(0, 1).toUpperCase() + inputName.slice(1)}`;
    if (inputName !== 'passwd2') {
      if (!window[fname](inputs[i].value)) {
        inputs[i].focus();
        return;
      }
    } else if (inputs[i].value !== inputs[i - 1].value) {
      inputs[i].nextElementSibling.textContent = '비밀번호가 다릅니다.';
      inputs[i].nextElementSibling.style.display = 'inline-block';
      inputs[i].focus();
      return;
    }
  }
  if (inputs[inputs.length - 3].nextElementSibling.contentWindow.getCheckParam === 'false') {
    inputs[inputs.length - 3].focus();
    return;
  }
  document.getElementById('registForm').submit();
}

function eventRegist() {
  document.getElementById('registMe').onclick = sendInfo;
  document.getElementById('id').onchange = sendId;
  document.getElementById('email').onchange = sendEmail;
  const inputs = document.getElementsByClassName('user-info-form')[0].getElementsByTagName('input');
  for (let i = 1; i < inputs.length - 3; i += 1) {
    inputs[i].onkeyup = isValid;
  }
}

window.onload = () => {
  eventRegist();
};
