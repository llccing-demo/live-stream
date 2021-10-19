package com.llccing.livestream.service;

public class ShellService {
    public static void execShell(String shell) {
        try {
            Runtime.getRuntime().exec(shell);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
