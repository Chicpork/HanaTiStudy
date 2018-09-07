import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionExample {

	public static void main(String[] args) {
		String str ="리플렉션이 뭔가요?..";
		
		Class cls = str.getClass();
		System.out.println(cls.getModifiers());
		System.out.println(Modifier.PUBLIC);
		System.out.println(cls.getName());
		System.out.println(cls.getSimpleName());
		System.out.println(cls.getSuperclass().getName());
		Field[] fs = cls.getFields();
		for (Field field : fs) {
			System.out.println(field.getName());
		}
		Method[] ms = cls.getMethods();
		for (Method method : ms) {
			System.out.println(method);
		}
		
	}

}
