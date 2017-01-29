package src;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public final class NavigateRecords extends javax.swing.JFrame {
    
    ResultSet resultSet;
    Connection connection;
    Statement statement;
    
    public NavigateRecords() {
        initComponents();
        

        try {

              //create the connection object
              //ATTN: username and password must be changed depending on the settings on your database server
              connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "sd3user", "pass");

              //create a statement object.
	      //We will use this object to carry our query to the database
	     statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);

              //exexute our query, which will lead to the return of a resultset
	      resultSet = statement.executeQuery("SELECT * FROM authors");

              if (resultSet.next()) {
                loadRecord();
              } //end if
      
              else {
                JOptionPane.showMessageDialog(null, "There are no records in the database");
              } //end else
        }//end try

	catch(SQLException sqlex) {
            JOptionPane.showMessageDialog(null, sqlex.toString());
            System.exit(0);
	}
    }
    
    public void loadRecord() {
        
        try  {
            String authorsID = resultSet.getObject(1).toString();
            String authorsFirstName = resultSet.getObject(2).toString();
            String authorsSecondName = resultSet.getObject(3).toString();

            authorIDTextfield.setText(authorsID);
            fnameTextField.setText(authorsFirstName);
            lnameTextfield.setText(authorsSecondName);    
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }
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
        controlPanel = new javax.swing.JPanel();
        firstButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        lastButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        insertButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Display Records From Books Database");
        setMinimumSize(new java.awt.Dimension(250, 150));

        recordsPanel.setLayout(new java.awt.GridLayout(3, 2, 5, 5));

        authorIDLabel.setText("Author ID");
        recordsPanel.add(authorIDLabel);

        authorIDTextfield.setEditable(false);
        authorIDTextfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorIDTextfieldMouseClicked(evt);
            }
        });
        recordsPanel.add(authorIDTextfield);

        fnameLabel.setText("First Name");
        recordsPanel.add(fnameLabel);

        fnameTextField.setEditable(false);
        fnameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fnameTextFieldMouseClicked(evt);
            }
        });
        fnameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameTextFieldActionPerformed(evt);
            }
        });
        recordsPanel.add(fnameTextField);

        lnameLabel.setText("Last Name");
        recordsPanel.add(lnameLabel);

        lnameTextfield.setEditable(false);
        lnameTextfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnameTextfieldMouseClicked(evt);
            }
        });
        recordsPanel.add(lnameTextfield);

        getContentPane().add(recordsPanel, java.awt.BorderLayout.CENTER);

        firstButton.setText("First");
        firstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstButtonActionPerformed(evt);
            }
        });
        controlPanel.add(firstButton);

        nextButton.setText("Next >>");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        controlPanel.add(nextButton);

        previousButton.setText("<< Previous");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });
        controlPanel.add(previousButton);

        lastButton.setText("Last");
        lastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastButtonActionPerformed(evt);
            }
        });
        controlPanel.add(lastButton);

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        controlPanel.add(updateButton);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        controlPanel.add(deleteButton);

        insertButton.setText("Insert");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });
        controlPanel.add(insertButton);

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        controlPanel.add(exitButton);

        getContentPane().add(controlPanel, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(710, 338));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        try  {
            if (resultSet.next()) {
                loadRecord();
            }
            else {
                JOptionPane.showMessageDialog(null, "You have reached the end of the list");
                resultSet.last();
            }
        }//end try
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }//end catch
    }//GEN-LAST:event_nextButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        try  {
            if (resultSet.previous()) {
                loadRecord();
            }
            else {
                JOptionPane.showMessageDialog(null, "You have reached the start of the list");
                resultSet.first();
            }
        }//end try
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }//end catch
    }//GEN-LAST:event_previousButtonActionPerformed

    private void firstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstButtonActionPerformed
        try {
            resultSet.first();
            loadRecord();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "ERROR" + ex);
        }
    }//GEN-LAST:event_firstButtonActionPerformed

    private void lastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastButtonActionPerformed
       try {
            resultSet.last();
            loadRecord();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR" + ex);
        }
    }//GEN-LAST:event_lastButtonActionPerformed

    private void authorIDTextfieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authorIDTextfieldMouseClicked
        authorIDTextfield.setEditable(true);
    }//GEN-LAST:event_authorIDTextfieldMouseClicked

    private void fnameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameTextFieldActionPerformed
        fnameTextField.setEditable(true);
    }//GEN-LAST:event_fnameTextFieldActionPerformed

    private void fnameTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fnameTextFieldMouseClicked
        fnameTextField.setEditable(true);
    }//GEN-LAST:event_fnameTextFieldMouseClicked

    private void lnameTextfieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnameTextfieldMouseClicked
        lnameTextfield.setEditable(true);
    }//GEN-LAST:event_lnameTextfieldMouseClicked

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
   
       lnameTextfield.setEditable(false);
       fnameTextField.setEditable(false);
       authorIDTextfield.setEditable(false);

       try {
               //update the individual fields in the resultset on the current row
               resultSet.updateInt("AuthorID", Integer.parseInt(authorIDTextfield.getText()));
               resultSet.updateString("FirstName", fnameTextField.getText());
               resultSet.updateString("LastName", lnameTextfield.getText());

               //update the underlying db
               resultSet.updateRow();
            
       }//end try
            
       catch (SQLException ex) {
                
                JOptionPane.showMessageDialog(null, ex.toString());
            
       }//end catch
            
    
       JOptionPane.showMessageDialog(null, "Record was updated");

    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
       int choice = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Delete This Record","Books DB", JOptionPane.YES_NO_OPTION);
     
       if (choice == JOptionPane.NO_OPTION) 
           JOptionPane.showMessageDialog(null, "The Record Has NOT Been Deleted");

       else {
           try {
               
               resultSet.deleteRow();
               
               JOptionPane.showMessageDialog(null,"The record has been deleted from the database");
               
               if (!resultSet.next())
                   resultSet.first();
                           
               loadRecord();
               
         }//end try           
           catch (SQLException | HeadlessException ex) {
               JOptionPane.showMessageDialog(null, ex.toString());
           }//end catch
          
           
       }//end else
           
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
    
        setVisible(false);
        
        InsertForm insertForm = new InsertForm(this, resultSet);
        
        insertForm.setVisible(true);
   
    }//GEN-LAST:event_insertButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NavigateRecords().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorIDLabel;
    private javax.swing.JTextField authorIDTextfield;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton firstButton;
    private javax.swing.JLabel fnameLabel;
    private javax.swing.JTextField fnameTextField;
    private javax.swing.JButton insertButton;
    private javax.swing.JButton lastButton;
    private javax.swing.JLabel lnameLabel;
    private javax.swing.JTextField lnameTextfield;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JPanel recordsPanel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

}
