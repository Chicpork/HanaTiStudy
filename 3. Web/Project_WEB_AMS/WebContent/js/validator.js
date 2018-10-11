/**
 * 유효성 검증을 위한 자바스크립트 파일
 */

/**
 * 빈 문자인지 검증
 * @param {*} value 유효성 검증이 필요한 값
 */
function isEmpty(value) { // eslint-disable-line no-unused-vars
  if (value == null || value.length === 0) {
    return true;
  }
  return false;
}

/**
 * 111-222-333과 같은 형식의 계좌 번호인지 검증
 * @param {*} value 유효성 검증이 필요한 값
 */
function isValidAccountNum(value) { // eslint-disable-line no-unused-vars
  const pattern = /\d{3}-\d{3}-\d{3}$/;
  return pattern.test(value);
}

/**
 * 이름 검증
 * 숫자, 한글, 영문자만 가능
 * @param {*} value 유효성 검증이 필요한 값
 */
function isValidAccountOwner(value) { // eslint-disable-line no-unused-vars
  const pattern = /^[a-zA-Zㄱ-힣0-9]*$/;
  return pattern.test(value);
}

/**
 * 숫자, 영문자, 특수문자가 적어도 1개 들어가 있는지 검증
 * @param {*} value 유효성 검증이 필요한 값
 */
function isValidPasswd(value) { // eslint-disable-line no-unused-vars
  const pattern = /(?=.*\d)(?=.*[#$@!%&*?])[A-Za-z\d#$@!%&*?]{8,}$/;
  return pattern.test(value);
}

/**
 * 입금 금액이 양수인지, 정수가 입력되었는지 검증
 * @param {*} value 유효성 검증이 필요한 값
 */
function isValidDepositMoney(value) { // eslint-disable-line no-unused-vars
  if (value < 0) {
    return false;
  }
  const pattern = /^\d*[^.]$/;
  return pattern.test(value);
}

/**
 * 대출 금액이 양수인지, 정수가 입력되었는지 검증
 * @param {*} value 유효성 검증이 필요한 값
 */
function isValidBorrowMoney(value) { // eslint-disable-line no-unused-vars
  if (value < 0) {
    return false;
  }
  const pattern = /^\d*[^.]$/;
  return pattern.test(value);
}
