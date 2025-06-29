import java.awt .*;
import java.awt.event.KeyEvent;

public class AngryBirds {
    public static void main(String[] args) {
        int width = 1600;
        int height = 800;
        double gravity = 9.80665;
        double x0 = 120;
        double y0 = 120;
        double velocity = 180;
        double angle = 45;


        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(-40, 1800);
        StdDraw.setYscale(-40, 1000);
        StdDraw.enableDoubleBuffering();
        double[][] obstacleArray = {
                {1200, 0, 60, 220},
                {1000, 0, 60, 160},
                {600, 0, 60, 80},
                {600, 180, 60, 160},
                {220, 0, 120, 180}
        };
        double[][] targetArray = {
                {1160, 0, 30, 30},
                {730, 0, 30, 30},
                {150, 0, 20, 20},
                {1480, 0, 60, 60},
                {340, 80, 60, 30},
                {1500, 600, 60, 60}
        };
        StdDraw.setPenColor(StdDraw.BLACK);

        boolean cont = true;
        while (cont) {
            StdDraw.clear();
            StdDraw.setFont(new Font("Helvetica", Font.BOLD, 10));
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(60, 60, 60, 60);
            String hız = String.format("v:%.1f", velocity);
            String açı = String.format("a:%.1f", angle);
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.textRight(80, 60, hız);
            StdDraw.textRight(70, 40, açı);
            //StdDraw.setPenColor(StdDraw.WHITE);
            //StdDraw.textRight(80, 60, hız);
            //StdDraw.textRight(70, 40, açı);
            //StdDraw.line(120, 120, x1, y1);
            double l = velocity / 3;
            double x1 = x0 + l * Math.cos(Math.toRadians(angle));
            double y1 = y0 + l * Math.sin(Math.toRadians(angle));

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(120, 120, x1, y1);
            StdDraw.setPenColor(StdDraw.YELLOW);

            for (double[] obs : obstacleArray) {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius(0.005);
                StdDraw.filledRectangle(obs[0] + obs[2] / 2, obs[1] + obs[3] / 2, obs[2] / 2, obs[3] / 2);
            }
            for (double[] tar : targetArray) {
                StdDraw.setPenColor(Color.ORANGE);
                StdDraw.setPenRadius(0.005);
                StdDraw.filledRectangle(tar[0] + tar[2] / 2, tar[1] + tar[3] / 2, tar[2] / 2, tar[3] / 2);
            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                StdDraw.pause(100);
                velocity += 1.0;

            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                StdDraw.pause(100);
                velocity -= 1.0;

            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                StdDraw.pause(100);
                angle += 1;

            }


            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                StdDraw.pause(100);
                angle = angle - 1.0;
            }


            if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                StdDraw.setFont(new Font("Helvetica", Font.HANGING_BASELINE, 30));
                double dt = 0.15;
                double t = 0;
                while (true) {
                    //StdDraw.setPenColor(StdDraw.BLACK);
                    //StdDraw.line(120,120,x1,y1);

                    double x = x0 + velocity * Math.cos(Math.toRadians(angle)) * t;
                    double y = y0 + velocity * Math.sin(Math.toRadians(angle)) * t - gravity * t * t / 2.0;
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledCircle(x, y, 2);
                    boolean hit = false;
                    for (double[] obs : obstacleArray) {
                        double minX = obs[0];
                        double minY = obs[1];
                        double maxX = obs[0] + obs[2];
                        double maxY = obs[1] + obs[3];
                        if ((x < maxX && x > minX) && (minY < y && y < maxY)) {
                            hit = true;
                            break;
                        }


                    }
                    if (hit) {
                        StdDraw.setFont();
                        //StdDraw.setPenColor(Color.BLACK);
                        StdDraw.textRight(500, 550, "hit the obs");
                        StdDraw.show();
                        break;
                    }


                    boolean tar = false;
                    for (double[] targ : targetArray) {
                        double minX = targ[0];
                        double minY = targ[1];
                        double maxX = targ[0] + targ[2];
                        double maxY = targ[1] + targ[3];
                        if ((x < maxX && x > minX) && (minY < y && y < maxY)) {
                            tar = true;
                            break;
                        }


                    }
                    if (tar) {
                        //StdDraw.setPenColor(Color.BLACK);
                        StdDraw.textRight(500, 550, "congratilations");
                        StdDraw.show();
                        break;
                    }
                    if (y <= 0) {
                        //StdDraw.setPenColor(Color.BLACK);
                        StdDraw.textRight(500, 550, "hit the ground");
                        StdDraw.show();
                        break;
                    }

                    if (x > 1800) {
                        //StdDraw.setPenColor(Color.BLACK);
                        StdDraw.textRight(500, 550, "beyond");
                        StdDraw.show();
                        break;
                    }

                    StdDraw.show();
                    StdDraw.pause(10);
                    t = dt + t;
                }
                if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
                    StdDraw.clear();
                    angle = 45;
                    velocity = 180;
                    cont = true;


                    StdDraw.pause(100);

                } else {
                    cont = false;
                }
            }


            StdDraw.show();

            StdDraw.pause(10);
            StdDraw.enableDoubleBuffering();
        }
    }
}

