import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import imagereader.IImageProcessor;

/*
 * class ImplementImageProcessor is to deal with the RGB
 * about the bitMap
 */
public class ImplementImageProcessor implements IImageProcessor {
	/*
	 * implement my red filter
	 */
	class MyRedFilter extends RGBImageFilter {
		/*
		 * Constructor
		 */
		public MyRedFilter() {
			canFilterIndexColorModel = true;
		}
		
		/*
		 * rgb filter method
		 */
		public int filterRGB(int x, int y, int rgb) {
			return (rgb & 0xffff0000);
		}
	}
	
	/*
	 * implement my green filter
	 */
	class MyGreenFilter extends RGBImageFilter {
		/*
		 * Constructor
		 */
		public MyGreenFilter() {
			canFilterIndexColorModel = true;
		}
		
		/*
		 * rgb filter method
		 */
		public int filterRGB(int x, int y, int rgb) {
			return (rgb & 0xff00ff00);
		}
	}
	
	/*
	 * implement my blue filter
	 */
	class MyBlueFilter extends RGBImageFilter {
		/*
		 * Constructor
		 */
		public MyBlueFilter() {
			canFilterIndexColorModel = true;
		}
		
		/*
		 * rgb filter method
		 */
		public int filterRGB(int x, int y, int rgb) {
			return (rgb & 0xff0000ff);
		}
	}
	
	/*
	 * implement my gray filter
	 */
	class MyGrayFilter extends RGBImageFilter {
		/*
		 * Constructor
		 */
		public MyGrayFilter() {
			canFilterIndexColorModel = true;
		}
		
		/*
		 * rgb filter method
		 */
		public int filterRGB(int x, int y, int rgb) {
			int gray = (int)(((rgb & 0x00ff0000) >> 16) * 0.299 + 
					((rgb & 0x0000ff00) >> 8) * 0.587 + 
					(rgb & 0x000000ff) * 0.114);
			
			return (rgb & 0xff000000) + (gray << 16) + (gray << 8) + gray;
		}
	}
	
	/*
	 * override showChanelR
	 */
	public Image showChanelR(Image image) {
        MyRedFilter filter = new MyRedFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(image
                .getSource(), filter));
        return img;
    }
	
	/*
	 * override the showChanelG
	 */
	public Image showChanelG(Image image) {
        MyGreenFilter filter = new MyGreenFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(image
                .getSource(), filter));
        return img;
    }
	
	/*
	 * override the showChanelB
	 */
	public Image showChanelB(Image image) {
        MyBlueFilter filter = new MyBlueFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(image
                .getSource(), filter));
        return img;
    }
	
	/*
	 * override the showGray method
	 */
	public Image showGray(Image image) {

        MyGrayFilter filter = new MyGrayFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(image
                .getSource(), filter));
        return img;

    }
}