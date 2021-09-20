import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class QuizCardBuilder {
    private JTextArea questionTextArea;
    private JTextArea answerTextArea;
    private ArrayList<QuizCard> cardList;
    private JFrame frame;

    public static void main(String[] args) {
        QuizCardBuilder builder = new QuizCardBuilder();
        builder.go();
    }

    public void go(){
        //Builds gui
			//The gui (Question area, answer area, and the nextCard button)
        frame = new JFrame("Quiz Card Builder");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        
        questionTextArea = new JTexrArea(6,20);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setFont(bigFont);
        
        JScrollPane questionScroller = new JScrollPane(questionTextArea);
        questionScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SROLLBAR_ALWAYS); 
        questionScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        answerTextArea = new JTextArea(6,20);
        answerTextArea.setLineWrap(true);
        answerTextArea.setWrapStyleWord(true);
        answerTextArea.setFont(bigFont);
        
        JScrollPane answerScroller = new JScrolPane(anwserTextArea);
        answerScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        answerScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        JButton nextButton = new JButton("Next Card");
        
        cardList = new ArrayList<QuizCard>();
        
        JLabel questionLabel = new JLabel("Question:");
        JLabel answerLabel = new JLable("Answer:");
        //Adding all the above elements in the panel
        mainPanel.add(questionLabel);
        mainPanel.add(questionScroller);
        mainPanel.add(answerLabel);
        mainPanel.add(answerScroller);
        mainPanel.add(nextButton);
        
        nextBUtton.addActionListener(new NextCardListener());
        //THE MENU BAR
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenueItem("Menu");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
        
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        
        menuBar.add(fileMenu);
        
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500,600);
        frame.setVisible(true);
    }
    
    public class NextCardListener implements ActionListener {
    	public void actionPerformed(ActionEvent ev){
    		QuizCard card = new QuizCard(questionTextArea.getText(), answerTextArea.getText());
    		cardList.add(card);
    		
    		JFileChooser fileSave = new JFileChooser();
    		fileSave.showSaveDialog(frame);
    		saveFile(fileSave.getSelectedFile());
    	}
    }
    
    public class NewMenuListener implements ActionListener{ 
    	public void actionPerformed(ActionEvent ev){
    		cardList.clear();
    		clearCard();
    	}
    }
    
    public void ClearCard() {
    	questionTextArea.setText("");
    	answerTextArea.setText("");
    	questionTextArea.requestFocus();
    }
    
    private void saveFile(File file) {
    	try{
    		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    		
    		for(QuizCard card: cardList){
    			writer.write(card.getQuestion()+"/");
    			writer.writer(card.getAnswer()+"\n");
    		}
    		write.close();
    		
    	}catch(IOException ex){
    			System.out.println("Couldn't write the cardlist out");
    			ex.printStackTrace();
    	}
    }
}





































