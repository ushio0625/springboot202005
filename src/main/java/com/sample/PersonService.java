package com.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.List;

/**
 * サービスクラス。
 * ここでDBトランザクションの管理をする。
 */
//@Transactional(readOnly = true,timeout = 60) //読み込専用,タイムアウト60

@Service
// デフォルト設定　メソッド開始時にトランザクションを開始、終了時にコミットする
//classに対してのトランザクション設定ができる
@Transactional
public class PersonService {

    @Autowired
    PersonRepository repository;
    //ここに、Transactional設定すると　メソッドに対してトランザクション設定できる
    public void add1(Person person) {
        // データベースに格納
        repository.save(person);

        // 50%の確率で例外を発生させる。トランザクションマネージャが自動でロールバックしてくれる
        if (new SecureRandom().nextBoolean()) {
            throw new RuntimeException("ちゅどーん");
        }
    }


    @Autowired
    TransactionTemplate transactiontemplate;
    @Autowired
    PlatformTransactionManager txManager;
//    private DataSourceTransactionManager txManager;

    //ここに、Transactional設定すると　メソッドに対してトランザクション設定できる
    public void add2(Person person) {
        DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
        txDef.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        TransactionStatus txStatus = txManager.getTransaction(txDef);

        // データベースに格納
        repository.save(person);

        // 50%の確率で例外を発生させる。トランザクションマネージャが自動でロールバックしてくれる
        if (new SecureRandom().nextBoolean()) {
            txManager.rollback(txStatus);
            throw new RuntimeException("ちゅどーん");
        }
            txManager.commit(txStatus);
    }


    public List<Person> getPersons() {
        return repository.findAll();
    }
}