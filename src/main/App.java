package main;

import javafx.application.Application;
import javafx.stage.Stage;
import gameplay.Gameplay;

public class App extends Application 
{
		
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) 
    {
       Gameplay gamePlay = new Gameplay();
       gamePlay.setStage(stage);
    }
    
    
}
