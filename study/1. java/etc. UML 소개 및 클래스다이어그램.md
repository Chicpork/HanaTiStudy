# UML 소개 및 클래스다이어그램

### 객체 모델링

- 모델링과 프로그래밍 비교

  ![1535001496515](.\img\modeling_vs_programming.png)

- 객체 관계 설정

  - 의존관계(Dependency) : use - a 관계
  - 집합관계(Aggregation) : has - a  관계
  - 일반화 관계(Generalization) : is - a 관계




### 모델링 언어 - UML(Unified Modeling Language)

* UML(Unified Modeling Language)
  * 개발하고자 하는 소프트웨어 시스템을 이해하기 쉽게 시각적으로 모델링 하기 위한 모델링 언어
  * 개발 프로세스 각 단계별 주요 산출물인 모델 생성을 위한 통합(표준) 그래픽 언어
    * 도형과 기호를 사용하는 9개의 다이어그램을 지원
    * Class Diagram, Object Diagram, Use-case Diagram, Sequence Diagram, Component Diagram, Deployment Diagram 등

- Use - case 모델
  ![1535001717492](.\img\usecasemodel.png)
- 클래스 다이어 그램
  ![1535001752475](.\img\classdiagram.png)
- 액티비티 다이어그램
  ![1535001760824](.\img\activitydiagram.png)
- 시퀀스 다이어그램
  ![1535001766501](.\img\sequencediagram.png)



### 클래스 다이어그램

- 클래스를 이용하여 시스템의 정적인 구조를 표현

- 클래스다이어그램 구성 요소

  - 클래스
    ![1535001900470](.\img\클래스다이어그램 구성 요소.png)

    - 각 객체의 공통요소를 추상화한 설계도이다.
    - 클래스이름, 속성, 메소드로 구성된다.

  - 가시성

    - 한 클래스의 속성이나 메소드가 다른 클래스에 대해서 어떻게 공개되어 있는지를 의미
    - public(+), protected(#), package(~), private(-)
    - 위의 예제에서 클래스이름 앞을 보면 확인할 수 있다.

  - 스코프
    ![1535001998298](.\img\umlscope.png)

    - 속성이나 메소드가 사용되는 범위를 나타낸다
    - 밑줄이 사용되면 클래스 스코프로 생각한다. 없으면 인스턴스 스코프이다.
    - 예제에서는 고객수와 고객수 취득이 클래스 속성과 메소드로 만들어야 하는 것들.

  - 관계
    ![1535002500921](.\img\association.png)

    - 클래스와 클래스 사이에 어떠한 관계가 있는지 나타낸다.

    - 인스턴스와 인스턴스 사이에 메시지 교환이 발생함을 의미

    - 관계가 있다면 표현할 땐 실선으로 이으면 된다.

    - 양쪽에 화살표가 없으면 상호작용이 이루어 진다는 것.

    - 만약 한쪽에만 화살표가 있으면 그 방향으로 작용이 이루어 진다는 의미.

    - 코드 예제

      ```java
      public class Item{
          private Customer orderCustomer;
      }
      public class Customer{
          private Item orderItem;
      }
      ```

  - 관계 다중도
    ![1535002748176](.\img\association2.png)

    - 관계를 맺고 있는 클래스의 인스턴스 수를 나타냄
    - 고정값(1, 10 등), 복수(\*), 범위(0..1,5..\* 등), 세트(2,4,6,7 등) 으로 나타냄.
      - 복수(*)는 0을 포함하는 것.
      - 범위(0..1, 5..* 등)에서 *은 제한 없다는 것을 의미.
    - 예제에서 한 학생은 강의 5개를 수강해야 하고
    - 한 강의는 학생 수 최소 3명에서 최대 40명까지 수강 가능하다는 것.
    - starUML에서 관계 다중도 기능을 이용하고 싶다면 실선 끝쪽 점을 더블클릭해서 입력.

  - 유도 가능성
    ![1535003372215](.\img\association3.png)

    - 클래스 사이에 존재하는 관계의 일정한 방향성을 의미
    - 유도가능성이 명확하지 않을 경우 화살표를 기술하지 않음

  - 관계 역할
    ![1535004428435](.\img\association4.png)

    - 관계를 수렴하는 클래스가 담당하는 역할의 이름
    - 역할명에는 가시성을 부여할 수 있다. 속성이나 메소드에 부과하는 가시성과 동일
    - 위의 예에서는 상품 입장에서 거래처는 발주자가 됨.

  - 관계_집합(Aggregation)
    ![1535003270910](.\img\Aggregation.png)

    - 클래스 사이에 전체-부분 관계가 존재함을 의미

    - 회사가 전체, 부서가 부분이 되는 것.

    - 관계 집합일 땐 생성자로 받아주면 됨.

    - has - a 관계

    - 코드 예제

      ```java
      public class 회사 {
          부서 department;
          회사(부서 department) {
              this.department = department;
          }
      }
      ```

  - 관계_복합(Composition)
    ![1535003701444](.\img\composition.png)

    - 클래스 사이에 전체-부분 관계가 있고, 그 연결이 매우 강함을 의미

    - 복합관계로 이루어진 클래스의 인스턴스는 라이프사이클이 같아진다

    - 전체쪽 인스턴스가 소멸되면 부분쪽 인스턴스도 같이 소멸하게 된다

    - 코드 예제

      ```java
      public class 회사 {
          부서 department;
          회사() {
              department = new 부서();
          }
      }
      ```

  - 관계_의존(Dependency)
    ![1535005975029](.\img\dependency.png)

    - 필요할때만 그때그때 사용하는 것.
    - use - a 관계라고 함.
    - 한 클래스가 다른 클래스에 어떤 형태로든 의존하고 있음을 나타냄
    - 주로 어떤 클래스가 동작을 하는 중에 일시적으로 다른 클래스를 사용함을 의미
    - 연결 강도가 강하지 않음.

  - 관계_일반화(Generalization)

    ![1535006947758](.\img\generalization.png)

    - 자바에서 상속이라 불리는 것을 표현하는 것.
    - 화살표를 이용해 표현함.
    - is - a 관계

  - 관계_실현(Realization)
    ![1535274937826](img/realization.png)

    - 인터페이스는 클래스가 외부에 공개하는 사양을 의미
    - 인터페이스에 정의한 사양을 별도의 다른 클래스에서 구체적으로 실현하는 것을 의미
    - 인터페이스가 외부에 공개하는 기능을 실현 관계로 연결된 클래스가 구현함을 의미
    - 일반화와 달리 화살표가 점선으로 이루어진다.

  - 추상클래스(Abstract Class)
    ![1535275058667](img/abstractclass.png)

    - 일부 메소드를 구현하지 않은 추상메소드를 가지는 추상적인 클래스를 의미
    - 추상클래스로부터 인스턴스가 생성될 수 없다
    - 추상클래스를 상속받는 서브클래스는 추상메소드를 반드시 구현하여야 한다
    - 추상클래스는 일반화에서 슈퍼클래스(부모클래스)의 이름을 기울여서 적으면 된다.
    - 추상메소드 또한 기울이면 추상메소드이다.

- 클래스 다이어그램을 만들때 확인할 순서

  - 관계를 가지는 지 먼저 확인해보기
  - 다음으로 관계 다중성 체크
  - ...