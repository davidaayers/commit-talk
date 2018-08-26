package com.in28minutes.springboot.controller;

import com.in28minutes.springboot.model.Course;
import com.in28minutes.springboot.model.Student;
import com.in28minutes.springboot.service.CourseService;
import com.in28minutes.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StudentController
{

   private final StudentService studentService;
   private final CourseService courseService;

   @Autowired
   public StudentController(StudentService studentService, CourseService courseService)
   {
      this.studentService = studentService;
      this.courseService = courseService;
   }

   @GetMapping("/courses")
   public List<Course> retrieveAllCourses()
   {
      return courseService.retrieveAllCourses();
   }


   @GetMapping("/students")
   public List<Student> retrieveAllStudents()
   {
      return studentService.retrieveAllStudents();
   }

   @GetMapping("/students/{studentId}/courses")
   public List<Course> retrieveCoursesForStudent(@PathVariable String studentId)
   {
      return studentService.retrieveCoursesForStudent(studentId);
   }

   @GetMapping("/students/{studentId}/courses/{courseId}")
   public Course retrieveDetailsForCourse(@PathVariable String studentId,
                                          @PathVariable String courseId)
   {
      return studentService.retrieveCourseForStudent(studentId, courseId);
   }

   @PostMapping("/students/{studentId}/courses")
   public ResponseEntity<Void> registerStudentForCourse(
         @PathVariable String studentId, @RequestBody Course newCourse)
   {

      Course course = studentService.enrollStudentInCourse(studentId, newCourse);

      if (course == null)
      {
         return ResponseEntity.noContent().build();
      }

      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
            "/{id}").buildAndExpand(course.getId()).toUri();

      return ResponseEntity.created(location).build();
   }

}
