package dk.sdu.mmmi.cbse.springenemysystem;

// import dk.sdu.mmmi.cbse.bullet.BulletPlugin;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IBulletService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EnemyControlSystem implements IEntityProcessingService {
    Random random = new Random();
    int whatToDo = random.nextInt(3);
    int turns = 0;
    int turnsBeforeChange = 60;
    int fireRan = random.nextInt(50);

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy: world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            if (whatToDo == 0) {
                movingPart.setLeft(true);
                movingPart.setRight(false);
                movingPart.setUp(false);
                turns++;
                if (turns % turnsBeforeChange == 0) {
                    whatToDo = random.nextInt(4);
                }
            } else if (whatToDo == 1) {
                movingPart.setRight(true);
                movingPart.setLeft(false);
                movingPart.setUp(false);
                turns++;
                if (turns % turnsBeforeChange == 0) {
                    whatToDo = random.nextInt(4);
                }
            } else {
                turns++;
                movingPart.setUp(true);
                movingPart.setLeft(false);
                movingPart.setRight(false);
                if (turns % (turnsBeforeChange) == 0) {
                    whatToDo = random.nextInt(4);
                }
            }

            IBulletService bullet = null;
            for (IBulletService bulletService: SPILocator.locateAll(IBulletService.class)) {
                bullet = bulletService;
            }

            if (bullet != null && fireRan == 0) {
                bullet.fire(gameData, world, enemy.getID());
            }

            fireRan = random.nextInt(50);

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
