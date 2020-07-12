package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import beans.Product;
import beans.ProductStore;
import constants.InventoryConstants;
import jaxB.XMLBinding;

import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

public class AddProductPage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField name_field;
	private static JTextField price_field;
	private static JTextField items_field;
	private static JTextPane desc_field;
	private static JTextField total_price_field;
	private Double totalPrice;

	/**
	 * Create the panel.
	 */
	public AddProductPage() {
		setPreferredSize(new Dimension(820, 800));
		//setBackground(Color.CYAN);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//panel.setBackground(Color.YELLOW);
		panel.setBounds(120, 140, 574, 423);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(120, 63, 80, 31);
		panel.add(lblName);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblPrice = new JLabel("Price Per Item");
		lblPrice.setBounds(120, 119, 118, 22);
		panel.add(lblPrice);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblItems = new JLabel("No Of Items");
		lblItems.setBounds(120, 183, 97, 16);
		panel.add(lblItems);
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblDesc = new JLabel("Description");
		lblDesc.setBounds(120, 300, 97, 16);
		panel.add(lblDesc);
		lblDesc.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		name_field = new JTextField();
		name_field.setBounds(274, 68, 191, 31);
		panel.add(name_field);
		name_field.setColumns(10);
		
		price_field = new JTextField();
		price_field.setBounds(274, 116, 191, 31);
		panel.add(price_field);
		price_field.setColumns(10);
		
		items_field = new JTextField();
		items_field.setBounds(274, 171, 191, 31);
		panel.add(items_field);
		items_field.setColumns(10);
		
		desc_field = new JTextPane();
		desc_field.setBounds(274, 287, 191, 62);
		panel.add(desc_field);
		
		items_field.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				//JOptionPane.showMessageDialog(null, Double.parseDouble(price_field.getText()) * Double.parseDouble(items_field.getText()));
				totalPrice = Double.parseDouble(price_field.getText()) * Double.parseDouble(items_field.getText());
				total_price_field.setText(totalPrice.toString());
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.setBounds(224, 375, 161, 35);
		panel.add(btnAddProduct);
		
		//Add Button ActionListener
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File file = new File(InventoryConstants.filepath);
				    
				    //Read file
				    Unmarshaller jaxbUnmarshaller = new XMLBinding().getXMLUnMarshaller();
			        ProductStore products = (ProductStore) jaxbUnmarshaller.unmarshal(file);
			        List<Product> productsList= products.getProductList();
			        
			        int id = Collections.max(productsList, Comparator.comparing(p -> p.getId())).getId() + 1;
					String name = !name_field.getText().isEmpty() ? name_field.getText() : "";
					Double price = !price_field.getText().isEmpty() ? Double.parseDouble(price_field.getText()) : 0.0;
					int items = !items_field.getText().isEmpty() ? Integer.parseInt(items_field.getText()) : 0;
					String desc = !desc_field.getText().isEmpty() ? desc_field.getText() : "";
					beans.Product product = new Product(id, name, price, items, desc);
				
					if(validateProductDetail(product) == true) {
						saveProduct(InventoryConstants.filepath, product);
					}
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		btnAddProduct.setForeground(Color.BLACK);
		btnAddProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblTotalPrice = new JLabel("Total Price");
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalPrice.setBounds(120, 229, 118, 22);
		panel.add(lblTotalPrice);
		
		total_price_field = new JTextField();
		total_price_field.setDisabledTextColor(Color.BLACK);
		total_price_field.setEnabled(false);
		total_price_field.setBounds(274, 230, 191, 31);
		panel.add(total_price_field);
		total_price_field.setColumns(10);
		
		JLabel lblAddNewProduct = new JLabel("Add New Product");
		lblAddNewProduct.setBounds(315, 49, 201, 43);
		add(lblAddNewProduct);
		lblAddNewProduct.setFont(new Font("Algerian", Font.PLAIN, 20));

	}
	
	
	public static void saveProduct(String filename, beans.Product product) throws Exception {
	    File file = new File(filename);
	    Marshaller jaxbMarshaller;
	    
	    //Read file
	    Unmarshaller jaxbUnmarshaller = new XMLBinding().getXMLUnMarshaller();
        ProductStore products = (ProductStore) jaxbUnmarshaller.unmarshal(file);
        ArrayList<Product> productsList= products.getProductList();
        if(products.getProductList().stream().anyMatch(P -> P.getName().equalsIgnoreCase(product.getName()))) {
        	JOptionPane.showMessageDialog(null, "Product with Name "+ product.getName() + " Already Exists !");
        }
        else {
	        productsList.add(product);
	        products.setProductList(productsList);
	        jaxbMarshaller = new XMLBinding().getXMLMarshaller();
	
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    jaxbMarshaller.marshal(products, file);
		    jaxbMarshaller.marshal(products, System.out);
		    
		    JOptionPane.showMessageDialog(null, "Product '"+ name_field.getText() + " 'Added Successfully!");
			//Clear Form Data
			name_field.setText("");
			price_field.setText("");
			total_price_field.setText("");
			items_field.setText("");
			desc_field.setText("");
        }
	}
	
	public boolean validateProductDetail(Product product) {
		boolean isProductValid = false;
		if(product.getName().isEmpty())
			JOptionPane.showMessageDialog(null, "Please Enter Product Name !");
		else if(product.getPrice().toString().isEmpty() || product.getPrice() == 0.0)
			JOptionPane.showMessageDialog(null, "Please Enter Product Price !");
		else if(product.getItems().toString().isEmpty() || product.getItems() == 0)
			JOptionPane.showMessageDialog(null, "Please Enter No Of Items !");
		else if(product.getDesc().isEmpty())
			JOptionPane.showMessageDialog(null, "Please Enter Product Description !");
		else
			isProductValid = true;
			
		
		return isProductValid;
	} 
}
