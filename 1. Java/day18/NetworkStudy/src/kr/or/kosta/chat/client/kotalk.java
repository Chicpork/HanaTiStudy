package kr.or.kosta.chat.client;

public class kotalk {

	public static void main(String[] args) {
		ChatClient chatClient = new ChatClient();
		ChatUI chatUI = new ChatUI("kotalk");
		chatClient.setChatUI(chatUI);
		chatUI.setChatClient(chatClient);
		
		chatUI.setContents();
		chatUI.setSize(400, 500);
		chatUI.setCenter();
		chatUI.eventRegist();
		chatUI.setVisible(true);
	}

}
