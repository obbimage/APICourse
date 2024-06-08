package com.app.course.models;

import com.app.course.models.keys.StudentCourseKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buy")
public class Buy implements Serializable {
//    @EmbeddedId
//    StudentCourseKey id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Id
    @ManyToOne
//    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    private Course course;

//    @Id
    @ManyToOne
//    @MapsId("student_id")
    @JoinColumn(name = "user_id")
    private User user;
    private String dateBuy;
    private Boolean studentComplete;

}
