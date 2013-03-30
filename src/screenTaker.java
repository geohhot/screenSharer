import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.AWTException;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Date;

class screenTaker {

	public static void takeScreenShot(String fileName) throws ScreenShotException,AWTException,IOException {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		ImageIO.write(image, "png", new File(fileName));

	}

	public static String uploadToImgur(String fileName, boolean link) throws ImgurException {

		uploadImage ui = new uploadImage();
		String id = ui.uploadImage(fileName, link);
		if (id.equals("-1")) {
			// error
			throw new ImgurException();
		}
		else {
			// no any errors
			return id;
		}

	}

	public static void main (String[] args) {

		try {
			String fileName = "tempScreen.png";
			takeScreenShot(fileName);
			try {
				String id = uploadToImgur(fileName, false);
				System.out.println("ID: "+id);
			}
			catch (ImgurException e) {
				// show notification about exception
			}
		}
		catch (ScreenShotException e) {
			// show notification about exception
		}
		catch (AWTException e) {
			// show notification about exception
		}
		catch (IOException e) {
			// show notification about exception
		}

	}

}