module.exports = {
    "extends": "airbnb-base",
    "env": {
        "browser": true,
        "node": true,
        "jasmine": true
    },
    "globals": {
        "Account": true,
        "MinusAccount": true,
        "AccountManager": true,
        "isEmpty": true,
        "isValidAccountNum": true,
        "isValidPasswd": true,
        "isValidDepositMoney": true,
        "isValidBorrowMoney": true,
        "ErrorMessage": true
    },
    "rules": {
        "max-len": 'off'
    }
};