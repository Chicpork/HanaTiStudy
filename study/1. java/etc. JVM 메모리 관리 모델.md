JVM 메모리 관리 모델
===============

Heap > Static > Stack  : 메모리 크기 순.

| Static | Stack |  Heap  |
| :----: | :---: | :----: |
|  ㅁㅁ  |  ㅁ   | ㅁㅁㅁ |
|  ㅁㅁ  |  ㅁ   | ㅁㅁㅁ |
|  ㅁㅁ  |  ㅁ   | ㅁㅁㅁ |

#### Account account; 를 선언 할 때

* Static(메소드)영역
  * Account가 스태틱 영역에 저장
* Stack 영역
  * account는 스택 영역에 저장
* Heap 영역

#### 이후 account = new Account(); 를 선언하면

- Static(메소드)영역
  - Account가 스태틱 영역에 저장
- Stack 영역
  - account는 스택 영역에 저장
- Heap 영역
  - 스태틱 영역의 Account를 복사해 힙 영역에 주소와 함께 저장한뒤
    스택 영역의 account에 주소값을 저장해둔다.

#### 그런데 만약 클래스에 클래스(static) 변수가 있으면

* 스태틱 영역에 저장될 때 클래스 변수만 따로 빠져나와 저장됨.
* 그래서 다른 Account 인스턴스를 선언하게 되면 바로 이 클래스 변수를 사용하게 됨.

#### Static 영역이 생명 주기가 가장 큼.

* 그 다음이 Heap이고 가장 짧은 게 Stack 영역임
* Stack 영역은 지역변수가 선언되는 곳이기 때문.
* Heap 영역에 선언되있던 인스턴스가 메모리에서 사라지는 경우는 가비지 컬렉터에 의해 사라짐.
  (참조가 끊어질 시 사라짐. account=null;이라고 하면 아마 관련 인스턴스는 삭제될 것)