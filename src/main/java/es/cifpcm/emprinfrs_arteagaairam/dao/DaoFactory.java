package es.cifpcm.emprinfrs_arteagaairam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Airam
 */
public class DaoFactory {

    private static DaoFactory instance;
    protected DaoConfig dfc;

    // METODO AGREGADO PARA LA API REST
    public DaoFactory() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        try {
            this.dfc = new DaoConfig(resourceBundle.getString("database.driver"), resourceBundle.getString("database.url"), resourceBundle.getString("database.user"), resourceBundle.getString("database.password"));
            DaoFactory.createInstance(dfc);
        } catch (Exception e) {
            Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, "Error en DaoFactory: {0}", e);
        }
    }

    protected DaoFactory(DaoConfig dfc) {
        this.dfc = new DaoConfig(dfc);
        try {
            Class.forName(dfc.getDriverClassName());
        } catch (ClassNotFoundException e) {
            Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, "Error en DaoFactory(DaoConfig dfc): {0}", e);
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(dfc.getDatabaseUrl(), dfc.getDatabaseUser(), dfc.getDatabasePassword());
        } catch (SQLException e) {
            Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, "Error en getConnection: {0}", e);
            return null;
        }
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, "Error en closeConnection: {0}", e);
            }
        }
    }

    // LE ENVIA ESTE DAOFACTORY AL TIENDADAOIMPL
    public DiscoDao readDiscoDao() {
        return new DiscoDaoImpl(this);
    }

    // EL MÉTODO ES SYNCHRONIZED, NO PUEDEN ENTRAR DOS HILOS A LA VEZ
    // SE ASEGURA QUE SÓLO SE CREA UNA INSTANCIA DE LA DAOFACTORY
    public static synchronized DaoFactory createInstance(DaoConfig dfc) {
        if (instance == null) {
            instance = new DaoFactory(dfc);
        }
        return instance;
    }

    public static DaoFactory getInstance() {
        return instance;
    }

}
