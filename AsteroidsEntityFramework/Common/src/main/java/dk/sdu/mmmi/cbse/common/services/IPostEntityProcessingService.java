package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService  {
        /**
         * Precondition: Game has started, the world and entities exist.
         * Postcondition: Collition detection has been processed.
         */
        void process(GameData gameData, World world);
}
