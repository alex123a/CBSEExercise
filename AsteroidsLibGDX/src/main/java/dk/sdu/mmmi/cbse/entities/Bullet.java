package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject {
    private float lifeTime;
    private float lifeTimer;

    private boolean remove;
    public Bullet(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        this.radians = radians;

        float speed = 450;
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;

        width = height = 2;

        lifeTime = 2;
        lifeTimer = 0;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public void update(float dt) {
        x += dx * dt;
        y += dy * dt;

        wrap();
        lifeTimer += dt;
        if (lifeTimer > lifeTime) {
            remove = true;
        }
    }

    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0, 1, 0, 1);
        sr.circle(x, y, width * 3);
        sr.end();
    }
}
