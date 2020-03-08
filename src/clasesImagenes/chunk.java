package clasesImagenes;

public class chunk {
	
	int ccols;
	int chunkCounter;
	int cpos[][];
	int crows;
	int pixelescols = 175;
	int pixelesrows = 170;
	
	public chunk (){}
	
	public chunk(int col,int row, int pos[][]){
		ccols	=	col;
		crows	=	row;
		cpos	=	pos;
	}
	

	
	//Recibir coordenada o n_bloque
	/*
	 * aqui debemos calcular el bloque que corresponde a este
	 * numero, pero no parece que sea perteneciente a esta clase,
	 * debemos sacar cuantos pixeles de alto y cuantos de ancho
	 * para poder procesar una operacion OperarImagen
	 * 
	 */
	public chunk obtainChunk(int n_bloque){
		chunk xchunk = new chunk();
		
		return xchunk;
	}
	/**************************************************************************************/
	/**************************************************************************************/
	/**************************************************************************************/
	
	/**************************  MI FUNCION OBTAINCHUNK  **********************************/
	
	/**************************************************************************************/
	/**************************************************************************************/
	/**************************************************************************************/
	
	//Con (x,y) como coordenadas solo debo hacer que estas avancen el numero de pixeles
	//que tiene cada uno de los bloques, por ejemplo si estoy en el bloque 2 sabemos que el
	//bloque uno avanza hasta el pixel [1][175] y termina en el [170][175], por ende debemos sumar
	//uno con el numero dado, comprobamos si es 0 el primero, otro caso se suma uno y al final solo
	//comprobar si es el limite de la matriz
	public int[][] getSection(int board[][],int x, int y)
	{
		int result[][] = null; 
		//en caso de no ser el primer bloque no debemos sobrescribir la ultima linea
		//de los bloques, esa es la funcion de este if
		if(x!=0&&y!=0){

			x+=1;
			y+=1;
			int auxcol=x,auxrow=y;

			//iniciamos el for para avanzar de 1-1 por la matriz
			for (int i=0; i<x-1; i++) {
				//damos 1 a col cada que avancemos en las columnas
				auxcol++;
				for (int j=0; j<y-1; j++) {
					//sumamos otro a las filas
					auxrow++;
					//accedemos a la posicion de los auxiliares que en primer turno valen x
					//correspondiente a las coordenadas ingresadas
					result[i][j] = board[auxcol][auxrow];
				}
			}
		}else{
			int auxcol=x,auxrow=y;
			//iniciamos el for para avanzar de 1-1 por la matriz
			for (int i=0; i<x-1; i++) {
				//damos 1 a col cada que avancemos en las columnas
				auxcol++;
				for (int j=0; j<y-1; j++) {
					//sumamos otro a las filas
					auxrow++;
					//accedemos a la posicion de los auxiliares que en primer turno valen x
					//correspondiente a las coordenadas ingresadas
					result[i][j] = board[auxcol][auxrow];
				}
			}
		}
		return result;
	}
	

	/**************************************************************************************/
	/**************************************************************************************/
	/**************************************************************************************/
	/**************************************************************************************/
	/**************************************************************************************/
	/**************************************************************************************/
	/**************************************************************************************/
	
	public int get_ccols(){
		return ccols;
	}
	
	public int get_crows(){
		return crows;
	}
	
	public int[][] get_cpos(){
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
