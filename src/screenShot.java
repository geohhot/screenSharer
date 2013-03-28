import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

class screenShot {

	public static void captureScreen(String fileName) throws Exception {
	 
	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   Rectangle screenRectangle = new Rectangle(screenSize);
	   Robot robot = new Robot();
	   BufferedImage image = robot.createScreenCapture(screenRectangle);
	   ImageIO.write(image, "png", new File(fileName));
	 
	}

	public static void main(String[] args) {

		try {
			captureScreen("tempScreen.png");
			System.out.println("ok");
			System.out.flush();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("error");
			System.out.flush();
		}
	}

}