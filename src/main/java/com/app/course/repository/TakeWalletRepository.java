package com.app.course.repository;

import com.app.course.models.TakeWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TakeWalletRepository extends JpaRepository<TakeWallet,Long> {
    List<TakeWallet> findByUserId(long userId);
    List<TakeWallet> findByIsGet(boolean isGet);
}
