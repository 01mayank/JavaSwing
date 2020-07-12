package beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	
	private int id;
	private String name;
    private Double price;
    private Integer items;
    private String desc;

    public Product(int id, String name, double price, int items, String desc) {
    	this.id = id;
        this.name = name;
        this.price = price;
        this.items = items;
        this.desc = desc;
    }
    
    public Product() {
    	 
    }

    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", items=" + items +
                ", desc=" + desc +
                '}';
    }
}