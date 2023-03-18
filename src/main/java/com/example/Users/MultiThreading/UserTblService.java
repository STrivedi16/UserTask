package com.example.Users.MultiThreading;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserTblService {

    @Autowired
    private UserTblRepo tblRepo;

    Logger logger = LoggerFactory.getLogger(UserTblService.class);

    @Async
    @Transactional
    public CompletableFuture<List<UserTbl>> saveUser(MultipartFile file) throws Exception {
        System.err.println("adasdsdasdad");

        long start = System.currentTimeMillis();
        System.out.println("time get");
        List<UserTbl> list;
        try {
            list = parseCSVFile(file);

            System.err.println("123123123");
            logger.info("Saving the list of size {}", list.size(), " " + Thread.currentThread().getName());
            logger.info("Storing in Database is Start");
            list = this.tblRepo.saveAll(list);
            long end = System.currentTimeMillis();
            System.err.println("123123123");
            logger.info("Total time {}", (end - start));
            return CompletableFuture.completedFuture(list);
        } catch (java.io.IOException e) {
            logger.error("Failed to parse CSV file", e);
            e.printStackTrace();
            throw new Exception("Failed to parse CSV file", e);
        }

    }

    @Async
    public CompletableFuture<List<UserTbl>> getAllUser() {
        List<UserTbl> list = this.tblRepo.findAll();
        logger.info("Get list of users", Thread.currentThread().getName());
        return CompletableFuture.completedFuture(list);
    }

    private List<UserTbl> parseCSVFile(final MultipartFile file) throws Exception {
        final List<UserTbl> users = new ArrayList<>();
        System.out.println("in parsecsv file");
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                System.out.println("in try read ");
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    // int a[]=(int) line.charAt(0);
                    
                    final UserTbl user = new UserTbl();
                    // user.setId(a[0]);
                    user.setName(data[1]);
                    
                    user.setEmail(data[2]);
                    user.setGender(data[3]);
                    users.add(user);
                }
                logger.info("user data has been connected with database table ");
                return users;

            }
        } catch (java.io.IOException e) {
            logger.error("Failed to parse CSV file", e);
            throw new Exception("Failed to parse CSV file", e);
        }
    }
}
