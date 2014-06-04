package net.atp.seedgenerator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Window extends Canvas {

    public static BufferedImage displayImg;

    public static boolean isAnimated = false;

    private static Window window;

    private static BufferedImage background;

    /**
     * String that indicates the images per second
     * ex: "59 per second\t457 total"
     */
    private static String ips = "";

    public static void initWindow() {
        displayImg = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        try {
            background = ImageIO.read(Window.class.getResourceAsStream("background.png"));
        } catch (Exception e){
            e.printStackTrace();
            background = new BufferedImage(500,300,BufferedImage.TYPE_INT_RGB);
        }

        window = new Window();

        JFrame frame = new JFrame("Seed Generator");
        frame.setSize(500, 800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.addKeyListener(new WindowKeyListener());
        frame.add(window);
        frame.setVisible(true);
    }

    public static void drawImage(BufferedImage b){
        displayImg = b;
        window.repaint();
    }

    @Override
    public void paint(Graphics g){
    }

    @Override
    public void update(Graphics g) {
        //Draw the image
        g.drawImage(displayImg, 128, 128, 256, 256, null);

        //Draw the debug background
        g.drawImage(background, 0, 500, 500, window.getHeight()-500, null);

        int pre = 20;

        //Titles
        g.setColor(Color.cyan); g.drawString("Current:", pre, 535);
        g.setColor(Color.red); g.drawString("Speed:", pre, 550);
        g.setColor(Color.green); g.drawString("Mode:", pre, 565);
        //580
        g.setColor(Color.yellow); g.drawString("Controls:", pre, 595);
        //610-640
        //655
        g.setColor(Color.orange); g.drawString("Modes:", pre, 700);

        int indent = pre + 70;

        //Info
        g.setColor(Color.white);
        long currentFrame = 0;
        g.drawString("" + currentFrame, indent, 535);
        if (isAnimated){
            g.drawString(ips, indent, 550);
        }else{
            g.setColor(Color.gray);
            g.drawString("N/A", indent, 550);
            g.setColor(Color.white);
        }
        String mode = "Mode: Sequential binary";
        g.drawString(mode, indent, 565);

        //Controls
        g.drawString("SPACE - Toggle animation", indent, 595);
        g.drawString("RIGHT - increment current seed (when paused)", indent, 610);
        g.drawString("R - create random seed", indent, 625);
        g.drawString("S - set seed", indent, 640);
        g.drawString("I - save image", indent, 655);
        g.drawString("W - write seed to disk", indent, 670);
        g.drawString("(only write if it's not pure noise!!)", indent, 685);
    }

    /**
     * Runs a loop to modify and display images
     */
    public static void loop() {
        //noinspection InfiniteLoopStatement
        while (true) {
            if (isAnimated) {
                nextFrame();
                int syncTime = 0;
                ImageController.syncSpeed(syncTime);
            }

            updateBuff();
            Window.drawImage(ImageController.img);
        }
    }

    public static void updateBuff(){
        ImageController.makeImage();
    }

	/*
	 * CONTROL METHODS:
	 */

    public static void toggleAnimated(){
        isAnimated = !isAnimated;
    }

    public static boolean isAnimated(){
        return isAnimated;
    }

    public static void nextFrame(){
        ImageController.increaseSeed();
    }

    public static void setIps(String ips){
        Window.ips = ips;
    }
}
