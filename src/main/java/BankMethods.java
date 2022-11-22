import java.util.List;

public interface BankMethods {
    void addAccount(String password, double amount);
    void getListOfAccounts();
    String accountInformation(int id);
    List<String> getListOfTransactions();
    String transactionInformation(int numberOfTransaction);
    void putMoney(int id, double amount);
    void getMoney(int id, double amount);
    void transaction(int fromId, int toId, String password, double amount);
}
