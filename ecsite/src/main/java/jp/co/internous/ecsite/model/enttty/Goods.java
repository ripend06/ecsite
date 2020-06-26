package jp.co.internous.ecsite.model.enttty;
//------------------------------------------
//[model] データベースとのやり取りに使用される情報や、 フロントとバック間でのやり取りに使用される情報などを、 役割に応じて格納するための親パッケージです
//[entity] DBテーブルの１レコードを表すクラスを格納します。
//------------------------------------------

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="goods")     //nameどこで使ってる？
	public class Goods {
		
		@Id
		@Column(name="id")     //nameどこで使ってる？
		@GeneratedValue(strategy=GenerationType.IDENTITY)     //IDフィールドの振る舞い方を指定します。 今回はAuto_incrementとして振る舞います。
		private long id;
		
		@Column(name="goods_name")     
		private String goodsName;
		
		@Column(name="price")
		private int price;
		
		@Column(name="updated_at")
		private Timestamp updatedAt;
		
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
		
		public Timestamp getUpdatedat() {
			return updatedAt;
		}
		public void setUpdated_at(Timestamp updatedAt) {
			this.updatedAt = updatedAt;
		}
	}