package clasesImagenes;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;

public class OperarImagen {
	
	public static ImageIcon redimension(BufferedImage A){
		ImageIcon icono = new ImageIcon(A.getScaledInstance(190, 190, Image.SCALE_SMOOTH));
		return icono;
	}
	public static BufferedImage suma(BufferedImage A, BufferedImage B, int ancho, int alto)
	{
		BufferedImage res = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		for(int i = 0; i < ancho; i++)
		{
			for(int j = 0; j < alto; j++)
			{
				rgb1 = new Color(A.getRGB(i,j));
				rgb2 = new Color(B.getRGB(i,j));
				if(rgb1.getRed() + rgb2.getRed() <= 255){
					r = rgb1.getRed() + rgb2.getRed();
				}else{
					r = 255;
				}
				if(rgb1.getBlue() + rgb2.getBlue() <= 255){
					g = rgb1.getBlue() + rgb2.getBlue();
				}else{
					g = 255;
				}
				if(rgb1.getGreen() + rgb2.getGreen() <= 255){
					b = rgb1.getGreen() + rgb2.getGreen();
				}else{
					b = 255;
				}
				auxColor = new Color(r,g,b);
				res.setRGB(i, j, auxColor.getRGB());
			}
		}
	return res;
	}
	
	public static BufferedImage resta(BufferedImage A, BufferedImage B, int ancho, int alto)
	{
		BufferedImage res = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		for(int i = 0; i < ancho; i++)
		{
			for(int j = 0; j < alto; j++)
			{
				rgb1 = new Color(A.getRGB(i,j));
				rgb2 = new Color(B.getRGB(i,j));
					r = Math.abs(rgb1.getRed() - rgb2.getRed());
					g = Math.abs(rgb1.getBlue() - rgb2.getBlue());
					b = Math.abs(rgb1.getGreen() - rgb2.getGreen());
				auxColor = new Color(r,g,b);
				res.setRGB(i, j, auxColor.getRGB());
	
			}
		}
	return res;
	}
	
	public static BufferedImage multiplicacion(BufferedImage A, BufferedImage B,int ancho, int alto)
	{
		BufferedImage res = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		int k = 1 / 255;
		for(int i = 0; i < ancho; i++)
		{
			for(int j = 0; j < alto; j++)
			{
				rgb1 = new Color(A.getRGB(i,j));
				rgb2 = new Color(B.getRGB(i,j));
					r =  (rgb1.getRed() * rgb2.getRed())/255;
					g =  (rgb1.getBlue() * rgb2.getBlue())/255;
					b =  (rgb1.getGreen() * rgb2.getGreen())/255;
				auxColor = new Color(r,g,b);
				res.setRGB(i, j, auxColor.getRGB());
	
			}
		}
	return res;
	}
	
	public static BufferedImage combinacion_lineal(BufferedImage A, BufferedImage B,int ancho, int alto)
	{
		BufferedImage res = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		for(int i = 0; i < ancho; i++)
		{
			for(int j = 0; j < alto; j++)
			{
				rgb1 = new Color(A.getRGB(i,j));
				rgb2 = new Color(B.getRGB(i,j));
				if(rgb1.getRed() + rgb2.getRed() <= 255){
					r = (int) Math.floor(rgb1.getRed()*0.4 + rgb2.getRed()*0.6);
				}else{
					r = 255;
				}
				if(rgb1.getBlue() + rgb2.getBlue() <= 255){
					g = (int) Math.floor(rgb1.getBlue()*0.4 + rgb2.getBlue()*0.6);
				}else{
					g = 255;
				}
				if(rgb1.getGreen() + rgb2.getGreen() <= 255){
					b = (int) Math.floor(rgb1.getGreen()*0.4 + rgb2.getGreen()*0.6);
				}else{
					b = 255;
				}
				auxColor = new Color(r,g,b);
				res.setRGB(i, j, auxColor.getRGB());
			}
		}
	return res;

	}
}
