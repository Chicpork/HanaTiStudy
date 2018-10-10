let tables;
let manager;
const borderColorDefault = '#ccc';
const borderColorError = 'crimson';

function init() {
  tables = document.getElementById('table').contentWindow.document.getElementById('accounts');
  manager = new AccountManager();
  manager.open(new Account('111-111-111', '김기정', 1111, 2000));
  manager.open(new Account('111-111-222', '정지원', 1111, 2000));
  manager.open(new MinusAccount('1111-222-222', '정지원', 1111, 2000, 5000));
}

function hiddenAllhidden() {
  const inputE = document.getElementsByClassName('inputError');
  for (let i = 0; i < inputE.length; i += 1) {
    inputE[i].style.display = 'none';
  }
}

function resetColor() {
  const inputT = document.querySelectorAll('input[type=text]');
  document.getElementById('accountType').style.borderColor = borderColorDefault;
  document.getElementById('passwd').style.borderColor = borderColorDefault;
  for (let i = 0; i < inputT.length; i += 1) {
    inputT[i].style.borderColor = borderColorDefault;
  }
}

function resetAccount() {
  tables.innerHTML = '';
}

function addAccountInfo(account) {
  let output = '';
  if (account instanceof MinusAccount) {
    output += '<tr>';
    output += '<td>마이너스</td>';
    output += '<td>';
    output += account.accountNum;
    output += '</td>';
    output += '<td>';
    output += account.accountOwner;
    output += '</td>';
    output += '<td>';
    output += account.restMoney;
    output += '</td>';
    output += '<td>';
    output += account.borrowMoney;
    output += '</td>';
    output += '</tr>';
    tables.innerHTML += output;
  } else if (account instanceof Account) {
    output += '<tr>';
    output += '<td>입출금</td>';
    output += '<td>';
    output += account.accountNum;
    output += '</td>';
    output += '<td>';
    output += account.accountOwner;
    output += '</td>';
    output += '<td>';
    output += account.restMoney;
    output += '</td>';
    output += '<td></td>';
    output += '</tr>';
    tables.innerHTML += output;
  }
}

function clearInput(...args) {
  const inputs = args;
  for (let i = 0; i < inputs.length; i += 1) {
    inputs[i].value = '';
  }
}

function resizeHeight() {
  document.getElementById('messages').style.height = `${document.getElementsByClassName('user_info')[0].offsetHeight}px`;
  document.getElementById('message').style.height = `${document.getElementsByClassName('user_info')[0].offsetHeight - 20}px`;
}

function showListAll() {
  hiddenAllhidden();
  resetColor();
  resetAccount();

  const lists = manager.listAll();
  if (document.getElementById('accountType').selectedIndex === 0) {
    for (let i = 0; i < lists.length; i += 1) {
      addAccountInfo(lists[i]);
    }
  } else if (document.getElementById('accountType').selectedIndex === 1) {
    for (let i = 0; i < lists.length; i += 1) {
      if (lists[i].constructor.name === 'Account') {
        addAccountInfo(lists[i]);
      }
    }
  } else if (document.getElementById('accountType').selectedIndex === 2) {
    for (let i = 0; i < lists.length; i += 1) {
      if (lists[i].constructor.name === 'MinusAccount') {
        addAccountInfo(lists[i]);
      }
    }
  } else {
    document.getElementById('accountType').style.borderColor = borderColorError;
    document.getElementsByClassName('inputError')[0].style.display = 'block';
  }
  resizeHeight();
}

function accountTypeListener(e) {
  if (e.target.selectedIndex === 2) {
    document.getElementById('borrowMoney').disabled = false;
    document.getElementById('borrowMoney').placeholder = '예) 1000000';
  } else {
    document.getElementById('borrowMoney').disabled = true;
    document.getElementById('borrowMoney').placeholder = '';
  }
  document.getElementById('accountType').style.borderColor = borderColorDefault;
  document.getElementsByClassName('inputError')[0].style.display = 'none';
  resizeHeight();
}

function getAccount() {
  hiddenAllhidden();
  resetColor();
  resetAccount();

  const accountNumIT = document.getElementById('getAccount').parentElement.children[1];
  const accountNum = accountNumIT.value;
  if (isValidAccountNum(accountNum)) {
    const account = manager.get(accountNum);
    if (account == null) {
      accountNumIT.style.borderColor = borderColorError;
      document.getElementsByClassName('inputError')[1].lastChild.nodeValue = ErrorMessage.accountNotExist;
      document.getElementsByClassName('inputError')[1].style.display = 'block';
    } else {
      accountNumIT.style.borderColor = borderColorDefault;
      document.getElementsByClassName('inputError')[1].style.display = 'none';
      addAccountInfo(account);
      clearInput(accountNumIT);
      document.getElementById('message').value += `${accountNum} 계좌 조회 완료\r\n`;
    }
  } else {
    accountNumIT.style.borderColor = borderColorError;
    document.getElementsByClassName('inputError')[1].lastChild.nodeValue = ErrorMessage.accountNumWrong;
    document.getElementsByClassName('inputError')[1].style.display = 'block';
  }
}

function removeAccount() {
  hiddenAllhidden();
  resetColor();
  resetAccount();

  const accountNumIT = document.getElementById('getAccount').parentElement.children[1];
  const accountNum = accountNumIT.value;
  if (isValidAccountNum(accountNum)) {
    if (manager.remove(accountNum)) {
      accountNumIT.style.borderColor = borderColorDefault;
      document.getElementsByClassName('inputError')[1].style.display = 'none';
      clearInput(accountNumIT);
      document.getElementById('message').value += `${accountNum} 계좌 삭제 완료\r\n`;
    } else {
      accountNumIT.style.borderColor = borderColorError;
      document.getElementsByClassName('inputError')[1].lastChild.nodeValue = ErrorMessage.accountNotExist;
      document.getElementsByClassName('inputError')[1].style.display = 'block';
    }
  } else {
    accountNumIT.style.borderColor = borderColorError;
    document.getElementsByClassName('inputError')[1].lastChild.nodeValue = ErrorMessage.accountNumWrong;
    document.getElementsByClassName('inputError')[1].style.display = 'block';
  }
}

function searchAccount() {
  hiddenAllhidden();
  resetColor();
  resetAccount();

  const accountOwnerIT = document.getElementById('searchAccount').parentElement.children[1];
  const accountOwner = accountOwnerIT.value;
  if (accountOwner.length === 0) {
    accountOwnerIT.style.borderColor = borderColorError;
    document.getElementsByClassName('inputError')[2].lastChild.nodeValue = ErrorMessage.accountOwnerEmpty;
    document.getElementsByClassName('inputError')[2].style.display = 'block';
    return;
  }
  const accounts = manager.search(accountOwner);
  if (accounts.length === 0) {
    accountOwnerIT.style.borderColor = borderColorError;
    document.getElementsByClassName('inputError')[2].lastChild.nodeValue = ErrorMessage.accountOwnerNotExist;
    document.getElementsByClassName('inputError')[2].style.display = 'block';
  } else {
    accountOwnerIT.style.borderColor = borderColorDefault;
    document.getElementsByClassName('inputError')[2].style.display = 'none';
    for (let i = 0; i < accounts.length; i += 1) {
      addAccountInfo(accounts[i]);
    }
    clearInput(accountOwnerIT);
    document.getElementById('message').value += `${accountOwner}님 계좌 조회 완료\r\n`;
  }
}

function openAccount() {
  hiddenAllhidden();
  resetColor();

  const accountNumIT = document.getElementById('getAccount').parentElement.children[1];
  const accountOwnerIT = document.getElementById('searchAccount').parentElement.children[1];
  const passwdIT = document.getElementById('passwd');
  const restMoneyIT = document.getElementById('depositMoney');
  const borrowMoneyIT = document.getElementById('borrowMoney');
  const inputE = document.getElementsByClassName('inputError');

  if (document.getElementById('accountType').selectedIndex === 3 || document.getElementById('accountType').selectedIndex === 0) {
    document.getElementById('accountType').style.borderColor = borderColorError;
    inputE[0].style.display = 'block';
  } else if (isEmpty(accountNumIT.value)) {
    accountNumIT.style.borderColor = borderColorError;
    inputE[1].lastChild.nodeValue = ErrorMessage.accountNumEmpty;
    inputE[1].style.display = 'block';
    document.getElementById('getAccount').parentElement.children[1].focus();
  } else if (!isValidAccountNum(accountNumIT.value)) {
    accountNumIT.style.borderColor = borderColorError;
    inputE[1].lastChild.nodeValue = ErrorMessage.accountNumWrong;
    inputE[1].style.display = 'block';
    document.getElementById('getAccount').parentElement.children[1].focus();
  } else if (isEmpty(accountOwnerIT.value)) {
    accountOwnerIT.style.borderColor = borderColorError;
    inputE[2].lastChild.nodeValue = ErrorMessage.accountOwnerEmpty;
    inputE[2].style.display = 'block';
    document.getElementById('searchAccount').parentElement.children[1].focus();
  } else if (isEmpty(passwdIT.value)) {
    passwdIT.style.borderColor = borderColorError;
    inputE[3].lastChild.nodeValue = ErrorMessage.passwdEmpty;
    inputE[3].style.display = 'block';
    document.getElementById('passwd').focus();
  } else if (!isValidPasswd(passwdIT.value)) {
    passwdIT.style.borderColor = borderColorError;
    inputE[3].lastChild.nodeValue = ErrorMessage.passwdWrong;
    inputE[3].style.display = 'block';
    document.getElementById('passwd').focus();
  } else if (isEmpty(restMoneyIT.value)) {
    restMoneyIT.style.borderColor = borderColorError;
    inputE[4].lastChild.nodeValue = ErrorMessage.depositMoneyEmpty;
    inputE[4].style.display = 'block';
    document.getElementById('depositMoney').focus();
  } else if (!isValidDepositMoney(restMoneyIT.value)) {
    restMoneyIT.style.borderColor = borderColorError;
    inputE[4].lastChild.nodeValue = ErrorMessage.depositMoneyWrong;
    inputE[4].style.display = 'block';
    document.getElementById('depositMoney').focus();
  } else if (document.getElementById('accountType').selectedIndex === 2) {
    if (isEmpty(borrowMoneyIT.value)) {
      borrowMoneyIT.style.borderColor = borderColorError;
      inputE[5].lastChild.nodeValue = ErrorMessage.borrowMoneyEmpty;
      inputE[5].style.display = 'block';
      document.getElementById('borrowMoney').focus();
    } else if (!isValidBorrowMoney(borrowMoneyIT.value)) {
      borrowMoneyIT.style.borderColor = borderColorError;
      inputE[5].lastChild.nodeValue = ErrorMessage.borrowMoneyWrong;
      inputE[5].style.display = 'block';
      document.getElementById('borrowMoney').focus();
    } else if (!manager.open(new MinusAccount(accountNumIT.value, accountOwnerIT.value, passwdIT.value, restMoneyIT.value - borrowMoneyIT.value, borrowMoneyIT.value))) {
      accountNumIT.style.borderColor = borderColorError;
      inputE[1].lastChild.nodeValue = ErrorMessage.accountExist;
      inputE[1].style.display = 'block';
      document.getElementById('borrowMoney').focus();
    } else {
      clearInput(accountNumIT, accountOwnerIT, passwdIT, restMoneyIT, borrowMoneyIT);
      document.getElementById('message').value += '마이너스 계좌 개설 완료!\r\n';
    }
  } else if (document.getElementById('accountType').selectedIndex === 1) {
    if (!manager.open(new Account(accountNumIT.value, accountOwnerIT.value, passwdIT.value, restMoneyIT.value))) {
      accountNumIT.style.borderColor = borderColorError;
      inputE[1].lastChild.nodeValue = ErrorMessage.accountExist;
      inputE[1].style.display = 'block';
      document.getElementById('borrowMoney').focus();
    } else {
      clearInput(accountNumIT, accountOwnerIT, passwdIT, restMoneyIT);
      document.getElementById('message').value += '입출금 계좌 개설 완료!\r\n';
    }
  } else {
    document.getElementById('message').value += '계좌 개설에 실패하였습니다.\r\n';
  }
}

function isValid(event) {
  const eventTarget = event.target;
  const textId = event.target.id;
  const textValue = event.target.value;
  const fname = `isValid${textId.slice(0, 1).toUpperCase() + textId.slice(1)}`;
  const errortarget = event.target.parentElement.children;
  resizeHeight();

  if (textValue.length === 0) {
    const empty = `${textId}Empty`;
    eventTarget.style.borderColor = borderColorError;
    errortarget[errortarget.length - 1].lastChild.nodeValue = ErrorMessage[empty];
    errortarget[errortarget.length - 1].style.display = 'block';
    return;
  }
  if (window[fname](textValue)) {
    eventTarget.style.borderColor = borderColorDefault;
    errortarget[errortarget.length - 1].style.display = 'none';
  } else {
    const wrong = `${textId}Wrong`;
    eventTarget.style.borderColor = borderColorError;
    errortarget[errortarget.length - 1].lastChild.nodeValue = ErrorMessage[wrong];
    errortarget[errortarget.length - 1].style.display = 'block';
  }
}

function eventRegist() {
  document.getElementById('accountType').onchange = accountTypeListener;
  document.getElementById('getAccount').onclick = getAccount;
  document.getElementById('listAllAccount').onclick = showListAll;
  document.getElementById('removeAccount').onclick = removeAccount;
  document.getElementById('searchAccount').onclick = searchAccount;
  document.getElementById('openAccount').onclick = openAccount;
  document.getElementById('accountNum').onkeyup = isValid;
  document.getElementById('accountOwner').onkeyup = isValid;
  document.getElementById('passwd').onkeyup = isValid;
  document.getElementById('depositMoney').onkeyup = isValid;
  document.getElementById('borrowMoney').onkeyup = isValid;
}

window.onload = function onload() {
  init();
  eventRegist();
};
