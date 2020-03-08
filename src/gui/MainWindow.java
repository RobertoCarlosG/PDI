package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import clasesImagenes.*;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Image;

public class MainWindow {

	private JFrame frame;
	abrirImagen ObjAbrir = new abrirImagen();
	BufferedImage img1, img2, result;
	boolean lbl1 = false, lbl2 = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblResult = new JLabel("");
		lblResult.setBounds(100, 10, 300, 300);
		frame.getContentPane().add(lblResult);
		lblResult.setVisible(false);
		
		JLabel lblImg1 = new JLabel("Imagen 1");
		lblImg1.setBounds(22, 24, 190, 190);
		frame.getContentPane().add(lblImg1);
		
		JLabel lblImg2 = new JLabel("Imagen 2");
		lblImg2.setBounds(282, 24, 190, 190);
		frame.getContentPane().add(lblImg2);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCerrar.setVisible(false);
				lblResult.setVisible(false);
				lblResult.setIcon(null);
			}
		});
		btnCerrar.setBounds(204, 321, 89, 23);
		frame.getContentPane().add(btnCerrar);
		btnCerrar.setVisible(false);
		
		JButton CargarImg2 = new JButton("Cargar");
		CargarImg2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				img2 = ObjAbrir.abrirImagenLocal();
		        if (img2 != null){
		            lblImg2.setIcon(OperarImagen.redimension(img2));
		            lbl2 = true;
		        }else{
		        	lbl2 = false;
		        }
			}
		});
		CargarImg2.setBounds(321, 225, 89, 23);
		frame.getContentPane().add(CargarImg2);
		
		JButton CargarImg1 = new JButton("Cargar");
		CargarImg1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				img1 = ObjAbrir.abrirImagenLocal();
				if (img1 != null){
		            lblImg1.setIcon(OperarImagen.redimension(img1));
		            lbl1 = true;
				}else{
					lbl1 = false;
				}
			}
		});
		CargarImg1.setBounds(62, 225, 89, 23);
		frame.getContentPane().add(CargarImg1);
		
		JComboBox boxOperacion = new JComboBox();
		boxOperacion.addItem("+");
		boxOperacion.addItem("-");
		boxOperacion.addItem("*");
		boxOperacion.addItem("#");
		boxOperacion.setBounds(222, 156, 50, 20);
		frame.getContentPane().add(boxOperacion);
		
		JButton btnProcesar = new JButton("Operar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lbl1 && lbl2){
					String current = boxOperacion.getSelectedItem().toString();
					int auxAncho, auxAlto;
					int cols,rows;
					ImageIcon icono;
					
					auxAncho	= new Integer( Math.min(img1.getWidth(), img2.getWidth()));
					auxAlto		= new Integer(Math.min(img1.getHeight(), img2.getHeight()));
					
					/*
					 * con esta division hago que sea practicamente los mismos bloques
					 * que tiene el profesor en su imagen, 6 columnas y 4 filas
					 * lo que indica que cada bloque tendra 175 pixeles de ancho por
					 * 170 de alto, con esta informacion podemos manejar y comenzar a crear
					 * los objetos chunk para procesar cada uno de ellos
					 * */
					
					cols		= (int) Math.floor(auxAncho/175);
					rows		= (int) Math.floor(auxAlto/170);
					System.out.println("filas : "+rows+"\tcolumnas : "+cols);
					
					switch(current){
					case "+":
						System.out.println("sumando");
						result	= OperarImagen.suma(img1, img2, auxAncho, auxAlto,/*pixeles*columna*/cols,/*pixeles*fila*/rows);
						lblResult.setVisible(true);
						icono	= new ImageIcon(result.getScaledInstance(lblResult.getWidth(), lblResult.getHeight(), Image.SCALE_SMOOTH));
						lblResult.setIcon(icono);
						btnCerrar.setVisible(true);
						break;
					case "-":
						System.out.println("resta");
						result	= OperarImagen.resta(img1, img2, auxAncho, auxAlto);
						lblResult.setVisible(true);
						icono	= new ImageIcon(result.getScaledInstance(lblResult.getWidth(), lblResult.getHeight(), Image.SCALE_SMOOTH));
						lblResult.setIcon(icono);
						btnCerrar.setVisible(true);
						break;
					case "*":
						System.out.println("multiplicando");
						result	= OperarImagen.multiplicacion(img1, img2, auxAncho, auxAlto);
						lblResult.setVisible(true);
						icono	= new ImageIcon(result.getScaledInstance(lblResult.getWidth(), lblResult.getHeight(), Image.SCALE_SMOOTH));
						lblResult.setIcon(icono);
						btnCerrar.setVisible(true);
						break;
					case "#":
						System.out.println("combinacion lineal");
						result	= OperarImagen.combinacion_lineal(img1, img2, auxAncho, auxAlto);
						lblResult.setVisible(true);
						icono	= new ImageIcon(result.getScaledInstance(lblResult.getWidth(), lblResult.getHeight(), Image.SCALE_SMOOTH));
						lblResult.setIcon(icono);
						btnCerrar.setVisible(true);
						break;
					}
				}
			}
		});
		btnProcesar.setBounds(194, 306, 89, 23);
		frame.getContentPane().add(btnProcesar);
		
	}
}
  