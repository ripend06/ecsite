package jp.co.internous.ecsite.model.form;
//------------------------------------------
//[model] データベースとのやり取りに使用される情報や、 フロントとバック間でのやり取りに使用される情報などを、 役割に応じて格納するための親パッケージです
//[form] modelの中でも、画面からJavaプログラムに送る データを管理するクラスを格納します。
//------------------------------------------

import java.io.Serializable;

public class GoodsForm implements Serializable {
	private static final long serialVersionUID = 1L;


		private long id;
		private String goodsName;
		private int price;
		
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		
		public String getGoodsName() {
			return goodsName;
		}
		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}
		
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
}
