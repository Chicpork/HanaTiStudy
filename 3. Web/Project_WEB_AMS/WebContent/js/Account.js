/**
 * 입출금 계좌를 저장하는 함수와 기능
 */

function Account(accountNum, accountOwner, passwd, restMoney) {
  this.accountNum = accountNum;
  this.accountOwner = accountOwner;
  this.passwd = passwd;
  this.restMoney = restMoney;
}

Account.bankName = '하나은행';

Account.prototype.deposit = function deposit(money) {
  this.restMoney += money;
  return this.restMoney;
};

Account.prototype.withdraw = function withdraw(money) {
  this.restMoney -= money;
  return this.restMoney;
};

Account.prototype.checkPasswd = function checkPasswd(pw) {
  if (this.passwd === pw) {
    return true;
  }
  return false;
};

Account.prototype.toString = function toString() {
  let output = '';
  Object.keys(this).forEach((key) => {
    output += key;
    output += ' : ';
    output += this[key];
    output += '\t';
  });
  return output;
};

Account.prototype.equals = function equals(account) {
  if (this.accountNum === account.accountNum) {
    return true;
  }
  return false;
};
