package jp.co.internous.ecsite.model.form;
//------------------------------------------
//[model] データベースとのやり取りに使用される情報や、 フロントとバック間でのやり取りに使用される情報などを、 役割に応じて格納するための親パッケージです
//[form] modelの中でも、画面からJavaプログラムに送る データを管理するクラスを格納します。
//------------------------------------------

import java.io.Serializable;

public class LoginForm implements Serializable {     //階層を無くし複数データを保存できるように直列化
	private static final long serialVersionUID = 1L;     //ファイルやデータとなって、他のマシンや環境でも再生できないようにキーを指定
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
