package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alan.ryan
 */
public class InsertForm extends javax.swing.JFrame {

    JFrame mainFrame;
    ResultSet resultSet;
    
    public InsertForm(JFrame mainFrame, ResultSet resultSet) {

        this.mainFrame = mainFrame;
        this.resultSet = resultSet;
        
        //setup GUI
        initComponents();
        

    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        recordsPanel = new javax.swing.JPanel();
        authorIDLabel = new javax.swing.JLabel();
        authorIDTextfield = new javax.swing.JTextField();
        fnameLabel = new javax.swing.JLabel();
        fnameTextField = new javax.swing.JTextField();
        lnameLabel = new javax.swing.JLabel();
        lnameTextfield = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        insertButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Insert A Record");
        setMinimumSize(new java.awt.Dimension(250, 150));

        recordsPanel.setLayout(new java.awt.GridLayout(3, 2, 5, 5));

        authorIDLabel.setText("Author ID");
        recordsPanel.add(authorIDLabel);
        recordsPanel.add(authorIDTextfield);

        fnameLabel.setText("First Name");
        recordsPanel.add(fnameLabel);
        recordsPanel.add(fnameTextField);

        lnameLabel.setText("Last Name");
        recordsPanel.add(lnameLabel);
        recordsPanel.add(lnameTextfield);

        getContentPane().add(recordsPanel, java.awt.BorderLayout.CENTER);

        insertButton.setText("Insert");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });
        jPanel1.add(insertButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel1.add(cancelButton);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(590, 290));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
        mainFrame.setVisible(true);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed

      try {

          resultSet.moveToInsertRow();
          resultSet.updateInt("AuthorID", Integer.parseInt(authorIDTextfield.getText()));
          resultSet.updateString("FirstName", fnameTextField.getText());
          resultSet.updateString("LastName", lnameTextfield.getText());
          
          resultSet.insertRow();
             
          JOptionPane.showMessageDialog(null, "Record Inserted");
          this.setVisible(false);
          mainFrame.setVisible(true);
              
             
      } //end try
      catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
      }//end catch
      
      
      
    }//GEN-LAST:event_insertButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorIDLabel;
    private javax.swing.JTextField authorIDTextfield;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel fnameLabel;
    private javax.swing.JTextField fnameTextField;
    private javax.swing.JButton insertButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lnameLabel;
    private javax.swing.JTextField lnameTextfield;
    private javax.swing.JPanel recordsPanel;
    // End of variables declaration//GEN-END:variables
}
