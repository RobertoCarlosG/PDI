package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

		JLabel lblImg1 = new JLabel("Imagen 1");
		JLabel lblImg2 = new JLabel("Imagen 2");
		JButton CargarImg2 = new JButton("Cargar");
		JButton CargarImg1 = new JButton("Cargar");
		JComboBox boxOperacion = new JComboBox();
		JButton btnProcesar = new JButton("Operar");
		JButton btnHecho = new JButton("Hecho");
		JSlider sliderA = new JSlider();
		JSlider sliderB = new JSlider();
		JLabel lblAlfa = new JLabel("\u03B1");
		JLabel lblBeta = new JLabel("\u03B2");


		lblImg1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImg1.setBackground(Color.WHITE);
		lblImg1.setBounds(50, 25, 300, 300);
		frame.getContentPane().add(lblImg1);

		lblImg2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImg2.setBackground(Color.WHITE);
		lblImg2.setBounds(450, 30, 300, 300);
		frame.getContentPane().add(lblImg2);

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
		boxOperacion.setBounds(365, 156, 70, 20);
		frame.getContentPane().add(boxOperacion);

		btnProcesar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lbl1 && lbl2){
					String current = boxOperacion.getSelectedItem().toString();
					int auxAncho, auxAlto;
					ImageIcon icono = null;
					auxAncho = new Integer( Math.min(img1.getWidth(), img2.getWidth()));
					auxAlto = new Integer(Math.min(img1.getHeight(), img2.getHeight()));
					switch(current){
					case " + ":
						result = OperarImagen.suma(img1, img2, auxAncho, auxAlto,0,0);
						icono = new ImageIcon(result.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
						break;
					case " - ":
						result = OperarImagen.resta(img1, img2, auxAncho, auxAlto,0,0);
						icono = new ImageIcon(result.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
						break;
					case " * ":
						result = OperarImagen.multiplicacion(img1, img2, auxAncho, auxAlto,0,0);
						icono = new ImageIcon(result.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
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
					if(current != "\u03B1 - \u03B2")
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
						lblRes.setIcon(icono);
						imgRes.getContentPane().add(btnGuardar);
						imgRes.getContentPane().add(lblRes);
						imgRes.setSize(420, 420);
						imgRes.setLocation(200, 200);
						lblRes.setBounds(60, 0, 300, 300);
						btnGuardar.setBounds(300, 340, 90, 25);
						imgRes.setVisible(true);

					}
				}
			}
		});
		btnProcesar.setBounds(355, 375, 90, 25);
		frame.getContentPane().add(btnProcesar);

		btnHecho.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHecho.setBounds(506, 525, 90, 25);
		btnHecho.setVisible(false);
		btnHecho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon icono = null;
				int auxAncho = new Integer( Math.min(img1.getWidth(), img2.getWidth()));
				int auxAlto = new Integer(Math.min(img1.getHeight(), img2.getHeight()));
				int alfa = sliderA.getValue();
				int betta = sliderB.getValue();
				result = OperarImagen.combinacionLineal(img1, img2, auxAncho, auxAlto,0,0,alfa,betta);
				icono = new ImageIcon(result.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
				JDialog imgRes = new JDialog(frame,"Imagen Resultante");
				JLabel lblRes = new JLabel();
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try{
							ImageIO.write(result, "png", new File("Resources\\ImgResultSumax.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				lblRes.setIcon(icono);
				imgRes.getContentPane().add(btnGuardar);
				imgRes.getContentPane().add(lblRes);
				imgRes.setSize(420, 420);
				imgRes.setLocation(200, 200);
				lblRes.setBounds(60, 0, 300, 300);
				btnGuardar.setBounds(300, 340, 90, 25);
				imgRes.setVisible(true);
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
		sliderA.setBounds(451, 420, 200, 50);
		sliderA.setPaintLabels(true);
		sliderA.setPaintTicks(true);
		sliderA.setMajorTickSpacing(25);
		sliderA.setMinorTickSpacing(10);
		sliderA.setVisible(false);
		frame.getContentPane().add(sliderA);

		sliderB.setBounds(451, 475, 200, 50);
		sliderB.setPaintLabels(true);
		sliderB.setPaintTicks(true);
		sliderB.setMajorTickSpacing(25);
		sliderB.setMinorTickSpacing(10);
		sliderB.setVisible(false);
		frame.getContentPane().add(sliderB);

		lblAlfa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlfa.setBounds(421, 424, 20, 20);
		lblAlfa.setText("\u03B1 :");
		lblAlfa.setVisible(false);
		frame.getContentPane().add(lblAlfa);

		lblBeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBeta.setBounds(421, 475, 20, 20);
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

	}
}
  