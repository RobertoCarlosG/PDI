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


public class Imagen extends Thread {
/*
	int chunkCols;
	int chunkMatrix;
	int chunkRows;
	int img1[][];
	int img2[][];
	int imgR[][];
	
	//Aquí declaro anchos minimos permitidos
	public static final int anchoPermitido=800;
    public static final int altoPermitido=600;
    //Matrices equivalente a la imagen 
    public Color arreglo[][];
    public Color auxarreglo[][];
    //ancho y alto estandar
    public int ancho;
    public int alto;
    
    public Imagen(String archivo) {
        arreglo = new Color[anchoPermitido][anchoPermitido];
        cargarImagen(archivo);
    }
    
    public void cargarImagen(String archivo){
        BufferedImage bf=null;
        try {
            bf = ImageIO.read(new File(archivo));
        } catch (IOException ex) {
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bf.getWidth()<anchoPermitido) {
            ancho=bf.getWidth();
        }else
            ancho=anchoPermitido;
        if (bf.getHeight()<altoPermitido) {
            alto=bf.getHeight();
        }else
            alto=altoPermitido;
        //int cont=0;
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                //cont++;
                arreglo[i][j]		= new Color(bf.getRGB(j, i));
                //auxarreglo[i][j]	= arreglo[i][j];
                //System.out.println(cont +":"+"RedGreenBlue:"+ bf.getRGB(j, i));
            }
        }

    }
    
    public BufferedImage redimensionar(String archivo, double porcentaje ){

        BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File(archivo));
        } catch (IOException ex) {
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        int ancho = bf.getWidth();
        int alto = bf.getHeight();
        int escalaAncho = (int)(porcentaje* ancho);
        int escalaAlto = (int)(porcentaje*alto);
        BufferedImage bufim = new BufferedImage(escalaAncho, escalaAlto, bf.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bf, 0,0, escalaAncho,escalaAlto, 0,0,ancho,alto, null);
        g.dispose();
        return bufim;
    }
*/
	String simbolo;
	int InicioW;
	int FinW;
	int InicioH;
	int FinH;
	BufferedImage imgA;
	BufferedImage imgB;
	BufferedImage salida;
	
	public Imagen(String sim, int initW, int endW, int initH, int endH ,BufferedImage A, BufferedImage B)
	{
		this.simbolo = sim;
		this.InicioW = initW;
		this.FinW = endW;
		this.InicioH = initH;
		this.FinH = endH;
		this.imgA = A;
		this.imgB = B;
	}
	
    
    
    public BufferedImage getResult()
	{
		return salida;
	} 
	
	public void run()
	{
		switch(simbolo){
		case " + ":
			salida = OperarImagen.suma(imgA, imgB, FinW, FinH, InicioW, InicioH);
			break;
		case " - ":
			salida = OperarImagen.resta(imgA, imgB, FinW, FinH, InicioW, InicioH);
			break;
		case " * ":
			salida = OperarImagen.multiplicacion(imgA, imgB, FinW, FinH, InicioW, InicioH);
			break;
		case "\u03B1 - \u03B2":
			salida = OperarImagen.combinacionLineal(imgA, imgB, FinW, FinH, InicioW, InicioH,5,5);
			break;
			
		}
		System.out.println(this.getName()+"Ejecutando");
		
	}


	
	public int pixel2matrix(int alto,int ancho){
		BufferedImage res = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		//Corregir el return
		return 0;
	}
	
}
