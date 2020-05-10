package com.sample;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * DBテーブルの1レコード分に相当。
 */
// Lombok でゲッターセッターなど便利なメソッド等を自動生成
// JPA エンティティとして扱う

@Data
@Entity
@Table(name = "person1") // DBテーブル情報
public class Person {

    @Id // JPA にこの変数をオブジェクトのIDだと認識させる
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID自動生成
    @Column(name = "id", nullable = false) // DBテーブルのカラム情報
    @JsonProperty("id") // マッピングする JSON キー (名前)
    private Integer id;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name = "created_at", nullable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}