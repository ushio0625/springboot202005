package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// sampleコード
// https://qiita.com/niwasawa/items/bd97d4eb179fe5a7ec3f
@SpringBootApplication
@RestController
public class SampleController {

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }

    @Autowired
    private SampleService service;

    @RequestMapping(value = "/findMaxId")
    public int get_findMaxId() {
        return service.findMaxId();
    }

    @RequestMapping(value = "/findNameBind/{id}")
    public String get_findNameBind(@PathVariable("id")  int id) {
        return service.findNameBindById(id);
    }

    @RequestMapping(value = "/findNameBindName/{id}")
    public String get_findNameBindName(@PathVariable("id")  int id) {
        return service.findNameBindNameById(id);
    }
    @RequestMapping(value = "/findById/{id}")
    public Person findById(@PathVariable("id") int id) {
        return service.findById(id);
    }
    @RequestMapping(value = "/findAllById")
    public List<Person> findAllById() {
        return service.findAllById();
    }

    @RequestMapping(value = "/findZero")
    public List<Person> findZero() {
        return service.findZero();
    }

    @RequestMapping(value = "/insert/{name}")
    public List<Person>  findById(@PathVariable("name") String name) {
        service.insert(name);
        return service.findAllById();
    }

    @RequestMapping(value = "/update/{id}/{name}")
    public List<Person> update(@PathVariable("id") int id,@PathVariable("name") String name) {
        service.update(id,name);
        return service.findAllById();
    }

    @RequestMapping(value = "/delete/{id}")
    public List<Person> delete(@PathVariable("id") int id) {
        service.delete(id);
        return service.findAllById();
    }

    // 指定した name のデータを追加する
    @RequestMapping("/add/{name}")
    public List<Person> add_test1(@ModelAttribute Person person) {
        service.save(person);
        return service.findAllById();
    }

}