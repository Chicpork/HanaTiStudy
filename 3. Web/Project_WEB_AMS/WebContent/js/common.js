let tables;
let manager;

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

function showListAll() {
  hiddenAllhidden();
  resetAccount();
  const lists = manager.listAll();
  for (let i = 0; i < lists.length; i += 1) {
    addAccountInfo(lists[i]);
  }
}

function accountTypeListener(e) {
  if (e.target.selectedIndex === 1) {
    document.getElementById('borrowMoney').disabled = false;
    document.getElementById('borrowMoney').placeholder = '예) 1000000';
  } else {
    document.getElementById('borrowMoney').disabled = true;
    document.getElementById('borrowMoney').placeholder = '';
  }
  document.getElementsByClassName('inputError')[0].style.display = 'none';
}

function getAccount() {
  hiddenAllhidden();
  resetAccount();
  const accountNum = document.getElementById('getAccount').parentElement.children[1].value;
  if (isValidAccountNum(accountNum)) {
    const account = manager.get(document.getElementById('getAccount').parentElement.children[1].value);
    if (account == null) {
      document.getElementsByClassName('inputError')[1].lastChild.nodeValue = '해당하는 계좌가 없습니다.';
      document.getElementsByClassName('inputError')[1].style.display = 'block';
    } else {
      document.getElementsByClassName('inputError')[1].style.display = 'none';
      addAccountInfo(account);
      document.getElementById('message').lastChild.nodeValue = `${accountNum} 계좌 조회 완료`;
    }
  } else {
    document.getElementsByClassName('inputError')[1].lastChild.nodeValue = '잘못된 계좌 번호를 입력하셨습니다.';
    document.getElementsByClassName('inputError')[1].style.display = 'block';
  }
}

function removeAccount() {
  hiddenAllhidden();
  resetAccount();
  const accountNum = document.getElementById('getAccount').parentElement.children[1].value;
  if (isValidAccountNum(accountNum)) {
    if (manager.remove(document.getElementById('getAccount').parentElement.children[1].value)) {
      document.getElementsByClassName('inputError')[1].style.display = 'none';
      document.getElementById('message').lastChild.nodeValue = `${accountNum} 계좌 삭제 완료`;
    } else {
      document.getElementsByClassName('inputError')[1].lastChild.nodeValue = '해당하는 계좌가 없습니다.';
      document.getElementsByClassName('inputError')[1].style.display = 'block';
    }
  } else {
    document.getElementsByClassName('inputError')[1].lastChild.nodeValue = '잘못된 계좌 번호를 입력하셨습니다.';
    document.getElementsByClassName('inputError')[1].style.display = 'block';
  }
}

function searchAccount() {
  hiddenAllhidden();
  resetAccount();

  const accountOwner = document.getElementById('searchAccount').parentElement.children[1].value;
  const accounts = manager.search(accountOwner);
  if (accounts.length === 0) {
    document.getElementsByClassName('inputError')[2].style.display = 'block';
  } else {
    document.getElementsByClassName('inputError')[2].style.display = 'none';
    for (let i = 0; i < accounts.length; i += 1) {
      addAccountInfo(accounts[i]);
    }
    document.getElementById('message').lastChild.nodeValue = `${accountOwner}님 계좌 조회 완료`;
  }
}

function openAccount() {
  hiddenAllhidden();

  const accountNum = document.getElementById('getAccount').parentElement.children[1].value;
  const accountOwner = document.getElementById('searchAccount').parentElement.children[1].value;
  const passwd = document.getElementById('passwd').value;
  const restMoney = document.getElementById('depositMoney').value;
  const borrowMoney = document.getElementById('borrowMoney').value;
  const inputE = document.getElementsByClassName('inputError');

  if (document.getElementById('accountType').selectedIndex === 2) {
    inputE[0].style.display = 'block';
  } else if (isEmpty(accountNum)) {
    inputE[1].lastChild.nodeValue = '계좌 번호를 입력해주세요.';
    inputE[1].style.display = 'block';
    document.getElementById('getAccount').parentElement.children[1].focus();
  } else if (!isValidAccountNum(accountNum)) {
    inputE[1].lastChild.nodeValue = '잘못된 계좌 번호를 입력하셨습니다.';
    inputE[1].style.display = 'block';
    document.getElementById('getAccount').parentElement.children[1].focus();
  } else if (isEmpty(accountOwner)) {
    inputE[2].lastChild.nodeValue = '이름을 입력해 주세요.';
    inputE[2].style.display = 'block';
    document.getElementById('searchAccount').parentElement.children[1].focus();
  } else if (isEmpty(passwd)) {
    inputE[3].lastChild.nodeValue = '비밀 번호를 입력해주세요.';
    inputE[3].style.display = 'block';
    document.getElementById('passwd').focus();
  } else if (!isValidPasswd(passwd)) {
    inputE[3].lastChild.nodeValue = '비밀 번호 형식에 맞춰 입력해주세요.';
    inputE[3].style.display = 'block';
    document.getElementById('passwd').focus();
  } else if (isEmpty(restMoney)) {
    inputE[4].lastChild.nodeValue = '입금금액을 입력해주세요.';
    inputE[4].style.display = 'block';
    document.getElementById('depositMoney').focus();
  } else if (!isValidMoney(restMoney)) {
    inputE[4].lastChild.nodeValue = '입금금액은 양의 정수여야 합니다.';
    inputE[4].style.display = 'block';
    document.getElementById('depositMoney').focus();
  } else if (document.getElementById('accountType').selectedIndex === 1) {
    if (isEmpty(borrowMoney)) {
      inputE[5].lastChild.nodeValue = '대출금액을 입력해주세요.';
      inputE[5].style.display = 'block';
      document.getElementById('borrowMoney').focus();
    } else if (!isValidMoney(borrowMoney)) {
      inputE[5].lastChild.nodeValue = '대출금액은 양의 정수여야 합니다.';
      inputE[5].style.display = 'block';
      document.getElementById('borrowMoney').focus();
    } else if (!manager.open(new MinusAccount(accountNum, accountOwner, passwd, restMoney - borrowMoney, borrowMoney))) {
      inputE[1].lastChild.nodeValue = '이미 존재하는 계좌 번호입니다.';
      inputE[1].style.display = 'block';
      document.getElementById('borrowMoney').focus();
    } else {
      document.getElementById('message').lastChild.nodeValue = '마이너스 계좌 개설 완료!';
    }
  } else if (document.getElementById('accountType').selectedIndex === 0) {
    if (!manager.open(new Account(accountNum, accountOwner, passwd, restMoney))) {
      inputE[1].lastChild.nodeValue = '이미 존재하는 계좌 번호입니다.';
      inputE[1].style.display = 'block';
      document.getElementById('borrowMoney').focus();
    } else {
      document.getElementById('message').lastChild.nodeValue = '입출금 계좌 개설 완료!';
    }
  } else {
    document.getElementById('message').lastChild.nodeValue = '여기까지 오다니';
  }
}

function eventRegist() {
  document.getElementById('accountType').onchange = accountTypeListener;
  document.getElementById('getAccount').onclick = getAccount;
  document.getElementById('listAllAccount').onclick = showListAll;
  document.getElementById('removeAccount').onclick = removeAccount;
  document.getElementById('searchAccount').onclick = searchAccount;
  document.getElementById('openAccount').onclick = openAccount;
}

window.onload = function onload() {
  init();
  eventRegist();
};
