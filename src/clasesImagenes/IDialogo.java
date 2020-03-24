package clasesImagenes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import gui.MainWindow;

public class IDialogo extends Dialog 
{
	private String dir;
	
	public IDialogo(Shell parent, String dir) {
		this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL, dir);
	}

	public IDialogo(Shell parent, int style, String dir) {
		// Let users override the default styles
	    super(parent, style);
	    this.dir = dir;
	}

	public void open() {
		Shell shell = new Shell(getParent(), getStyle());
	    shell.setText(getText());
	    createContents(shell);
	    shell.pack();
	    shell.open();
	    Display display = getParent().getDisplay();
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch()) {
	        display.sleep();
	      }
	    }
	}

	private void createContents(final Shell shell) {
		//Create image
		Image image = new Image(shell.getDisplay(), dir);
		Image resize = null;
		
		// Show label
		Label lbl_Img = new Label(shell, SWT.NONE);
		lbl_Img.setBounds(1, 1, 600, 600);
		if(image.getBounds().height > 600 && image.getBounds().width > 800)
		{
			lbl_Img.setImage(resize);
		}
		else 
		{
			lbl_Img.setImage(image);
		}
	}
	
	
	
	public static Image resize(Image image, int factor) {
		// Resize to +- factor
		
		int w1 = image.getBounds().width;
		int h1 = image.getBounds().height;

		int w = image.getBounds().width;
		int h = image.getBounds().height;
		
		if(w1 > factor && w1 > h1)
		{
			w = factor;
			h = (h1 * factor) / w1;
		}
		
		if(h1 > factor && h1 > w1)
		{
			h = factor;
			w = (w1 * factor) / h1;
		}
		
		Image scaled = new Image(Display.getDefault(), w, h);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0,image.getBounds().width, image.getBounds().height, 0, 0, w, h);
		gc.dispose();
		image.dispose();
		return scaled;
	}
}
