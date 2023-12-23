package T2;

import T1.SortAlgorithm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Scratch {

    private Picture pic = null;
    private Color white = new Color(255, 255, 255);
    private Color blue = new Color(0, 0, 255);

    public Scratch() throws IOException {
        this.pic = new Picture("D:/temp/tomography.png");
    }

    public int[] calculateLength(Picture pic) {
        int[] length = new int[pic.getWidth()];
        for (int width = 0; width < pic.getWidth(); width++) {
            if (pic.getColor(width, pic.getHeight() - 1).equals( pic.getColor(width, 0))){
                length[width] = 0;                                                //如果最首尾颜色一致，则长度一定为0
            }
            else {
                if (pic.getColor(width, 0).equals(white)) {
                    int left = 0;
                    int right = pic.getHeight() - 1;
                    int mid = left + (right - left) / 2;
                    while (pic.getColor(width, mid).equals(pic.getColor(width, mid + 1)) && pic.getColor(width, mid).equals(pic.getColor(width, mid - 1))) {
                        if (pic.getColor(width, mid).equals( blue)) {
                            right = mid;
                            mid = left + (right - left) / 2;
                        } else {
                            left = mid;
                            mid = left + (right - left) / 2;
                        }
                    }
                    if (pic.getColor(width, mid).equals( blue)) {
                        length[width] = pic.getHeight() - mid;
                    } else {
                        length[width] = pic.getHeight() - 1 - mid;
                    }
                } else {
                    int left = 0;
                    int right = pic.getHeight() - 1;
                    int mid = left + (right - left) / 2;
                    while (pic.getColor(width, mid).equals(pic.getColor(width, mid + 1)) && pic.getColor(width, mid).equals(pic.getColor(width, mid - 1))) {
                        if (pic.getColor(width, mid).equals(blue)) {
                            left = mid;
                            mid = left + (right - left) / 2;
                        } else {
                            right = mid;
                            mid = left + (right - left) / 2;
                        }
                    }
                    if (pic.getColor(width, mid).equals(blue)) {
                        length[width] = mid + 1;
                    } else {
                        length[width] = mid;
                    }
                }
            }
        }
        return length;
    }



    public static void main(String[] args) throws IOException {
         Scratch imag = new Scratch();
         int max = 0;
         imag.calculateLength(imag.pic);
         for(int i =0;i<1200;i++){
             System.out.println(imag.calculateLength(imag.pic)[i]);
             if(max<imag.calculateLength(imag.pic)[i]){
                 max = imag.calculateLength(imag.pic)[i];
             }
         }
         System.out.println();
         System.out.println();
         System.out.println(max);


    }
}
