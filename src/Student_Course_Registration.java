import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Student_Course_Registration extends JFrame {

	private JFrame frmLsalleCollege;
	private Connection connection;

	private JTextField textFieldid;
	private JTextField textFieldname;
	private JTextField textFieldcontact;
	private JTextField textFieldcourse;
	private JTable table;
	private JTextField textFieldsearch;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_Course_Registration window = new Student_Course_Registration();
					window.frmLsalleCollege.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void reloadtable() {
		// TODO Auto-generated method stub
		try {
			String query="select Id,Name,Contact,Course from Studentinfo";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			textFieldid.setText(null);
			textFieldname.setText(null);
			textFieldcontact.setText(null);
			textFieldcourse.setText(null);
			
			rs.close();
			pst.close();
			
			
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e2);
			
		}
	}
	/**
	 * Create the application.
	 */
	public Student_Course_Registration() {
		initialize();
		connection=sqliteConnection.dbConnector();
		reloadtable();

		
		
		
	}
	private void initialize() {
		// TODO Auto-generated method stub
		frmLsalleCollege = new JFrame();
		frmLsalleCollege.getContentPane().setBackground(new Color(0, 255, 255));
		frmLsalleCollege.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Admin\\eclipse-workspace\\PatelDhruvilkumar_1911662\\img\\new_LaSalle_college.png"));
		lblNewLabel.setBounds(10, 10, 391, 108);
		frmLsalleCollege.getContentPane().add(lblNewLabel);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblId.setBounds(69, 128, 80, 30);
		frmLsalleCollege.getContentPane().add(lblId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblName.setBounds(69, 179, 80, 30);
		frmLsalleCollege.getContentPane().add(lblName);
		
		JLabel lblNewLabelEid_1_1 = new JLabel("Contact:");
		lblNewLabelEid_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabelEid_1_1.setBounds(69, 230, 80, 30);
		frmLsalleCollege.getContentPane().add(lblNewLabelEid_1_1);
		
		JLabel lblNewLabelEid_1_2 = new JLabel("Course:");
		lblNewLabelEid_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabelEid_1_2.setBounds(69, 282, 91, 30);
		frmLsalleCollege.getContentPane().add(lblNewLabelEid_1_2);
		
		textFieldid = new JTextField();
		textFieldid.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldid.setColumns(10);
		textFieldid.setBounds(160, 129, 146, 29);
		frmLsalleCollege.getContentPane().add(textFieldid);
		
		textFieldname = new JTextField();
		textFieldname.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldname.setColumns(10);
		textFieldname.setBounds(160, 179, 146, 29);
		frmLsalleCollege.getContentPane().add(textFieldname);
		
		textFieldcontact = new JTextField();
		textFieldcontact.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldcontact.setColumns(10);
		textFieldcontact.setBounds(160, 231, 146, 29);
		frmLsalleCollege.getContentPane().add(textFieldcontact);
		
		textFieldcourse = new JTextField();
		textFieldcourse.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldcourse.setColumns(10);
		textFieldcourse.setBounds(160, 283, 146, 29);
		frmLsalleCollege.getContentPane().add(textFieldcourse);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="Insert into Studentinfo (id,Name,Contact,course) values (?,?,?,?)";
					
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, textFieldid.getText());
					pst.setString(2, textFieldname.getText());
					pst.setString(3, textFieldcontact.getText());
					pst.setString(4, textFieldcourse.getText());
			


					pst.execute();
					JOptionPane.showMessageDialog(null, "Saved Successfully!");
 
					
					pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
					
			}
				reloadtable();
		}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnAdd.setFocusable(false);
		btnAdd.setBounds(53, 364, 125, 47);
		frmLsalleCollege.getContentPane().add(btnAdd);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					String query="Update studentinfo set Name='"+textFieldname.getText()+
							"', Contact='"+textFieldcontact.getText()+"', course='"+textFieldcourse.getText()+"' where id='"+textFieldid.getText()+"'";
					PreparedStatement pst=connection.prepareStatement(query);
					
					pst.execute();
					JOptionPane.showMessageDialog(null, "Updated Successfully!");
 
					
					pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
					
				}
				reloadtable();
			}
		});
		btnupdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnupdate.setFocusable(false);
		btnupdate.setBounds(206, 364, 125, 47);
		frmLsalleCollege.getContentPane().add(btnupdate);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action=JOptionPane.showConfirmDialog(null, "Are u sure?","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0) {
					try {
						String query="Delete from Studentinfo where id='"+textFieldid.getText()+"'";
						PreparedStatement pst=connection.prepareStatement(query);
						
						pst.execute();
						JOptionPane.showMessageDialog(null, "Removed Successfully!");
	 
						
						pst.close();
						
						
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2);
						
					}
					reloadtable();
			}
		}
			});
		btndelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btndelete.setFocusable(false);
		btndelete.setBounds(53, 446, 125, 47);
		frmLsalleCollege.getContentPane().add(btndelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadtable();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnClear.setFocusable(false);
		btnClear.setBounds(206, 446, 125, 47);
		frmLsalleCollege.getContentPane().add(btnClear);
		
		JLabel lblMontreal = new JLabel("Montreal");
		lblMontreal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMontreal.setBounds(107, 77, 91, 30);
		frmLsalleCollege.getContentPane().add(lblMontreal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(341, 128, 470, 492);
		frmLsalleCollege.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					int row=table.getSelectedRow();
					String id=table.getModel().getValueAt(row, 0).toString();
					

					String query="Select * from Studentinfo where id='"+id+"'";
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					while(rs.next()) {
						textFieldid.setText(rs.getString("id"));
						textFieldname.setText(rs.getString("Name"));
						textFieldcontact.setText(rs.getString("contact"));
						textFieldcourse.setText(rs.getString("course"));
						
					}
					pst.close();
					rs.close();

					
				}catch(Exception e2){
					JOptionPane.showMessageDialog(null, e2);

				}
			}
			
		});
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		
		JLabel lblSearchByName = new JLabel("Search By Name:");
		lblSearchByName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblSearchByName.setBounds(341, 77, 212, 30);
		frmLsalleCollege.getContentPane().add(lblSearchByName);
		
		textFieldsearch = new JTextField();
		textFieldsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					
					String query="select * from Studentinfo where name=?";
					
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, textFieldsearch.getText());
					ResultSet rs=pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					textFieldid.setText(null);
					textFieldname.setText(null);
					textFieldcontact.setText(null);
					textFieldcourse.setText(null);
					
					rs.close();
					pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
					
				}
				
			}
		});
		textFieldsearch.setText((String) null);
		textFieldsearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldsearch.setColumns(10);
		textFieldsearch.setBounds(518, 79, 146, 29);
		frmLsalleCollege.getContentPane().add(textFieldsearch);
		frmLsalleCollege.setTitle("LSalle College - Course Registration");
		frmLsalleCollege.setBounds(100, 100, 859, 668);
		frmLsalleCollege.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
