package clasesImagenes;

public class chunk {
	
	private int[] cpos;
	private int crows;
	private int ccols;

	private int pInicioX;
	private int pFinX;
	private int pInicioY;
	private int pFinY;

	public chunk(int x, int y,  int rows, int cols,
				 int pInicioX, int pFinX, int pInicioY, int pFinY)
	{
		setCpos(x, y);
		this.crows = rows;
		this.ccols = cols;
		this.pInicioX = pInicioX;
		this.pFinX = pFinX;
		this.pInicioY = pInicioY;
		this.pFinY = pFinY;
	}

	public int[] getCpos() { return cpos; }

	public void setCpos(int posx, int posy)
	{
		this.cpos = new int[2];
		cpos[0] = posx;
		cpos[1] = posy;
	}

	public int getCrows() {
	    return crows;
    }

	public int getCcols() {
	    return ccols;
	}

	public int getpInicioX() {
	    return pInicioX;
    }

	public int getpFinX() {
	    return pFinX;
	}

	public int getpInicioY() {
	    return pInicioY;
    }

	public int getpFinY() {
	    return pFinY;
	}

	public int[][] suma(int[][] img1, int[][] img2)
	{
		int[][] resultado = new int[ccols][crows];

		int c = 0;
		for (int y = pInicioY; y < pFinY; y++)
        {
			int d = 0;
            for (int x = pInicioX; x < pFinX; x++)
            {
            	int p1 = img1[x][y];
                int r1 = (p1>>16) & 0xff;
                int g1 = (p1>>8) & 0xff;
                int b1 = p1 & 0xff;

                int p2 = img2[x][y];
                int r2 = (p2>>16) & 0xff;
                int g2 = (p2>>8) & 0xff;
                int b2 = p2 & 0xff;

                int p, a = 255, r = 255, g = 255, b = 255;
                if((r1 + r2) <= 255) r = r1 + r2;
                if((g1 + g2) <= 255) g = g1 + g2;
                if((b1 + b2) <= 255) b = b1 + b2;

                p = (a<<24) | (r<<16) | (g<<8) | b;

                resultado[d][c] = p;
                d++;
            }
            c++;
        }

		return resultado;
	}

	public int[][] resta(int[][] img1, int[][] img2)
	{
		int[][] resultado = new int[ccols][crows];

		int c = 0;
		for (int y = pInicioY; y < pFinY; y++)
        {
			int d = 0;
            for (int x = pInicioX; x < pFinX; x++)
            {
            	int p1 = img1[x][y];
                int r1 = (p1>>16) & 0xff;
                int g1 = (p1>>8) & 0xff;
                int b1 = p1 & 0xff;

                int p2 = img2[x][y];
                int r2 = (p2>>16) & 0xff;
                int g2 = (p2>>8) & 0xff;
                int b2 = p2 & 0xff;

                int p, a = 255, r = 0, g = 0, b = 0;
                if((r1 - r2) >= 0) r = r1 - r2;
                if((g1 - g2) >= 0) g = g1 - g2;
                if((b1 - b2) >= 0) b = b1 - b2;

                p = (a<<24) | (r<<16) | (g<<8) | b;

                resultado[d][c] = p;
                d++;
            }
            c++;
        }

		return resultado;
	}

	public int[][] combinacionLineal(int[][] img1, int[][] img2, double peso)
	{
		double peso2 = 1 - peso;
		int[][] resultado = new int[ccols][crows];

		int c = 0;
		for (int y = pInicioY; y < pFinY; y++)
        {
			int d = 0;
            for (int x = pInicioX; x < pFinX; x++)
            {
            	int p1 = img1[x][y];
                int r1 = (p1>>16) & 0xff;
                int g1 = (p1>>8) & 0xff;
                int b1 = p1 & 0xff;

                int p2 = img2[x][y];
                int r2 = (p2>>16) & 0xff;
                int g2 = (p2>>8) & 0xff;
                int b2 = p2 & 0xff;

                int p, a = 255, r = 255, g = 255, b = 255;

                if((int) ((r1 * peso) + (r2 * peso2)) <= 255)
                	r = (int) ((r1 * peso) + (r2 * peso2));

                if((int) ((g1 * peso) + (g2 * peso2)) <= 255)
                	g = (int) ((g1 * peso) + (g2 * peso2));

                if((int) ((b1 * peso) + (b2 * peso2)) <= 255)
                	b = (int) ((b1 * peso) + (b2 * peso2));

                p = (a<<24) | (r<<16) | (g<<8) | b;

                resultado[d][c] = p;
                d++;
            }
            c++;
        }

		return resultado;
	}

	public int[][] multiplicacion(int[][] img1, int[][] img2)
	{
		double k  = 1.0 / 255.0;
		int[][] resultado = new int[ccols][crows];

		int c = 0;
		for (int y = pInicioY; y < pFinY; y++)
        {
			int d = 0;
            for (int x = pInicioX; x < pFinX; x++)
            {
            	int p1 = img1[x][y];
                int r1 = (p1>>16) & 0xff;
                int g1 = (p1>>8) & 0xff;
                int b1 = p1 & 0xff;

                int p2 = img2[x][y];
                int r2 = (p2>>16) & 0xff;
                int g2 = (p2>>8) & 0xff;
                int b2 = p2 & 0xff;

                int p, a = 255, r = 0, g = 0, b = 0;
                r = (int) (r1 * r2 * k);
                g = (int) (g1 * g2 * k);
                b = (int) (b1 * b2 * k);

                p = (a<<24) | (r<<16) | (g<<8) | b;

                resultado[d][c] = p;
                d++;
            }
            c++;
        }

		return resultado;
	}

	
}
