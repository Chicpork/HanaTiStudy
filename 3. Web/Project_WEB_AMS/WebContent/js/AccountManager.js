/**
 * 
 */

function AccountManager() {
    this.accounts = Array();
}

AccountManager.prototype.open = function (account) {
    if (account instanceof Account) {
        for (var key in this.accounts) {
            if (this.accounts[key].equals(account)) {
                return false;
            }
        }
        this.accounts.push(account);
        return true;
    } else {
        return false;
    }
}

AccountManager.prototype.listAll = function () {
    return this.accounts;
}

AccountManager.prototype.get = function (accountNum) {
    for (var key in this.accounts) {
        if (this.accounts[key].accountNum === accountNum) {
            return this.accounts[key];
        }
    }
    return null;
}

AccountManager.prototype.search = function (accountOwner) {
    var output = Array();
    for (var key in this.accounts) {
        if (this.accounts[key].accountOwner === accountOwner) {
            output.push(this.accounts[key]);
        }
    }
    return output;
}

AccountManager.prototype.remove = function (accountNum) {
    for (var key in this.accounts) {
        if (this.accounts[key].accountNum === accountNum) {
            this.accounts.splice(key, 1);
            return true;
        }
    }
    return false;
}