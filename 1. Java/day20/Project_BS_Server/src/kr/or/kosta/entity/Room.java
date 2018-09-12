package kr.or.kosta.entity;

public interface Room {
	public abstract void addClient(Client client) throws Exception;
	public abstract void removeClient(String nickName);
	public abstract String getClientsList();
}
