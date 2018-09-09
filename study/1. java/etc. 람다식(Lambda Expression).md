# 람다식(Lambda Expression)

* 함수(메소드)를 간단한 식(Expression)으로 표현하는 방법

* 익명 함수 (이름이 없는 함수)

* 람다식 작성방법

  1. 메소드의 이름과 반환타입을 제거하고 `->`를 블록{} 앞에 추가한다.

     ``` java
     int max(int a, int b) {
         return a > b ? a : b;
     }
     ```

     에서 아래와 같이 변경

     ``` java
     (int a, int b) -> { return a > b ? a : b; }
     ```

  2.  반환값이 있는 경우, 식이나 값만 적고 return문 생략 가능(끝에 ; 안붙임)

     ``` java
     (int a, int b) -> { return a > b ? a : b; }
     ```

     에서 아래와 같이 변경

     ``` java
     (int a, int b) -> a > b ? a : b
     ```

  3.  매개변수의 타입이 추론 가능하면 생략가능 (대부분 생략가능)

     ``` java
     (int a, int b) -> a > b ? a : b
     ```

     에서 아래와 같이 변경

     ``` java
     (a, b) -> a > b ? a : b
     ```

* 람다식 작성시 주의사항

  1.  매개변수가 하나인 경우, 괄호() 생략가능(타입이 없을 때만)

     ``` java
     (a) -> a*a
     (int a) -> a*a
     
     // 위의 식중 타입이 없는 위만 가능
     a -> a*a // OK
     int a -> a*a // Error
     ```

  2.  블록 안의 문장이 하나뿐일 때, 괄호{} 생략가능(끝에 ; 안 붙임)

     ``` java
     (int i) -> { System.out.println(i); }
     (int i) -> System.out.println(i) // OK
     ```

     그런데 하나뿐인 문장이 return문이면 괄호{} 생략 불가

     ``` java
     (int a, int b) -> { return a > b ? a : b; } // OK
     (int a, int b) -> return a > b ? a : b // Error
     ```



## 함수형 인터페이스

* 람다식은 익명 함수이긴 하지만 실제로는 익명 객체이다.

  ``` java
  (a, b) -> a > b ? a : b
  ```

  는 사실 다음과 같은 객체를 만들어 낸다

  ``` java
  new Object() {
      int functionName(int a, int b){
          return a > b ? a : b;
      }
  }
  ```

* 따라서 이 람다식을 사용하기 위해선 이 객체를 담을 무언가가 필요하게 되고 그게 함수형 인터페이스이다.

* 함수형 인터페이스는 인터페이스인데 선언부에 `@FunctionalInterface`가 추가되어 있는 인터페이스이다.

  * 이 인터페이스에는 하나의 추상 메소드만이 선언되어 있어야 한다.

* 코드 예제 :

  함수형 인터페이스 코드

  ``` java
  @FunctionalInterface
  public interface MyFunc {
  	public abstract int Calculator(int a, int b);
  }
  ```

  람다식을 이용하는 코드

  ``` java
  public class LambdaExmaple {
  
  	public static void main(String[] args) {
  
  		MyFunc add = (a, b) -> a + b;
  		MyFunc sub = (a, b) -> a - b;
  		MyFunc mul = (a, b) -> a * b;
  		MyFunc div = (a, b) -> a / b;
  		System.out.println(add.Calculator(6, 4));
  		System.out.println(sub.Calculator(6, 4));
  		System.out.println(mul.Calculator(6, 4));
  		System.out.println(div.Calculator(6, 4));
  
  	}
  
  }
  ```

  실행 결과

  ```
  10
  2
  24
  1
  ```



## java.util.function패키지

* 그런데 이런 함수형 인터페이스를 사용하려고 할때마다 만들면 힘들고 귀찮은 일이다. 그래서 이미 만들어져 있는 함수형 인터페이스들이 존재한다.



### 매개변수가 1개인 함수형 인터페이스

![1536469875283](img/functionPackage1.png)

* 코드 예제

  ``` java
  new Thread(new Runnable() {
      @Override
      public void run() {
          System.out.println("헐...");
      }
  }).start();
  
  // 아래 코드는 위의 코드와 똑같은 역할을 한다.
  new Thread(() -> {
      System.out.println("헐...");
  }).start();
  ```



### 매개변수가 2개인 함수형 인터페이스

![1536470047658](img/functionPackage2.png)

* 코드 예제 1 (BiPredicate)

  ``` java
  import java.util.function.BiPredicate;
  
  public class LambdaExmaple {
  
  	public static void main(String[] args) {
  
  		BiPredicate<Integer,Integer> f = (a,b) -> a > b;
  		System.out.println(f.test(10, 20));
  	}
  
  }
  // 결과 : false
  ```

* 코드 예제 2 (BiPredicate)

  ``` java
  import java.util.function.BiPredicate;
  
  public class Main {
      
      public static void main(String[] args) {
          boolean result = myCheckFunction((a, b) -> a%3 != 0 && b%5 != 0, 10, 9);
          System.out.println(result);
      }
      public static boolean myCheckFunction(BiPredicate<Integer, Integer> bi, Integer a, Integer b) {
          return bi.test(a, b);
      }
  }
  // 결과 : true
  ```

  * 위의 예제에서는 BiPredicate를 함수의 매개변수로 생각을 해 내가 조건을 확인할 때 조건식까지 같이 바로 넣어서 만들 수 있도록 만들어져 있다.



### 매개변수의 타입과 반환타입이 일치하는 함수형 인터페이스

![1536471070746](img/functionPackage3.png)



### 함수형 인터페이스를 사용하는 컬렉션 프레임웍의 메소드

![1536471117675](img/functionPackage4.png)

* 코드 예제

  ``` java
  // list의 모든 요소를 출력
  list.forEach(i -> System.out.print(i+", "));
  
  // 2 또는 3의 배수 제거
  list.removeIf(x -> x%2 == 0 || x%3 == 0);
  
  // 모든 요소에 10 곱한뒤 반환
  list.replaceAll(i -> i*10);
  
  // map의 모든 요소를 {k,v}의 형식으로 출력
  map.forEacH((k,v) -> System.out.println("{"+k+", "+v+"}"));
  ```


### 기본형을 사용하는 함수형 인터페이스

![1536473259481](img/functionPackage5.png)

![1536473300338](img/functionPackage5_1.png)





계속...



















------

참고

1. 자바의 정석 3rd ppt, 남궁성

