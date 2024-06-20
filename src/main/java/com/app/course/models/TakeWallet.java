package com.app.course.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// yêu cầu rút tiền
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TakeWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    double price;
    @Column(columnDefinition = "LONGTEXT")
    private String note;

    @Column(nullable = false)
    private String nameBank; // Tên ngân hàng
    @Column(nullable = false)
    private int numberBank; // số tài khoản ngân hàng
    private String tradingCode; // mã giao dịch
    private boolean isGet = false; // cho biết admin đã thực hiện yêu cầu hay chưa
    private LocalDateTime dateRequest; // ngày yêu cầu
    private LocalDateTime dateGet; // ngày giải quyết yêu cầu

}
