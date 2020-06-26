package jp.co.internous.ecsite.model.enttty;
//------------------------------------------
//[model] データベースとのやり取りに使用される情報や、 フロントとバック間でのやり取りに使用される情報などを、 役割に応じて格納するための親パッケージです
//[entity] DBテーブルの１レコードを表すクラスを格納します。
//------------------------------------------

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//クラス宣言部です。 @Entityアノテーションを付与すると、Springの機能により 当該クラスはEntityとして振る舞うようになります。
@Entity
@Table(name="user")     //@Tableアノテーションは、DBにある「どのテーブルの実体 なのか」を指定するものです。
public class User {
	
	@Id     //プライマリキーであることを指定します。
	@Column(name="id")     //テーブルのどのカラムとマッピングするかを指定します。
	@GeneratedValue(strategy=GenerationType.IDENTITY)     //IDフィールドの振る舞い方を指定します。 今回はAuto_incrementとして振る舞います。
	private long id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="is_admin")
	private int isAdmin;
	
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
	
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
}


