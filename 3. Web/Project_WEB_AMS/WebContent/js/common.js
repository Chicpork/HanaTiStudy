let tables;
let manager;
const borderColorDefault = '#ccc';
const borderColorError = 'crimson';

/**
 * ams 문서 실행시 해야할 초기 작업들
 */
function init() {
  tables = document.getElementById('table').contentWindow.document.getElementById('accounts');
  manager = new AccountManager();
  manager.open(new Account('111-111-111', '김기정', 1111, 2000));
  manager.open(new Account('111-111-222', '정지원', 1111, 2000));
  manager.open(new MinusAccount('1111-222-222', '정지원', 1111, 2000, 5000));
}

/**
 * 화면에 나온 경고 메시지 숨기는 기능
 */
function hiddenAllhidden() {
  const inputE = document.getElementsByClassName('inputError');
  for (let i = 0; i < inputE.length; i += 1) {
    inputE[i].style.display = 'none';
  }
}

/**
 * 화면 색을 초기 색으로 돌리는 기능
 */
function resetColor() {
  const inputT = document.querySelectorAll('input[type=text]');
  document.getElementById('accountType').style.borderColor = borderColorDefault;
  document.getElementById('passwd').style.borderColor = borderColorDefault;
  for (let i = 0; i < inputT.length; i += 1) {
    inputT[i].style.borderColor = borderColorDefault;
  }
}

/**
 * 화면에 출력된 계좌 정보 초기화
 */
function resetAccount() {
  tables.innerHTML = '';
}

/**
 * 계좌 정보를 받아 화면에 출력해주는 기능
 * @param {*} account 출력하고 싶은 계좌
 */
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

/**
 * 화면 input text를 받아 그 안의 내용을 초기화 해주는 기능
 * @param  {...any} args 초기화 하고자 하는 input text타입
 */
function clearInput(...args) {
  const inputs = args;
  for (let i = 0; i < inputs.length; i += 1) {
    inputs[i].value = '';
  }
}

/**
 * 화면에 메시지 출력창과 정보 입력창의 높이를 조정하기 위한 기능
 */
function resizeHeight() {
  document.getElementById('messages').style.height = `${document.getElementsByClassName('user_info')[0].offsetHeight}px`;
  document.getElementById('message').style.height = `${document.getElementsByClassName('user_info')[0].offsetHeight - 20}px`;
}

/**
 * 현재 만들어진 계좌 정보 전체를 보여주는 기능
 */
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

/**
 * 계좌타입 변경 시 확인할 요소 검증 기능
 * @param {*} e 이벤트발생 소스
 */
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

/**
 * 계좌번호로 계좌 정보를 얻어오는 기능
 */
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
  resizeHeight();
}

/**
 * 계좌번호로 계좌를 삭제하는 기능
 */
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
  resizeHeight();
}

/**
 * 계좌이름으로 계좌 정보를 검색하는 기능
 */
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
  resizeHeight();
}

/**
 * 계좌를 새로 개설하는 기능
 */
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
  resizeHeight();
}

/**
 * 계좌 관련 정보 입력시 유효성 검증하는 기능
 * @param {*} event 입력시 발생하는 이벤트 소스와 관련된 정보
 */
function isValid(event) {
  const eventTarget = event.target;
  const textId = event.target.id;
  const textValue = event.target.value;
  const fname = `isValid${textId.slice(0, 1).toUpperCase() + textId.slice(1)}`;
  const errortarget = event.target.parentElement.children;

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
  resizeHeight();
}

/**
 * 이벤트 등록을 위한 함수
 */
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

/**
 * ams가 시작된 이후 모든 기능들이 동작하기 위해서 만들어진 기능
 */
window.onload = function onload() {
  init();
  eventRegist();
};
