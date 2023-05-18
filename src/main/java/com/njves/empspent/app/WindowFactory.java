package com.njves.empspent.app;

import com.njves.empspent.Application;
import com.njves.empspent.controler.UpdateRequiredSpecialityController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class WindowFactory {

    public void showWindow(String fxml, HashMap<String, Object> arguments, String[] keys, WindowListener listener) {
        Stage secondStage = new Stage();
        fxml = fxml + ".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            secondStage.setTitle("Изменить");
            secondStage.setScene(scene);
            CustomController controller = fxmlLoader.getController();
            controller.setArguments(arguments, keys);
            secondStage.setOnCloseRequest((eventClose -> {
                listener.onEvent();
            }));
            secondStage.setOnHiding(event -> {
                listener.onEvent();
            });
            secondStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
