import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {
    private int numberOfTransaction;
    private int fromId;
    private int toId;
    private double amount;
    private DBWorker worker;

    {
        try {
            worker = new DBWorker();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Statement statement;

    public Transaction(int fromId, int toId, double amount) {
        try {
            statement  = worker.getConnection().createStatement();
            statement.execute(String.format("insert into transactions (fromId, toId, amount)  values(%s, %s, %s)", fromId, toId, amount));

            ResultSet resultSet = statement.executeQuery(String.format("select amount from clients where id=%s", fromId));
            resultSet.next();
            double fromClientAmount = resultSet.getDouble(1);
            statement.execute(String.format("update clients set amount=%s where id=%s", fromClientAmount-amount, fromId));

            ResultSet resultSet2 = statement.executeQuery(String.format("select amount from clients where id=%s", toId));
            resultSet2.next();
            double toClientAmount = resultSet2.getDouble(1);
            statement.execute(String.format("update clients set amount=%s where id=%s", toClientAmount+amount, toId));

        }catch (SQLException e) {
            System.out.println("Incorrect input data");
            e.printStackTrace();
        }
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
    }
}
