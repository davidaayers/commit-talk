package com.in28minutes.springboot.service;

import com.in28minutes.springboot.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService
{
   private static List<Course> courses = new ArrayList<>();

   static
   {
      //Initialize Data
      Course course1 = new Course("Course1", "Spring", "10 Steps", Arrays
            .asList("Learn Maven", "Import Project", "First Example",
                  "Second Example"));
      Course course2 = new Course("Course2", "Spring MVC", "10 Examples",
            Arrays.asList("Learn Maven", "Import Project", "First Example",
                  "Second Example"));
      Course course3 = new Course("Course3", "Spring Boot", "6K Students",
            Arrays.asList("Learn Maven", "Learn Spring",
                  "Learn Spring MVC", "First Example", "Second Example"));
      Course course4 = new Course("Course4", "Maven",
            "Most popular maven course on internet!", Arrays.asList(
            "Pom.xml", "Build Life Cycle", "Parent POM",
            "Importing into Eclipse"));

      courses.add(course1);
      courses.add(course2);
      courses.add(course3);
      courses.add(course4);
   }

   public List<Course> retrieveAllCourses()
   {
      return courses;
   }

   public Course retrieveCourse(String courseId)
   {
      Optional<Course> foundCourse = courses.stream()
            .filter(course -> course.getId().equals(courseId))
            .findFirst();

      return foundCourse.orElse(null);
   }

}
