package com.app.course.initController;

import com.app.course.models.*;
import com.app.course.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "initData")
public class InitController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentWillLearnRepository studentWillLearnRepository;
    @Autowired
    WhoCourseRepository whoCourseRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    SubRoleRepository subRoleRepository;
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    SectionRepository sectionRepository;
    @PostMapping("/course")
    public ResponseEntity<?> saveAllCourses(@RequestBody List<Course> courses) {
        try {
            List<Course> coursesNotExit = new ArrayList<>();
            for (Course course : courses) {
                boolean exist = courseRepository.existsById(course.getId());
                if (!exist) coursesNotExit.add(course);
            }
            courseRepository.saveAll(coursesNotExit);
            return Response.resultOk(coursesNotExit);
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }


    }

    @PostMapping("/course/role/{roleName}/subRole/{subRoleName}")
    public ResponseEntity<?> saveCourse(@PathVariable String roleName, @PathVariable String subRoleName, @RequestBody Course course) {
        try {
            boolean exist = courseRepository.existsById(course.getId());
            if (!exist) {
//            find role
                Optional<Role> roleOptional = roleRepository.findByName(roleName);
                if (roleOptional.isPresent()) {
                    Role role = roleOptional.get();
                    course.setRole(role);
                }
//                find sub role
                Optional<SubRole> subRoleOptional = subRoleRepository.findByName(subRoleName);
                if (subRoleOptional.isPresent()) {
                    SubRole subRole = subRoleOptional.get();
                    course.setSubRole(subRole);
                }
                var response = courseRepository.save(course);
                return Response.resultOk(response);
            }
            return Response.resultFail();
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @PostMapping("/studyLearn/course/{courseId}")
    public ResponseEntity<?> saveAllStudyLearn(@PathVariable long courseId, @RequestBody ArrayList<StudentWillLearn> studentWillLearns) {
        try {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                for (StudentWillLearn studentWillLearn : studentWillLearns) {
                    studentWillLearn.setCourse(course);
                }
                studentWillLearnRepository.saveAll(studentWillLearns);
                return Response.resultOk(studentWillLearns);
            }
            return Response.resultFail();
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @PostMapping("/whoseCourse/course/{courseId}")
    public ResponseEntity<?> saveAllWhoseCourse(@PathVariable long courseId, @RequestBody ArrayList<WhoCourse> whoCourses) {
        try {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                for (WhoCourse whoCourse : whoCourses) {
                    whoCourse.setCourse(course);
                }
                whoCourseRepository.saveAll(whoCourses);
                return Response.resultOk(whoCourses);
            }
            return Response.resultFail();
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @PostMapping("roles")
    public ResponseEntity<?> saveRole(@RequestBody ArrayList<Role> roles) {
        try {
            List<Role> responses = new ArrayList<>();
            for (Role role : roles) {
                boolean exist = roleRepository.existsByName(role.getName());
                if (!exist) {
                    var response = roleRepository.save(role);
                    responses.add(response);
                }
            }
            return Response.resultOk(responses);
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @PostMapping("role/{stringSubRole}")
    public ResponseEntity<?> saveRole(@PathVariable String stringSubRole, @RequestBody Role role) {
        try {
            Role newRole = new Role();
            Optional<Role> roleOptional = roleRepository.findByName(role.getName());
            if (roleOptional.isEmpty()) {
                // neu role chua co thi luu vao
                var response = roleRepository.save(role);
                newRole = response;
                return Response.resultOk(response);
            } else {
                newRole = roleOptional.get();
            }
            boolean subRoleExist = subRoleRepository.existsByName(stringSubRole);
            // lus subRole
            if (!subRoleExist) {
                SubRole subRole = new SubRole();
                subRole.setName(stringSubRole);
                subRole.setRole(newRole);
                subRoleRepository.save(subRole);
            }
            return Response.resultFail();
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @PostMapping("subRoles")
    public ResponseEntity<?> saveSubRole(@RequestBody ArrayList<SubRole> subRoles) {
        try {
            List<SubRole> responses = new ArrayList<>();
            for (SubRole subRole : subRoles) {
                boolean exist = subRoleRepository.existsByName(subRole.getName());
                if (!exist) {
                    var response = subRoleRepository.save(subRole);
                    responses.add(response);
                }
            }
            return Response.resultOk(responses);
        } catch (Exception ex) {
            return Response.resultError(ex.getMessage());
        }
    }

    @PostMapping("units")
    public ResponseEntity<?> insertUnitForAllCourse(@RequestBody ArrayList<Unit> units){
        List<Course> courses = courseRepository.findAll();
        for(Unit unit : units){
            for(Course course : courses){
                Unit unit1 = new Unit();
                unit1.setNumberUnit(unit.getNumberUnit());
                unit1.setTitle(unit.getTitle());
                unit1.setCourse(course);
                unitRepository.save(unit1);
            }
        }
//        var response = unitRepository.saveAll(units);
        return Response.resultOk(units);
    }
    @PostMapping("sections")
    public ResponseEntity<?> insertSectionsForAllCourse(@RequestBody ArrayList<Section> sections){
        List<Unit> units= unitRepository.findAll();
        for(Section section : sections){
            for(Unit unit : units){
                Section section1 = new Section();
                section1.setNumberSection(section.getNumberSection());
                section1.setTitle(section.getTitle());
                section1.setUrlVideo(section.getUrlVideo());
                section1.setUnit(unit);
               sectionRepository.save(section1);
            }
        }
//        var response = unitRepository.saveAll(units);
        return Response.resultOk(sections);
    }
}
