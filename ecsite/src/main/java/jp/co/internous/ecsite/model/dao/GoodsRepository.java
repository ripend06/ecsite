package jp.co.internous.ecsite.model.dao;
//------------------------------------------
//[model] データベースとのやり取りに使用される情報や、 フロントとバック間でのやり取りに使用される情報などを、 役割に応じて格納するための親パッケージです
//[dao] Data Access Object の略。 modelの中でも、DBにアクセスする役割をもつクラス・インターフェイ スを格納します。
//------------------------------------------

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.internous.ecsite.model.enttty.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
	
	

}
