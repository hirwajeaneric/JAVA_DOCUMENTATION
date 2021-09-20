public class RyanAndMonicaJob implements Runnable {
	//Here we called the BankAccount instance and named it account
	//It means that now, we can run everything inside that class.
	private BankAccount account = new BankAccount();
	
	public static void main (String[] args){
		//Creating threads and giving them the SAME ONE job present.
		RyanAndMonicaJob theJob = new RyanAndMonicaJob();
		Thread one = new Thread(theJob);
		Thread two = new Thread(theJob);
		one.setName("Ryan");
		two.setName("Monica");
		one.start();
		two.start();
	}
	
	public void run(){
		for (int x = 0; x < 10; x++){
			makeWithdrawal(10);
			if(account.getBalance() < 0){
				System.out.println("Overdrawn");
			}
		}
	}
	
	private synchronized void makeWithdrawal (int amount) {
		if(account.getBalance() >= amount) {
			try {
				System.out.println(Thread.currentThread().getName()+" is about to withdraw");
			Thread.sleep(500);
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName()+" Woke up.");
			account.withdraw(amount);
			System.out.println(Thread.currentThread().getName()+" Completes the withdrawal.");
		}else{
			System.out.println("Sorry, not enough for "+Thread.currentThread().getName());
		}
	}
}
