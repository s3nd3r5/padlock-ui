package com.senders.padlock.ui.main;

import com.senders.padlock.ui.main.screens.Login;

public class Main {
    public void start(){
       new Login(this).start();

    }

    public static void main(String[] args){
        new Main().start();

    }
}
