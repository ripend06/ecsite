package jp.co.internous.ecsite.model.form;
//------------------------------------------
//[model] データベースとのやり取りに使用される情報や、 フロントとバック間でのやり取りに使用される情報などを、 役割に応じて格納するための親パッケージです
//[form] modelの中でも、画面からJavaプログラムに送る データを管理するクラスを格納します。
//------------------------------------------

import java.io.Serializable;
import java.util.List;

public class CartForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long userId;
	private List<Cart> cartList;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public List<Cart> getCartList(){
		return cartList;
	}
	
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

}
