package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import beans.Product;
import beans.ProductStore;
import jaxB.XMLBinding;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

public class ViewProducts extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String path = "C://Mayank/Inventory/product.xml";
	final JTable table;
	JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public ViewProducts() {
		//setBackground(Color.YELLOW);
		setPreferredSize(new Dimension(1050, 923));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		table = new JTable();
		table.setPreferredSize(new Dimension(800, 2000));
		table.setPreferredScrollableViewportSize(new Dimension(650, 900));
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setRowHeight(20);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		
		//Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);
		scrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setViewportBorder(new CompoundBorder());
		scrollPane.setPreferredSize(new Dimension(800, 800));
		add(scrollPane);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		ArrayList<beans.Product> productsList = null;
		Object[] obj = null;
		try {
				Unmarshaller unmarsheller = new XMLBinding().getXMLUnMarshaller();
				File file = new File(path);
				ProductStore products = (ProductStore) unmarsheller.unmarshal(file);
				productsList= products.getProductList();
			} 
		catch (JAXBException ex) 
			{
				ex.printStackTrace(); 
			}
		
		String[] columnNames = {"Product Name", "Price", "No Of Items", "Description"};
		
		DefaultTableModel dataModel = new DefaultTableModel();
	    dataModel.setColumnIdentifiers(columnNames);
	    obj = new Object[columnNames.length];
	   
		for(Product p : productsList) {
				obj[0] = p.getName();
				obj[1] = p.getPrice();
				obj[2] = p.getItems();
				obj[3] = p.getDesc();
				dataModel.addRow(obj);
		}
		table.setModel(dataModel);
	}

}
