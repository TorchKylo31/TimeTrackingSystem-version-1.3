import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.Timer;
import java.text.*;
import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   


public class Tts extends JFrame implements ActionListener{
	
	JPanel		panel0, panel1, panel2, panel3, timePanel, mainPanel;
	
	JComboBox<String> cbInOut;
	JPasswordField passtf;
	JTextField  idNotf;
	JButton		logbtn;
	JLabel     passlbl, idNolbl, timeLabel;
	JList		inPut;
	DefaultListModel<String> sList;
	private String[] timeIn_Out = {"Log in: ","Log out:"};
	
	
	Tts(){
		
	cbInOut = new JComboBox<>(timeIn_Out);
	logbtn = new JButton("LOGIN");
	logbtn.addActionListener(this);
	
	
	idNolbl = new JLabel("ID No:");
	passlbl = new JLabel("Passcode: ");
	timeLabel = new JLabel();
	

	
	
	
	passtf = new JPasswordField(10);
	idNotf = new JTextField(10);
	
	
	panel0 = new JPanel();
	panel1 = new JPanel();
	panel2 = new JPanel();
	panel3 = new JPanel();
	timePanel = new JPanel();
	mainPanel = new JPanel();
	
	final SimpleDateFormat timeFormat = new SimpleDateFormat("MMMM/dd/YYYY  HH:mm:ss a");
	ActionListener timerListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Date date = new Date();
                String time = timeFormat.format(date);
                timeLabel.setText(time);
            }
        };
        Timer timer = new Timer(1000, timerListener);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();
		
	

	getContentPane().add(panel0);
	panel0.setLayout(new BorderLayout(10,10));
	panel0.add(BorderLayout.NORTH, timeLabel);
	panel0.add(BorderLayout.WEST,idNolbl);
	panel0.add(BorderLayout.EAST, idNotf);
	
	getContentPane().add(panel1);
	panel1.setLayout(new BorderLayout(10,10));
	panel1.add(BorderLayout.WEST, passlbl);
	panel1.add(BorderLayout.EAST, passtf);
	
	sList = new DefaultListModel<>();
    inPut = new JList<>(sList);


	JScrollPane sc = new JScrollPane(inPut);
	sc.setPreferredSize(new Dimension(250,250));
	

	getContentPane().add(panel2);
	panel2.setLayout(new BorderLayout(10,10));
	panel2.add(BorderLayout.NORTH, panel0);
	panel2.add(BorderLayout.CENTER, panel1);
	panel2.add(BorderLayout.SOUTH, logbtn);
	
	
	
	getContentPane().add(panel3);
	panel3.setLayout(new BorderLayout(10,10));
	panel3.add(BorderLayout.NORTH, panel2);
	panel3.add(BorderLayout.CENTER, cbInOut);
	
	getContentPane().add(mainPanel);
	mainPanel.setLayout(new BorderLayout(10,10));
	mainPanel.add(BorderLayout.NORTH, panel3);
	mainPanel.add(BorderLayout.SOUTH, sc);
		
	
		
	}
	
	
	
	public void actionPerformed(ActionEvent event){
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss a");  
		LocalDateTime now = LocalDateTime.now(); 
		String TimeAndDate = dtf.format(now);
		
		
		char[] pString = passtf.getPassword();		
		String id = idNotf.getText();
		String pass = new String(pString);
		String inout = (String) cbInOut.getSelectedItem();
		
	
	
		
		if (id.isEmpty() || pass.isEmpty()) {
				return;
			}
	if (pass.equals("100")) {
			if (event.getActionCommand().equals("LOGIN")) {
			int size = inPut.getModel().getSize();
			sList.add(size,inout + TimeAndDate);
			save();
		
			idNotf.setText("");
			passtf.setText("");
			
			
			}
			}
			else{
				return;
			}
			
			
			
			}
			
			
		
		
	
	
	public void load() {
			FileReader in = null;
			
			try {
				in = new FileReader("add.time");
				BufferedReader bufferedReader = new BufferedReader(in);
				String employee;
				while ((employee = bufferedReader.readLine()) != null) {
					int size = inPut.getModel().getSize();
					sList.add(size, employee);
				}
				in.close();
			} catch (IOException e) {
				
			}
		}
		
public void save() {
			FileWriter out = null;
			int size = inPut.getModel().getSize();
			
			try {
				out = new FileWriter("add.time");
				for (int i = 0; i < size; i++) {
					String employee = (String) inPut.getModel().getElementAt(i) + "\n";
					out.write(employee);
				}
				out.close();
			} catch (IOException e) {
				
			}
		}
		
		
	
	public static void main(String[] args){
		
	Tts f = new Tts();
	f.setTitle("Time Tracking System");
		
		WindowListener l = new WindowAdapter(){
			
			public void windowClosing(WindowEvent e){
				
				System.exit(0);
				
			}
		};
		f.addWindowListener(l);
		f.pack();
		f.setSize(new Dimension(300,500));
		f.setLocationRelativeTo(null);
		f.setResizable(true);
		f.setVisible(true);
		
	}
	
	
	
	
}