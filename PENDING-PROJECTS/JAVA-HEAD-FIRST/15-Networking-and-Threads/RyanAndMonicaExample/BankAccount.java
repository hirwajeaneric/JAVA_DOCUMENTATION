class BankAccount {
	private int balance = 100;
	
	//This getter will help us to get and use the balance variable in other classes.
	public int getBalance(){
		return balance;
	}
	public void withdraw(int amount){
		balance = balance - amount;
	}
}

