package shopToys;

import shopToys.controller.UserController;
import shopToys.model.*;
import shopToys.view.ViewUser;

public class Main {
    public static void main(String[] args) {

        ToysOperation toysOperation = new ToysOperationImpl("showcase.txt");
        Showcase showcase = new ShowcaseImpl(toysOperation);

        UserController controller = new UserController(showcase);
        ViewUser view = new ViewUser(controller);
        view.run();


    }
}