package com.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DBアクセス用リポジトリ。
 * 何も実装しなくても、Spring Data JPA が標準で提供するメソッドが自動生成される。
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> { // エンティティと主キーの型を指定
}

// @Query("SELECT COUNT(*) FROM Person)
// のようにかくことがｄけいる
// https://qiita.com/yoshikawaa/items/c25715df81ba0d18a74f