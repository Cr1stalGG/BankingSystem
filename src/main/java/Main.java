import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankController controller = new BankController();
        Scanner scanner = new Scanner(System.in);

        int id;
        String password;
        double amount;

        String UI = """
                BANKING SYSTEM:
                1. Add account
                2. Get list of accounts
                3. Get information about account
                4. Get list of transactions
                5. Get information about transaction
                6. Put money
                7. Get money
                8. Credit
                9. Transaction
                10. Quit
                """;
        byte check;

        do {
            System.out.println(UI);
            check = scanner.nextByte();

            switch (check){
                case 1:
                    System.out.println("Input password: ");
                    password = scanner.next();
                    controller.addAccount(password, 0.0);
                    break;
                case 2:
                    controller.getListOfAccounts();
                    break;
                case 3:
                    System.out.println("Input id of account: ");
                    id = scanner.nextInt();

                    System.out.println(controller.accountInformation(id));
                    break;
                case 4:
                    List<String> listOfTransactions = controller.getListOfTransactions();
                    for(int i = 0; i < listOfTransactions.size(); i++){
                        System.out.println(listOfTransactions.get(i));
                    }
                    break;
                case 5:
                    System.out.println("Input number of transaction: ");
                    id = scanner.nextInt();
                    System.out.println(controller.transactionInformation(id));
                    break;
                case 6:
                    System.out.println("Input id of account: ");
                    id = scanner.nextInt();
                    System.out.println("Input amount: ");
                    amount = scanner.nextDouble();
                    controller.putMoney(id, amount);
                    break;
                case 7:
                    System.out.println("Input id of account: ");
                    id = scanner.nextInt();
                    System.out.println("Input amount: ");
                    amount = scanner.nextDouble();
                    controller.getMoney(id, amount);
                    break;
                case 8:

                    break;
                case 9:
                    System.out.println("Input ur id: ");
                    int fromId = scanner.nextInt();

                    System.out.println("Input id of getter: ");
                    int toId = scanner.nextInt();

                    System.out.println("Input password: ");
                    password = scanner.next();

                    System.out.println("Input amount: ");
                    amount = scanner.nextDouble();

                    controller.transaction(fromId, toId, password, amount);

                    break;
            }

        }while(check != 10);
        System.out.println("Goodbye!");
    }
}
