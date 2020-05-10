package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SampleService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // 1件返すサンプル
    public int findMaxId() {
        // 実行する SQL を組み立てて実行
        String query = "SELECT Max(id) from person";
        return jdbcTemplate.queryForObject(query,Integer.class);
    }

    // バインド変数を利用したSQL
    public String findNameBindById(int name_id) {
        // 実行する SQL を組み立てて実行
        String query = "SELECT name from person where id = ?";
        return jdbcTemplate.queryForObject(query,String.class,name_id);
    }

    // 名前付きバインド変数を利用したSQL
    public String findNameBindNameById(int name_id) {
        // 実行する SQL を組み立てて実行
        String query = "SELECT name from person where id = :name_id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name_id",name_id);
        return namedParameterJdbcTemplate.queryForObject(query,params,String.class);
    }

    // 名前付きバインド変数を利用したSQL
    public Person findById(int name_id) {
        // 実行する SQL を組み立てて実行
        String query = "SELECT * from person where id = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(query,name_id);
        Person person = new Person();
        person.setId((Integer) result.get("id"));
        person.setName((String) result.get("name") );
        return person;
    }

    // データの一覧を返す
    public List<Person> findAllById() {
        String query = "SELECT * from person";
        List<Map<String, Object>>resultList = jdbcTemplate.queryForList(query);
        List<Person> personList = new ArrayList<Person>();
        // 実行する SQL を組み立てて実行
        for(Map<String, Object> result:resultList) {
            Person person = new Person();
            person.setId((Integer) result.get("id"));
            person.setName((String) result.get("name") );
            personList.add(person);
        }
        return personList;
    }

    // データの一覧を返す
    public List<Person> findZero() {
        String query = "SELECT * from person where id = 0";
        //queryForListは0件のとき空を返す
        //jdbcTemplateはqueryForList以外の場合例外処理になる
        List<Map<String, Object>>resultList = jdbcTemplate.queryForList(query);
        List<Person> personList = new ArrayList<Person>();
        // 実行する SQL を組み立てて実行
        for(Map<String, Object> result:resultList) {
            Person person = new Person();
            person.setId((Integer) result.get("id"));
            person.setName((String) result.get("name") );
            personList.add(person);
        }
        return personList;
    }

    public int insert(String name){
        String query = "INSERT INTO person (name) VALUES(?)";
        return jdbcTemplate.update(query,name);
    }

   public int update(int id,String name){
       String query = "UPDATE person SET name = ? where id = ?";
       return jdbcTemplate.update(query,name,id);
   }

  public int delete(int id){
      String query = "DELETE FROM person where id = ?";
      return jdbcTemplate.update(query,id);
  }

    //sampleコード
    public List<Person> findAll(){
        String query = "SELECT * from person";
        List<Person> persons = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Person.class));
        return persons;
    }

    public Person save(Person person) {
        // 実行する SQL を組み立てる
        SqlParameterSource param = new BeanPropertySqlParameterSource(person);
        SimpleJdbcInsert insert =
                new SimpleJdbcInsert(jdbcTemplate)
                        .withTableName("person")
                        .usingGeneratedKeyColumns("id");
        // SQL を実行して、AUTO_INCREMENT の値を取得する
        Number key = insert.executeAndReturnKey(param);
        person.setId(key.intValue());
        System.out.println("Add: " + person);
        return person;
    }
    /*
     ** 変換形式は飛ばす
     ** RowMapper
     ** ResultSetExtractor
     ** RowCallbackHandler
     */

    /* 下記は説明のみ資料作成が必要
     ** ローカルトランザクション (単一データストア)
     ** グローバルトランザクション (複数データストア)
     */

    /*
     * 分離レベルと伝達レベル
     * データアクセスのハンドリング
     */

}