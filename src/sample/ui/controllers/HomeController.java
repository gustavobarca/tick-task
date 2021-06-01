package sample.ui.controllers;

import javafx.scene.input.MouseEvent;
import sample.application.TimeTrackingService;
import sample.infrastructure.repositories.JsonTaskRepository;

public class HomeController {
    private TimeTrackingService timeTrackingService;

    public HomeController() {
        timeTrackingService = new TimeTrackingService(new JsonTaskRepository());
    }

    public void onShowChartClick(MouseEvent mouseEvent) {
        System.out.println("Clicou!");
    }
}
