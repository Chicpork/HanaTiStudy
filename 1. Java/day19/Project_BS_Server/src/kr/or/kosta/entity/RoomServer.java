package kr.or.kosta.entity;

public interface RoomServer {
	public abstract void addClient(Client client);
	public abstract void removeClient(String nickName);
	public abstract String getClientsList();
	public abstract String getRoomName();
}
