/**
 * 마이너스 계좌.
 */
/* global Account */
function MinusAccount(accountNum, accountOwner, passwd, restMoney, borrowMoney) {
  Account.call(this, accountNum, accountOwner, passwd, restMoney);
  this.borrowMoney = borrowMoney;
}
MinusAccount.prototype = Object.create(Account.prototype);
MinusAccount.prototype.constructor = MinusAccount;
