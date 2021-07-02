package banking.repository;

import banking.account.Account;
import banking.account.AccountImpl;
import banking.infrastructure.DBSource;
import banking.infrastructure.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccountRepositoryImpl implements AccountRepository {

    private DBSource dbSource = null;

    public DatabaseAccountRepositoryImpl(DBSource dbSource) {
        this.dbSource = dbSource;
    }

    @Override
    public Account createAccount(Account account) {
        String createAccountSQL = "INSERT INTO " + Table.CARD.value() + " (number, pin, balance) "
                + " VALUES('" + account.getCardNumber() + "', '" + account.getPinCode() + "', " + account.getBalance() + ")";

        try (Connection connection = this.dbSource.getConnection(); Statement statement = connection.createStatement()) {
            int rows = statement.executeUpdate(createAccountSQL);

            if (rows == 0) {
                throw new Exception("Unable to insert values");
            }

        } catch (SQLException sqlException) {
            return null;
        } catch (Exception exception) {
            return null;
        }

        return this.getAccountByNumber(account.getCardNumber());
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        String getAccountSql = "SELECT * FROM " + Table.CARD.value() + " WHERE number = '" + accountNumber + "'";
        try (Connection connection = this.dbSource.getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getAccountSql);

            return this.getAccountByNumberFromResultSet(resultSet);

        } catch (SQLException sqlException) {
            return null;
        } catch (Exception exception) {
            return null;
        }
    }

    private AccountImpl getAccountByNumberFromResultSet(ResultSet resultSet) throws SQLException {
        resultSet.next();

        int id = resultSet.getInt("id");
        String accountNumber = resultSet.getString("number");
        String pinCode = resultSet.getString("pin");
        int balance = resultSet.getInt("balance");


        return new AccountImpl(accountNumber, pinCode, balance);
    }
}
