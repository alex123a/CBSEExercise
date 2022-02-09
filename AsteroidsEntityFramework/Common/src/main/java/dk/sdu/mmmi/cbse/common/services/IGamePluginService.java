package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

    /**
     * Precondition: The world exist.
     * Postcondition: Game world now contains game entities.
     */
    void start(GameData gameData, World world);

    /**
     * Precondition: The world and the entities exist.
     * Postcondition: The entities are removed.
     */
    void stop(GameData gameData, World world);
}
