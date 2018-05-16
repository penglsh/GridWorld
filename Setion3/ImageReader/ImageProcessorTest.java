import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

/*
 * class ImagePrpcessorTest for testing the methods
 */
public class ImageProcessorTest {
	/*
	 * grobal variables for test
	 */
	private File srcFile1, srcFile2;
	private File red1, red2, green1, green2, blue1, blue2, gray1, gray2;
    private ImplementImageProcessor processor;
    private ImplementImageIO io;
    private Image img1, img2, proimg1, proimg2, goal1, goal2;
    
    /*
     * Before test, set some file
     */
    @Before
    public void setFile() throws Exception {
        srcFile1 = new File(
                "/home/penglsh6/Desktop/bmptest/1.bmp");
        srcFile2 = new File(
                "/home/penglsh6/Desktop/bmptest/2.bmp");
        
        red1 = new File(
                "/home/penglsh6/Desktop/bmptest/goal/1_red_goal.bmp");
        red2 = new File(
                "/home/penglsh6/Desktop/bmptest/goal/2_red_goal.bmp");
        green1 = new File(
                "/home/penglsh6/Desktop/bmptest/goal/1_green_goal.bmp");
        green2 = new File(
                "/home/penglsh6/Desktop/bmptest/goal/2_green_goal.bmp");
        blue1 = new File(
                "/home/penglsh6/Desktop/bmptest/goal/1_blue_goal.bmp");
        blue2 = new File(
                "/home/penglsh6/Desktop/bmptest/goal/2_blue_goal.bmp");
        
        gray1 = new File(
                "/home/penglsh6/Desktop/bmptest/goal/1_gray_goal.bmp");
        gray2 = new File(
                "/home/penglsh6/Desktop/bmptest/goal/2_gray_goal.bmp");

        // new a io and processor
        processor = new ImplementImageProcessor();
        io = new ImplementImageIO();
        img1 = io.myRead(srcFile1.toString());
    	img2 = io.myRead(srcFile2.toString());
    	
    	// change color by calling methods
    	proimg1 = processor.showChanelR(img1);
    	proimg2 = processor.showChanelR(img2);
    	
    }

    private void myCompare() {
        // compare goal1 with proimg1
        assertEquals(proimg1.getWidth(null), goal1.getWidth(null));
        assertEquals(proimg1.getHeight(null), goal1.getHeight(null));
        BufferedImage proBI = bufferImage(proimg1);
        BufferedImage goalBI = bufferImage(goal1);
        assertArrayEquals(proBI.getRGB(0, 0, proimg1.getWidth(null), proimg1.getHeight(null), null, 0, proimg1.getWidth(null)),
                goalBI.getRGB(0, 0, goal1.getWidth(null), goal1.getHeight(null), null, 0, goal1.getWidth(null)));
        
        // compare proimg2 with goal2
        assertEquals(proimg2.getWidth(null), goal2.getWidth(null));
        assertEquals(proimg2.getHeight(null), goal2.getHeight(null));
        BufferedImage proBI2 = bufferImage(proimg2);
        BufferedImage goalBI2 = bufferImage(goal2);
        assertArrayEquals(proBI2.getRGB(0, 0, proimg2.getWidth(null), proimg2.getHeight(null), null, 0, proimg2.getWidth(null)),
                goalBI2.getRGB(0, 0, goal2.getWidth(null), goal2.getHeight(null), null, 0, goal2.getWidth(null)));
    }
    
    /*
     * bufferImage method is to get the Image in the buffer
     */
    public static BufferedImage bufferImage(Image img) {
    	BufferedImage newBuff = new BufferedImage(img.getWidth(null),
    			                    img.getHeight(null), BufferedImage.TYPE_INT_RGB);
    	return newBuff;
    }
    
    @Test
    /*
     * Test for red
     */
    public void testForRed() throws IOException {

    	goal1 = ImageIO.read(red1);
    	goal2 = ImageIO.read(red2);
    	
    	myCompare();
    	
    }
    
    @Test
    /*
     * Test for Green
     */
    public void testForGreen() throws IOException {
    	
    	goal1 = ImageIO.read(green1);
    	goal2 = ImageIO.read(green2);
    	
    	myCompare();
    	
    }
    
    @Test
    /*
     * Test for Blue
     */
    public void testForBlue() throws IOException {

    	goal1 = ImageIO.read(blue1);
    	goal2 = ImageIO.read(blue2);
    	
    	// compare goal1 with proimg1
    	myCompare();
    	
    }
    
    @Test
    /*
     * Test for Gray
     */
    public void testForGray() throws IOException {

    	goal1 = ImageIO.read(gray1);
    	goal2 = ImageIO.read(gray2);
    	
    	myCompare();
    	
    }
}