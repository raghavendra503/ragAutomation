import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.RandomAccessFile;

import javax.imageio.ImageIO;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.tiff.TiffDecoder;

public class Test3 {

public static void main(String args[]) throws Exception {
	File file=new File("C:\\GeethaUpdated\\Grace\\CSRDest\\Apr\\2002Apr.tif");
	
     try {
         SeekableStream s = new FileSeekableStream(file);
         TIFFDecodeParam param = null;
         ImageDecoder dec = ImageCodec.createImageDecoder("tiff", s, param);
         RenderedImage op = new NullOpImage(dec.decodeAsRenderedImage(0),
                                            null,
                                            OpImage.OP_IO_BOUND,
                                            null);
         FileOutputStream fos = new FileOutputStream("output.jpg");
         JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(fos);
         jpeg.encode(op.getData());
         fos.close();
     }
     catch (java.io.IOException ioe) {
         System.out.println(ioe);
     } 
 }
}
	
	
	//BufferedImage image = JDeli.read(file);
//	BufferedImage image1=ImageIO.read(file);
//	ImageIO.write(image1, "TIFF", outputTIFF);
//
//	final short[] pixels = ((DataBufferUShort) image1.getRaster().getDataBuffer()).getData();
//
//    int[][] data = new int[2048][2048];
//
//    int col = 0;
//    int row = 0;
//    int blockSize = 2048;
//    for (int i=0; i<pixels.length; i++) {
//        data[col][row] = pixels[i];
//        row++;
//        if (row == blockSize) {
//            col++;
//            row = 0;
//        }
//    }
//
//    return data;
//}   
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	Raster r= image1.getData();
//	System.out.println(r.toString());
//	//image1.getProperty("Size");
//
//String[] list=	image1.getPropertyNames();
//
//for(int i=0;i<list.length;i++) {
//	System.out.println(list[i]);
//}
//	//RandomAccessFile raf = new RandomAccessFile("C:\\GeethaUpdated\\Grace\\CSRDest\\Apr\\2002Apr.tif", "r");
//	//TiffDecoder decoder = new TiffDecoder(raf);
//
//}
}
