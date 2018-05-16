import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import imagereader.IImageIO;


/*
 * class ImplementImageIO implements IImageIO to read or
 * write with the image
 */
public class ImplementImageIO implements IImageIO {
	
	/* 
	 * ImageHeader class to store the info below:
	 * #0-1	保存位图文件的标识符，这两个字节的典型数据是BM
     * #2-5	使用一个dword保存位图文件大小
     * #6-9	是保留部分，留做以后的扩展使用,对实际的解码格式没有影响
     * #10-13 保存位图数据位置的地址偏移，也就是起始地址
	 */

	public class ImageHeader {
		/*
		 * #0-1	保存位图文件的标识符，这两个字节的典型数据是BM
         * #2-5	使用一个dword保存位图文件大小
         * #6-9	是保留部分，留做以后的扩展使用,对实际的解码格式没有影响
         * #10-13 保存位图数据位置的地址偏移，也就是起始地址
		 */
		private int bfType;    // the type of the file, is bm
		private int bfSize;
		private int bfReserve1;    // reserve word, = 0
		private int bfStart;    // the position of the bitmap

		public int getbfType() {
			return bfType;
		}
		public int getbfSize() {
			return bfSize;
		}
		public int getbfReserve1() {
			return bfReserve1;
		}
		
		public int geybfStart() {
			return bfStart;
		}		
		/*
		 * Construct with a FileInputStream
		 */
		public ImageHeader(FileInputStream file) {
			int tmpsize = 14;
			byte fileHeader[] = new byte[tmpsize];
			// do with a try catch for exception
			try {
				bfType = 16973;    // ox424d, md
				file.read(fileHeader, 0, tmpsize);
				
				// transform 8 bytes into 32 bits
				bfSize = (((int)fileHeader[5] & 0xff) << 24)
						| (((int)fileHeader[4] & 0xff) << 16)
						| (((int)fileHeader[3] & 0xff) << 8)
						| ((int)fileHeader[2] & 0xff);
				bfReserve1 = 0;
				
				
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * detail info for bitmap
	 * InfoHeader class
	 */
	public class InfoHeader {
		/*
		 * #14-17	定义以下用来描述影像的区块（BitmapInfoHeader）的大小。它的值是：40 - Windows 3.2、95、NT、12 - OS/2 1.x、240 - OS/2 2.x
         * #18-21	保存位图宽度（以像素个数表示）
         * #22-25	保存位图高度（以像素个数表示）
         * #26-27	保存所用彩色位面的个数。不经常使用
         * #28-29	保存每个像素的位数，它是图像的颜色深度。常用值是1、4、8（灰阶）和24（彩色）
         * #30-33	定义所用的压缩算法。允许的值是0、1、2、3、4、5，见下表
         * #34-37	保存图像大小。这是原始 （:en:raw）位图数据的大小，不要与文件大小混淆。
         * #38-41	保存图像水平方向分辨率
         * #42-45	保存图像竖值方向分辨率
         * #46-49	保存所用颜色数目
         * #50-53	保存所用重要颜色数目。当每个颜色都重要时这个值与颜色数目相等
		 */
		private int bitSize;    // the size of the infoheader
		private int bitWidth;    // the width of the bitmap, pixel
		private int bitHeight;    // the height of the bitmap, pixel
		private int bitCount;    // the bit every pixel
		private int bitMapSize;    // the size of the bitMap
        
        private int colorMedol[];
        
        public int[] getColorModel() {
        	return colorMedol;
        }

        public int getbitSize() {
        	return bitSize;
        }

        public int getbitWidth() {
        	return bitWidth;
        }

        public int getbitHeight() {
        	return bitHeight;
        }
        
        public int getbitCount() {
        	return bitCount;
        }
      
        /*
         * Constructs with a FileInputStream
         */
        public InfoHeader(FileInputStream file) {
        	int bitsize = 40;
        	byte infoHeader[] = new byte[bitsize];

        	try {
                
                file.read(infoHeader, 0, bitsize);


				bitWidth = (((int)infoHeader[7] & 0xff) << 24)
						| (((int)infoHeader[6] & 0xff) << 16)
						| (((int)infoHeader[5] & 0xff) << 8)
						| ((int)infoHeader[4] & 0xff);

				bitHeight = (((int)infoHeader[11] & 0xff) << 24)
						| (((int)infoHeader[10] & 0xff) << 16)
						| (((int)infoHeader[9] & 0xff) << 8)
						| ((int)infoHeader[8] & 0xff);


				bitCount = (((int)infoHeader[15] & 0xff) << 8)
						    | ((int)infoHeader[14] & 0xff);

                bitMapSize = (((int)infoHeader[23] & 0xff) << 24)
						| (((int)infoHeader[22] & 0xff) << 16)
						| (((int)infoHeader[21] & 0xff) << 8)
						| ((int)infoHeader[20] & 0xff);



                // must be 24 
				if (bitCount == 24) {
					// get the extended size
					int extendedSize = (bitMapSize / bitHeight) - bitWidth * 3;
					if (extendedSize == 4) {
						extendedSize = 0;
					}

					colorMedol = new int[bitHeight * bitWidth];

					byte bitMapArray[] = new byte[bitMapSize];
					file.read(bitMapArray, 0, bitMapSize);

					int index = 0;
					for (int i = 0; i < bitHeight; i ++) {
						for (int j = 0; j < bitWidth; j ++) {
							colorMedol[bitWidth * (bitHeight - i - 1) + j] = ((255 & 0xff) << 24)
							                                           | (((int)bitMapArray[index + 2] & 0xff) << 16)
							                                           | (((int)bitMapArray[index + 1] & 0xff) << 8)
							                                           | ((int)bitMapArray[index] & 0xff);

			                index += 3;

						}
						index += extendedSize;
					}
				}
        	}
        	catch (IOException e) {
                e.printStackTrace();
        	}
        }

        /*
         * getImage method to get the 
         */
        public Image getImage() {
        	Image img = Toolkit.getDefaultToolkit().createImage(
        		new MemoryImageSource(bitWidth, bitHeight, colorMedol, 0, bitWidth));
        
            return img;
        }
	}

	/*
	 * implement the method myRead
	 */
	public Image myRead(String filePath) {
		Image img = null;
		try {
			FileInputStream file = new FileInputStream(filePath);
            ImageHeader fileHeader = new ImageHeader(file);

            InfoHeader infoHeader = new InfoHeader(file);

            img = infoHeader.getImage();

            file.close();

            return img;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * write file
	 */
	public Image myWrite(Image img, String filePath) {
		try {
			File file = new File(filePath + ".bmp");
            
            BufferedImage buffImg = new BufferedImage(img.getWidth(null),
            	                        img.getHeight(null), BufferedImage.TYPE_INT_RGB);

            Graphics grap = buffImg.getGraphics();
            grap.drawImage(img, 0, 0, null);
            grap.dispose();
            ImageIO.write(buffImg, "bmp", file);
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}

		return img;
	}
}