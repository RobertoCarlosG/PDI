package clasesImagenes;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class abrirImagen {
	private JFileChooser selectorDialog;
	
	private void errorImagen(){
        JOptionPane.showMessageDialog(null,"No se pudo cargar la imagen","Error",JOptionPane.ERROR_MESSAGE);
   }
   
   private boolean abrirJFileChooser(){
	   String fileFilterPath = "C:\\Users\\ChristianPasten\\Pictures";
	   this.selectorDialog = new JFileChooser();
	   File init = new File(fileFilterPath);
	   this.selectorDialog.setCurrentDirectory(init);
	   this.selectorDialog.setDialogTitle("Elige una Imagen");
	   this.selectorDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
	   FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("Imagenes","jpg","jpeg","bmp");
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
