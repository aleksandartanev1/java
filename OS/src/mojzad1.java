class BankAccount {
    private int balance = 0;

    // Оваа метода НЕ е синхронизирана
    public void deposit(int amount) {
        int currentBalance = this.balance;
        // Симулираме кратко доцнење за префрлување на контекст
        try { Thread.sleep(10); } catch (InterruptedException e) {}
        this.balance = currentBalance + amount;
    }

    public int getBalance() {
        return balance;
    }
}

public class mojzad1 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();

        // Креираме две нитки кои уплаќаат по 100 денари
        Thread t1 = new Thread(() -> account.deposit(100));
        Thread t2 = new Thread(() -> account.deposit(100));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Очекувано: 200, но често ќе отпечати 100!
        System.out.println("Вкупно на сметка: " + account.getBalance());
    }
}