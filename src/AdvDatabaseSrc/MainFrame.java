package AdvDatabaseSrc;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class MainFrame extends javax.swing.JFrame
{

    JdbcRowSetImpl rowSet;

    String username = "sduser";
    String databaseURI = "jdbc:mysql://localhost:3306/litrealty";
    String password = "pass";

    DecimalFormat decimalFormat;

    private void setupDecimalformat()
    {
        String mask = "€#.00";
        decimalFormat = new DecimalFormat(mask);
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
    }

    private void setupRowset() throws SQLException
    {

        rowSet = new JdbcRowSetImpl();

        rowSet.setUrl(databaseURI);
        rowSet.setUsername(username);
        rowSet.setPassword(password);

    }
//////////////////////////////////////////////////////////////////////////

    private void getAgents() throws SQLException
    {
        rowSet.setCommand("SELECT name FROM agents");
        rowSet.execute();
        ///
        //  populates the gui with data 
        //
        this.agentsComboBox.removeAllItems();
        while (rowSet.next())
        {
            this.agentsComboBox.addItem(rowSet.getString(1));
        }

    }
//////////////////////////////////////////////////////////////////////////

    private void getPropertyPrice() throws SQLException
    {
        rowSet.setCommand("SELECT SUM(price) FROM properties");
        rowSet.execute();
        double totalPrice = 0;
        if (rowSet.next())
        {
            totalPrice = rowSet.getDouble(1);
        }
        JOptionPane.showMessageDialog(null, this.decimalFormat.format(totalPrice));
    }
/////////////////////////////////////////////////////////////////////////



    private void getPropertyDetails() throws SQLException
    {
        rowSet.setCommand("SELECT properties.id,\n"
                + "properties.street, \n"
                + "properties.city, \n"
                + "properties.description, \n"
                + "properties.price,\n"
                + "agents.name,\n"
                + "agents.phone\n"
                + "FROM properties, agents WHERE agents.agentID = properties.agentID ORDER BY properties.id ;");
        rowSet.execute();
        String results = this.createTextforRecords(rowSet);

        JTextArea jta = new JTextArea(5, 5);
        jta.setText(results);
        JOptionPane.showMessageDialog(null, jta);

    }
    private String createTextforRecords(JdbcRowSetImpl myRowSet) throws SQLException
    {
        ResultSetMetaData metaData = myRowSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        String text = "";
        while (myRowSet.next())
        {
            text += this.createTextforRecord(myRowSet, numberOfColumns);
            text += "\n";
        }//end while
        return text;
    }
    private String createTextforRecord(JdbcRowSetImpl myRowSet, int numberOfColumns) throws SQLException
    {
        String text = "";
        for (int i = 1; i <= numberOfColumns; i++)
        {
            text += myRowSet.getObject(i) + "\t";
        }
        return text;
    }
    /////////////////////////////////////////////////////////////////////////////////
    private void getInsertFunction() throws SQLException
    {

        rowSet.setCommand("SELECT * FROM properties");
        rowSet.execute();

        String agentID_ = JOptionPane.showInputDialog("Enter ID of agent");
        String street = JOptionPane.showInputDialog("Enter Street Name");
        String city = JOptionPane.showInputDialog("Enter City Name");
        String bed = JOptionPane.showInputDialog("Enter Number of Bedrooms");
        String bath = JOptionPane.showInputDialog("Enter Number of Bathrooms");
        String description = JOptionPane.showInputDialog("Enter Description");
        String price = JOptionPane.showInputDialog("Enter Price");

        int agentID = Integer.parseInt(agentID_);
        int NumBed = Integer.parseInt(bed);
        int NumBath = Integer.parseInt(bath);
        int priceAmount = Integer.parseInt(price);

        rowSet.moveToInsertRow();
        rowSet.updateInt("agentID", agentID);
        rowSet.updateString("street", street);
        rowSet.updateString("city", city);
        rowSet.updateInt("bedrooms", NumBed);
        rowSet.updateInt("bathrooms", NumBath);
        rowSet.updateString("description", description);
        rowSet.updateInt("price", priceAmount);
        rowSet.insertRow();

        System.out.println("You Have Updated Data");
        System.exit(0);
        rowSet.close();

    }
///////////////////////////////////////////////////////////////////////////////////////

    public MainFrame()
    {
        initComponents();
        try
        {
            this.setupDecimalformat();
            this.setupRowset();
            this.getAgents();

        } catch (SQLException ex)
        {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        agentsComboBox = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(4, 1, 10, 10));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Agents"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        agentsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Agent 1", "Agent 2", "Agent 3", "Agent 4" }));
        agentsComboBox.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                agentsComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(agentsComboBox, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Total House Price"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jButton3.setText("Display Total Price Of All Houses");
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Print Details"));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jButton1.setText("Print Property Details");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Do Insert"));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jButton2.setText("Do Insert");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try
        {
            this.getPropertyDetails();
        } catch (SQLException ex)
        {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void agentsComboBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_agentsComboBoxActionPerformed
    {//GEN-HEADEREND:event_agentsComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agentsComboBoxActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
        try
        {
            this.getPropertyPrice();
        } catch (SQLException ex)
        {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        try
        {
            this.getInsertFunction();
        } catch (SQLException ex)
        {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox agentsComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
