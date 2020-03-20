package clasesImagenes;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.core.runtime.Path;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

public class abrirImagen {
	private JFileChooser selectorDialog;
	
	private void errorImagen(){
        JOptionPane.showMessageDialog(null,"No se pudo cargar la imagen","Error",JOptionPane.ERROR_MESSAGE);
   }
   
   private boolean abrirJFileChooser(){
	   String fileFilterPath = "Resources";
	   String path = this.getClass().getClassLoader().getResource("gui").getPath();
	   System.out.println(path);
	   //Creamos un dialogo para seleccionar archivos
	   this.selectorDialog = new JFileChooser();
	   // se carga la direccion inicial
	   File init = new File(fileFilterPath);
	   // se configura la direccion inicial
	   this.selectorDialog.setCurrentDirectory(init);
	   this.selectorDialog.setDialogTitle("Elige una Imagen");
	   // cargar el tipo de seleccion
	   this.selectorDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
	   // extencion del archivo
	   FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("Imagenes","jpg","jpeg","bmp","png");
	   selectorDialog.setFileFilter(imgFilter);
	   int flag = this.selectorDialog.showOpenDialog(null);
	   if (flag==JFileChooser.APPROVE_OPTION){
		   return true;
	   }else{
		   return false;
	   }
  }   
   
  public BufferedImage abrirImagenLocal(){
      BufferedImage imagenRetorno = null;
      if(this.abrirJFileChooser() == true){
          try {
        	  //se carga la imagen seleccionada
              imagenRetorno = ImageIO.read(this.selectorDialog.getSelectedFile());
              if (imagenRetorno!=null){
            	  //Aqui deberia construit un onjeto de tipo imagen
              }else{
                  errorImagen();
              }
          } catch (Exception e) {
              errorImagen();
          }
      }        
      return imagenRetorno;
  }
}
