package com.in28minutes.springboot.service;

import com.in28minutes.springboot.model.Course;
import com.in28minutes.springboot.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService
{

   private final List<Student> students = new ArrayList<>();
   private final CourseService courseService;
   private final SecureRandom random = new SecureRandom();

   @Autowired
   public StudentService(CourseService courseService)
   {
      this.courseService = courseService;
   }

   @PostConstruct
   public void setup()
   {
      List<Course> courses = courseService.retrieveAllCourses();

      Student ranga = new Student("Student1", "Ranga Karanam",
            "Hiker, Programmer and Architect", courses);

      Student satish = new Student("Student2", "Satish T",
            "Hiker, Programmer and Architect", courses);

      students.add(ranga);
      students.add(satish);
   }

   public List<Student> retrieveAllStudents()
   {
      return students;
   }

   private Student retrieveStudent(String studentId)
   {
      for (Student student : students)
      {
         if (student.getId().equals(studentId))
         {
            return student;
         }
      }
      return null;
   }

   public List<Course> retrieveCourses(String studentId)
   {
      Student student = retrieveStudent(studentId);

      if (student == null)
      {
         return null;
      }

      return student.getCourses();
   }

   public Course retrieveCourse(String studentId, String courseId)
   {
      Student student = retrieveStudent(studentId);

      if (student == null)
      {
         return null;
      }

      for (Course course : student.getCourses())
      {
         if (course.getId().equals(courseId))
         {
            return course;
         }
      }

      return null;
   }

   public Course addCourse(String studentId, Course course)
   {
      Student student = retrieveStudent(studentId);

      if (student == null)
      {
         return null;
      }

      String randomId = new BigInteger(130, random).toString(32);
      course.setId(randomId);

      student.getCourses().add(course);

      return course;
   }
}