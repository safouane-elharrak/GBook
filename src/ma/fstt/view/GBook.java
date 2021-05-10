package ma.fstt.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class GBook {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_2;
	private Connection con ;
	private JButton btnNewButton;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GBook window = new GBook();
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
	public GBook() {
		initialize();
		Connect();
		table_load();
	}
	private  PreparedStatement pst;
	private ResultSet rs;

	private String url = "jdbc:mysql://localhost:3306/gbook1";
	private String user = "root";
	
	private String password = "";
	private JTextField textField_3;

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
			 pst = con.prepareStatement("select * from book");
			 rs = pst.executeQuery();
			 table.setModel(DbUtils.resultSetToTableModel(rs));
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
		frame.getContentPane().setForeground(new Color(51, 51, 51));
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.setBounds(100, 100, 973, 666);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK");
		lblNewLabel.setForeground(new Color(51, 51, 51));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 45));
		lblNewLabel.setBounds(6, 113, 172, 97);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBorder(new TitledBorder(null, "Enregistrer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(193, 10, 719, 299);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nom Book :");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(25, 45, 158, 39);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Auteur :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1_1.setBounds(25, 105, 158, 39);
		panel.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		textField.setBounds(181, 51, 422, 36);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(181, 107, 422, 36);
		panel.add(textField_1);
		
		 btnNewButton = new JButton("Add\r\n");
		 btnNewButton.setForeground(Color.DARK_GRAY);
		 btnNewButton.setBackground(new Color(204, 204, 204));
		 
		
		 
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		Connect();
				String name, auteur,annee;

				name = textField.getText();
				auteur = textField_1.getText();
				annee = textField_3.getText();
				
			
					try {
						pst = con.prepareStatement("insert into book (name,auteur,annee) values (? ,?,?)");

						
						pst.setString(1, textField.getText());
						pst.setString(2, textField_1.getText());
						pst.setString(3, textField_3.getText());
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Bien Enregistrer");
						textField.setText("");
						textField_1.setText("");
						textField_3.setText("");
						textField.requestFocus();
						table_load();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnNewButton.setBounds(78, 230, 130, 49);
		panel.add(btnNewButton);
		
		JButton btnEffacer = new JButton("Delete");
		btnEffacer.setForeground(Color.DARK_GRAY);
		btnEffacer.setBackground(new Color(204, 204, 204));
		btnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
			}
		});
		btnEffacer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnEffacer.setBounds(298, 230, 130, 49);
		panel.add(btnEffacer);
		
		JButton btnQuitter = new JButton("Exit");
		btnQuitter.setForeground(Color.DARK_GRAY);
		btnQuitter.setBackground(new Color(204, 204, 204));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnQuitter.setBounds(533, 230, 130, 49);
		panel.add(btnQuitter);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(181, 165, 422, 36);
		panel.add(textField_3);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Ann\u00E9e :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1_1_1.setBounds(25, 162, 158, 39);
		panel.add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.LIGHT_GRAY);
		scrollPane.setBounds(6, 319, 906, 184);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 522, 429, 91);
		frame.getContentPane().add(scrollPane_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Button.background"));
		scrollPane_1.setViewportView(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Chercher", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("ID BOOK : \r\n");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1_2.setBounds(10, 21, 183, 39);
		panel_1.add(lblNewLabel_1_2);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String id = textField_2.getText();
					
					pst = con.prepareStatement("select name,auteur,annee from book where id_book = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					if(rs.next()==true) {
						String nom = rs.getString(1);
						String auteur = rs.getString(2);
						String annee = rs.getString(3);

						textField.setText(nom);
						textField_1.setText(auteur);
						textField_3.setText(annee);
					}else {

						textField.setText("");
						textField_1.setText("");
						textField_3.setText("");
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
		
		JButton btnModifier = new JButton("Update\r\n");
		btnModifier.setForeground(Color.DARK_GRAY);
		btnModifier.setBackground(new Color(204, 204, 204));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect();
				String name, auteur,id,annee;

				name = textField.getText();
				auteur = textField_1.getText();
				annee = textField_3.getText();
				id = textField_2.getText();
			
					try {
						pst = con.prepareStatement("UPDATE `book` SET `name`=?,`auteur`=?,`annee`=? WHERE id_book = ?");

						pst.setString(1, textField.getText());
						pst.setString(2, textField_1.getText());
						pst.setString(3, textField_3.getText());
						pst.setString(4, textField_2.getText());
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Bien Modifier");
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField.requestFocus();
						table_load();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnModifier.setBounds(487, 541, 180, 49);
		frame.getContentPane().add(btnModifier);
		
		JButton btnSupprimer = new JButton("Remove");
		btnSupprimer.setForeground(Color.DARK_GRAY);
		btnSupprimer.setBackground(new Color(204, 204, 204));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connect();
				String id;

				
				id = textField_2.getText();
			
					try {
						pst = con.prepareStatement("delete book from book WHERE id_book = ?");

						
						
						pst.setString(1, textField_2.getText());
						pst.executeUpdate();
					
						JOptionPane.showMessageDialog(null, "Bien Supprimer");
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField.requestFocus();
						table_load();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnSupprimer.setBounds(694, 541, 188, 49);
		frame.getContentPane().add(btnSupprimer);
	}
}
