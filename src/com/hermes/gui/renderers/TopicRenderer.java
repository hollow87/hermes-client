/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.gui.renderers;

import com.hermes.common.AresFormater;
import com.hermes.common.HChannel;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author joaquin
 */
public class TopicRenderer extends javax.swing.JPanel implements TableCellRenderer {

    /**
     * Creates new form TopicCellRender
     */
    private boolean drawn;

    public TopicRenderer() {
        initComponents();
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

        EPTopic = new javax.swing.JEditorPane();

        EPTopic.setContentType("text/html"); // NOI18N
        EPTopic.setEditorKit(new HTMLEditorKit());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EPTopic, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(EPTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane EPTopic;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        
        TopicRenderer tr=new TopicRenderer();
        if (isSelected) {

            
            tr.EPTopic.setForeground(table.getSelectionForeground());
            tr.EPTopic.setBackground(table.getSelectionBackground());
        } else {
            
            tr.EPTopic.setBackground(Color.WHITE);
            tr.EPTopic.setForeground(Color.BLACK);
        }
        
        if(!drawn)
        {
            String topic = ((String) value);
            setSize(table.getCellRect(row, column, true).width, 100);
        topic = "<html><body><div style='overflow: hidden;'>"+AresFormater.getInstance().toHTML(topic)+"</div></body></html>";

        String name = (String) table.getModel().getValueAt(row,1);        
        
        
            HTMLDocument doc = new HTMLDocument();
            tr.EPTopic.setDocument(doc);

            try {
                ((HTMLEditorKit) EPTopic.getEditorKit()).insertHTML(doc, doc.getLength(), topic, 0, 0, null);
            } catch (BadLocationException ex) {
                Logger.getLogger(TopicRenderer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TopicRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
            tr.setDrawn(true);
        }
        return tr;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    
    
}
