package Statistics;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Interface extends JFrame {

	//GUI Variables
	public JTextField filePathTextField;
	String filePath;
	private JButton submit, clear;
	JComboBox<String> operationsBox;
	public DefaultComboBoxModel<String> operationsModel;
	static JTextArea output;
	JScrollPane scrollable;
	
	//call instance of statistics class 
	Statistics stats = new Statistics();
	
	//builds GUI
	public Interface () {
		JPanel p1 = new JPanel(new FlowLayout());
		p1.add(new JLabel("File Path"));
		p1.add(filePathTextField = new JTextField(10));
		p1.add(new JLabel("Operation:"));
		
		operationsModel = new DefaultComboBoxModel<String>();
		operationsModel.addElement("Mean");
		operationsModel.addElement("Variance & Standard Deviation");
		operationsModel.addElement("R correlation");
		operationsModel.addElement("Z-Score");
		operationsModel.addElement("Relative Probability");
		
		output = new JTextArea();
		output.setEditable(false);
		
		p1.add(operationsBox = new JComboBox<String>(operationsModel));
		
		JPanel p4 = new JPanel(new BorderLayout());
		p4.add(new JLabel(" Specify a File Path, choose an Operation to be performed on the data, and click Submit"));
		
		JPanel p2 = new JPanel(new FlowLayout());
		p2.add(submit = new JButton("Submit"));
		p2.add(clear = new JButton("Clear"));
		
		JPanel p3 = new JPanel(new BorderLayout());
		p3.add(p1, BorderLayout.CENTER);
		p3.add(p4, BorderLayout.SOUTH);
		
		setLayout(new BorderLayout());
		add(p3, BorderLayout.NORTH);
		add(scrollable = new JScrollPane(output), BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				filePath = filePathTextField.getText();	
				DoublyLinkedList<Double> list = readFile(filePath);  
				
			
				switch(operationsBox.getSelectedIndex()) {
				
				//Selection option - Mean
				case 0:
					double mean = stats.mean(list);
					String mean2 = String.valueOf(mean);
					output.setText("MEAN: " + mean2);
					break;
				
				//Selection option - STD and Variance
				case 1:
					double std = stats.standardDev(list);
					double var = stats.variance(list);
					String std2 = String.valueOf(std);
					String var2 = String.valueOf(var);
					output.setText("STANDARD DEVIATION: " + std2 + "\n" + "VARIANCE: " + var2);
					break;

				//Selection option - Correlation
				case 2:
					
					DoublyLinkedList<Double> x = new DoublyLinkedList<Double>(); 
					DoublyLinkedList<Double>y = new DoublyLinkedList<Double>();
					LinkedListIterator<Double> listIt = list.iterator();
					
					x.addFirst(listIt.getCurrentElement()); 
					y.addFirst(listIt.next());
					
					while(listIt.next() != null )  {
						x.addLast(listIt.getCurrentElement());
						y.addLast(listIt.next());
					}
					
					double corr = stats.correlation(x, y);
					String corr2 = String.valueOf(corr);
					output.setText("CORRELATION: " + corr2);
					break;
				
				//Selection option - zScore
				case 3:
					DoublyLinkedList<Double> z = stats.zScore(list);
					LinkedListIterator<Double> zIt = z.iterator();
					StringBuilder buildString = new StringBuilder();
					buildString.append(zIt.current.getElement() + "\n" );
					while (zIt.next() != null) {
						buildString.append(zIt.getCurrentElement() + "\n");
					}
					output.setText(buildString.toString());
					break;
				
				//Selection option - Normal Probability 
				case 4: 
					DoublyLinkedList<Double> norm = stats.normProb(list);
					LinkedListIterator<Double> normIt = norm.iterator();
					StringBuilder buildAgain = new StringBuilder();
					buildAgain.append(normIt.current.getElement() + "\n");
					while(normIt.next()!=null) {
						buildAgain.append(normIt.getCurrentElement() + "\n");
					}
					output.setText(buildAgain.toString());
					break;
				
				default:
						//print something
					break;
				}
	
			}
		});
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				filePathTextField.setText("");
				output.setText("");
			}
		});
		
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//call interface class
		Interface i = new Interface();
	    i.setDefaultCloseOperation(3);
	    i.setTitle("Statistical Calculator");
	    i.setSize(600,500);
	    i.setResizable(false);
	    i.setLocationRelativeTo(null);
	    i.setVisible(true);
	    
	}	
	
	//reads in file through scanner
		//create new instance of DLL
		//parse file until hits double which serve as our inputs
		//add doubles to tail of DLL 
		//returns DLL with stored values from file, returns null if file error
		public static DoublyLinkedList<Double> readFile(String file){
		
			try {
				Scanner scanner = new Scanner(new File(file));
				DoublyLinkedList<Double> myList = new DoublyLinkedList<Double>();	
			
				
					while(!scanner.hasNextDouble()) {
						scanner.next();
					}
					
					while(scanner.hasNextDouble()) {
						double value = scanner.nextDouble();
						myList.addLast(value);
					}
			
				scanner.close();
				return myList;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				output.setText("File not found, please enter a valid file location");
				return null;
			}
		}
		
}
	

