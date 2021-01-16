package ui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Image;

public class SwingImageDisplay  extends JPanel implements ImageDisplay {
    private BufferedImage currentImage;
    private BufferedImage altImage;
    private Point shift;

    
    
    @Override
    public void show(Image image) {
        this.currentImage = imageOf(image);
        shift=new Point(0,0);
        altImage=null;
        this.repaint();
    }
    
    @Override
    public void show(Image image, Point s, Image alt) {
        this.currentImage = imageOf(image);
        shift = s;
        altImage=imageOf(alt);
        this.repaint();
        
    }
    
    @Override
    public void paint(Graphics g) {
        if (currentImage == null) return;
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        
        double [] d=getD(currentImage);
        int w=(int)d[0];
        int h=(int)d[1];
        
        g.drawImage(currentImage, shift.x+(this.getWidth()-w)/2, (this.getHeight()-h)/2,
            shift.x+(this.getWidth()+w)/2, (this.getHeight()+h)/2,
            0, 0,
            currentImage.getWidth(),currentImage.getHeight(), null);
        
        
        if (altImage == null) return;
        d=getD(altImage);
        int altw=(int)d[0];
        int alth=(int)d[1];
        
        g.drawImage(altImage, 
            shift.x + (shift.x>0? -altw:this.getWidth()), (this.getHeight()-alth)/2,
            shift.x + (shift.x>0? 0:this.getWidth()+altw), (this.getHeight()+alth)/2,
            0, 0,
            altImage.getWidth(),altImage.getHeight(), null);
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
