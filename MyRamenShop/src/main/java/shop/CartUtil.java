package shop;

import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.ResolveResult;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import shop.entity.CartItem;
import shop.entity.RamenProduct;

public class CartUtil {

	public static synchronized void saveProductToCart(HttpSession session, RamenProduct product, Integer num,
			Integer sum) {
		Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute("cart");

		if (cartItemMap == null) {
			cartItemMap = new HashMap<Integer, CartItem>();
		}
		// 判断当前购物车中是否包含此商品
		if (cartItemMap.containsKey(product.getId())) {
			CartItem currentCi = cartItemMap.get(product.getId());

			currentCi.setNum(currentCi.getNum() + num);
			currentCi.setSum(currentCi.getSum() + sum);
			cartItemMap.put(product.getId(), currentCi);

		} else {
			CartItem ci = new CartItem(product, num, sum);
			cartItemMap.put(product.getId(), ci);
		}

		if (session.getAttribute("ss") != null) {
			int t = (int) session.getAttribute("ss");
			t = t + sum;
			session.setAttribute("ss", t);
		} else {
			session.setAttribute("ss", sum);
		}

		session.setAttribute("cart", cartItemMap);

	}

	public static synchronized void updateItem(HttpSession session, RamenProduct product, Integer num, Integer sum) {
		Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute("cart");


			CartItem currentCi = cartItemMap.get(product.getId());
			int lastTotal = (int)session.getAttribute("ss")+ sum - currentCi.getSum() ;
			currentCi.setNum(currentCi.getNum() - currentCi.getNum() + num);
			currentCi.setSum(currentCi.getSum() - currentCi.getSum() + sum);
			
		
			
			cartItemMap.put(product.getId(), currentCi);
      
	
			
			
			session.setAttribute("ss",lastTotal);
			session.setAttribute("cart", cartItemMap);
		
		
		
		

	}

	public static int opitem(int price, int count) {
		int Result = price * count;
		return Result;
	}

	public static synchronized void deleteProductFromCart(HttpSession session, Integer productId) {
		Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute("cart");

		if (session.getAttribute("ss") != null) {
			int re = (int) session.getAttribute("ss") - cartItemMap.get(productId).getSum();
			session.setAttribute("ss", re);
		}

		if (cartItemMap != null) {
			cartItemMap.remove(productId);
		}

		session.setAttribute("cart", cartItemMap);

	}

	public static synchronized void cleanCart(HttpSession session) {
		Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute("cart");

		if (session.getAttribute("ss") != null) {
			int zero = (int) session.getAttribute("ss");
			zero = 0;
			session.setAttribute("ss", zero);
		}
		if (cartItemMap != null) {
			cartItemMap.clear();
		}
		session.setAttribute("cart", cartItemMap);
	}

	public static synchronized void discount(HttpSession session, String procode) {

		if (procode.equals("123")) {

			session.setAttribute("ss", (int) session.getAttribute("ss") - 100);

		} else if (procode.equals("12")) {
			session.setAttribute("ss", (int) session.getAttribute("ss") - 50);

		}

	}

//	    public static List<OrderItem> getOrderItemFromCart(HttpSession session){
//	        Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute("cart");
//	        logger.debug("获取商品信息 ： cart :"+cartItemMap);
//	        if(cartItemMap==null) cartItemMap = new HashMap<Integer, CartItem>();
//	        List<OrderItem> oiList = new ArrayList<OrderItem>();
//	        for(CartItem ci:cartItemMap.values()){
//	            OrderItem oi = new OrderItem();
//	            oi.setProduct(ci.getProduct());
//	            oi.setQuantity(ci.getTotal());
//	            oiList.add(oi);
//	        }
//	        return oiList;
//	    }
}
