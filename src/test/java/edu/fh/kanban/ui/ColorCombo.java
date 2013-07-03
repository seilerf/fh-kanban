package edu.fh.kanban.ui;

import javax.swing.JToolBar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorCombo extends JFrame {
    private JToolBar toolbar;
    private ColorBox colorBox;
    public ColorCombo() {
        super("Combo Colors Demo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        toolbar = new JToolBar();
        //Create the ColorBox:
        colorBox = new ColorBox();
        //Layout:
        toolbar.add(colorBox, BorderLayout.NORTH);
        getContentPane().add(toolbar, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(null);
        //Listener:
        colorBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                colorBoxItemStateChanged(e);
            }
        });
    }
    private void colorBoxItemStateChanged(final ItemEvent e){
        if(e.getStateChange() == ItemEvent.DESELECTED) return;
        int selectedIndex = colorBox.getSelectedIndex();
        System.out.println(colorBox.LABELS[selectedIndex]+" "+colorBox.COLORS[selectedIndex]);
    }
    public static void main(final String args[]) {new ColorCombo().setVisible(true);}
}
class ColorBox extends JComboBox{
    private final Icon[] COLOR_ICONS;
    public final String LABELS[] = {
        "BLACK","BLUE","CYAN","DARK_GRAY","GRAY","GREEN","LIGHT_GRAY",
        "MAGENTA","ORANGE","PINK","RED","WHITE","YELLOW"
    };
    public final Color COLORS[] = {
        Color.BLACK,Color.BLUE,Color.CYAN,Color.DARK_GRAY,Color.GRAY,Color.GREEN,Color.LIGHT_GRAY,
        Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.WHITE,Color.YELLOW
    };
    public ColorBox(){
        super();
        //Load the COLOR_ICONS and create an array of indexes:
        COLOR_ICONS = new Icon[LABELS.length];
        final Integer[] INT_ARRAY = new Integer[LABELS.length];
        for (int i = 0; i < LABELS.length; i++) {
            INT_ARRAY[i] = new Integer(i);
            COLOR_ICONS[i] = new ColorIcon(COLORS[i], new Dimension(100, 20));
        }
        setModel(new DefaultComboBoxModel(INT_ARRAY));
        setRenderer(new ComboBoxRenderer());
    }
    class ComboBoxRenderer extends JLabel implements ListCellRenderer {
        public ComboBoxRenderer() {setOpaque(true);}
        public Component getListCellRendererComponent(
                final JList list, final Object value, final int index,
                final boolean isSelected, final boolean cellHasFocus) {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }
            //Set the icon and text:
            int selectedIndex = ((Integer)value).intValue();
            setIcon(COLOR_ICONS[selectedIndex]);
            setText(LABELS[selectedIndex]);
            return this;
        }
    }
    class ColorIcon implements Icon {
        final private Color color;
        final private Dimension size;
        public ColorIcon(final Color color, final Dimension size) {
            this.color = color;
            this.size = size;
        }
        public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
            g.setColor(color);
            g.fillRect(x, y, getIconWidth(), getIconHeight());
        }
        public int getIconWidth() {return size.width;}
        public int getIconHeight() {return size.height;}
    }
}
