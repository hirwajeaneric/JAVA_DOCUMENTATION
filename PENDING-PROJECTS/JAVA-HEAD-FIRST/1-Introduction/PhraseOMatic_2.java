/*This is the Phrasomatic program with some lines of code from the JAVA HEAD FIRST 2nd Edition. I elaborated it by looping it so that it can display many different lines of random text. That's another good example of how java can be cool.*/

public class PhraseOMatic_2 {
	public static int number = 0;
	public static void main(String[] args){
		String[] wordListOne = {"27/7","multi-Tier","30,000 foot","B-to-B","Win-win","front-end","web-based","pervasive","smart","six-sigma","critical-path","dynamic"};

		String[] wordListTwo = {"empowered","sticky","value-added","oriented","centric","distributed","clustered","branded","outside-the-box","positioned","networked","focused","leveraged","aligned","targeted","shared","cooperative","accelerated"};

		String[] wordListThree = {"process","tipping-point","solution","architecture","core competency","strategy","mindshare","portal","space","vision","paradigm","mission"};
		
		
		int oneLength = wordListOne.length;
		int twoLength = wordListTwo.length;
		int threeLength = wordListThree.length;
	
		if (oneLength > twoLength && oneLength > threeLength)
			number = oneLength;
		if (twoLength > oneLength && twoLength > threeLength)
			number = twoLength;
		if (threeLength > twoLength && threeLength > oneLength)
			number = threeLength;
		
		for (int i=0; i<= number; i++) {
		
			int numbering = i;
			numbering = numbering+1;
			
			int rand1 = (int) (Math.random() * oneLength);
			int rand2 = (int) (Math.random() * twoLength);
			int rand3 = (int) (Math.random() * threeLength);
			
			String phrase = wordListOne[rand1]+" "+wordListTwo[rand2]+" "+wordListThree[rand3];
			System.out.println(numbering+". What we need is a "+phrase+".");
		}
	}
}
