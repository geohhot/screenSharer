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

		imageUploader ui = new imageUploader();
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
				System.out.println("Imgur ID: "+id);
				System.out.println("Imgur link: http://imgur.com/"+id);
			}
			catch (ImgurException e) {
				// show notification about exception
				System.err.println("fatal: errors uploading to Imgur(ImgurException)");
			}
		}
		catch (ScreenShotException e) {
			// show notification about exception
			System.err.println("fatal: errors taking screen shot(ScreenShotException)");
		}
		catch (AWTException e) {
			// show notification about exception
			System.err.println("fatal: errors taking screen shot (AWTException)");

		}
		catch (IOException e) {
			// show notification about exception
			System.err.println("fatal: errors writing to file (IOException)");
		}

	}

}