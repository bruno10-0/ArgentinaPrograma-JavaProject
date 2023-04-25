package com.mycompany.tpARG;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class DatabaseConnectionTest {

    @Test
    public void testConnection() {
        try {
            Connection connection = Database.getConnection();
            assertNotNull(connection, "La conexión a la base de datos no debe ser nula");
            connection.close();
        } catch (SQLException e) {
            fail("Error al conectarse a la base de datos: " + e.getMessage());
        }
    }
}
