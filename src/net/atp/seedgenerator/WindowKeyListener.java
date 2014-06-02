package net.atp.seedgenerator;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WindowKeyListener implements KeyListener{

    /**
     * Controls:
     *
     * Space - toggle animation
     * left - previous frame (when stopped)
     * right - next frame (when stopped)
     * cmd-s or ctrl-s - save the image(open dialog)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case (KeyEvent.VK_RIGHT):{  //increase seed
                ImageController.increaseSeed();
                break;
            }
            case (KeyEvent.VK_S):{      //set seed
                String result = JOptionPane.showInputDialog(null, "Enter seed: (0 and 1 only, up to 256 digits)", "0");
                ImageController.setSeedFromString(result);
                break;
            }
            /*case (KeyEvent.VK_SPACE):{
                Window.toggleAnimated();
                break;
            }
            case (KeyEvent.VK_LEFT):{
                if (!Window.isAnimated())
                    Window.previousFrame();
                break;
            }
            case (KeyEvent.VK_RIGHT):{
                if (!Window.isAnimated())
                    Window.nextFrame();
                break;
            }
            case (KeyEvent.VK_S):{
                if (!Window.isAnimated()){
                    if (e.isControlDown() || e.isMetaDown()){
                        JFileChooser chooser = new JFileChooser();
                        int result = chooser.showSaveDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION){
                            File f = chooser.getSelectedFile();
                            if (!f.getAbsolutePath().endsWith(".png"))
                                f = new File(f.getAbsolutePath().concat(".png"));

                            try {
                                ImageIO.write(ImageController.img, "PNG", f);
                                JOptionPane.showMessageDialog(null, "Saved image successfully");
                            } catch (IOException e1) {
                                e1.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Could not save image");
                            }
                        }
                    } else {
                        String result = JOptionPane.showInputDialog(null, "Enter seed: (Integer between -2^63 and 2^63-1)", "0");
                        try {
                            long r = Long.parseLong(result);
                            Window.setFrame(r);
                        } catch (Exception ex){
                            //Just ignore
                        }
                    }
                }
                break;
            }
            case (KeyEvent.VK_M):{
                Window.incrementMode();
                break;
            }   */
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
