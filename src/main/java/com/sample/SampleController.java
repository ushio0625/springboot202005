package com.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
// org.slf4j.Logger 型の static final 変数 log を自動生成
//sample Qiita
//https://qiita.com/niwasawa/items/a7f707b58a00ad7832dd


@SpringBootApplication
@RestController
public class SampleController {

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }

    @Autowired
    private PersonService service;

    // 指定した name のデータを追加する
    // 宣言トランザクション
    @RequestMapping("/add1/{name}")
    public Response add1(@PathVariable("name") String name){
        Person person = new Person();
        System.out.println("SampleController#add");
        String message = "success";
        try {
            person.setName(name);
            person.setCreatedAt(LocalDateTime.now());
            System.out.println("Before PersonService#add");
            // データを追加する
            service.add1(person);
            System.out.println("After PersonService#add");
        } catch (Exception e) {
            System.out.println("PersonService#add threw an exception.");
            message = e.getMessage();
        }

        // 全データを取得
        System.out.println("Before PersonService#getPersons");
        List<Person> persons = service.getPersons();
        System.out.println("After PersonService#getPersons");

        // JSON レスポンスを生成
        return Response.builder()
                .result(
                        Response.Result.builder()
                                .message(message)
                                .count(persons.size())
                                .build())
                .persons(persons)
                .build();
    }
    //明示的トランザクション
    //Qiita
    //https://qiita.com/NagaokaKenichi/items/a279857cc2d22a35d0dd
    //第6回 Spring環境におけるトランザクション処理
    //https://dev.classmethod.jp/articles/transaction-management-in-spring/
    @RequestMapping("/add2/{name}")
    public Response add2(@PathVariable("name") String name){
        Person person = new Person();

        System.out.println("SampleController#add");

        String message = "success";
        try {
            person.setName(name);
            person.setCreatedAt(LocalDateTime.now());
            System.out.println("Before PersonService#add");
            // データを追加する
            service.add2(person);
            System.out.println("After PersonService#add");
        } catch (Exception e) {
            System.out.println("PersonService#add threw an exception.");
            message = e.getMessage();
        }

        // 全データを取得
        System.out.println("Before PersonService#getPersons");
        List<Person> persons = service.getPersons();
        System.out.println("After PersonService#getPersons");

        // JSON レスポンスを生成
        return Response.builder()
                .result(
                        Response.Result.builder()
                                .message(message)
                                .count(persons.size())
                                .build())
                .persons(persons)
                .build();
    }

}