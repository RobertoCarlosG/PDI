package clasesImagenes;

public class chunk {
	
	int ccols;
	int chunkCounter;
	int cpos[][];
	int crows;
	
	public chunk(int col,int row, int pos){
		ccols	=	col;
		crows	=	row;
		cpos	=	pos;
	}
	
	//Recibir coordenada o n_bloque
	/*
	 * aqui debemos calcular el bloque que corresponde a este
	 * numero
	public Chunk obtainChunk(int n_bloque){
		
	}
	*/
	
	public int get_ccols(){
		return ccols;
	}
	
	public int get_crows(){
		return crows;
	}
	
	public int get_cpos(){
		return cpos;
	}
	
	public void set_ccols(int col){
		ccols	= col;
	}
	
	public void set_crows(int row){
		crows	= row;
	}
	
	public void set_cpos(int pos){
		ccols	= pos;
	}
	
}
