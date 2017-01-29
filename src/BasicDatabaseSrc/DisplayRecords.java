package BasicDatabaseSrc;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DisplayRecords extends javax.swing.JFrame
{

        String username = "sduser";
    String databaseURI = "jdbc:mysql://localhost:3306/books";
    String password = "pass";

    /**
     * Creates new form DisplayRecords
     */
    public DisplayRecords()
    {
        initComponents();

        try
        {

            //create the connection object
            //ATTN: username and password must be changed depending on the settings on your database server
            Connection connection = DriverManager.getConnection(databaseURI, username, password);
        
            //create a statement object.
            //We will use this object to carry our query to the database
            Statement statement = connection.createStatement();

            //exexute our query, which will lead to the return of a resultset
           // ResultSet resultSet = statement.executeQuery("SELECT * FROM titles");
            //ResultSet resultSet = statement.executeQuery("SELECT Title, YearPublished FROM titles WHERE YearPublished < 1998 ORDER BY title ASC");
            ResultSet resultSet = statement.executeQuery("SELECT Title, YearPublished FROM titles WHERE (YearPublished <= 1998) AND (YearPublished >= 1994)");
           // ResultSet resultSet = statement.executeQuery("SELECT COUNT(Title)FROM titles WHERE PublisherID = 1");
            //ResultSet resultSet = statement.executeQuery("SELECT AVG (Price) FROM titles");

            String headings = this.createTextforRecordHeadings(resultSet);
            String results = this.createTextforRecords(resultSet);


            statement.close();
            connection.close();

            textArea.setText(headings + "\n" + results);

        }//end try
        catch (SQLException sqlex)
        {
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
        setTitle("Display Records From Books Database");
        setMinimumSize(new java.awt.Dimension(250, 150));

        textArea.setColumns(40);
        textArea.setEditable(false);
        textArea.setRows(10);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new DisplayRecords().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
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
        return  text;
    }
}

