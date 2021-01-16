package ui;

import java.awt.Point;
import model.Image;

public interface ImageDisplay {
    
    void show(Image image);
    void show(Image image, Point shift, Image alt);

}
