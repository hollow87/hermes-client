/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.gui.dialogs;

import com.hermes.common.AresFormater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author jomartinez
 */
public class ColorDialog extends javax.swing.JDialog
{

    /**
     * Creates new form ColorDialog
     */
    private String colorCode;

    public ColorDialog(java.awt.Frame parent, boolean modal)
    {
        super(parent, true);
        initComponents();
        setSize(120, 120);
        getRootPane().setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.black));
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        colorCode = null;
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action actionListener = new AbstractAction()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                setVisible(false);
            }
        };
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", actionListener);

        JButton bColor;
        String name;
        for (int i = 0; i < AresFormater.COLORS.length; i++)
        {
            bColor = new JButton();
            name = "" + i;
            if (i < 10)
            {
                name = "0" + name;
            }
            bColor.setName(name);
            bColor.setSize(30, 30);
            bColor.setBorderPainted(false);
            bColor.setContentAreaFilled(false);
            bColor.setOpaque(true);
            bColor.setBackground(AresFormater.COLORS[i]);
            bColor.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    colorCode = ((JButton) e.getSource()).getName();
                    dispose();
                }
            });
            add(bColor);
        }
    }

    public String getColorCode()
    {
        return colorCode;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(4, 4));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
