//package com.bsu.pt.exam.client;
//
//import com.bsu.pt.exam.entity.Student;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.Optional;
//
//
////@FeignClient(url = "http://localhost:5002", name = "user-service")
//public interface UserServiceFeignClient {
//
//    @GetMapping(value = "/login/{username}")
//    Optional<Student> getByLogin(@PathVariable String username);
//
//    @PostMapping(value = "")
//    Student save(@RequestBody Student user);
//}