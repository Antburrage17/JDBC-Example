package BasicDatabaseSrc;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class InsertAndDisplay extends javax.swing.JFrame {

    /** Creates new form DisplayRecords */
    public InsertAndDisplay() {
        initComponents();

        try {

              //create the connection object
              //ATTN: username and password must be changed depending on the settings on your database server
              Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "sduser", "pass");

	      //create a statement object.
	      //We will use this object to carry our query to the database
	      Statement statement = connection.createStatement();

              String insertSQL = "INSERT INTO authors(AuthorID, FirstName, LastName, YearBorn)VALUES ('4', 'Gerry', 'Guinane', '1933')";
              int rowCount = statement.executeUpdate(insertSQL);

              textArea.setText(rowCount + " row inserted\n");

   	      //exexute our query, which will lead to the return of a resultset
	      ResultSet resultSet = statement.executeQuery("SELECT * FROM authors");

	      String results="";

	      ResultSetMetaData metaData =  resultSet.getMetaData();

	      int numberOfColumns = metaData.getColumnCount();

	      for (int i =1; i < numberOfColumns; i++) {
				results += metaData.getColumnName(i) + "\t";
	      }

	     results +="\n";

	     while(resultSet.next()) {
		for (int i=1;  i < numberOfColumns; i++){
                    results +=resultSet.getObject(i) + "\t";
		}//end for

                results+= "\n";
            }//end while

	    statement.close();
            connection.close();

	     textArea.append(results);

	}//end try

	catch(SQLException sqlex) {
            JOptionPane.showMessageDialog(null, sqlex.toString());
            System.exit(0);
	}
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Insert Record Into Books Database");

        textArea.setColumns(40);
        textArea.setEditable(false);
        textArea.setRows(10);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertAndDisplay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

}
