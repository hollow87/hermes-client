/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hermes.gui;

import com.hermes.client.HCChannelDownloader;
import com.hermes.client.events.ChannelListClickedEvent;
import com.hermes.client.events.HChannelListEvents;
import com.hermes.client.events.HClientEvent;
import com.hermes.common.HChannel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jomartinez
 */
public class ListPane extends javax.swing.JPanel
{

    /**
     * Creates new form List
     */
    private HCChannelDownloader downloader;
    private TableRowSorter<DefaultTableModel> sorter;

    public ListPane(final ChannelListClickedEvent evt) throws IOException
    {
        initComponents();
        sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) TChannels.getModel()));
        TChannels.setRowSorter(sorter);
        TFFilter.getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent e)
            {
                RowFilter<DefaultTableModel, Object> rf = null;
                //declare a row filter for your table model
                try
                {
                    rf = RowFilter.regexFilter("(?i)" + TFFilter.getText(), 1, 5);

                } catch (java.util.regex.PatternSyntaxException ex)
                {
                    ex.printStackTrace();
                    return;
                }
                sorter.setRowFilter(rf);
                TChannels.repaint();
            }

            public void insertUpdate(DocumentEvent e)
            {
                RowFilter<DefaultTableModel, Object> rf = null;
                try
                {
                    rf = RowFilter.regexFilter("(?i)" + TFFilter.getText(), 1, 5);
                } catch (java.util.regex.PatternSyntaxException ex)
                {
                    ex.printStackTrace();
                    return;
                }
                sorter.setRowFilter(rf);
                TChannels.repaint();
            }

            public void removeUpdate(DocumentEvent e)
            {
                if (TFFilter.getText().length() == 0)
                {
                    sorter.setRowFilter(null);
                } else
                {
                    RowFilter<DefaultTableModel, Object> rf = null;
                    try
                    {
                        rf = RowFilter.regexFilter("(?i)" + TFFilter.getText(), 1, 5);
                    } catch (java.util.regex.PatternSyntaxException ex)
                    {
                        ex.printStackTrace();
                        return;
                    }
                    sorter.setRowFilter(rf);
                    TChannels.repaint();
                }
            }
        });
        TChannels.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int row = TChannels.rowAtPoint(e.getPoint());
                HChannel selectedChannel =downloader.get((int) TChannels.getValueAt(row, 0)) ;
                if (e.getClickCount() % 2 == 0 && e.getButton() == MouseEvent.BUTTON1)
                {
                        evt.channelListClick(selectedChannel);
                }                
            }
        });
        TFFilter.requestFocus();
        downloader = new HCChannelDownloader(new File("ChatroomIPs.dat"));
                
        downloader.addEventListener(new HChannelListEvents()
        {

            @Override
            public void onNewChannel(HChannel channel, int index)
            {
                Object[] row =
                {
                    index, channel.getName(), channel.getTopic(),channel.getLanguage(),channel.getUserCount() 
                };

                ((DefaultTableModel) TChannels.getModel()).addRow(row);
            }

            @Override
            public void onDownloadStart(HClientEvent evt)
            {
               BRefresh.setEnabled(false);
            }

            @Override
            public void onDownloadFinish(HClientEvent evt)
            {
                BRefresh.setEnabled(true);
            }
            
            
        });
        downloader.start();
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

        BUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TChannels = new javax.swing.JTable();
        TFFilter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BRefresh = new javax.swing.JButton();

        BUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/update.png"))); // NOI18N
        BUpdate.setBorderPainted(false);
        BUpdate.setContentAreaFilled(false);
        BUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BUpdate.setMaximumSize(new java.awt.Dimension(24, 19));
        BUpdate.setMinimumSize(new java.awt.Dimension(24, 19));
        BUpdate.setPreferredSize(new java.awt.Dimension(24, 19));
        BUpdate.setRequestFocusEnabled(false);
        BUpdate.setRolloverEnabled(false);

        TChannels.setAutoCreateRowSorter(true);
        TChannels.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        TChannels.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Id", "Name", "Topic", "Language", "Users"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        TChannels.setRowHeight(25);
        jScrollPane1.setViewportView(TChannels);
        if (TChannels.getColumnModel().getColumnCount() > 0)
        {
            TChannels.getColumnModel().getColumn(0).setMinWidth(0);
            TChannels.getColumnModel().getColumn(0).setPreferredWidth(0);
            TChannels.getColumnModel().getColumn(0).setMaxWidth(0);
            TChannels.getColumnModel().getColumn(1).setMinWidth(260);
            TChannels.getColumnModel().getColumn(1).setPreferredWidth(260);
            TChannels.getColumnModel().getColumn(1).setMaxWidth(260);
            TChannels.getColumnModel().getColumn(2).setCellRenderer(new TopicRenderer());
            TChannels.getColumnModel().getColumn(3).setMinWidth(80);
            TChannels.getColumnModel().getColumn(3).setPreferredWidth(80);
            TChannels.getColumnModel().getColumn(3).setMaxWidth(80);
            TChannels.getColumnModel().getColumn(4).setMinWidth(50);
            TChannels.getColumnModel().getColumn(4).setPreferredWidth(50);
            TChannels.getColumnModel().getColumn(4).setMaxWidth(50);
        }

        TFFilter.setMaximumSize(new java.awt.Dimension(340, 24));
        TFFilter.setMinimumSize(new java.awt.Dimension(340, 24));
        TFFilter.setPreferredSize(new java.awt.Dimension(340, 24));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/filter.png"))); // NOI18N

        BRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/refresh.png"))); // NOI18N
        BRefresh.setBorderPainted(false);
        BRefresh.setContentAreaFilled(false);
        BRefresh.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TFFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(TFFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                    .addComponent(BRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BRefreshActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BRefreshActionPerformed
    {//GEN-HEADEREND:event_BRefreshActionPerformed
        downloader.restart();
        ((DefaultTableModel) TChannels.getModel()).setRowCount(0);
        downloader.start();
    }//GEN-LAST:event_BRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BRefresh;
    private javax.swing.JButton BUpdate;
    private javax.swing.JTable TChannels;
    private javax.swing.JTextField TFFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
