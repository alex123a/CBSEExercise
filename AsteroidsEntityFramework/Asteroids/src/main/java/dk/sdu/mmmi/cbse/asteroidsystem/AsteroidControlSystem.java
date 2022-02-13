package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);

            movingPart.setUp(true);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        float radius = entity.getRadius();

        // TODO Make asteroid instead
        shapex[0] = (float) (x + Math.cos(radians + 3.1415f / 2) * radius);
        shapey[0] = (float) (y + Math.sin(radians + 3.1415f / 2) * radius);

        shapex[1] = (float) (x + Math.cos(radians + 3.1415f / 3) * radius);
        shapey[1] = (float) (y + Math.sin(radians + 3.1415f / 3) * radius);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f / 4) * radius);;
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f / 4) * radius);;

        shapex[3] = (float) (x + Math.cos(radians + 3.1415f / 6) * radius);
        shapey[3] = (float) (y + Math.sin(radians + 3.1415f / 6) * radius);

        shapex[4] = (float) (x + Math.cos(radians) * radius);
        shapey[4] = (float) (y + Math.sin(radians) * radius);

        shapex[5] = (float) (x + Math.cos(radians + 11 * 3.1415f / 6) * radius);
        shapey[5] = (float) (y + Math.sin(radians + 11 * 3.1415f / 6) * radius);

        shapex[6] = (float) (x + Math.cos(radians + 7 * 3.1415f / 4) * radius);
        shapey[6] = (float) (y + Math.sin(radians + 7 * 3.1415f / 4) * radius);

        shapex[7] = (float) (x + Math.cos(radians + 5 * 3.1415f / 3) * radius);
        shapey[7] = (float) (y + Math.sin(radians + 5 * 3.1415f / 3) * radius);

        shapex[8] = (float) (x + Math.cos(radians + 3 * 3.1415f / 2) * radius);
        shapey[8] = (float) (y + Math.sin(radians + 3 * 3.1415f / 2) * radius);

        shapex[9] = (float) (x + Math.cos(radians + 4 * 3.1415f / 3) * radius);
        shapey[9] = (float) (y + Math.sin(radians + 4 * 3.1415f / 3) * radius);

        shapex[10] = (float) (x + Math.cos(radians + 5 * 3.1415f / 4) * radius);
        shapey[10] = (float) (y + Math.sin(radians + 5 * 3.1415f / 4) * radius);

        shapex[11] = (float) (x + Math.cos(radians + 7 * 3.1415f / 6) * radius);
        shapey[11] = (float) (y + Math.sin(radians + 7 * 3.1415f / 6) * radius);

        shapex[12] = (float) (x + Math.cos(radians + 3.1415f) * radius);
        shapey[12] = (float) (y + Math.sin(radians + 3.1415f) * radius);

        shapex[13] = (float) (x + Math.cos(radians + 5 * 3.1415f / 6) * radius);
        shapey[13] = (float) (y + Math.sin(radians + 5 * 3.1415f / 6) * radius);

        shapex[14] = (float) (x + Math.cos(radians + 3 * 3.1415f / 4) * radius);
        shapey[14] = (float) (y + Math.sin(radians + 3 * 3.1415f / 4) * radius);

        shapex[15] = (float) (x + Math.cos(radians + 2 * 3.1415f / 3) * radius);
        shapey[15] = (float) (y + Math.sin(radians + 2 * 3.1415f / 3) * radius);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
