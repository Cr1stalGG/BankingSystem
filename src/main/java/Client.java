import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;

public class Client {
    private int id;
    private String password;
    private double amount;
    DBWorker worker;

    {
        try {
            worker = new DBWorker();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    Statement statement;

    public Client(int id, String password, double amount) {
        try {
            statement  = worker.getConnection().createStatement();
            statement.execute(String.format("insert into clients (id, password, amount)  select max(id)+1, '%s', %s from clients", id, password, amount));
        }catch (SQLException e) {
            System.out.println("Incorrect input data");
            e.printStackTrace();
        }
        this.id = id;
        this.password = password;
        this.amount = amount;
    }
    public Client(String password, double amount) {
        try {
            statement  = worker.getConnection().createStatement();
            statement.execute(String.format("insert into clients (password, amount)  values('%s', %s)", password, amount));
        }catch (SQLException e) {
            System.out.println("Incorrect input data");
            e.printStackTrace();
        }
        this.password = password;
        this.amount = amount;
    }
}
