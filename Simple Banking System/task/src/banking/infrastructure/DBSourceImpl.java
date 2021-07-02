package banking.infrastructure;

import banking.ui.UserInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSourceImpl implements DBSource {

    private static DBSource instance = null;

    private String dbFileName = null;

    private UserInterface userInterface = null;

    private DBSourceImpl(String fileName, UserInterface userInterface) {
        this.dbFileName = fileName;
        this.userInterface = userInterface;
        this.initializeDatabase();
    }

    public static DBSource getInstance(String dbFileName, UserInterface userInterface) {
        if (DBSourceImpl.instance == null) {
            DBSourceImpl.instance = new DBSourceImpl(dbFileName, userInterface);
        }
        return DBSourceImpl.instance;
    }

    public static DBSource getInstance() throws Exception {
        if (DBSourceImpl.instance == null) {
            throw new Exception("File name is not provided");
        }
        return DBSourceImpl.instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.getConnectionUri());
    }

    private String getConnectionUri() {
        return "jdbc:sqlite:" + this.dbFileName;
    }

    private void initializeDatabase() {
        String cardsTableSQL = "CREATE TABLE IF NOT EXISTS " + Table.CARD.value() + " ("
                + "id integer PRIMARY KEY,"
                + "number text NOT NULL,"
                + "pin text NOT NULL,"
                + "balance integer DEFAULT 0"
                + ");";

        try (Connection connection = this.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(cardsTableSQL);
            this.userInterface.println("Database initialized.");
        } catch (SQLException exception) {

            if (this.userInterface != null) {
                this.userInterface.println(exception.getMessage());
            }
        }
    }

    public void setLoggingInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
}
