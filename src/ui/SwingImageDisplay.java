package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Image;

public class SwingImageDisplay  extends JPanel implements ImageDisplay {
    private BufferedImage currentImage;


    
    

    @Override
    public void show(Image image) {
        this.currentImage = imageOf(image);
        this.repaint();
        
    }
    
    @Override
    public void paint(Graphics g) {
        if (currentImage == null) return;
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        
        double [] d=getD(currentImage);
        int w=(int)d[0];
        int h=(int)d[1];
        
        g.drawImage(currentImage, (this.getWidth()-w)/2, (this.getHeight()-h)/2,
        (this.getWidth()-w)/2+w, (this.getHeight()-h)/2+h,
        0, 0,
        currentImage.getWidth(),currentImage.getHeight(), null);
    }

    private BufferedImage imageOf(Image image) {
        try {
            BufferedImage bi=ImageIO.read(image.stream());
            return bi;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    };

    private double[] getD(BufferedImage bi) {
        double [] res= {bi.getTileWidth(),bi.getHeight()};
        if(res[0]>this.getWidth()){
            res[0]=this.getWidth();
            res[1]=(res[0]/bi.getWidth())*bi.getHeight();
        }
        if(res[1]>this.getHeight()){
            res[1]=this.getHeight();
            res[0]=(res[1]/bi.getHeight())*bi.getWidth();
        }
        
        return res;
    }

    

}
