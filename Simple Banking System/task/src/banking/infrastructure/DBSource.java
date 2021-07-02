package banking.infrastructure;

import banking.ui.UserInterface;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBSource {
    public Connection getConnection() throws SQLException;

    public void setLoggingInterface(UserInterface userInterface);
}
