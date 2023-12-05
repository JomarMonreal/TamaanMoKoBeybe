package gameplay;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Gameplay {
	private Stage stage;
	private Scene splashScene;		// the splash scene
	private Scene gameScene;		// the game scene
	private Group root;
	private Canvas canvas;			// the canvas where the animation happens
	
	public final static int WINDOW_WIDTH = 400;
	public final static int WINDOW_HEIGHT = 700;
	
	public Gameplay() {
		this.canvas = new Canvas( Gameplay.WINDOW_WIDTH, Gameplay.WINDOW_HEIGHT );
		this.root = new Group();
        this.root.getChildren().add( this.canvas );
        this.gameScene = new Scene( root );
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		this.stage.setTitle( "Tamaan Mo Ko Beybe" );
        
		this.initSplash(stage);			// initializes the Splash Screen with the New Game button
		
		this.stage.setScene( this.splashScene );
        this.stage.setResizable(false);
		this.stage.show();
		this.setGame(stage);
	}
	
	private void initSplash(Stage stage) {
		StackPane root = new StackPane();
        root.getChildren().addAll(this.createCanvas());
        this.splashScene = new Scene(root);
	}
	
	void setGame(Stage stage) {
        stage.setScene( this.gameScene );	
        
        GraphicsContext gc = this.canvas.getGraphicsContext2D();	// we will pass this gc to be able to draw on this Game's canvas
        
        GameplayTimer gameTimer = new GameplayTimer(gc, gameScene);
        gameTimer.start();			// this internally calls the handle() method of our GameTimer
        
	}	
	
	private Canvas createCanvas() {
    	Canvas canvas = new Canvas(Gameplay.WINDOW_WIDTH,Gameplay.WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Image bg = new Image("images/somethings.jpg");
        gc.drawImage(bg, 0,0,Gameplay.WINDOW_WIDTH,Gameplay.WINDOW_HEIGHT);
        
        return canvas;
    }
	
	

}
