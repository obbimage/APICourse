package com.app.course.service.TakeWallet;

import com.app.course.models.TakeWallet;
import com.app.course.models.User;

import java.util.List;

public interface TakeWalletService {
    List<TakeWallet>  getAll();
    List<TakeWallet> getTakeWalletByIsGet(boolean isGet);
    TakeWallet insertTakeWallet(long userId, TakeWallet takeWallet);
    TakeWallet updateTakeWallet(TakeWallet takeWallet);
}
