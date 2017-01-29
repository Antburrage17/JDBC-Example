package BasicDatabaseSrc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Lecture1 extends javax.swing.JFrame
{

    ResultSet resultset;
    Connection connection;
    Statement statement;

    String username = "sduser";
    String databaseURI = "jdbc:mysql://localhost:3306/books";
    String password = "pass";

    public Lecture1()
    {
        initComponents();

        this.LoadFirstRecordOnStart();

    }

    private void LoadFirstRecordOnStart()
    {
        int number = 0;

        try
        {
            this.ConnectToDatabase();
            this.LoadFirstRecord();
        } catch (SQLException ex)
        {
            Logger.getLogger(Lecture1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ConnectToDatabase() throws SQLException
    {
        connection = DriverManager.getConnection(databaseURI, username, password);
        statement = connection.createStatement();
        resultset = statement.executeQuery("SELECT * FROM authors");
    }

    public final void loadCurrentRecord()
    {
        try
        {
            String ID = resultset.getObject(1).toString();
            String firstname = resultset.getObject(2).toString();
            String lastname = resultset.getObject(3).toString();

            IDField1.setText(ID);
            FirstField1.setText(firstname);
            LastField1.setText(lastname);
        } catch (SQLException sqlex)
        {
        }
    }

    private void LoadPreviousRecord()
    {

        try
        {

            if (resultset.previous())
            {
                this.loadCurrentRecord();
            } else
            {
                JOptionPane.showMessageDialog(null, "You have reached the end of the list");
                resultset.first();
            }

        } catch (SQLException sqlex)
        {

        }
    }

    private void LoadFirstRecord()
    {
        try
        {

            if (resultset.first())
            {
                this.loadCurrentRecord();
            } else
            {
                JOptionPane.showMessageDialog(null, "ERROR NO RECORDS");
            }

        } catch (SQLException sqlex)
        {

        }
    }

    private void LoadNextRecord()
    {
        try
        {

            if (resultset.next())
            {
                this.loadCurrentRecord();

            } else
            {
                JOptionPane.showMessageDialog(null, "You have reached the end of the list");
                resultset.last();
            }

        } catch (SQLException sqlex)
        {
        }
    }

    private void LoadLastRecord()
    {
        try
        {

            if (resultset.last())
            {
                this.loadCurrentRecord();
            }

        } catch (SQLException sqlex)
        {

        }
    }

    private void LoadExit()
    {
        try
        {
            System.exit(0);
            connection.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Lecture1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void DeleteFunction()
    {
        try
        {
            String Id = IDField1.getText();

            int NumID = Integer.parseInt(Id);

            PreparedStatement insertstatment = connection.prepareStatement("DELETE from authors WHERE authorID = ?");
            insertstatment.setInt(1, NumID);

            insertstatment.executeUpdate();

            this.LoadFirstRecordOnStart();
            System.out.println("You Have Deleted Data");

        } catch (Exception ex)
        {

        }
    }

    private void InsertFunction()
    {
        try
        {
            String Id = IDField1.getText();
            String Firstname = FirstField1.getText();
            String Lastname = LastField1.getText();

            int NumID = Integer.parseInt(Id);

            PreparedStatement insertstatment = connection.prepareStatement("INSERT into authors(AuthorID, FirstName, LastName) VALUES (?, ?, ?)");
            insertstatment.setInt(1, NumID);
            insertstatment.setString(2, Firstname);
            insertstatment.setString(3, Lastname);

            insertstatment.executeUpdate();
            this.LoadFirstRecordOnStart();
            System.out.println("An Insertion Has Been Made");

        } catch (Exception ex)
        {

        }
    }

    private void UpdateFunction()
    {
        try
        {
            String Id = IDField1.getText();
            String Firstname = FirstField1.getText();
            String Lastname = LastField1.getText();

            int NumID = Integer.parseInt(Id);

            PreparedStatement insertstatment = connection.prepareStatement("UPDATE authors SET FirstName = ?, LastName = ? WHERE AuthorID = ?");

            insertstatment.setString(1, Firstname);
            insertstatment.setString(2, Lastname);
            insertstatment.setInt(3, NumID);

            insertstatment.executeUpdate();
            this.LoadFirstRecordOnStart();
            System.out.println("Update Has Been Made");

        } catch (Exception ex)
        {

        }
    }

    public static void main(String args[])
    {

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Lecture1().setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        FirstField1 = new javax.swing.JTextField();
        IDField1 = new javax.swing.JTextField();
        AuthorLabel = new javax.swing.JLabel();
        FirstLabel = new javax.swing.JLabel();
        LastField1 = new javax.swing.JTextField();
        LastLabel = new javax.swing.JLabel();
        PreviousButton = new javax.swing.JButton();
        FirstButton1 = new javax.swing.JButton();
        NextButton = new javax.swing.JButton();
        LastButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        UpdateButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        InsertButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FirstField1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                FirstField1MouseClicked(evt);
            }
        });
        FirstField1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                FirstField1ActionPerformed(evt);
            }
        });

        IDField1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                IDField1MouseClicked(evt);
            }
        });
        IDField1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                IDField1ActionPerformed(evt);
            }
        });

        AuthorLabel.setText("Author ID");

        FirstLabel.setText("First Name");

        LastField1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                LastField1MouseClicked(evt);
            }
        });
        LastField1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                LastField1ActionPerformed(evt);
            }
        });

        LastLabel.setText("Last Name");

        PreviousButton.setText("<< Previous");
        PreviousButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                PreviousButtonActionPerformed(evt);
            }
        });

        FirstButton1.setText("First");
        FirstButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                FirstButton1ActionPerformed(evt);
            }
        });

        NextButton.setText("Next >>");
        NextButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                NextButtonActionPerformed(evt);
            }
        });

        LastButton.setText("Last");
        LastButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                LastButtonActionPerformed(evt);
            }
        });

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ExitButtonActionPerformed(evt);
            }
        });

        UpdateButton.setText("Update");
        UpdateButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                UpdateButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                DeleteButtonActionPerformed(evt);
            }
        });

        InsertButton.setText("Insert");
        InsertButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                InsertButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(LastLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LastField1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(FirstButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PreviousButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NextButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LastButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UpdateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DeleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(InsertButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FirstLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AuthorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IDField1)
                            .addComponent(FirstField1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExitButton)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AuthorLabel)
                    .addComponent(IDField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FirstField1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FirstLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LastField1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LastLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PreviousButton)
                    .addComponent(NextButton)
                    .addComponent(LastButton)
                    .addComponent(FirstButton1)
                    .addComponent(InsertButton)
                    .addComponent(UpdateButton)
                    .addComponent(DeleteButton)
                    .addComponent(ExitButton))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IDField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDField1ActionPerformed

    }//GEN-LAST:event_IDField1ActionPerformed

    private void FirstButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstButton1ActionPerformed
        this.LoadFirstRecord();
    }//GEN-LAST:event_FirstButton1ActionPerformed

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        this.LoadNextRecord();
    }//GEN-LAST:event_NextButtonActionPerformed

    private void PreviousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousButtonActionPerformed
        this.LoadPreviousRecord();
    }//GEN-LAST:event_PreviousButtonActionPerformed

    private void LastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastButtonActionPerformed
        this.LoadLastRecord();
    }//GEN-LAST:event_LastButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        this.LoadExit();
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        this.DeleteFunction();
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void InsertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertButtonActionPerformed
        this.InsertFunction();
    }//GEN-LAST:event_InsertButtonActionPerformed

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateButtonActionPerformed
        this.UpdateFunction();
    }//GEN-LAST:event_UpdateButtonActionPerformed

    private void FirstField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FirstField1ActionPerformed

    private void LastField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LastField1ActionPerformed

    private void IDField1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_IDField1MouseClicked
    {//GEN-HEADEREND:event_IDField1MouseClicked
        IDField1.setText("");
    }//GEN-LAST:event_IDField1MouseClicked

    private void FirstField1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_FirstField1MouseClicked
    {//GEN-HEADEREND:event_FirstField1MouseClicked
        FirstField1.setText("");
    }//GEN-LAST:event_FirstField1MouseClicked

    private void LastField1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_LastField1MouseClicked
    {//GEN-HEADEREND:event_LastField1MouseClicked
        LastField1.setText("");
    }//GEN-LAST:event_LastField1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AuthorLabel;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton FirstButton1;
    private javax.swing.JTextField FirstField1;
    private javax.swing.JLabel FirstLabel;
    private javax.swing.JTextField IDField1;
    private javax.swing.JButton InsertButton;
    private javax.swing.JButton LastButton;
    private javax.swing.JTextField LastField1;
    private javax.swing.JLabel LastLabel;
    private javax.swing.JButton NextButton;
    private javax.swing.JButton PreviousButton;
    private javax.swing.JButton UpdateButton;
    // End of variables declaration//GEN-END:variables

}
