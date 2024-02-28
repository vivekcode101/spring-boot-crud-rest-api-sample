package net.javaguides.springbootrestapi.controller;

import net.javaguides.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Since requests is getting used in each of the uri in the api's we can define a base url in the endpoint
//we can define requestmapping.
@RestController
@RequestMapping("/students")
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("/student")
    //this is a generic way the next one will be of response entity type api
//    public Student getStudent() {
//        Student student = new Student(
//                1,
//                "Vivek",
//                "Yadav"
//        );
//        return student;
//    }
    public ResponseEntity<Student> getstudent(){
        Student students = new Student(
                2,
                "Vivek",
                "Yadav"
        );
//        return new ResponseEntity<>(students,HttpStatus.OK); Another way of Response entity with header
//                                                             and body
//                                                             The ok should be BodyBuilder type not T
          return ResponseEntity.ok().header("custom-header", "vivek").body(students);
    }


    //http://localhost:8080/students
    @GetMapping
    public List<Student> getstudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(2,"t1","t2"));
        return students;
    }

    // Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    //http://localhost:8080/students/2/t1/t2
    @GetMapping("{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstname,
                                       @PathVariable("last-name") String lastname){
        return new Student(studentId,firstname,lastname);
    }

    //Spring boot REST API with Request Param
    //http://localhost:8080/students/query?id=1&firstname=t1&lastname=t2
    @GetMapping("query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstname,
                                          @RequestParam String lastname){
        return new Student(id,firstname,lastname);
    }

    //Spring boot REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody
    //http://localhost:8080/students/create
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //Spring boot REST API that handles HTTP PUT Request = updating existing resource
    @PutMapping("{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //Spring boot REST API that handles HTTP DELETE Request
    @DeleteMapping("{id}")
    public String deletestudent(@PathVariable("id") int studentID){
        return "Student deleted Successfully!";
    }










}
