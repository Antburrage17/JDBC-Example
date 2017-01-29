package AdvDatabaseSrc;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TestDB extends javax.swing.JFrame
{
    
    JdbcRowSetImpl rowSet;
    
    String username = "sduser";
    String databaseURI = "jdbc:mysql://localhost:3306/iseq";
    String password = "pass";
    Connection connection;
    
    private void setupRowset() throws SQLException
    {
        
        rowSet = new JdbcRowSetImpl();
        
        rowSet.setUrl(databaseURI);
        rowSet.setUsername(username);
        rowSet.setPassword(password);
        
    }
    
    private void ConnectToDatabase() throws SQLException
    {
        connection = DriverManager.getConnection(databaseURI, username, password);
        
    }
    
    public TestDB() throws SQLException
    {
        this.ConnectToDatabase();
        
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel = new javax.swing.JPanel();
        alphaButton = new javax.swing.JButton();
        priceIncrButton = new javax.swing.JButton();
        spButton = new javax.swing.JButton();
        batchButton = new javax.swing.JButton();
        outputPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        controlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Control Panel"));

        alphaButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        alphaButton.setText("Display Companys Alphabetical Order");
        alphaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alphaButtonActionPerformed(evt);
            }
        });
        controlPanel.add(alphaButton);

        priceIncrButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        priceIncrButton.setText("Display Companys By Price Increase");
        priceIncrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceIncrButtonActionPerformed(evt);
            }
        });
        controlPanel.add(priceIncrButton);

        spButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        spButton.setText("Call Stored Procedure");
        spButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spButtonActionPerformed(evt);
            }
        });
        controlPanel.add(spButton);

        batchButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        batchButton.setText("Call Batch Statements");
        batchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchButtonActionPerformed(evt);
            }
        });
        controlPanel.add(batchButton);

        getContentPane().add(controlPanel, java.awt.BorderLayout.NORTH);

        outputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Output"));
        outputPanel.setLayout(new java.awt.BorderLayout());

        output.setColumns(20);
        output.setEditable(false);
        output.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        output.setRows(5);
        jScrollPane1.setViewportView(output);

        outputPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(outputPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void spButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spButtonActionPerformed
        try
        {
            this.callStoredProcedure();
        } catch (SQLException ex)
        {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_spButtonActionPerformed

    private void alphaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alphaButtonActionPerformed
        this.alphaButtonFuntion();
    }//GEN-LAST:event_alphaButtonActionPerformed

    private void priceIncrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceIncrButtonActionPerformed
        this.priceIncrButtonFunction();

    }//GEN-LAST:event_priceIncrButtonActionPerformed

    private void batchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchButtonActionPerformed
        try
        {
            this.batchFunction();
        } catch (SQLException ex)
        {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_batchButtonActionPerformed

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
            java.util.logging.Logger.getLogger(TestDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(TestDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(TestDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(TestDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            
            public void run()
            {
                try
                {
                    new TestDB().setVisible(true);
                } catch (SQLException ex)
                {
                    Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alphaButton;
    private javax.swing.JButton batchButton;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea output;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JButton priceIncrButton;
    private javax.swing.JButton spButton;
    // End of variables declaration//GEN-END:variables

    private String createTextforRecordHeadings(ResultSet resultSet) throws SQLException
    {
        String text = "";
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        
        for (int i = 1; i <= numberOfColumns; i++)
        {
            text += metaData.getColumnName(i) + "\t";
        }
        return text;
    }
    
    private String createTextforRecords(ResultSet resultSet) throws SQLException
    {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        
        String text = "";
        
        while (resultSet.next())
        {
            for (int i = 1; i <= numberOfColumns; i++)
            {
                text += resultSet.getObject(i) + "\t";
            }//end for

            text += "\n";
        }//end while
        return text;
    }
    
    private void alphaButtonFuntion()
    {
        try
        {
            this.setupRowset();
            rowSet.setCommand("SELECT companynum,\n"
                    + "company, \n"
                    + "Current_Price, \n"
                    + "Yesterdays_Price, \n"
                    + "Shares_Traded_Yesterday, \n"
                    + "Yearly_Low, \n"
                    + "Yearly_High,\n"
                    + "Listing_Date\n"
                    + "FROM iseq.prices\n"
                    + "ORDER BY company ASC;");
            rowSet.execute();
            
            String headings = this.createTextforRecordHeadings(rowSet);
            String results = this.createTextforRecords(rowSet);
            
            rowSet.close();
            
            output.setText(headings + "\n" + results);
            
        }//end try
        catch (SQLException sqlex)
        {
            JOptionPane.showMessageDialog(null, sqlex.toString());
            System.exit(0);
        }
    }
    
    private void callStoredProcedure() throws SQLException
    {
        CallableStatement callableStatement = connection.prepareCall("{call getCompanybyID(?)}");
        
        String agentID_ = JOptionPane.showInputDialog("Enter ID of agent");
        int agentID = Integer.parseInt(agentID_);
        
        callableStatement.setInt(1, agentID);
        
        ResultSet resultSet = callableStatement.executeQuery();
        
        String headings = this.createTextforRecordHeadings(resultSet);
        String results = this.createTextforRecords(resultSet);
        
        resultSet.close();
        
        output.setText(headings + "\n" + results);
    }
    
    private void priceIncrButtonFunction()
    {
        try
        {
            this.setupRowset();
            rowSet.setCommand("SELECT companynum,\n"
                    + "company, \n"
                    + "Current_Price, \n"
                    + "Yesterdays_Price, \n"
                    + "Shares_Traded_Yesterday, \n"
                    + "Yearly_Low, \n"
                    + "Yearly_High,\n"
                    + "(Current_Price - Yesterdays_Price) AS Price_Difference,"
                    + "Listing_Date\n"
                    + "FROM iseq.prices\n"
                    + "ORDER BY  (Current_Price - Yesterdays_Price) DESC;");
            rowSet.execute();
            
            String headings = this.createTextforRecordHeadings(rowSet);
            String results = this.createTextforRecords(rowSet);
            
            rowSet.close();
            
            output.setText(headings + "\n" + results);
            
        }//end try
        catch (SQLException sqlex)
        {
            JOptionPane.showMessageDialog(null, sqlex.toString());
            System.exit(0);
        }
    }
    
    private void batchFunction() throws SQLException
    {
        Statement statement = connection.createStatement();
        String updateSqlMult = "UPDATE iseq.prices\n"
                + "SET\n"
                + "Current_Price = (Current_Price * 1.1)\n"
                // + "WHERE companynum = 34";
                + "WHERE (Current_Price - Yesterdays_Price) > 30";
        
        statement.addBatch(updateSqlMult);
        
        int[] recordsAffected = statement.executeBatch();
        System.out.println("Number of records updated = "  + recordsAffected.length);
        
    }
}
