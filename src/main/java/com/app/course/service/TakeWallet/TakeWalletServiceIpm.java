package com.app.course.service.TakeWallet;

import com.app.course.config.Constants;
import com.app.course.models.TakeWallet;
import com.app.course.models.User;
import com.app.course.repository.TakeWalletRepository;
import com.app.course.repository.UserRepository;
import com.app.course.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TakeWalletServiceIpm implements TakeWalletService {
    @Autowired
    private TakeWalletRepository takeWalletRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public List<TakeWallet> getAll() {
        return takeWalletRepository.findAll();
    }

    @Override
    public List<TakeWallet> getTakeWalletByIsGet(boolean isGet) {
        return takeWalletRepository.findByIsGet(isGet);

    }

    @Override
    public TakeWallet insertTakeWallet(long userId, TakeWallet takeWallet) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty())
            return null;

        User user = userOptional.get();
        // send mail to user
        emailService.sendEmail(user.getEmail(), "Yêu cầu rút tiền", "Bạn đã yêu cầu rút " + takeWallet.getPrice() + "VND");


        takeWallet.setUser(user);
        takeWallet.setDateRequest(LocalDateTime.now());
        var response = takeWalletRepository.save(takeWallet);
        // cập nhật lại ví
        double wallet = user.getWallet() - takeWallet.getPrice();
        user.setWallet(wallet);
        var responseUser = userRepository.save(user);
        return response;
    }

    @Override
    public TakeWallet updateTakeWallet(TakeWallet takeWallet) {

        User user = takeWallet.getUser();
        takeWallet.setGet(true);
        takeWallet.setDateGet(LocalDateTime.now());
        var response = takeWalletRepository.save(takeWallet);

        // update wallet for admin
        User admin = userRepository.findByUsername("admin");
        double walletAdmin = admin.getWallet() - takeWallet.getPrice();
        userRepository.save(admin);

        // send mail for educator
        emailService.sendEmail(user.getEmail(), "Yêu cầu rút tiền", "Yêu cầu rút " + takeWallet.getPrice() + "Của bạn đã được duyệt");


        return response;
    }

}
