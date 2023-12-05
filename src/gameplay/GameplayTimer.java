package gameplay;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
//import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class GameplayTimer extends AnimationTimer{
	
	private GraphicsContext gc;
	private Scene scene;
	private Image background;
	private Thrower thrower;
	private Ball ball;
	
	public GameplayTimer(GraphicsContext gc, Scene scene) {
		this.gc = gc;
		this.scene = scene;
		
		this.background = new Image("images/sample.png");
		gc.drawImage(background, 0, 0, Gameplay.WINDOW_WIDTH,Gameplay.WINDOW_HEIGHT);
		
		this.thrower = new Thrower(Gameplay.WINDOW_WIDTH/2, Gameplay.WINDOW_HEIGHT/2, 40, 40, new Image("images/thrower.png"));
		this.ball = new Ball(this.thrower.getXPos()+50,this.thrower.getYPos()-20,10);
		this.prepareActionHandlers();
	}


	@Override
	public void handle(long currentNanoTime) {
		this.renderSprites();
        this.moveSprites();
		
	}

	private void prepareActionHandlers() {
    	this.scene.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                double xMousePos = e.getSceneX();
                double yMousePos = e.getSceneY();
                System.out.println("x:" + xMousePos);
                System.out.println("y:" + yMousePos);
                
            }
        });
    	
    	this.scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event)
            {
    			if (event.getButton() == MouseButton.PRIMARY) { // if left mouse button clicked
                    // find direction of ball thrown when mouse clicked 
                    double directionX = event.getX() - this.ball.getXPos();
                    double directionY = event.getY() - this.ball.yPos;

                    // get magnitude to be used for computing new direction for x and y
                    double magnitude = Math.sqrt(directionX * directionX + directionY * directionY);

                    // get unit vector for x and y
                    double normalizedDirectionX = directionX / magnitude;
                    double normalizedDirectionY = directionY / magnitude;

                    // set speed of ball thrown (change if needed)
                    double throwSpeed = 5.0;

                    // update the position
                    thrownBallPosition[0] = characterX + normalizedDirectionX * (characterRadius + thrownBallRadius);
                    thrownBallPosition[1] = characterY + normalizedDirectionY * (characterRadius + thrownBallRadius);

                    // set up timer for animation before starting throw animation
                    AnimationTimer throwAnimation = new AnimationTimer() {
                        @Override
                        public void handle(long now) {
                        	//update position based on speed
                            thrownBallPosition[0] += normalizedDirectionX * throwSpeed;
                            thrownBallPosition[1] += normalizedDirectionY * throwSpeed;

                            // redraw canvas for update of positions
                            gc.clearRect(0, 0, Gameplay.WINDOW_WIDTH, Gameplay.WINDOW_HEIGHT);
                            gc.drawImage(bg, 0, 0, Gameplay.WINDOW_WIDTH, Gameplay.WINDOW_HEIGHT);
                            gc.setFill(Color.BLUE);
                            gc.fillOval(characterX - characterRadius, characterY - characterRadius, 2 * characterRadius, 2 * characterRadius);
                            gc.setFill(Color.YELLOW);
                            gc.fillOval(thrownBallPosition[0] - thrownBallRadius, thrownBallPosition[1] - thrownBallRadius, 2 * thrownBallRadius, 2 * thrownBallRadius);

                            // check if the yellow ball has reached left, right, top and down edge of the window
                            if (thrownBallPosition[0] < 0 || thrownBallPosition[0] > Gameplay.WINDOW_WIDTH ||
                                    thrownBallPosition[1] < 0 || thrownBallPosition[1] > Gameplay.WINDOW_HEIGHT) {
                                // stop the animation when the yellow ball has satisfied at least one of the conditions
                                this.stop();
                            }
                        }
                    };

                    // start the throw animation
                    throwAnimation.start();
                }
                
            }
    	});
    	
    	
    }
	
	private void moveSprites() {
		this.moveBall();
		
	}


	private void moveBall() {
		// TODO Auto-generated method stub
		
	}


	private void renderSprites() {
		this.thrower.render(this.gc);
		
	}


}
