package clasesImagenes;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ArrayList;

import clasesImagenes.OperarImagen;

public class Parallel extends Thread {

	OperarImagen auxImgOp;
	Lock lock;
	Random r;
	int idThread;
	chunk auxChunk;
	boolean bloqueAsignado;
	
	
	
	public Parallel(OperarImagen auxImgOp, Lock lock, int idThread) {
		r = new Random();
		this.lock = lock;
		this.auxImgOp = auxImgOp;
		this.idThread = idThread;
		this.bloqueAsignado = false;
	}
	
	private void nonCriticalRegion() {
		auxImgOp.processchunk();
		Util.mySleep( r.nextInt( 1000 ) );
	}
	
	private void criticalRegion() 
	{
		if(auxImgOp.getchunkCounter() > 0)
		{
			if(!bloqueAsignado)
			{
				if(auxImgOp.searchchunk())
					bloqueAsignado = true;
			}
			else
			{
				// Almacenar en un archivo la imagen
				auxImgOp.almacenarImg();
				// Mostrar imagen actualizada 
				auxImgOp.mostrarImagen();
				// Decrementa chunkConter
				auxImgOp.decrementarCounter();
				// Cambiar el estatus del proceso
				bloqueAsignado = false;
			}
		}
		Util.mySleep( r.nextInt( 1000 ) );
	}
	
	public void run() 
	{
		while (auxImgOp.getchunkCounter() > 0) 
		{
			lock.requestCR(idThread);
			criticalRegion();
			
			// System.out.println(idThread+" "+auxImgOp.getchunkCounter());
					
			lock.releaseCR(idThread);
			nonCriticalRegion();
		}
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