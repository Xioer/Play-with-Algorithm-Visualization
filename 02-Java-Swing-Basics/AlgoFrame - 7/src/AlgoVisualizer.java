import java.awt.*;
import java.util.Random;

public class AlgoVisualizer {

    private int N;
    private Circle[] circles;
    static private int R = 50;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, AlgoFrame frame){
        this.N = N;
        this.frame = frame;

        circles = new Circle[N];

        Random rand = new Random();
        for(int i = 0 ; i < N ; i ++ ) {
            int x = rand.nextInt(frame.getCanvasWidth()-2*R) + R;
            int y = rand.nextInt(frame.getCanvasHeight()-2*R) + R;
            int vx = rand.nextInt(11) - 5;
            int vy = rand.nextInt(11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }
    }

    public void run(){

        while(true){
            // render
            frame.setCircles(circles);
            AlgoVisHelper.pause(20);

            // update
            for(Circle circle: circles)
                circle.go(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Welcome", sceneWidth,sceneHeight);

            int N = 10;
            AlgoVisualizer vis = new AlgoVisualizer(N, frame);
            new Thread(() -> {
                vis.run();
            }).start();
        });

    }
}