/**
 * 유효성 검증을 위한 자바스크립트 파일
 */

/**
 * 영문자와 숫자로 이루어진 6 ~ 10자리 아이디
 * @param {*} value 유효성 검증이 필요한 값
 */
function isValidId(value) { // eslint-disable-line no-unused-vars
  const pattern = /^\w{6,10}$/;
  return pattern.test(value);
}

/**
 * 이름 검증
 * 숫자, 한글, 영문자만 가능
 * @param {*} value 유효성 검증이 필요한 값
 */
function isValidName(value) { // eslint-disable-line no-unused-vars
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
 * 이메일 형식 체크
 * @param {*} value 유효성 검증이 필요한 값
 */
function isValidEmail(value) { // eslint-disable-line no-unused-vars
  const pattern = /\w{1,}@[a-zA-Z]{1,}.[a-zA-Z]{1,}$/;
  return pattern.test(value);
}
