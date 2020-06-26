package jp.co.internous.ecsite.model.dto;
//------------------------------------------
//[model] データベースとのやり取りに使用される情報や、 フロントとバック間でのやり取りに使用される情報などを、 役割に応じて格納するための親パッケージです
//[dto] Data Transfer Object の略。 modelの中でも、Javaプログラムから画面に送るデータを 管理するクラスを格納します。
//------------------------------------------


import jp.co.internous.ecsite.model.enttty.User;

public class LoginDto {

	private long id;
	private String userName;
	private String password;
	private String fullName;
	
	//LoginDto は、コンストラクタを３つ持ちます。 これは、以下の3パターンでインスタンス化 できるようにするためです。
	//①インスタンス化の際に初期設定せず、 あとから一つずつSetterを使って データをセット
	public LoginDto() {}
	
	//②Userエンティティをまとめて受け取りデータをセット
	public LoginDto(User user) {
		this.id = user.getId();
		this.userName =user.getUserName();
		this.password =user.getPassword();
		this.fullName =user.getFullName();
	}
	
	//③引数を分けて受け取り、データをセット
	public LoginDto(long id, String userName, String password, String fullName) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}
	


	public long getId() {
		return id;
	}	
	public void setId(long id) {
		this.id = id;
	}
	
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
	
	public String getFullName() {
		return fullName;
	}	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	}
