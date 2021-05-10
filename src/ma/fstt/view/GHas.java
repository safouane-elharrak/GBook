package ma.fstt.view;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;

public class GHas {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JTextField textField_2;
	private Connection con ;
	private JButton btnNewButton;
	private String x,y;
	private JComboBox comboBox;
	private String Value,Valeur;
	private JComboBox comboBox_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GHas window = new GHas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GHas() {
		initialize();
		Connect();
		table_load();
		combo_load();
		combo_prod_load();
	}

	/**
	 * Initialize the contents of the frame.
	 */



private  PreparedStatement pst;
private ResultSet rs;

private String url = "jdbc:mysql://localhost:3306/gbook1";
private String user = "root";

private String password = "";

public void Connect() {
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, password);
		
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

 public void table_load() {
	 try {
		 pst = con.prepareStatement("select * from has");
		 rs = pst.executeQuery();
		 table.setModel(DbUtils.resultSetToTableModel(rs));
	 }
	 catch(SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 public void combo_load() {
	 try {
		 pst = con.prepareStatement("select * from book");
		 rs = pst.executeQuery();
		 while (rs.next()){
	             x = rs.getString("id_book");
	             y = rs.getString("name");
	            //String cmb = (id+"-"+nme);
	             comboBox.addItem(y);
	        }
	 }
	 catch(SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 public void combo_prod_load() {
	 try {
		 pst = con.prepareStatement("select * from bookstore");
		 rs = pst.executeQuery();
		 while (rs.next()){
	             x = rs.getString("id_bookstore");
	             y = rs.getString("name");
	            //String cmb = (id+"-"+nme);
	             comboBox_1.addItem(y);
	        }
	 }
	 catch(SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 
 
/**
 * Initialize the contents of the frame.
 */
private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 973, 666);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JLabel lblNewLabel = new JLabel("HAS");
	lblNewLabel.setForeground(new Color(51, 51, 51));
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 45));
	lblNewLabel.setBounds(31, 86, 188, 97);
	frame.getContentPane().add(lblNewLabel);
	
	JPanel panel = new JPanel();
	panel.setBorder(new TitledBorder(null, "Enregistrer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel.setBounds(264, 0, 608, 283);
	frame.getContentPane().add(panel);
	panel.setLayout(null);
	
	JLabel lblNewLabel_1 = new JLabel("Date : ");
	lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
	lblNewLabel_1.setBounds(25, 29, 183, 39);
	panel.add(lblNewLabel_1);
	
	JLabel lblNewLabel_1_1 = new JLabel("Book :");
	lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
	lblNewLabel_1_1.setBounds(25, 101, 183, 39);
	panel.add(lblNewLabel_1_1);
	
	textField = new JTextField();
	textField.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
	textField.setBounds(181, 31, 333, 36);
	panel.add(textField);
	textField.setColumns(10);
	
	 btnNewButton = new JButton("Add");
	 
	
	 
	btnNewButton.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
	Connect();
			String product_name, product_price,valeur;

			product_name = textField.getText();
			product_price = Value;
			valeur = Valeur;
		
				try {
					pst = con.prepareStatement("insert into has (date_has, id_book,id_bookstore) values (? ,?,?)");

					//pst = con.preparedStatement("insert into cmd (numero, id_client) values (? ,?)");
					pst.setString(1, textField.getText());
					pst.setInt(2, Integer.parseInt(Value));
					pst.setInt(3, Integer.parseInt(Valeur));

					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Bien Enregistrer");
					textField.setText("");
					//test.getSelectionModel().select(0);
					textField.requestFocus();
					table_load();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			
		}
	});
	btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
	btnNewButton.setBounds(50, 212, 130, 49);
	panel.add(btnNewButton);
	
	JButton btnEffacer = new JButton("Delete");
	btnEffacer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			textField.setText("");
			//textField_1.setText("");
		}
	});
	btnEffacer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
	btnEffacer.setBounds(265, 212, 130, 49);
	panel.add(btnEffacer);
	
	JButton btnQuitter = new JButton("Exit");
	btnQuitter.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	btnQuitter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
	btnQuitter.setBounds(442, 212, 130, 49);
	panel.add(btnQuitter);
	
	 comboBox = new JComboBox();
	 comboBox.setBounds(181, 107, 333, 34);
	 panel.add(comboBox);
	 
	  comboBox_1 = new JComboBox();
	  comboBox_1.addActionListener(new ActionListener(){
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		
		 	 	String display = (String) comboBox_1.getSelectedItem();
		  	    String sql = "Select id_bookstore as id from bookstore where name =?";
		 
		  	  // test.removeAllItems();
		  	    try {
		  	        pst = con.prepareStatement(sql);
		  	        pst.setString(1, display);
		  	        rs = pst.executeQuery();
		 
		  	        while (rs.next()) {  // <- Include all authors found
		  	           String add1 = rs.getString("id");
		  	           System.out.println(add1);
		  	          // test.addItem(add1);
		  	           Valeur = add1;
		  	      //   JOptionPane.showMessageDialog(null,Value);
		  	        }
		  	      
		 
		  	   } catch(Exception ev) {
		  	        JOptionPane.showMessageDialog(null,ev);
		  	   }
		  	    
		  	  comboBox_1.removeAllItems();
		 	 	combo_prod_load();
		 		
		 	}
		 });
	 comboBox_1.setBounds(181, 151, 333, 34);
	 panel.add(comboBox_1);
	 
	 JLabel lblNewLabel_1_1_1 = new JLabel("Book Store :");
	 lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
	 lblNewLabel_1_1_1.setBounds(25, 145, 183, 39);
	 panel.add(lblNewLabel_1_1_1);
	 comboBox.addActionListener(new ActionListener(){
	 	public void actionPerformed(ActionEvent e) {
	 		comboBox.removeAllItems();
	
	 	 combo_load();
	 	 	String display = (String) comboBox.getSelectedItem();
	  	    String sql = "Select id_book as id from book where name =?";
	 
	  	  // test.removeAllItems();
	  	    try {
	  	        pst = con.prepareStatement(sql);
	  	        pst.setString(1, display);
	  	        rs = pst.executeQuery();
	 
	  	        while (rs.next()) {  // <- Include all authors found
	  	           String add1 = rs.getString("id");
	  	           System.out.println(add1);
	  	          // test.addItem(add1);
	  	           Value = add1;
	  	      //   JOptionPane.showMessageDialog(null,Value);
	  	        }
	 
	  	   } catch(Exception ev) {
	  	        JOptionPane.showMessageDialog(null,ev);
	  	   }
	 		
	 	}
	 });
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(31, 292, 898, 162);
	frame.getContentPane().add(scrollPane);
	
	table = new JTable();
	scrollPane.setViewportView(table);
	
	JButton btnModifier = new JButton("Update");
	btnModifier.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Connect();
			String product_name, product_price,id,valeur;

			product_name = textField.getText();
			product_price = Value;
			valeur = Valeur;
			id = textField_2.getText();
		
				try {
					pst = con.prepareStatement("UPDATE `has` SET `date_has`=?,`id_book`=?,`id_bookstore`=? WHERE id_has = ?");

					//pst = con.preparedStatement("insert into produit (nom, pu) values (? ,?)");
					pst.setString(1, textField.getText());
					pst.setInt(2, Integer.parseInt(Value));
					pst.setInt(3, Integer.parseInt(Valeur));
					pst.setString(4, textField_2.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Bien Modifier");
					textField.setText("");
					//textField_1.setText("");
					textField_2.setText("");
					textField.requestFocus();
					table_load();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	});
	btnModifier.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
	btnModifier.setBounds(472, 510, 180, 49);
	frame.getContentPane().add(btnModifier);
	
	JButton btnSupprimer = new JButton("Remove");
	btnSupprimer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			Connect();
			String id;

			
			id = textField_2.getText();
		
				try {
					pst = con.prepareStatement("delete has from has WHERE id_has = ?");

					
					
					pst.setString(1, textField_2.getText());
					pst.executeUpdate();
				
					JOptionPane.showMessageDialog(null, "Bien Supprimer");
					textField.setText("");
					//textField_1.setText("");
					textField_2.setText("");
					textField.requestFocus();
					table_load();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	});
	btnSupprimer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
	btnSupprimer.setBounds(695, 510, 188, 49);
	frame.getContentPane().add(btnSupprimer);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBounds(10, 490, 427, 89);
	frame.getContentPane().add(panel_1);
	panel_1.setBorder(new TitledBorder(null, "Chercher", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel_1.setLayout(null);
	
	JLabel lblNewLabel_1_2 = new JLabel("ID HAS : \r\n");
	lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
	lblNewLabel_1_2.setBounds(10, 25, 183, 39);
	panel_1.add(lblNewLabel_1_2);
	
	textField_2 = new JTextField();
	textField_2.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			/*pst = con.prepareStatement("select * from lign_cmd");
		 rs = pst.executeQuery();
		 table.setModel(DbUtils.resultSetToTableModel(rs));*/
			
			try {
				String id = textField_2.getText();
				
				pst = con.prepareStatement("select * from has where id_has = ?");
				pst.setString(1, id);
				ResultSet rs = pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				pst = con.prepareStatement("select * from has where id_has = ?");
				pst.setString(1, id);
				ResultSet rss = pst.executeQuery();
				if(rss.next()==true) {

					String numero = rss.getString(2);
					String nom = rss.getString(2);
					textField.setText(numero);
					//test.addItem(nom);
				}else {

					textField.setText("");
					//textField_1.setText("");
				}
			}catch(SQLException e1){
				e1.printStackTrace();
				
			}
		}
	});
	textField_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
	textField_2.setColumns(10);
	textField_2.setBounds(164, 27, 235, 36);
	panel_1.add(textField_2);
	
	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(0, 0, 429, 91);
	panel_1.add(scrollPane_1);
	
}
}
