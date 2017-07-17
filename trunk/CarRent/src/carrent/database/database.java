
package carrent.database;

import java.sql.Connection;

public interface database {
        public Connection conectar();
        public void desconectar(Connection conn);       
}
