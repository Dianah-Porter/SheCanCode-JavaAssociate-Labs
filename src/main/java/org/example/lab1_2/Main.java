package org.example.lab1_2;

public class Main {
    public static void main(String[] args){
        Account alice = new Account("A001", "Alice", 5000);
        Account bob = new Account("A002", "Bob", 2000);
        BankService bank = new BankService();

        CsvTransactionProcessor processor = new CsvTransactionProcessor();

        processor.processTransactions(
                "transactions.csv",
                "failed_transactions.txt"
        );

        try{
            bank.transfer(alice,bob, 4000);
        }
        catch(TransactionException e){
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
    }
}
