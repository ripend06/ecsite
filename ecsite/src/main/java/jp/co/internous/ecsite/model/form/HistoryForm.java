package jp.co.internous.ecsite.model.form;
//------------------------------------------
//[model] データベースとのやり取りに使用される情報や、 フロントとバック間でのやり取りに使用される情報などを、 役割に応じて格納するための親パッケージです
//[form] modelの中でも、画面からJavaプログラムに送る データを管理するクラスを格納します。
//------------------------------------------

import java.io.Serializable;

public class HistoryForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
