package AdvDatabaseSrc;

import com.sun.rowset.JdbcRowSetImpl;
import javax.swing.JOptionPane;
import utilities.Books;


public class TestRowSetWithPreparedStatement extends javax.swing.JFrame {

    /** Creates new form TestRowSetWithPreparedStatement */
    public TestRowSetWithPreparedStatement() {
        initComponents();

        try {

            Books.resetTableInDB();

            JdbcRowSetImpl rowSet = new JdbcRowSetImpl();

            rowSet.setUrl("jdbc:mysql://localhost:3306/books");
            rowSet.setUsername("sd3user");
            rowSet.setPassword("pass");
            rowSet.setCommand("SELECT * FROM authors where lastname = ? and YearBorn < ?");
            rowSet.setString(1, "Deitel");
            rowSet.setInt(2, 1960);

            rowSet.execute();

            String results ="";

            while(rowSet.next()) {

                results +=rowSet.getString(1) + "\t" + //output AuthorID
                rowSet.getString(2) + "\t" +    //output Author FName
                rowSet.getString(3) + "\t" +    //output Author LName
                rowSet.getString(4)  + "\n";    //output Author YearBorn

            }

	    textArea.setText(results);

	}//end try

	catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            System.exit(0);
	}
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RowSets and Prepared Statements");

        textArea.setColumns(40);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestRowSetWithPreparedStatement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

}
