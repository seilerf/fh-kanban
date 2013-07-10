package edu.fh.kanban.ui.dialog;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveFileDialog extends JFrame {
	
	private File file;

    public SaveFileDialog() {
    	JFileChooser chooser;
    	String path = System.getProperty("user.home");
        this.file = new File(path.trim());

        chooser = new JFileChooser(path);
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        FileNameExtensionFilter plainFilter = new FileNameExtensionFilter(
                "Plaintext: txt, csv", "txt", "csv");
        FileNameExtensionFilter markUpFilter = new FileNameExtensionFilter(
                "Markup: xml, htm, html", "xml", "html", "htm");
        chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
        chooser.setFileFilter(plainFilter);
        chooser.setFileFilter(markUpFilter);
        chooser.setDialogTitle("Board speichern...");
        chooser.setVisible(true);

        int result = chooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            path = chooser.getSelectedFile().toString();
            this.file = new File(path);

            chooser.setVisible(false);
        }
        chooser.setVisible(false);
    }

    public File getFile() {
    	return this.file;
    }
}
