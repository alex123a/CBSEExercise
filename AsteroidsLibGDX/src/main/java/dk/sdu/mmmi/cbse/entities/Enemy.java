package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends SpaceObject {

    private float maxSpeed;
    private ArrayList<Bullet> bullets;

    Random random = new Random();
    int whatToDo = random.nextInt(3);
    int turns = 0;
    int turnsBeforeChange = 60;
    int fireRan = random.nextInt(50);

    public Enemy(ArrayList<Bullet> bullets) {

        x = Game.WIDTH / 2 + 30;
        y = Game.HEIGHT / 2 + 30;

        maxSpeed = 30;

        shapex = new float[4];
        shapey = new float[4];

        radians = 3.1415f / 2;
        rotationSpeed = 3;

        this.bullets = bullets;

    }

    private void setShape() {
        // The 8 is the number of pixels in a direction.
        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 5) * 8;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

        shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
        shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
    }

    private void fire() {
        bullets.add(new Bullet(x, y, radians));
    }


    public void update(float dt) {

        if (whatToDo == 0) {
            radians += rotationSpeed * dt;
            turns++;
            if (turns % turnsBeforeChange == 0) {
                dx = 0;
                dy = 0;
                whatToDo = random.nextInt(4);
            }
        } else if (whatToDo == 1) {
            radians -= rotationSpeed * dt;
            turns++;
            if (turns % turnsBeforeChange == 0) {
                dx = 0;
                dy = 0;
                whatToDo = random.nextInt(4);
            }
        } else {
            turns++;
            if (turns % (turnsBeforeChange + 100) == 0) {
                whatToDo = random.nextInt(4);
            }
        }

        dx += MathUtils.cos(radians) * maxSpeed * dt;
        dy += MathUtils.sin(radians) * maxSpeed * dt;

        // set position
        x += dx * dt;
        y += dy * dt;

        // set shape
        setShape();

        // Fire
        if (fireRan == 0) {
            this.fire();
        }
        fireRan = random.nextInt(50);

        // screen wrap
        wrap();

    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 0, 0, 1);

        sr.begin(ShapeType.Line);

        // Draw enemy
        for (int i = 0, j = shapex.length - 1;
                i < shapex.length;
                j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }

        sr.end();

    }

}
