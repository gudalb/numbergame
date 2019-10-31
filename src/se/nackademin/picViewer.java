package se.nackademin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class picViewer implements Runnable{

    int showTime = 0;

    ImageIcon p = new ImageIcon();
    BufferedImage[] imgs = new BufferedImage[2];


    public void picViewer(ImageIcon pic, int showTime) {
        this.showTime = showTime * 1000;
        this.p = pic;
    }



    @Override
    public void run() {

        //BufferedImage img1 = ImageIO.read("");
        //BufferedImage img2 = ImageIO.read("");
        //imgs[0] = img1;
        //imgs[1] = img2;


        while (!Thread.interrupted()) {
            try {
                for (BufferedImage i:imgs) {
                    p = new ImageIcon(i);
                    Thread.sleep(5000);
                }
            } catch (Exception e) {
                System.out.println("thread interuppted");
            }

        }

    }
}
