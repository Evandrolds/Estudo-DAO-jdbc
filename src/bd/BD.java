package bd;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author Evandro
 */
public class BD {

    // metodos para gerar conexão com Banco de Dados
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loaderProperties();
                String path = props.getProperty("dburl");
                conn = DriverManager.getConnection(path,props);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return conn;
    }

    // metodo para garregar as propriedades do arquivo bd.properties
    private static Properties loaderProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException ex) {
            throw new Bd_Exception(ex.getMessage());
        }
    }
    // fechando a conexão

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException s) {
                throw new Bd_Exception("Error, connection isn't closing: " + s.getMessage());
            }
        }
    }
    // fechando a Statement

    public static void coloseStatement(Statement st) {
        if (st != null) {

            try {
                st.close();
            } catch (SQLException ex) {

                throw new Bd_Exception(ex.getMessage());
            }

        }

    }
    // fechando a ResultSet

    public static void coloseResulset(ResultSet rs) {
        if (rs != null) {

            try {
                rs.close();
            } catch (SQLException ex) {

                throw new Bd_Exception(ex.getMessage());
            }

        }

    }
}
