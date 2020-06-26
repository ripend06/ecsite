package jp.co.internous.ecsite.controller;
//-----------------------------------------------
//[contoroller] Java Servlet と類似の働きをするJavaクラスを格納します。具体的にはページ遷移、model/view間の橋渡しの役割を担います。 
//簡易なものであれば、ビジネスロジックが記述される場合もあります。
//------------------------------------------------

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.ecsite.model.dao.GoodsRepository;
import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.enttty.Goods;
import jp.co.internous.ecsite.model.enttty.User;
import jp.co.internous.ecsite.model.form.GoodsForm;
import jp.co.internous.ecsite.model.form.LoginForm;

@Controller
@RequestMapping("/ecsite/admin")
public class AdminController {
	
	@Autowired
	private UserRepository userRepos;
	
	@Autowired
	private GoodsRepository goodsRepos;
	
//-----------------------------------------------
//管理者初期ページ
//------------------------------------------------
	@RequestMapping("/")
	public String index() {
		return "adminindex";
	}
	
	
//-----------------------------------------------
//ログイン後ページ
//------------------------------------------------
	@PostMapping("/welcome")
	public String welcome(LoginForm form, Model m) {     //第１引数：値名,　第２引数：保管する値

		List<User> users = userRepos.findByUserNameAndPassword(form.getUserName(), form.getPassword());
		
		if (users != null && users.size() > 0) {     			  //?
			boolean isAdmin = users.get(0).getIsAdmin() != 0;     //?
			if (isAdmin) {    									  //?
				List<Goods> goods = goodsRepos.findAll();
				m.addAttribute("userName", users.get(0).getUserName());     //第２引数？
				m.addAttribute("password", users.get(0).getPassword());     //第２引数？
				m.addAttribute("goods", goods);
			}
		}
		
		//System.out.println(form.getUserName()+ " " + form.getPassword());
		
		return "welcome";
	}
	
	
//-----------------------------------------------
//新規追加画面
//------------------------------------------------
	@RequestMapping("/goodsMst")
	public String goodsMst(LoginForm form, Model m) {
		m.addAttribute("userName", form.getUserName());
		m.addAttribute("password", form.getPassword());
		
		return "goodsmst";
	}

	
//-----------------------------------------------
//追加後のWelcomeフォワード前ページ  →　Welcome.html
//------------------------------------------------
	@RequestMapping("/addGoods")
	public String addGoods(GoodsForm goodsForm, LoginForm loginForm, Model m) {
		m.addAttribute("userName", loginForm.getUserName());
		m.addAttribute("password", loginForm.getPassword());
		
		Goods goods = new Goods();
		goods.setGoodsName(goodsForm.getGoodsName());
		goods.setPrice(goodsForm.getPrice());
		goodsRepos.saveAndFlush(goods);     //セーブして更新
		
		return "forward:/ecsite/admin/welcome";     //フォワード先に移す
		}
	
	
//-----------------------------------------------
//商品削除　→　更新で Welcome.html
//------------------------------------------------
	@ResponseBody
	@PostMapping("/api/deleteGoods")
	public String deleteApi(@RequestBody GoodsForm f, Model m) {
		try {
			goodsRepos.deleteById(f.getId());     //deleteById?
		} catch (IllegalArgumentException e) {
			return "-1";
		}
		
		return "1";
	}
}
