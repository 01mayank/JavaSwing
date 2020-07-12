package beans;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//XmlElement sets the name of the entities
@XmlRootElement(name = "products")
public class ProductStore 
{
    
    private ArrayList<Product> productList;
    
    
	public ArrayList<Product> getProductList() {
		return productList;
	}
	
	@XmlElement(name="Product")
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
    
}
