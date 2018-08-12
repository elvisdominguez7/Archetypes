#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import org.springframework.hateoas.ResourceSupport;


public class Invoice extends ResourceSupport {
    private long invoiceId;
    private long UserId;
    private double price;
    private String title;
    private String firstName;
    private String lastName1;
    
    
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName1;
	}

	public void setLastName(String lastName) {
		this.lastName1 = lastName;
	}
    
    
    public Invoice() {}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long UserId) {
        this.UserId = UserId;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long id) {
        this.invoiceId = id;
    }
}
