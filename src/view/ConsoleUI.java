package view;

public class ConsoleUI {
    private final MenuView menuView = new MenuView();

    public void start() {
        menuView.show();
    }
}