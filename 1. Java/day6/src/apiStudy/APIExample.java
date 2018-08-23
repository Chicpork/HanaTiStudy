package apiStudy;
/**
 * 자바 표준 API의 기본 클래스 사용하기
 */
public class APIExample{
    public static void main(String[] args){
        // 명시적 선언
        String str;
        str = new String("자바가 싫어요...");

        // 묵시적 선언
        String str2 = "묵시적!";
        System.out.println(str2);
        int length = str.length();
        System.out.println(length);
        char c = str.charAt(0);
        System.out.println(c);

        int x = -20;
        System.out.println(Math.abs(x));
    }
}