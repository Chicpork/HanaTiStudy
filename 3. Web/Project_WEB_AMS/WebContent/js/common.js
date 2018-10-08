window.onload = function () {
  init()
  eventRegist()
}

function init() {
  tables = document.getElementById('table').contentWindow.document.getElementById('accounts');
  manager = new AccountManager();
  console.log(manager.open(new Account('111-111-111', '김기정', 1111, 2000)));
  console.log(manager.open(new Account('111-111-222', '정지원', 1111, 2000)));
  console.log(manager.open(new MinusAccount('1111-2222-2222', '정지원', 1111, 2000, 5000)));
}

function eventRegist() {
  document.getElementById('accountType').onchange = accountType_listener;
  document.getElementById('getAccount').onclick = getAccount;
  document.getElementById('listAllAccount').onclick = showListAll;
  document.getElementById('removeAccount').onclick = removeAccount;
  document.getElementById('searchAccount').onclick = searchAccount;
  document.getElementById('openAccount').onclick = openAccount;
}

function accountType_listener(e) {
  if (e.target.selectedIndex == 1) {
    document.getElementById('borrowMoney').disabled = false;
    document.getElementById('borrowMoney').placeholder = "예) 1000000";
  } else {
    document.getElementById('borrowMoney').disabled = true;
    document.getElementById('borrowMoney').placeholder = '';
  }
  document.getElementsByClassName("inputError")[0].style.display = "none";
}

function getAccount(e) {
  resetAccount();
  var account = manager.get(document.getElementById('getAccount').parentElement.children[1].value);
  if (account == null) {
    document.getElementsByClassName("inputError")[1].lastChild.nodeValue = "해당하는 계좌가 없거나 잘못된 계좌 번호를 입력하셨습니다.";
    document.getElementsByClassName("inputError")[1].style.display = "block";
  } else {
    document.getElementsByClassName("inputError")[1].style.display = "none";
    addAccountInfo(account);
  }
}

function removeAccount() {
  resetAccount();
  if (manager.remove(document.getElementById('getAccount').parentElement.children[1].value)) {
    document.getElementsByClassName("inputError")[1].style.display = "none";
  } else {
    document.getElementsByClassName("inputError")[1].style.display = "block";
  }
}

function searchAccount() {
  resetAccount();
  var accounts = manager.search(document.getElementById('searchAccount').parentElement.children[1].value);
  if (accounts.length == 0) {
    document.getElementsByClassName("inputError")[2].style.display = "block";
  } else {
    document.getElementsByClassName("inputError")[2].style.display = "none";
    for (let i = 0; i < accounts.length; i++) {
      addAccountInfo(accounts[i]);
    }
  }
}

function openAccount() {
  var accountNum = document.getElementById('getAccount').parentElement.children[1].value;
  var accountOwner = document.getElementById('searchAccount').parentElement.children[1].value;
  var passwd = document.getElementById('passwd').value;
  var restMoney = document.getElementById('depositMoney').value;
  var borrowMoney = document.getElementById('borrowMoney').value;

  hiddenAllhidden();
  if (document.getElementById('accountType').selectedIndex == 2) {
    document.getElementsByClassName("inputError")[0].style.display = "block";
  } else if (accountNum.length == 0) {
    document.getElementsByClassName("inputError")[1].style.display = "block";
    document.getElementById('getAccount').parentElement.children[1].focus();
  } else if (accountOwner.length == 0) {
    document.getElementsByClassName("inputError")[2].style.display = "block";
    document.getElementById('searchAccount').parentElement.children[1].focus();
  } else if (passwd.length == 0) {
    document.getElementsByClassName("inputError")[3].style.display = "block";
    document.getElementById('passwd').focus();
  } else if (restMoney < 0) {
    document.getElementsByClassName("inputError")[4].style.display = "block";
    document.getElementById('depositMoney').focus();
  } else if (document.getElementById('accountType').selectedIndex == 1 && borrowMoney < 0) {
    document.getElementsByClassName("inputError")[5].style.display = "block";
    document.getElementById('borrowMoney').focus();
  } else {
    if (document.getElementById('accountType').selectedIndex == 0) {
      if (manager.open(new Account(accountNum, accountOwner, passwd, restMoney))) {

      } else {
        document.getElementsByClassName("inputError")[1].style.display = "block";
        document.getElementsByClassName("inputError")[1].lastChild.nodeValue = "이미 존재하는 계좌 번호 입니다.";
      }
    } else {
      manager.open(new MinusAccount(accountNum, accountOwner, passwd, restMoney, borrowMoney));
    }
  }
}

function resetAccount() {
  var tablesNum = tables.childNodes.length - 1;
  for (let i = tablesNum; i >= 0; i--) {
    tables.removeChild(tables.childNodes[i]);
  }
}

function showListAll() {
  resetAccount();
  var lists = manager.listAll();
  for (var i = 0; i < lists.length; i++) {
    addAccountInfo(lists[i]);
  }
}

function addAccountInfo(account) {
  var output = '';
  if (account instanceof MinusAccount) {
    output += '<tr>';
    output += '<td>마이너스</td>';
    output += '<td>' + account.accountNum + '</td>';
    output += '<td>' + account.accountOwner + '</td>';
    output += '<td>' + account.restMoney + '</td>';
    output += '<td>' + account.borrowMoney + '</td>';
    output += '</tr>';
    tables.innerHTML += output;
  } else if (account instanceof Account) {
    output += '<tr>';
    output += '<td>입출금</td>';
    output += '<td>' + account.accountNum + '</td>';
    output += '<td>' + account.accountOwner + '</td>';
    output += '<td>' + account.restMoney + '</td>';
    output += '<td></td>';
    output += '</tr>';
    tables.innerHTML += output;
  }
}

function hiddenAllhidden() {
  document.getElementsByClassName("inputError")[0].style.display = "none";
  document.getElementsByClassName("inputError")[1].style.display = "none";
  document.getElementsByClassName("inputError")[2].style.display = "none";
  document.getElementsByClassName("inputError")[3].style.display = "none";
  document.getElementsByClassName("inputError")[4].style.display = "none";
  document.getElementsByClassName("inputError")[5].style.display = "none";
}