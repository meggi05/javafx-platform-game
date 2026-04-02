package com.example.game_2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bg1, bg11, player, enemy;

    @FXML
    private Label label_pause, label_lose;



    private final int BG_WIDTH = 650;

    private ParallelTransition parallelTransition;
    private TranslateTransition enemyTransition;

    ///
    public static boolean jump = false;


    public static boolean right = false;
    public static boolean left = false;

    public static boolean isPause = false;



    private int playerVelocity = 3, jumpDownVelocity = 1;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(jump && player.getLayoutY() > 70f)
                player.setLayoutY(player.getLayoutY() - playerVelocity);
            else if (player.getLayoutY() <=140f) {
                jump = false;
                player.setLayoutY(player.getLayoutY() + jumpDownVelocity);
            }

            if (right && player.getLayoutX() < 77f)
               player.setLayoutX(player.getLayoutX() + playerVelocity);
           if (left && player.getLayoutX() > 28f)
               player.setLayoutX(player.getLayoutX() - playerVelocity);

           if (isPause && !label_pause.isVisible()) {
                playerVelocity= 0;
                jumpDownVelocity = 0;
                parallelTransition.pause();
                enemyTransition.pause();
               label_pause.setVisible(true);
           }
           else if (!isPause && label_pause.isVisible()) {
               playerVelocity= 3;
               jumpDownVelocity = 1;
               parallelTransition.play();
               enemyTransition.play();
               label_pause.setVisible(false);
           }
            if (player.getBoundsInParent().intersects(enemy.getBoundsInParent())){
                label_lose.setVisible(true);
                playerVelocity= 0;
                jumpDownVelocity = 0;
                parallelTransition.pause();
                enemyTransition.pause();
            }
        }
    };


    @FXML
    void initialize() {
        TranslateTransition bg_1_Transithion = new TranslateTransition(Duration.millis(5000), bg1);
        bg_1_Transithion.setFromX(0);
        bg_1_Transithion.setToX(BG_WIDTH * -1);
        bg_1_Transithion.setInterpolator(Interpolator.LINEAR);


        TranslateTransition bg_11_Transithion = new TranslateTransition(Duration.millis(5000), bg11);
        bg_11_Transithion.setFromX(0);
        bg_11_Transithion.setToX(BG_WIDTH * -1);
        bg_11_Transithion.setInterpolator(Interpolator.LINEAR);

        enemyTransition = new TranslateTransition(Duration.millis(3500), enemy);
        enemyTransition.setFromX(0);
        enemyTransition.setToX(BG_WIDTH * -1 - 100);
        enemyTransition.setInterpolator(Interpolator.LINEAR);
        enemyTransition.setCycleCount(Animation.INDEFINITE);
        enemyTransition.play();


        parallelTransition = new ParallelTransition(bg_1_Transithion, bg_11_Transithion);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();
    }

}
