package project_memo;

public class MemojangException extends Exception {
	
	public MemojangException(String message) {
		super(message);
	}
	@Override
	public String toString() {
		return getMessage();
	}

}
