package shop.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import shop.entity.CartItem;
import shop.entity.RamenProduct;

@Service
public class CartService {

	public  synchronized void saveProductToCart(HttpSession session, RamenProduct product, Integer num,
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

	public  synchronized void updateItem(HttpSession session, RamenProduct product, Integer num, Integer sum) {
		Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute("cart");

		CartItem currentCi = cartItemMap.get(product.getId());
		int lastTotal = (int) session.getAttribute("ss") + sum - currentCi.getSum();
		currentCi.setNum(currentCi.getNum() - currentCi.getNum() + num);
		currentCi.setSum(currentCi.getSum() - currentCi.getSum() + sum);

		cartItemMap.put(product.getId(), currentCi);

		session.setAttribute("ss", lastTotal);
		session.setAttribute("cart", cartItemMap);

	}

	public  synchronized int opitem(int price, int count) {
		int Result = price * count;
		return Result;
	}

	public  synchronized void deleteProductFromCart(HttpSession session, Integer productId) {
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

	public  synchronized void cleanCart(HttpSession session) {
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



}
