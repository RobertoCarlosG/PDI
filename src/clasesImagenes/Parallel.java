package clasesImagenes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import clasesImagenes.OperarImagen;

public class Parallel {

	
	
	public static BufferedImage parallelOperate(String simb,BufferedImage imgA, BufferedImage imgB, int NumThreads) throws InterruptedException
	{	
		int[] factor = factores(NumThreads);
		int tamW = imgA.getWidth() / factor[1];
		int inicioW  = 0;
		int finW = tamW;
		int tamH = imgA.getHeight() / factor[0];
		int inicioH = 0;
		int finH = tamH;
		
		BufferedImage[] res = new BufferedImage[NumThreads]; 
		
		
		Imagen[] hilos = new Imagen[NumThreads];
		for(int i = 0; i < NumThreads; i++)
		{
			
			Imagen hilo = new Imagen(simb, inicioW, finW, inicioH, finH, imgA, imgB);
			hilos[i] = hilo;
			if((i+1) % factor[1] != 0){
				inicioW = inicioW + tamW;
				finW = finW + tamW;
			}else{
				inicioW = 0;
				finW = tamW;
				inicioH = inicioH + tamH;
				finH = finH + tamH;
			}
		}
		
		try {
			for (int i = 0; i < NumThreads; i++)
			{
				hilos[i].start();
				hilos[i].join();
				res[i] = hilos[i].getResult();
			}
			return (OperarImagen.acopla(res));
		} catch (Exception e) {};

	return null;
	}
	
	public static int[] factores(int n)
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
		Integer[] aux = new Integer[fact.size()];
		aux = fact.toArray(aux);
		int f1 = 1, f2 = 1;
		for(int i = 0; i < aux.length; i++)
		{
			if(i < aux.length /2){
				f1 = f1 * aux[i];
			}else{
				f2 = f2 * aux[i];
			}
		}

		int[] res = {f1,f2};
	return res;
	}


}