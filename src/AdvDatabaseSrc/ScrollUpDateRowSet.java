package AdvDatabaseSrc;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.Books;

public class ScrollUpDateRowSet extends javax.swing.JFrame {

    String results;

    public ScrollUpDateRowSet() throws ClassNotFoundException, SQLException {
        initComponents();

        Books.resetTableInDB();

        

         JdbcRowSetImpl rowSet = new JdbcRowSetImpl();

         rowSet.setUrl("jdbc:mysql://localhost:3306/books");
         rowSet.setUsername("sduser");
         rowSet.setPassword("pass");
         rowSet.setCommand("SELECT * FROM authors");
         rowSet.execute();

         displayDataBaseContents(rowSet, "Before update: Display All Records");

         //////////////////////////////////////////////////////////////////

         //position cursor on second row and delete it
         rowSet.absolute(2);//asigns row
         rowSet.deleteRow();//deletes row
         rowSet.beforeFirst();//returns to first otherwise it wont display the first.. 
         displayDataBaseContents(rowSet, "\n\nAfter delete: Display All Records");

         //////////////////////////////////////////////////////////////////


         //position the cursor to the first row and update the FirstName of the author to Rex
         rowSet.absolute(1);
         rowSet.updateString("FirstName", "Rex");
         rowSet.updateRow();
         rowSet.beforeFirst();

         displayDataBaseContents(rowSet, "\n\nAfter update: Display All Records");

         //////////////////////////////////////////////////////////////////
         //move the cursor to the end of the rowset and insert a new row
         rowSet.moveToInsertRow();
         rowSet.updateInt("AuthorID", 14);
         rowSet.updateString("FirstName", "Bertie");
         rowSet.updateString("LastName", "Ahern");
         rowSet.updateInt("YearBorn",1964 );
         rowSet.insertRow();
         rowSet.beforeFirst();

         displayDataBaseContents(rowSet, "\n\nAfter insertion: Display All Records");

         //////////////////////////////////////////////////////////////////
         rowSet.close();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Testing ScrollUpDateRowSet");

        textArea.setColumns(40);
        textArea.setRows(40);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ScrollUpDateRowSet().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ScrollUpDateRowSet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ScrollUpDateRowSet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

   public void displayDataBaseContents(JdbcRowSetImpl rowSet, String message) throws SQLException {

        results += message + "\n";
        while(rowSet.next()) {
                results +=rowSet.getString(1) + "\t" + //output AuthorID
                rowSet.getString(2) + "\t" +    //output Author FName
                rowSet.getString(3) + "\t" +    //output Author LName
                rowSet.getString(4)  + "\n";    //output Author YearBorn
         }

	    textArea.setText(results);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

}
