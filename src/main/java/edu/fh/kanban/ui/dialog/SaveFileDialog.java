package edu.fh.kanban.ui.dialog;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.fh.kanban.dao.XML.XMLDAOProperties;

public class SaveFileDialog extends JFrame {
	

    
    public void showSaveDialog(){
    	JFileChooser chooser;
    	String path = System.getProperty("user.home");
        XMLDAOProperties.setFile(new File(path.trim()));

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

            chooser.setVisible(false);
        }
        XMLDAOProperties.setFile(new File(path));
        chooser.setVisible(false);
        this.setVisible(true);
    }

}
