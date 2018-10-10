/**
 * 유효성 검증을 위한 자바스크립트 파일
 */


function isEmpty(value) { // eslint-disable-line no-unused-vars
  if (value == null || value.length === 0) {
    return true;
  }
  return false;
}

function isValidAccountNum(value) { // eslint-disable-line no-unused-vars
  const pattern = /\d{3}-\d{3}-\d{3}$/;
  return pattern.test(value);
}

function isValidAccountOwner(value) { // eslint-disable-line no-unused-vars
  const pattern = /^[a-zA-Zㄱ-힣0-9]*$/;
  return pattern.test(value);
}

function isValidPasswd(value) { // eslint-disable-line no-unused-vars
  const pattern = /(?=.*\d)(?=.*[#$@!%&*?])[A-Za-z\d#$@!%&*?]{8,}$/;
  return pattern.test(value);
}

function isValidDepositMoney(value) { // eslint-disable-line no-unused-vars
  if (value < 0) {
    return false;
  }
  const pattern = /^\d*[^.]$/;
  return pattern.test(value);
}

function isValidBorrowMoney(value) { // eslint-disable-line no-unused-vars
  if (value < 0) {
    return false;
  }
  const pattern = /^\d*[^.]$/;
  return pattern.test(value);
}
