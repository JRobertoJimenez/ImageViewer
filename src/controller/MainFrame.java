package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import model.Image;
import persistence.FileImageLoader;
import ui.ImageDisplay;
import ui.SwingImageDisplay;

public class MainFrame extends JFrame {

    private ImageDisplay imageDisplay;
    private Image i;

    public MainFrame() {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width*75/100,
                Toolkit.getDefaultToolkit().getScreenSize().height*75/100);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(imageDisplay());
        this.setVisible(true);
    }

    private JPanel imageDisplay() {
        SwingImageDisplay sid = new SwingImageDisplay();
        this.imageDisplay = sid;
        sid.addMouseListener(moueseListener());
        //sid.addMouseMotionListener(mouseMotion());
        return sid;
    }
    
    public void setImage(Image i){
        this.i=i;
        imageDisplay.show(i);
    }

    private MouseListener moueseListener() {
        return new MouseListener() {
            private int x;
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                x=e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(x<e.getX()){
                    i=i.prev();
                    imageDisplay.show(i);
                }
                if(x>e.getX()){
                    i=i.next();
                    imageDisplay.show(i);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        };
    }

    /*private MouseMotionListener mouseMotion() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
        };
    }*/
    
    public static void main(String[] args) {
        new MainFrame().setImage(
                new FileImageLoader(
                        new File("C:/Users/José Roberto Jiménez/Desktop/Nueva carpeta/")).load());
    }

}


/*private JPanel toolbar() {
        JPanel panel = new JPanel();
        panel.add(prevButton());
        panel.add(nextButton());
        return panel;
    }

    private Component prevButton() {
        JButton button = new JButton("<");
        button.addActionListener(prevImage());
        return button;
    }

    private ActionListener prevImage() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                imageDisplay.show(imageDisplay.current().prev());
            }
        };
    }

    private Component nextButton() {
        JButton button = new JButton(">");
        button.addActionListener(nextImage());
        return button;
    }

    private ActionListener nextImage() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                imageDisplay.show(imageDisplay.current().next());
            }
        };
    }*/