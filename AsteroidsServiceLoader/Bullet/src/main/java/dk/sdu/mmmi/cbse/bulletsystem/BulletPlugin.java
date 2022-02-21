package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IBulletService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class BulletPlugin implements IGamePluginService, IBulletService {
    private Entity bullet;
    private Entity whoFired;

    public BulletPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

    }

    private Entity createBullet(GameData gameData) {
        PositionPart entityPosition = whoFired.getPart(PositionPart.class);
        float deacceleration = 10;
        float acceleration = 10000;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float radians = entityPosition.getRadians();
        // Last assignment I made it so the first 0.1 seconds the bullets could not collide but I can't use that now
        // so I just add few pixels here. This is incorret as this does not take account for the direction.
        float x = entityPosition.getX() + 8;
        float y = entityPosition.getY() + 8;
        float radius = 3.0f;

        Entity bullet = new Bullet();
        bullet.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        bullet.add(new PositionPart(x, y, radians));
        bullet.setRadius(radius);
        float[] newShapeX = new float[16];
        float[] newShapeY = new float[16];
        bullet.setShapeX(newShapeX);
        bullet.setShapeY(newShapeY);
        // Expiration is in seconds.
        bullet.add(new LifePart(1, 1.0f));
        bullet.setBoundingCircleX(x);
        bullet.setBoundingCircleY(y);

        return bullet;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(bullet);
    }

    @Override
    public void fire(GameData gameData, World world, String whoFiredID) {
        this.whoFired = world.getEntity(whoFiredID);
        bullet = createBullet(gameData);
        world.addEntity(bullet);
    }
}
