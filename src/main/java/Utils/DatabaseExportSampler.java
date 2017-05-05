package Utils;

import com.mysql.cj.jdbc.MysqlDataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;


/**
 * Created by ms on 04-05-17.
 */
public class DatabaseExportSampler {
    public static void main(String[] args) throws Exception {

        // database connection
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/shoptest");
        dataSource.setUser("root");
        dataSource.setPassword("pwd");
        dataSource.setDatabaseName("shoptest");
        IDatabaseConnection connection = new DatabaseConnection(dataSource.getConnection());
      //  connection.getConfig().setProperty(DatabaseConfig.PROPERTY_ESCAPE_PATTERN , "[?]");

        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);

        partialDataSet.addTable("customer");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

        // full database export
     //   IDataSet fullDataSet = connection.createDataSet();
      //  FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));

        // dependent tables database export: export table X and all tables that
        // have a PK which is a FK on X, in the right order for insertion
//        String[] depTableNames =
//                TablesDependencyHelper.getAllDependentTables( connection, "X" );
//        IDataSet depDataset = connection.createDataSet( depTableNames );
//        FlatXmlDataSet.write(depDataset, new FileOutputStream("dependents.xml"));

    }
}
