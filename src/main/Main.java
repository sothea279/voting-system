package main;

import database.DbMigrate;
import view.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        // DB PROCESS: create tables if not exist (safe to run every time)
        DbMigrate.migrate();

        ConsoleUI ui = new ConsoleUI();
        ui.start();
    }
}