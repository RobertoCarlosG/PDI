package clasesImagenes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Imagen {

	int chunkCols;
	int chunkMatrix;
	int chunkRows;
	int img1[][];
	int img2[][];
	int imgR[][];
	
	public int pixel2matrix(int alto,int ancho){
		BufferedImage res = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		//Corregir el return
		return 0;
	}
	
}
