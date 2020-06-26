package jp.co.internous.ecsite.controller;
//-----------------------------------------------
//[contoroller] Java Servlet と類似の働きをするJavaクラスを格納します。具体的にはページ遷移、model/view間の橋渡しの役割を担います。 
//簡易なものであれば、ビジネスロジックが記述される場合もあります。
//------------------------------------------------

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.ecsite.model.dao.GoodsRepository;
import jp.co.internous.ecsite.model.dao.PurchaseRepository;
import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.dto.HistoryDto;
import jp.co.internous.ecsite.model.dto.LoginDto;
import jp.co.internous.ecsite.model.enttty.Goods;
import jp.co.internous.ecsite.model.enttty.Purchase;
import jp.co.internous.ecsite.model.enttty.User;
import jp.co.internous.ecsite.model.form.CartForm;
import jp.co.internous.ecsite.model.form.HistoryForm;
import jp.co.internous.ecsite.model.form.LoginForm;

//RequestMappingアノテーションにより localhost:8080/ecsite/ のURLで アクセスできるよう設定しています。
@Controller
@RequestMapping("/ecsite")
public class IndexController {
	
	//Userエンティティからuserテーブルにア クセスするDAO
	@Autowired
	private UserRepository userRepos;
	
	//Goodsエンティティからgoodsテーブル にアクセスするDAOです。
	@Autowired
	private GoodsRepository goodsRepos;
	
	@Autowired
	private PurchaseRepository purchaseRepos;
	
	//WebサービスAPIとして作成するためJSON形式を 扱えるようGsonをインスタンス化しておく
	private Gson gson = new Gson();
	
	
//-----------------------------------------------
//お客様初期ページ
//------------------------------------------------
//トップページ（index.html）に遷移する メソッドです。
	@RequestMapping("/")
	public String index(Model m) {
		
		//goodsテーブルから取得した商品エンティテ ィの一覧を、フロントに渡すModelに追加し ています。
		List<Goods> goods = goodsRepos.findAll();
		m.addAttribute("goods",goods);
		
		return "index";
	}
	
	
//-----------------------------------------------
//ログイン機能
//------------------------------------------------
	@ResponseBody
	@PostMapping("/api/login")
	public String loginApi(@RequestBody LoginForm form) {
		
		//DBテーブル（user）から、ユーザ名 とパスワードで検索し、結果を取得し ています。
		List<User> users = userRepos.findByUserNameAndPassword(form.getUserName(),form.getPassword());
		
		//その後、DTOをゲストの情報で初期化し、検索結果が存在していた場合のみ、実在のユーザ情報をDTOに詰めています。
		LoginDto dto = new LoginDto(0, null, null, "ゲスト");
		if (users.size() > 0) {
			dto = new LoginDto(users.get(0));
		}
		//最終的に、DTOをJSONオブジェクト として画面側に返しています。
		return gson.toJson(dto);
	}
	

//-----------------------------------------------
//購入処理
//------------------------------------------------
	@ResponseBody
	@PostMapping("/api/purchase")
	public String purchaseApi(@RequestBody CartForm f) {
		
		f.getCartList().forEach((c) -> {
			long total = c.getPrice() * c.getCount();
			purchaseRepos.persist(f.getUserId(), c.getId(), c.getGoodsName(), c.getCount(), total);
		});
		
		return String.valueOf(f.getCartList().size());
	}

	
//-----------------------------------------------
//履歴
//------------------------------------------------
	@ResponseBody
	@PostMapping("/api/history")
	public String historyApi(@RequestBody HistoryForm form) {
		String userId = form.getUserId();
		List<Purchase> history = purchaseRepos.findHistory(Long.parseLong(userId));
		List<HistoryDto> historyDtoList = new ArrayList<>();
		history.forEach((v) -> {
			HistoryDto dto = new HistoryDto(v);
			historyDtoList.add(dto);
			});
		
			return gson.toJson(historyDtoList);
	}
}
