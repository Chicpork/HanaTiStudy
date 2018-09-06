import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class GameFrame extends Frame implements Runnable {
	Image image;
	public int x = 10;

	public GameFrame(String title) {
		super(title);
		image = Toolkit.getDefaultToolkit().getImage("icon.png");
	}

	public void setContests() {

	}

	public void eventRegist() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				gameStart();
			}
		});
	}

	public void gameStart() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		System.out.println("시작");
		Random random = new Random();

		for (int i = x; i <= 800; i += 10) {
			x = i;
			try {
				Thread.sleep(random.nextInt(100));
			} catch (InterruptedException e) {
			}
			repaint();
		}

//		while (true) {
//			x += 5;
//			repaint();
//			try {
//				Thread.sleep(random.nextInt(500));
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	@Override
	public void paint(Graphics g) {
//		g.fillOval(x, 50, 100, 100);
		g.drawImage(image, x, 50, this);
	}

}
