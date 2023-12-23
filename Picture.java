package T2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture {
    private BufferedImage image;
    public int width;
    private int height;
    public Picture(String filename) throws IOException {
        File file = new File(filename);
        image = ImageIO.read(file);
        width = image.getWidth();
        height = image.getHeight();
    }
    public Picture(int width, int height){
        this.width = width;
        this.height = height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setColor(int col, int row, Color c){
        image.setRGB(col, row, c.getRGB());
    }
    public Color getColor(int col, int row){
        int rgb = image.getRGB(col, row);
        return new Color(rgb);
    }
    public void save(String filename) throws IOException {
        String suffix = filename.substring(filename.lastIndexOf('.')+1);
        if (suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("jpg"))
            ImageIO.write(image, suffix, new File(filename));
    }
    public void darker(){
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++){
                Color c = getColor(i, j);
                setColor(i, j, c.darker());
            }
    }
}

