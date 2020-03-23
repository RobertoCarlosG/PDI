package clasesImagenes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class OperarImagen {
	
	static BufferedImage imgSave;
	static String op;
	
	public int[] factores(int n)
	{
		int divisor = 2;
		ArrayList<Integer> fact = new ArrayList<>();
		while(n != 1)
		{
			if(n % divisor == 0){
				fact.add(divisor);
				n = n / divisor;
			}else{
				divisor++;
			}
		}
		Integer[] aux 	= new Integer[fact.size()];
		aux 			= fact.toArray(aux);
		int f1 			= 1, f2 = 1;
		for(int i 		= 0; i < aux.length; i++)
		{
			if(i < aux.length /2){
				f1 = f1 * aux[i];
				System.out.print("\tCols:"+f1);
			}else{
				f2 = f2 * aux[i];
				System.out.print("\tCols:"+f2);
			}
		}

		int[] res = {f1,f2};
	return res;
	}
	
	public int[] bloques(int ancho, int alto, int cols, int rows){
		int auxAlto = 0, auxAncho =0;
		auxAlto		= (int) Math.floor(alto/rows);
		auxAncho	= (int) Math.floor(ancho/cols);
		int res[]	= {auxAncho,auxAlto};
		return res;
	}
	

	//metodo para regresar como icono la imagen seleccionada
	
	
	public static ImageIcon redimension(BufferedImage A){
		ImageIcon icono = new ImageIcon(A.getScaledInstance(190, 190, Image.SCALE_SMOOTH));
		return icono;
	}
	
	public static void obtenerImagenAGuardar(BufferedImage img, String operacion){
		imgSave 	= img;
		op 			= operacion;
	}
	
	public static void guardarImagen(){
		/* DE LIZ
		  if(op == "+"){
			try {
				ImageIO.write(imgSave, "png", new File("C:\\Users\\Liz Vazquez\\Documents\\Universidad\\6to semestre\\C�mputo distribuido\\ImagenesResultantes\\ImgResultSuma.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(op == "-"){
			try {
				ImageIO.write(imgSave, "png", new File("C:\\Users\\Liz Vazquez\\Documents\\Universidad\\6to semestre\\C�mputo distribuido\\ImagenesResultantes\\ImgResultResta.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else if(op == "*"){
			try {
				ImageIO.write(imgSave, "png", new File("C:\\Users\\Liz Vazquez\\Documents\\Universidad\\6to semestre\\C�mputo distribuido\\ImagenesResultantes\\ImgResultMult.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else if(op == "#"){
			try {
				ImageIO.write(imgSave, "png", new File("C:\\Users\\Liz Vazquez\\Documents\\Universidad\\6to semestre\\C�mputo distribuido\\ImagenesResultantes\\ImgResultOpLineal.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		*/
		if(op == "+"){
			try {
				ImageIO.write(imgSave, "png", new File("Resources\\ImgResultSuma.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(op == "-"){
			try {
				ImageIO.write(imgSave, "png", new File("Resources\\ImgResultResta.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else if(op == "*"){
			try {
				ImageIO.write(imgSave, "png", new File("Resources\\ImgResultMult.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else if(op == "#"){
			try {
				ImageIO.write(imgSave, "png", new File("Resources\\ImgResultOpLineal.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
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
	
	public static BufferedImage suma(BufferedImage A, BufferedImage B, int ancho, int alto, int cols, int rows)
	{
		//Buffered es una extension de la clase Image de java, seleccionamos el tipo de
		//datos que entraran para formar la imagen en este caso enteros correspondientes
		//al numero RGB del color seleccionado por pixel
		BufferedImage sum	= new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		sum					= redimensionar(sum);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		int ancho_bloque[]	= new int[cols];
		int alto_bloque[]	= new int[rows];
		
		//Obteniendo el numero de saltos que hara cada bloque
		for(int j = 0; j<cols;j++)
			ancho_bloque[j]	= (int) Math.floor(ancho/cols*j);				
		for(int j = 0; j<rows;j++)
			alto_bloque[j]	= (int) Math.floor(alto/rows*j);
		
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
				
				int comparaAncho = 0, comparaAlto = 0;
				while (comparaAncho<ancho_bloque.length){
					if (i==ancho_bloque[comparaAncho])
					{
						r = 0;
						g = 0;
						b = 0;
					}
					comparaAncho++;
				}
				while (comparaAlto<alto_bloque.length){
					if (j==alto_bloque[comparaAlto])
					{
						r = 0;
						g = 0;
						b = 0;
					}
					comparaAlto++;
				}
				/*
				if((i==ancho_bloque[0] || i==ancho_bloque[1] || i==ancho_bloque[2]  ||
				    i==ancho_bloque[3] || i==ancho_bloque[4] || i==ancho_bloque[5]) ||
				   (j==alto_bloque[0]  || j==alto_bloque[1]  || j==alto_bloque[2]   ||
				    j==alto_bloque[3]))
				{
					r = 0;
					g = 0;
					b = 0;
				}*/
				
				/*
				 * Set RGB va creando una imagen conforme enviamos los pixeles
				 * en las pocisiones (i,j)
				*/
				auxColor = new Color(r,g,b);
				sum.setRGB(i, j, auxColor.getRGB());
			}
		}
		return sum;
	}
	
	public static BufferedImage resta(BufferedImage A, BufferedImage B, int ancho, int alto, int cols, int rows)
	{
		BufferedImage res = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		res				  = redimensionar(res);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		int ancho_bloque[]	= new int[cols];
		int alto_bloque[]	= new int[rows];
		//Obteniendo el numero de saltos que hara cada bloque
		for(int j = 0; j<cols;j++)
			ancho_bloque[j]	= (int) Math.floor(ancho/cols*j);				
		for(int j = 0; j<rows;j++)
			alto_bloque[j]	= (int) Math.floor(alto/rows*j);
				
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
				
				
/*	A Q U I		S E		H A C E N 		L O S	 	B O R D E S*/
				
				int comparaAncho = 0, comparaAlto = 0;
				while (comparaAncho<ancho_bloque.length){
					if (i==ancho_bloque[comparaAncho])
					{
						r = 0;
						g = 0;
						b = 0;
					}
					comparaAncho++;
				}
				while (comparaAlto<alto_bloque.length){
					if (j==alto_bloque[comparaAlto])
					{
						r = 0;
						g = 0;
						b = 0;
					}
					comparaAlto++;
				}
				/*
				if((i==ancho_bloque[0] || i==ancho_bloque[1] || i==ancho_bloque[2]  ||
				    i==ancho_bloque[3] || i==ancho_bloque[4] || i==ancho_bloque[5]) ||
				   (j==alto_bloque[0]  || j==alto_bloque[1]  || j==alto_bloque[2]   ||
				    j==alto_bloque[3]))
				{
					r = 0;
					g = 0;
					b = 0;
				}*/
				
				/*
				 * Set RGB va creando una imagen conforme enviamos los pixeles
				 * en las pocisiones (i,j)
				*/
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
	
	public static BufferedImage multiplicacion(BufferedImage A, BufferedImage B,int ancho, int alto, int cols, int rows)
	{
		BufferedImage multi = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		multi  			    = redimensionar(multi);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		int k = 1 / 255;
		int ancho_bloque[]	= new int[cols];
		int alto_bloque[]	= new int[rows];
		//Obteniendo el numero de saltos que hara cada bloque
		for(int j = 0; j<cols;j++)
			ancho_bloque[j]	= (int) Math.floor(ancho/cols*j);				
		for(int j = 0; j<rows;j++)
			alto_bloque[j]	= (int) Math.floor(alto/rows*j);
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
					
					/*	A Q U I		S E		H A C E N 		L O S	 	B O R D E S*/
					
					int comparaAncho = 0, comparaAlto = 0;
					while (comparaAncho<ancho_bloque.length){
						if (i==ancho_bloque[comparaAncho])
						{
							r = 0;
							g = 0;
							b = 0;
						}
						comparaAncho++;
					}
					while (comparaAlto<alto_bloque.length){
						if (j==alto_bloque[comparaAlto])
						{
							r = 0;
							g = 0;
							b = 0;
						}
						comparaAlto++;
					}
					/*
					if((i==ancho_bloque[0] || i==ancho_bloque[1] || i==ancho_bloque[2]  ||
					    i==ancho_bloque[3] || i==ancho_bloque[4] || i==ancho_bloque[5]) ||
					   (j==alto_bloque[0]  || j==alto_bloque[1]  || j==alto_bloque[2]   ||
					    j==alto_bloque[3]))
					{
						r = 0;
						g = 0;
						b = 0;
					}*/
					
					/*
					 * Set RGB va creando una imagen conforme enviamos los pixeles
					 * en las pocisiones (i,j)
					*/
					
				auxColor = new Color(r,g,b);
				/*
				 * Set RGB va creando una imagen conforme enviamos los pixeles
				 * en las pocisiones (i,j)
				*/
				multi.setRGB(i, j, auxColor.getRGB());
	
			}
		}
	return multi;
	}
	
	public static BufferedImage combinacionLineal(BufferedImage A, BufferedImage B,
			int ancho, int alto, int initW, int initH, int alfa, int beta)
	{
		BufferedImage res = new BufferedImage(ancho - initW,alto - initH,BufferedImage.TYPE_INT_RGB);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		double a, be;
		a = (double)alfa / 100;
		be =(double) beta / 100;
		for(int i = initW; i < ancho; i++)
		{
			for(int j = initH; j < alto; j++)
			{
				rgb1 = new Color(A.getRGB(i,j));
				rgb2 = new Color(B.getRGB(i,j));
					r = (int)(a * rgb1.getRed() + be * rgb2.getRed());
					g = (int)(a * rgb1.getBlue() + be * rgb2.getBlue());
					b = (int)(a * rgb1.getGreen() + be * rgb2.getGreen());
				auxColor = new Color(r,g,b);
				res.setRGB(i - initW, j - initH, auxColor.getRGB());
			}
		}

	return res;
	}
	
	public static BufferedImage acopla(BufferedImage[] elemento)
	{
		int n = elemento.length;
		int[] factor = Parallel.factores(n);
		int auxWidth = elemento[0].getWidth() * factor[1];
		int auxHeight = elemento[0].getHeight() * factor[0];
		int inicioW = 0;
		int inicioH = 0;
		int tamW = elemento[0].getWidth();
		int tamH = elemento[0].getHeight();
		BufferedImage res = new BufferedImage(auxWidth,auxHeight,BufferedImage.TYPE_INT_RGB);

		Graphics g = res.getGraphics();
		for(int i = 0; i < n; i++)
		{
			g.drawImage(elemento[i], inicioW, inicioH, null);
			if((i+1) % factor[1] != 0){
				inicioW = inicioW + tamW;
			}else{
				inicioW = 0;
				inicioH = inicioH + tamH;
			}
		}
		try{
			ImageIO.write(res, "jpg", new File("Resources\\PARALELOOOOOOOOOOOO.jpg"));
		}catch (IOException e) {
			e.printStackTrace();
		}

	return res;
	}
	
}
