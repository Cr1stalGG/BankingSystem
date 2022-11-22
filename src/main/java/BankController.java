import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankController implements BankMethods{
    DBWorker worker;
    Statement statement;

    {
        try {
            worker = new DBWorker();
            statement = worker.getConnection().createStatement();
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addAccount(String password, double amount) {
        Client client = new Client(password, amount);
    }

    @Override
    public void getListOfAccounts() { //-
        try {
            ResultSet resultSet = statement.executeQuery("select * from clients");
            while (resultSet.next())
                System.out.println(String.format("id: %s, password: %s, amount: %s;", resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String accountInformation(int id) {
        String information = "";
        try {
             ResultSet resultSet = statement.executeQuery(String.format("select * from clients where id=%s", id));
             resultSet.next();
             for(int i = 1; i <= 3; i++){
                 information += resultSet.getString(i) + " ";

             }
        } catch (SQLException e) {
            information = "Error";
            e.printStackTrace();
        }

        return information;
    }

    @Override
    public List<String> getListOfTransactions() {
        List<String> listOfTransactions = new ArrayList<>();
        String transaction = new String("");
        try {
            ResultSet resultSet = statement.executeQuery(String.format("select * from transactions"));
            while(resultSet.next()){
                for(int i = 1; i <= 4; i++)
                    transaction += resultSet.getString(i) + " ";
                listOfTransactions.add(transaction);
                transaction = "";

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return listOfTransactions;
    }

    @Override
    public String transactionInformation(int numberOfTransaction) {
        String information = new String("");

        try {
            ResultSet resultSet = statement.executeQuery(String.format("select * from transactions where id=%s", numberOfTransaction));
            resultSet.next();
            for(int i = 1; i <= 4; i++)
                information += resultSet.getString(i) + " ";
        } catch (SQLException e) {
            information = "error";
            e.printStackTrace();
        }

        return information;

    }

    @Override
    public void putMoney(int id, double amount) {
        try {
            ResultSet resultSet = statement.executeQuery(String.format("select * from clients where id=%s", id));
            resultSet.next();
            double clientsAmount = resultSet.getDouble(3);
            statement.execute(String.format("update clients set amount=%s where id=%s", clientsAmount+amount, id));
            System.out.println("Success...");
        } catch (SQLException e) {
            System.out.println("Error...");
            e.printStackTrace();
        }

    }

    @Override
    public void getMoney(int id, double amount) {
        try {
            ResultSet resultSet = statement.executeQuery(String.format("select * from clients where id=%s", id));
            resultSet.next();
            double clientsAmount = resultSet.getDouble(3);
            statement.execute(String.format("update clients set amount=%s where id=%s", clientsAmount-amount, id));
            System.out.println("Success...");
        } catch (SQLException e) {
            System.out.println("Error...");
            e.printStackTrace();
        }
    }

    @Override
    public void transaction(int fromId, int toId, String password, double amount) {
        String checkPassword;
        try {
            ResultSet resultSet = statement.executeQuery(String.format("select * from clients where id=%s", fromId));
            resultSet.next();
            checkPassword = resultSet.getString(2);
            if(password.equals(checkPassword)) {
                Transaction transaction = new Transaction(fromId, toId, amount);
            } else {
                System.out.println("Incorrect password!!!");
            }
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
