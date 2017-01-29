package AdvDatabaseSrc;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TestRowSet extends javax.swing.JFrame {


    public TestRowSet() {
        initComponents();


        try {
           

            JdbcRowSetImpl rowSet = new JdbcRowSetImpl();

            rowSet.setUrl("jdbc:mysql://localhost:3306/books");
            rowSet.setUsername("sduser");
            rowSet.setPassword("pass");
            rowSet.setCommand("SELECT * FROM authors");
            rowSet.execute();

            String results ="";

            while(rowSet.next()) {

                results +=rowSet.getString(1) + "\t" + //output AuthorID
                rowSet.getString(2) + "\t" +    //output Author FName
                rowSet.getString(3) + "\n";    //output Author LName



            }

	    textArea.setText(results);

	}//end try

	catch(SQLException ex) {
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
        setTitle("Working With RowSets");

        textArea.setColumns(40);
        textArea.setRows(10);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestRowSet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

}
