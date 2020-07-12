package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import beans.Product;
import beans.ProductStore;
import constants.InventoryConstants;
import jaxB.XMLBinding;

import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class SearchProduct extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String emptyProductErrorMsg = "Proudct Name can't be Empty!";
	private final String notFoundErrorMsg = "Proudct Not Found!";
	private JTextField search_field;
	private static final String path = "C://Mayank/Inventory/product.xml";
	private static JTextField search_name_field;
	private static JTextField search_price_field;
	private static JTextField search_items_field;
	private static JTextPane search_desc_field;
	private static Product searchedProduct;
	private static ProductStore products;

	/**
	 * Create the panel.
	 */
	public SearchProduct() {
		setLayout(null);
		
		JLabel lblAddNewProduct = new JLabel("Search Product");
		lblAddNewProduct.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblAddNewProduct.setBounds(282, 13, 201, 43);
		add(lblAddNewProduct);
		
		JLabel lblProductName = new JLabel("Enter Product");
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProductName.setBounds(182, 79, 127, 32);
		add(lblProductName);
		
		search_field = new JTextField();
		search_field.setFont(new Font("Tahoma", Font.PLAIN, 16));
		search_field.setBounds(321, 81, 139, 31);
		add(search_field);
		search_field.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(507, 79, 113, 32);
		add(btnSearch);
		
		JPanel searchProductDetailPanel = new JPanel();
		//searchProductDetailPanel.setBackground(Color.YELLOW);
		searchProductDetailPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchProductDetailPanel.setBounds(129, 151, 572, 413);
		add(searchProductDetailPanel);
		searchProductDetailPanel.setLayout(null);
		
		JLabel lblProductDetail = new JLabel("Product Detail");
		lblProductDetail.setBounds(198, 49, 139, 32);
		searchProductDetailPanel.add(lblProductDetail);
		lblProductDetail.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel search_product_lbl = new JLabel("Name");
		search_product_lbl.setBounds(102, 108, 80, 31);
		searchProductDetailPanel.add(search_product_lbl);
		search_product_lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		search_name_field = new JTextField();
		search_name_field.setFont(new Font("Tahoma", Font.PLAIN, 16));
		search_name_field.setBounds(256, 113, 175, 26);
		searchProductDetailPanel.add(search_name_field);
		search_name_field.setDisabledTextColor(Color.BLACK);
		search_name_field.setColumns(10);
		
		JLabel search_price_lbl = new JLabel("Price");
		search_price_lbl.setBounds(102, 164, 97, 22);
		searchProductDetailPanel.add(search_price_lbl);
		search_price_lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		search_price_field = new JTextField();
		search_price_field.setFont(new Font("Tahoma", Font.PLAIN, 16));
		search_price_field.setDisabledTextColor(Color.BLACK);
		search_price_field.setBounds(256, 165, 175, 26);
		searchProductDetailPanel.add(search_price_field);
		search_price_field.setColumns(10);
		
		JLabel search_items_lbl = new JLabel("No Of Items");
		search_items_lbl.setBounds(102, 224, 97, 16);
		searchProductDetailPanel.add(search_items_lbl);
		search_items_lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		search_items_field = new JTextField();
		search_items_field.setFont(new Font("Tahoma", Font.PLAIN, 16));
		search_items_field.setDisabledTextColor(Color.BLACK);
		search_items_field.setBounds(256, 221, 175, 26);
		searchProductDetailPanel.add(search_items_field);
		search_items_field.setColumns(10);
		
		JLabel search_desc_lbl = new JLabel("Description");
		search_desc_lbl.setBounds(102, 278, 97, 16);
		searchProductDetailPanel.add(search_desc_lbl);
		search_desc_lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		search_desc_field = new JTextPane();
		search_desc_field.setFont(new Font("Tahoma", Font.PLAIN, 16));
		search_desc_field.setDisabledTextColor(Color.BLACK);
		search_desc_field.setBounds(256, 278, 175, 58);
		searchProductDetailPanel.add(search_desc_field);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(151, 368, 116, 32);
		searchProductDetailPanel.add(btnUpdate);
		
		// Update Button ActionListener
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validateSearchedProduct() == true) {
					try {
						updateProduct();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Product '"+ search_name_field.getText() + " 'Updated Successfully!");
					//Clear Form Data
					search_name_field.setText("");
					search_price_field.setText("");
					search_items_field.setText("");
					search_desc_field.setText("");
				}
			}
		});
		
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(315, 368, 116, 32);
		searchProductDetailPanel.add(btnDelete);
		
		//Search Button ActionListener
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!search_field.getText().isEmpty())
					findProductDetailByName(search_field.getText());
				else
					JOptionPane.showMessageDialog(null, emptyProductErrorMsg);
			}
		});
		
		//Delete Button ActionListener
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					deleteProduct();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Product '"+ search_name_field.getText() + " 'Deleted Successfully!");
				//Clear Form Data
				search_name_field.setText("");
				search_price_field.setText("");
				search_items_field.setText("");
				search_desc_field.setText("");
			}
		});
	}
	
	public void findProductDetailByName(String name) {
		XMLBinding xmlBinding = new XMLBinding();
		Unmarshaller unmarshaller = xmlBinding.getXMLUnMarshaller();
		File file = new File(path);
		try {
			products = (ProductStore) unmarshaller.unmarshal(file);
			ArrayList<Product> al;
			
			if(products.getProductList().stream().anyMatch(Product -> Product.getName().equalsIgnoreCase(name)))
			{
				al = new ArrayList<Product>();
				al.add(products.getProductList().stream().filter(Product -> Product.getName().equalsIgnoreCase(name)).findFirst().get());
				searchedProduct = new Product(al.get(0).getId(), al.get(0).getName(), al.get(0).getPrice(), al.get(0).getItems(), al.get(0).getDesc());
				showSearchedProduct();	
			}
			else {
				JOptionPane.showMessageDialog(null, notFoundErrorMsg);
			}
				
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showSearchedProduct() 
	{
		search_name_field.setText(searchedProduct.getName());
		search_price_field.setText(searchedProduct.getPrice().toString());
		search_items_field.setText(searchedProduct.getItems().toString());
		search_desc_field.setText(searchedProduct.getDesc());
	}
	
	
	public boolean validateSearchedProduct() {
		boolean isProductValid = false;
		if(search_name_field.getText().isEmpty())
			JOptionPane.showMessageDialog(null, "Please Enter Product Name !");
		else if(search_price_field.getText().isEmpty())
			JOptionPane.showMessageDialog(null, "Please Enter Product Price !");
		else if(search_items_field.getText().isEmpty() || search_items_field.getText().equals("0"))
			JOptionPane.showMessageDialog(null, "Please Enter No Of Items !");
		else if(search_items_field.getText().isEmpty())
			JOptionPane.showMessageDialog(null, "Please Enter Product Description !");
		else
			isProductValid = true;
		
		return isProductValid;
	} 
	
	
	public static void updateProduct() throws Exception {
	    File file = new File(InventoryConstants.filepath);
	    
	    //Read file
	    Unmarshaller jaxbUnmarshaller = new XMLBinding().getXMLUnMarshaller();
        ProductStore products = (ProductStore) jaxbUnmarshaller.unmarshal(file);
        ArrayList<Product> productsList= products.getProductList();
        
        //productsList.forEach((p) -> p.setItems(5));
        
        //Update file
        for(Product p : productsList) {
        	if(p.getId() == searchedProduct.getId()) {
        		p.setName(search_name_field.getText());
        		p.setPrice(Double.parseDouble(search_price_field.getText()));
        		p.setItems(Integer.parseInt(search_items_field.getText()));
        		p.setDesc(search_desc_field.getText());
        		break;
        	}
        }
        
        //Write file
        products.setProductList(productsList);
        Marshaller jaxbMarshaller = new XMLBinding().getXMLMarshaller();
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    jaxbMarshaller.marshal(products, file);
	    jaxbMarshaller.marshal(products, System.out);
	    
	}
	
	public static void deleteProduct() throws Exception {
	    File file = new File(InventoryConstants.filepath);
	    
	    //Read file
	    Unmarshaller jaxbUnmarshaller = new XMLBinding().getXMLUnMarshaller();
        ProductStore products = (ProductStore) jaxbUnmarshaller.unmarshal(file);
        ArrayList<Product> productsList= products.getProductList();
        
        //Update file
        for(Product p : productsList) {
        	if(p.getId() == searchedProduct.getId()) {
        		productsList.remove(p);
        		break;
        	}
        }
        
        //Write file
        products.setProductList(productsList);
        Marshaller jaxbMarshaller = new XMLBinding().getXMLMarshaller();
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    jaxbMarshaller.marshal(products, file);
	    jaxbMarshaller.marshal(products, System.out);
	    
	}
}
