package clasesImagenes;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics2D;

public class OperarImagen {
	
	
	//metodo para regresar como icono la imagen seleccionada
	public static ImageIcon redimension(BufferedImage A){
		ImageIcon icono = new ImageIcon(A.getScaledInstance(190, 190, Image.SCALE_SMOOTH));
		return icono;
	}
	
	public static BufferedImage redimensionar(BufferedImage A){
		 BufferedImage bf = A;
	       
	        int ancho = bf.getWidth();
	        int alto = bf.getHeight();
	        int escalaAncho = (int)(1* ancho);
	        int escalaAlto = (int)(1*alto);
	        BufferedImage bufim = new BufferedImage(escalaAncho, escalaAlto, bf.getType());
	        Graphics2D g = bufim.createGraphics();
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g.drawImage(bf, 0,0, escalaAncho,escalaAlto, 0,0,ancho,alto, null);
	        g.dispose();
	        return bufim;
	}
	
	public static BufferedImage suma(BufferedImage A, BufferedImage B, int ancho, int alto, int rows, int cols)
	{
		//Buffered es una extension de la clase Image de java, seleccionamos el tipo de
		//datos que entraran para formar la imagen en este caso enteros correspondientes
		//al numero RGB del color seleccionado por pixel
		BufferedImage res	= new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		res					= redimensionar(res);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		int ancho_bloque[]	= new int[6];
		int alto_bloque[]	= new int[4];
		
		//Obteniendo el numero de saltos que hara cada bloque
		for(int j = 0; j<6;j++)
			ancho_bloque[j]	= (int) Math.floor(133*j);				
		for(int j = 0; j<4;j++)
			alto_bloque[j]	= (int) Math.floor(150*j);
		
		for(int i = 0; i < ancho; i++)
		{
			for(int j = 0; j < alto; j++)
			{
				/*Declaramos variables de color asignandole el pixel inicial y asi
				Recorrer toda la matriz de la imagen
				*/
				rgb1 = new Color(A.getRGB(i,j));
				rgb2 = new Color(B.getRGB(i,j));
				/*Comprobacion en caso de ser  255
				dentro de los if sumamos el valor de cada color 
				*/
								
					
		/*		AQUI ES LA FUNCION ORIGINAL
		 * 		if(rgb1.getRed() + rgb2.getRed() <= 255){
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
		*/
				
				/*********	FUNCION PARA MOSTRAR LOS BLOQUES		*******/
				/*********	FUNCION PARA MOSTRAR LOS BLOQUES		*******/
				/*********	FUNCION PARA MOSTRAR LOS BLOQUES		*******/
				
				
				
				if(rgb1.getRed() + rgb2.getRed() <= 255 ){
					r = rgb1.getRed() + rgb2.getRed();
				}else{
					r = 255;
				}
				if(rgb1.getBlue() + rgb2.getBlue() <= 255 ){
					g = rgb1.getBlue() + rgb2.getBlue();
				}else{
					g = 255;
				}
				if(rgb1.getGreen() + rgb2.getGreen() <= 255 ){
					b = rgb1.getGreen() + rgb2.getGreen();
				}else{
					b = 255;
				}
				
				/*	A Q U I		S E		H A C E N 		L O S	 	B O R D E S*/
				
				if((i==ancho_bloque[0] || i==ancho_bloque[1] || i==ancho_bloque[2]  ||
				    i==ancho_bloque[3] || i==ancho_bloque[4] || i==ancho_bloque[5]) || 
				   (j==alto_bloque[0]  || j==alto_bloque[1]  || j==alto_bloque[2]   || 
				    j==alto_bloque[3])){
					r = 0;
					g = 0;
					g = 0;
				}
				
				/*
				 * Set RGB va creando una imagen conforme enviamos los pixeles
				 * en las pocisiones (i,j)
				*/
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
				//a diferencia de la suma aqui obtendremos el valor absoluto de la resta
				//donde declaramos todo de la misma manera
				rgb1 = new Color(A.getRGB(i,j));
				rgb2 = new Color(B.getRGB(i,j));
					r = Math.abs(rgb1.getRed() - rgb2.getRed());
					g = Math.abs(rgb1.getBlue() - rgb2.getBlue());
					b = Math.abs(rgb1.getGreen() - rgb2.getGreen());
				auxColor = new Color(r,g,b);
				/*
				 * Set RGB va creando una imagen conforme enviamos los pixeles
				 * en las pocisiones (i,j)
				*/
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
				/*
				 * siguinendo las indicaciones del profesor para la multiplicacion
				 * asi se declara la formula
				*/
				rgb1 = new Color(A.getRGB(i,j));
				rgb2 = new Color(B.getRGB(i,j));
					r =  (rgb1.getRed() * rgb2.getRed())/255;
					g =  (rgb1.getBlue() * rgb2.getBlue())/255;
					b =  (rgb1.getGreen() * rgb2.getGreen())/255;
				auxColor = new Color(r,g,b);
				/*
				 * Set RGB va creando una imagen conforme enviamos los pixeles
				 * en las pocisiones (i,j)
				*/
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
				/*
				 * siguinendo las indicaciones del profesor para la Combinacion lineal
				 * asi se declara la formula
				*/
				rgb1 = new Color(A.getRGB(i,j));
				rgb2 = new Color(B.getRGB(i,j));
				if(rgb1.getRed() + rgb2.getRed() <= 255){
					/*
					 * uso la funcion de piso para obtener el numero menor de la 
					 * multiplicacion y lo casteo a numero entero
					 * 
					
					*/
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
				/*
				 * Set RGB va creando una imagen conforme enviamos los pixeles
				 * en las pocisiones (i,j)
				*/
				res.setRGB(i, j, auxColor.getRGB());
			}
		}
	return res;

	}
}
