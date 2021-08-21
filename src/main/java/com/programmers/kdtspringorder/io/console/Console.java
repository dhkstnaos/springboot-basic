package com.programmers.kdtspringorder.io.console;

import com.programmers.kdtspringorder.io.Input;
import com.programmers.kdtspringorder.io.Output;
import com.programmers.kdtspringorder.voucher.domain.Voucher;

import java.util.Scanner;

public class Console implements Input, Output {
    private Scanner scanner;

    public Console(){
        scanner = new Scanner(System.in);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void print(Voucher voucher) {
        System.out.println(voucher);
    }

    public String inputText(){
        return scanner.nextLine();
    }

    public String inputText(String s) {
        System.out.println(s);
        return scanner.nextLine();
    }

    public void newLine() {
        System.out.println();
    }
}