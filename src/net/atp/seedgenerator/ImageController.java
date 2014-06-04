package net.atp.seedgenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageController {

    public static BufferedImage img;
    public static Graphics2D g2d;
    private static long counter = 0;
    private static long lastCheck = System.currentTimeMillis();

    //seed manipulation variables
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    public static final int[] addArray = new int[WIDTH*HEIGHT];
    public static int[] seed = new int[WIDTH*HEIGHT];
    public static int[] sumArray = new int[WIDTH*HEIGHT];
    public static int carry = 0;


    public static void main(String[] args) {
        img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();

        for(int a = 0; a < addArray.length-1; a++)
            addArray[a] = 0;
        addArray[addArray.length-1] = 1;

        makeImage();

        Window.initWindow();
        Window.loop();
    }

    private static int[][] arrayAsMatrix(){
        int[][] matrix = new int[WIDTH][HEIGHT];
        int index = 0;
        for(int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                matrix[x][y] = seed[index];
                index++;
            }
        }
        return matrix;
    }

    public static void makeImage(){
        int[][] values = arrayAsMatrix();
        for(int x = 0; x < WIDTH; x++)
            for(int y = 0; y < HEIGHT; y++){
                if(values[x][y] == 0) {
                    g2d.setPaint(Color.black);
                }
                else if(values[x][y] == 1) {
                    g2d.setPaint(Color.white);
                }
                g2d.fillRect(x,y,1,1);
            }
    }

    /**
     * While animating, this should be called every time an image is created.
     */
    public static void syncSpeed(int time) {
        counter++;
        if (System.currentTimeMillis() - lastCheck > 1000) {
            Window.setIps(counter + " per second");
            counter = 0;
            lastCheck = System.currentTimeMillis();
        }

        while (System.currentTimeMillis() - lastCheck < time){
            //wait
            try {
                Thread.sleep(time - (System.currentTimeMillis() - lastCheck));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void increaseSeed(){
        //adding one to the main array
        for(int i = seed.length-1; i >= 0; i--){
            int result = seed[i] + addArray[i] + carry;
            switch(result){
                case 0:
                    sumArray[i] = 0;
                    carry = 0;
                    break;
                case 1:
                    sumArray[i] = 1;
                    carry = 0;
                    break;
                case 2:
                    sumArray[i] = 0;
                    carry = 1;
                    break;
            }

        }
        System.arraycopy(sumArray, 0, seed, 0, seed.length - 1 + 1);
    }

    public static void setSeedFromString(String result) {
        for(int i = 0; i < seed.length; i++)
            seed[i] = 0;

        for(int s = 0; s < result.length(); s++)
            seed[s] = Integer.parseInt(result.substring(s, s+1));
    }

    public static void setRandomSeed() {
        Random r = new Random();
        for(int i = 0; i < seed.length; i++)
            seed[i] = r.nextBoolean()?1:0;
    }

    public static String getSeed() {
        StringBuilder result = new StringBuilder();
        for (int aSeed : seed) {
            result.append(aSeed);
        }
        return result.toString();
    }
}
