package shop.entity;

import java.io.Serializable;

public class CartItem implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RamenProduct product;
    private Integer num;
    private Integer sum;

    public CartItem() {
    	
    }
    public CartItem(RamenProduct product, Integer num,Integer sum) {
        this.product = product;
        this.num = num;
        this.sum = sum;
    }

    public RamenProduct getProduct() {
        return product;
    }

    public void setProduct(RamenProduct product) {
        this.product = product;
    }


	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}


}
