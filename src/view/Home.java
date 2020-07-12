package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import constants.InventoryConstants;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Home jFrame; 
	private static final String firstTabName = "Add Product";
	private static final String secondTabName = "View Products";
	private static final String thirdTabName = "Search Product";
	private static final String title = "Inventory Management System";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showHomePage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 722);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setTitle(InventoryConstants.appTitle);
		Image icon = Toolkit.getDefaultToolkit().getImage(InventoryConstants.titleImg);
		setIconImage(icon);
	}
	
	public static void showHomePage() {
		jFrame = new Home();
		jFrame.setBounds(100, 100, 991, 439);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Changing Tab Head Padding
		UIManager.put("TabbedPane.tabInsets", new Insets(15, 15, 15, 15));
				
		//Changing color of Active Tab
		//UIManager.put("TabbedPane.selected", Color.CYAN);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		Font font = new Font("Times New Roman", Font.BOLD, 18);
		tabbedPane.setFont(font);
		
		
		//First Panel
		JPanel firstPanel = new JPanel();
		//Adding Panel to show when clicked first add product tab
		JPanel panelForFirstTab = new JPanel();
		panelForFirstTab.setPreferredSize(new Dimension(1200,2000));
		GridLayout gridLayoutForPanelFirstTab = new GridLayout(5,4);
		//panelForFirstTab.setLayout(gridLayoutForPanelFirstTab);
		panelForFirstTab.add(new AddProductPage());
		firstPanel.add(panelForFirstTab);
		
		
		//Second Panel
		JPanel secondPanel = new JPanel();
		//GridLayout gridLayout2 = new GridLayout(2,1);
		//secondPanel.setLayout(gridLayout2);
		
		//Adding Panel to show when clicked second view products tab
		JPanel panelForSecondTab = new JPanel();
		panelForSecondTab.setPreferredSize(new Dimension(1500,2000));
		//panelForSecondTab.setBackground(Color.CYAN);
		ViewProducts viewProduct = new ViewProducts();
		panelForSecondTab.add(viewProduct);
		secondPanel.add(panelForSecondTab);
		
		//Third Panel
		JPanel thirdPanel = new JPanel();
		//GridLayout gridLayout3 = new GridLayout(2,1);
		//thirdPanel.setLayout(gridLayout3);
		
		//Adding Panel to show when clicked third product search tab
		JPanel panelForThirdTab = new JPanel();
		panelForThirdTab.setPreferredSize(new Dimension(800,3000));
		GridLayout gridLayoutForPanelThirdTab = new GridLayout(4,2);
		panelForThirdTab.setLayout(gridLayoutForPanelThirdTab);
		panelForThirdTab.add(new SearchProduct());
		thirdPanel.add(panelForThirdTab);
		
		
		
		//Adding Panels to Tabbed Pane
		tabbedPane.add(firstTabName, firstPanel);
		tabbedPane.add(secondTabName, secondPanel);
		tabbedPane.add(thirdTabName, thirdPanel);
		
		//Set Tabs placement - Top, Right, Left, Bottom
		tabbedPane.setTabPlacement(SwingConstants.TOP);
		
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent changeEvent) {
				System.out.println("Active Tab Position: " + tabbedPane.getSelectedIndex());
				
				String selectedTab = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
				if(selectedTab != null && !selectedTab.equals("") && selectedTab.equalsIgnoreCase("View Products")) {
					viewProduct.actionPerformed(null);
				}
			}
		});
		
		jFrame.getContentPane().add(tabbedPane);
		jFrame.setVisible(true);
	}

}
