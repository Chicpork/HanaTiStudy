/**
 * 
 */

function Account(accountNum, accountOwner, passwd, restMoney) {
    this.accountNum = accountNum;
    this.accountOwner = accountOwner;
    this.passwd = passwd;
    this.restMoney = restMoney;
}

Account.bankName = '하나은행';

Account.prototype.deposit = function (money) {
    this.restMoney += money;
    return this.restMoney;
};

Account.prototype.withdraw = function (money) {
    this.restMoney -= money;
    return this.restMoney;
};

Account.prototype.checkPasswd = function (pw) {
    if (this.passwd == pw) {
        return true;
    } else {
        return false;
    }
};

Account.prototype.toString = function () {
    var output = '';
    for (var key in this) {
        if (!(key in Account.prototype)) {
            output += key + ' : ' + this[key] + '\t';
        }
    }
    return output;
};

Account.prototype.equals = function (account) {
    if (this.toString() == account.toString()) {
        return true;
    }
    return false;
};