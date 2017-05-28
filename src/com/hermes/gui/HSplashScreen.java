/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SplashScreen.java
 *
 * Created on 24-jun-2010, 16:39:55
 */

package com.hermes.gui;

import com.hermes.common.HHash;
import com.hermes.util.ConfigReader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author jomartinez
 */
public class HSplashScreen extends javax.swing.JFrame {

    private Timer timer;
    /** Creates new form SplashScreen */
    public HSplashScreen() {
        initComponents();
         final Random rnd=new Random();
        timer=new Timer(500,new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(jProgressBar1.getValue()<100)
                {
                   
                   jProgressBar1.setValue(jProgressBar1.getValue()+rnd.nextInt(10)+10);
                }
                else
                {
                      try {
                          timer.stop();
                          ConfigReader.getInstance();
                          HHash.getInstance();
                          new HermesClient().setVisible(true);
                           dispose();
                        }
                      catch (IOException ex)
                      {
                         JOptionPane.showMessageDialog(null,ex.getMessage());
                          Logger.getLogger(HSplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(HSplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DataFormatException ex) {
                        Logger.getLogger(HSplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        timer.start();
        setSize(600,239);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hermes-Client");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/com/hermes/resources/images/icon.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hermes/resources/images/logo.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 600, 200);

        jProgressBar1.setForeground(new java.awt.Color(159, 159, 159));
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(0, 200, 600, 10);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

}
