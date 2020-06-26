package jp.co.internous.ecsite.model.dao;
//------------------------------------------
//[model] データベースとのやり取りに使用される情報や、 フロントとバック間でのやり取りに使用される情報などを、 役割に応じて格納するための親パッケージです
//[dao] Data Access Object の略。 modelの中でも、DBにアクセスする役割をもつクラス・インターフェイ スを格納します。
//------------------------------------------

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import jp.co.internous.ecsite.model.enttty.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
	
	@Query(value="SELECT * FROM purchase p " +
			"INNER JOIN goods g " +      //内部結合
			"ON p.goods_id = g.id " +     //条件
			"WHERE  created_at =  (" +     //WHERE?
			"SELECT MAX(created_at) FROM purchase p WHERE p.user_id = :userId) ",     //最大値　:（userId）←なにこれ?
			nativeQuery=true)     //SQLを文字列で記述し、そのままDBに渡す方法
	List<Purchase> findHistory(@Param("userId") long userId);
	
	@Query(value="INSERT INTO purchase (user_id, goods_id, goods_name, item_count, total, created_at) " +
			"VALUES (?1, ?2, ?3, ?4, ?5, now())", nativeQuery=true)
	@Transactional
	@Modifying
	void persist(@Param("userId") long userId,
				 @Param("goodsId") long goodsId,
				 @Param("goodsName") String goodsName,
				 @Param("itemCount") long itemCount,
				 @Param("total") long total);
	
}
