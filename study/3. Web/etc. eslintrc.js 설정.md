# eslintrx.js 설정

``` js
module.exports = {
    "extends": "airbnb-base",
    "env": {
        "browser": true,
        "node": true,
        "jasmine": true,
        "jquery": true
    },
    "globals": {
        "SomeThing": true
    },
    "rules": {
        "max-len": 'off'
    }
};
```

* extends : 어떤 형식을 따를지
* env : 환경으로 어떤 것들을 추가할 지
  * browser를 true로 설정해야 document 에러가 나지 않는다.
* globals : 글로벌 변수를 설정해주는 영역