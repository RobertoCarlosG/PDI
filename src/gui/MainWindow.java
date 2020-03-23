package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JSlider;
import java.awt.Color;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Desktop;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import clasesImagenes.*;

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
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblImg1 			= new JLabel("Imagen 1");
		JLabel lblImg2 			= new JLabel("Imagen 2");
		JLabel lblResult 		= new JLabel("");
		JButton CargarImg2 		= new JButton("Cargar");
		JButton CargarImg1 		= new JButton("Cargar");
		JComboBox boxOperacion	= new JComboBox();
		JButton btnProcesar 	= new JButton("PROCESAR");
		JButton btnHecho 		= new JButton("Hecho");
		JButton btnGuarda		= new JButton("G U A R D A R");
		JButton btnCER 			= new JButton("C E R R A R");
		btnCER.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					btnCER.setVisible(false);
					btnGuarda.setVisible(false);
					lblResult.setVisible(false);
					boxOperacion.show();
					CargarImg1.show();
					CargarImg2.show();
					lblImg1.show();;
					lblImg2.show();
					btnProcesar.show();
					lblResult.setIcon(null);
			}
		});
		JSlider sliderA = new JSlider();
		JSlider sliderB = new JSlider();
		JLabel lblAlfa = new JLabel("\u03B1");
		JLabel lblBeta = new JLabel("\u03B2");
		
		btnGuarda.setBounds(413, 29, 215, 164);
		frame.getContentPane().add(btnGuarda);
		btnGuarda.setVisible(false);
		btnGuarda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					int resp = (int) JOptionPane.showConfirmDialog(null, "�Seguro que quiere guardar la imagen?");
					if (resp == 0){ //Si se desea guardar la imagen
						OperarImagen.guardarImagen();
						JOptionPane.showMessageDialog(null, "Se ha guardado la imagen");
					}else if(resp == 1){ // No se desea guardar la imagen
						JOptionPane.showMessageDialog(null, "No se ha guardado la imagen");
					}else if(resp == 2){ // cancelar operaci�n
						JOptionPane.showMessageDialog(null, "Operaci�n cancelada...");
					}
				
			}
		});
		
		btnCER.setBounds(413, 223, 215, 158);
		frame.getContentPane().add(btnCER);
		btnCER.setVisible(false);
		
		
		
		
		lblImg1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImg1.setBackground(Color.WHITE);
		lblImg1.setBounds(22, 30, 300, 300);
		frame.getContentPane().add(lblImg1);

		lblImg2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImg2.setBackground(Color.WHITE);
		lblImg2.setBounds(474, 30, 300, 300);
		frame.getContentPane().add(lblImg2);

		lblResult.setBounds(100, 10, 273, 300);
		frame.getContentPane().add(lblResult);
		lblResult.setVisible(false);
		
		JSpinner spinnerF = new JSpinner();
		spinnerF.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(0)));
		spinnerF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spinnerF.setBounds(235, 446, 38, 20);
		frame.getContentPane().add(spinnerF);

		JSpinner spinnerC = new JSpinner();
		spinnerC.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spinnerC.setBounds(235, 475, 38, 20);
		frame.getContentPane().add(spinnerC);
		

		CargarImg2.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
		CargarImg2.setBounds(550, 375, 90, 25);
		frame.getContentPane().add(CargarImg2);

		CargarImg1.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
		CargarImg1.setBounds(150, 375, 90, 25);
		frame.getContentPane().add(CargarImg1);

		boxOperacion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		boxOperacion.addItem(" + ");
		boxOperacion.addItem(" - ");
		boxOperacion.addItem(" * ");
		boxOperacion.addItem("\u03B1 - \u03B2");
		boxOperacion.setBounds(355, 63, 70, 20);
		frame.getContentPane().add(boxOperacion);

		btnProcesar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                boxOperacion.hide();
				CargarImg1.hide();
				CargarImg2.hide();
				lblImg1.hide();
				lblImg2.hide();
				btnProcesar.hide();
				if(lbl1 && lbl2){
					String current = boxOperacion.getSelectedItem().toString();
					int auxAncho, auxAlto;
					int cols,rows;
					ImageIcon icono;
					auxAncho = new Integer( Math.min(img1.getWidth(), img2.getWidth()));
					auxAlto = new Integer(Math.min(img1.getHeight(), img2.getHeight()));

					/*
					 * con esta division hago que sea practicamente los mismos bloques
					 * que tiene el profesor en su imagen, 6 columnas y 4 filas
					 * lo que indica que cada bloque tendra 175 pixeles de ancho por
					 * 170 de alto, con esta informacion podemos manejar y comenzar a crear
					 * los objetos chunk para procesar cada uno de ellos
					 * */

					//cols		= (int) Math.floor(auxAncho/133);
					//rows		= (int) Math.floor(auxAlto/150);
					cols		= (int) spinnerC.getValue();
					rows		= (int) spinnerF.getValue();
					System.out.println("filas : "+rows+"\tcolumnas : "+cols);
					System.out.println("Ancho: " +auxAncho+"\tAlto: "+auxAlto);

					switch(current){
					case " + ":
						System.out.println("sumando");
						result	= OperarImagen.suma(img1, img2, auxAncho, auxAlto,/*pixeles*columna*/cols,/*pixeles*fila*/rows);
						OperarImagen.obtenerImagenAGuardar(result, "+");
						lblResult.setVisible(true);
						icono	= new ImageIcon(result.getScaledInstance(lblResult.getWidth(), lblResult.getHeight(), Image.SCALE_SMOOTH));
						lblResult.setIcon(icono);
						btnCER.setVisible(true);
						btnGuarda.setVisible(true);
						BufferedImage res 	= new BufferedImage(auxAncho,auxAlto,BufferedImage.TYPE_INT_RGB);
						try {
							res					= Parallel.parallelOperate("+", img1, img2, 2);
							ImageIO.write(res, "jpg", new File("Resources\\PARALLEL.jpg"));
						} catch (InterruptedException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case " - ":
                        System.out.println("resta");
						result	= OperarImagen.resta(img1, img2, auxAncho, auxAlto, cols, rows);
						OperarImagen.obtenerImagenAGuardar(result, "-");
						lblResult.setVisible(true);
						icono	= new ImageIcon(result.getScaledInstance(lblResult.getWidth(), lblResult.getHeight(), Image.SCALE_SMOOTH));
						lblResult.setIcon(icono);
						btnCER.setVisible(true);
						btnGuarda.setVisible(true);
						break;
					case " * ":
						System.out.println("multiplicando");
						result	= OperarImagen.multiplicacion(img1, img2, auxAncho, auxAlto, cols, rows);
						OperarImagen.obtenerImagenAGuardar(result, "*");
						lblResult.setVisible(true);
						icono	= new ImageIcon(result.getScaledInstance(lblResult.getWidth(), lblResult.getHeight(), Image.SCALE_SMOOTH));
						lblResult.setIcon(icono);
						btnCER.setVisible(true);
						btnGuarda.setVisible(true);
						break;
					case "\u03B1 - \u03B2":
						btnHecho.setVisible(true);
						sliderA.setVisible(true);
						sliderB.setVisible(true);
						lblAlfa.setVisible(true);
						lblBeta.setVisible(true);
						btnProcesar.setVisible(false);

						break;
					}
					System.out.println("COLUMNAS: "+ spinnerC.getValue() +" FILAS: "+ spinnerF.getValue());
					
					/*
					 * if(current != "\u03B1 - \u03B2")
					 
					{
						JDialog imgRes = new JDialog(frame,"Imagen Resultante");
						JLabel lblRes = new JLabel();
						JButton btnGuardar = new JButton("Guardar");
						btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 13));
						btnGuardar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								try{
									ImageIO.write(result, "jpg", new File("Resources\\salidax.jpg"));
								}catch (IOException e) {
									e.printStackTrace();
								}
							}
						});
						icono	= new ImageIcon(result.getScaledInstance(lblResult.getWidth(), lblResult.getHeight(), Image.SCALE_SMOOTH));
						lblRes.setIcon(icono);
						imgRes.getContentPane().add(btnGuardar);
						imgRes.getContentPane().add(lblRes);
						imgRes.setSize(420, 420);
						imgRes.setLocation(200, 200);
						lblRes.setBounds(60, 0, 300, 300);
						btnGuardar.setBounds(300, 340, 90, 25);
						imgRes.setVisible(true);

					}
				*/
				}
			}
		});
		btnProcesar.setBounds(355, 375, 100, 25);
		frame.getContentPane().add(btnProcesar);

		btnHecho.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHecho.setBounds(355, 297, 90, 25);
		btnHecho.setVisible(false);
		btnHecho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon icono = null;
				int auxAncho = new Integer( Math.min(img1.getWidth(), img2.getWidth()));
				int auxAlto = new Integer(Math.min(img1.getHeight(), img2.getHeight()));
				int alfa = sliderA.getValue();
				int betta = sliderB.getValue();

				System.out.println("combinacion lineal");
                result = OperarImagen.combinacionLineal(img1, img2, auxAncho, auxAlto,0,0,alfa,betta);
				OperarImagen.obtenerImagenAGuardar(result, "#");
				lblResult.setVisible(true);
				icono	= new ImageIcon(result.getScaledInstance(lblResult.getWidth(), lblResult.getHeight(), Image.SCALE_SMOOTH));
				lblResult.setIcon(icono);
				btnCER.setVisible(true);
				btnGuarda.setVisible(true);
				btnProcesar.setVisible(true);
				}

		});
		frame.getContentPane().add(btnHecho);


		//Cambiando valores a Sliders
		sliderB.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int n = sliderB.getValue();
				sliderA.setValue(100 - n);
			}
		});
		sliderA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int n = sliderA.getValue();
				sliderB.setValue(100 - n);
			}
		});
		sliderA.setBounds(319, 143, 145, 50);
		sliderA.setPaintLabels(true);
		sliderA.setPaintTicks(true);
		sliderA.setMajorTickSpacing(25);
		sliderA.setMinorTickSpacing(10);
		sliderA.setVisible(false);
		frame.getContentPane().add(sliderA);

		sliderB.setBounds(319, 236, 145, 50);
		sliderB.setPaintLabels(true);
		sliderB.setPaintTicks(true);
		sliderB.setMajorTickSpacing(25);
		sliderB.setMinorTickSpacing(10);
		sliderB.setVisible(false);
		frame.getContentPane().add(sliderB);

		lblAlfa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlfa.setBounds(381, 110, 20, 20);
		lblAlfa.setText("\u03B1 :");
		lblAlfa.setVisible(false);
		frame.getContentPane().add(lblAlfa);

		lblBeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBeta.setBounds(381, 204, 20, 20);
		lblBeta.setText("\u03B2 :");
		lblBeta.setVisible(false);
		frame.getContentPane().add(lblBeta);

		JLabel lblFilas = new JLabel("Filas:");
		lblFilas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFilas.setBounds(187, 450, 38, 14);
		frame.getContentPane().add(lblFilas);

		JLabel lblColumnas = new JLabel("Columnas:");
		lblColumnas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblColumnas.setBounds(160, 479, 70, 14);
		frame.getContentPane().add(lblColumnas);

		
		
		
		

	}
}
