package day7;

public interface Ainterface {
	public void a();
}

interface Binterface{
	public void b();
}

interface Cinterface extends Ainterface,Binterface{
	public void c();
}
