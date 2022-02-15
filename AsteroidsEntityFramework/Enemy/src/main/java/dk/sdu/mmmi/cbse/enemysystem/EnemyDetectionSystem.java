package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.asteroidsystem.Asteroid;
import dk.sdu.mmmi.cbse.bulletsystem.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class EnemyDetectionSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        // Checking if enemy is too close to an asteroid
        for (Entity enemy: world.getEntities(Enemy.class)) {
            PositionPart enemyPosition = enemy.getPart(PositionPart.class);
            for (Entity asteroid: world.getEntities(Asteroid.class)) {
                PositionPart asteroidPosition = asteroid.getPart(PositionPart.class);
                // 8 is the length of the enemy
                if (Math.sqrt(Math.pow(enemyPosition.getX() - asteroidPosition.getX(), 2) +
                        Math.pow(enemyPosition.getY() - asteroidPosition.getY(), 2)) <= 8 + asteroid.getRadius()) {
                    world.removeEntity(enemy);
                    System.out.println("Enemy is hit by an asteroid");
                }
            }

            for (Entity bullet: world.getEntities(Bullet.class)) {
                PositionPart bulletPosition = bullet.getPart(PositionPart.class);
                LifePart bulletLifepart = bullet.getPart(LifePart.class);
                if (Math.sqrt(Math.pow(enemyPosition.getX() - bulletPosition.getX(), 2) +
                        Math.pow(enemyPosition.getY() - bulletPosition.getY(), 2)) <= 8 + bullet.getRadius() && bulletLifepart.getExpiration() <= 0.9f) {
                    world.removeEntity(enemy);
                    world.removeEntity(bullet);
                    System.out.println("Enemy is hit by a bullet");
                }
            }
        }
    }
}
