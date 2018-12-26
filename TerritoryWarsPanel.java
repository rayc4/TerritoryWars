import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TerritoryWarsPanel implements ActionListener{
	
	// Variables
	String strIP="";
	
	// Properties
	JFrame frame = new JFrame("Territory Wars");
	JPanel panel = new JPanel();
	JTextArea area = new JTextArea();
	JScrollPane scroll = new JScrollPane(area);
	JTextField field = new JTextField();
	JTextField inputIP = new JTextField();
	SuperSocketMaster ssm;
	JButton host = new JButton("Host Server");
	JButton client = new JButton("Join Server");
	JLabel hostIP = new JLabel("host");
	
	// Methods
	public void actionPerformed(ActionEvent evt){
			ssm = new SuperSocketMaster(6112, this);
			ssm.connect();
			System.out.println(ssm.getMyAddress());
		if(evt.getSource()==host){
			
		}else if(evt.getSource()==client){
			if(evt.getSource()==inputIP){
				strIP=inputIP.getText();
				ssm = new SuperSocketMaster(strIP, 6112, this);
			}
		}
		if(evt.getSource()==field){
			System.out.println("Sending this out over the network: "+field.getText());
			ssm.sendText(field.getText());
			field.setText("");
		}else if(evt.getSource()==ssm){
			String strData = ssm.readText();
			area.append(strData+"\n");
		}
	}
	
	// Constructors
	public TerritoryWarsPanel(){
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1280,720));
		
		host.setSize(100,50);
		host.setLocation(500,500);
		panel.add(host);
		
		client.setSize(100,50);
		client.setLocation(700,500);
		panel.add(client);
		
		hostIP.setSize(400,100);
		hostIP.setLocation(300,600);
		panel.add(hostIP);
		
		
		inputIP.setSize(400,100);
		inputIP.setLocation(500,600);
		panel.add(inputIP);
		
		scroll.setSize(400,400);
		scroll.setLocation(0,0);
		panel.add(scroll);
		
		field.setSize(400,100);
		field.setLocation(0,400);
		field.addActionListener(this);
		panel.add(field);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	// Main Method
	public static void main(String[] args){
		new TerritoryWarsPanel();
	}
}
