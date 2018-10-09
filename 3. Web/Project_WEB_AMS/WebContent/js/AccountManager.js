/**
 * 계좌 관리를 위한 계좌 관리 매니저
 */

function AccountManager() {
  this.accounts = [];
}

AccountManager.prototype.open = function open(account) {
  if (account instanceof Account) {
    for (let i = 0; i < this.accounts.length; i += 1) {
      if (this.accounts[i].equals(account)) {
        return false;
      }
    }
    this.accounts.push(account);
    return true;
  }
  return false;
};

AccountManager.prototype.listAll = function listAll() {
  return this.accounts;
};

AccountManager.prototype.get = function get(accountNum) {
  for (let i = 0; i < this.accounts.length; i += 1) {
    if (this.accounts[i].accountNum === accountNum) {
      return this.accounts[i];
    }
  }
  return null;
};

AccountManager.prototype.search = function search(accountOwner) {
  const output = [];
  for (let i = 0; i < this.accounts.length; i += 1) {
    if (this.accounts[i].accountOwner === accountOwner) {
      output.push(this.accounts[i]);
    }
  }
  return output;
};

AccountManager.prototype.remove = function remove(accountNum) {
  for (let i = 0; i < this.accounts.length; i += 1) {
    if (this.accounts[i].accountNum === accountNum) {
      this.accounts.splice(i, 1);
      return true;
    }
  }
  return false;
};
