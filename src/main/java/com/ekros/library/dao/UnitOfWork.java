package com.ekros.library.dao;

import com.ekros.library.dao.interfaces.IUserRepo;

public class UnitOfWork {

    private UnitOfWork() {
    }

    private static IUserRepo userRepo;

    public static IUserRepo getUserRepo(){
        if(userRepo == null){
            userRepo = new UserRepo();
        }
        return userRepo;
    }

}
