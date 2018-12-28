package quests.cooksAssistant;

import quester.Main;
import quester.Node;
import org.dreambot.api.wrappers.interactive.GameObject;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

import static org.dreambot.api.methods.MethodProvider.log;

public class GetGrainNode extends Node {
    public GetGrainNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        return !main.getInventory().contains(constants.IT_GRAIN) && !main.getInventory().contains(constants.IT_FLOUR);
    }
    @Override
    public int execute() {
        log("GetGrain: Start");
        util.TravelTo(areas.wheatArea);
        main.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), util.Rand.nextInt(6000));
        while (!main.getInventory().contains(constants.IT_GRAIN)) {
            util.ClosestSpecifiedGameObjectInteractConditional(constants.GO_GATE, constants.AC_OPEN, constants.AC_OPEN, true);
            main.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), 1000+util.Rand.nextInt(500));
            GameObject wheat = main.getGameObjects().closest(constants.GO_WHEAT);
            if (wheat.hasAction(constants.AC_PICK) && wheat != null) {
                wheat.interact(constants.AC_PICK);
                main.sleep(5000 + util.Rand.nextInt(500));
            }
        }
        //In case the gate has been closed
        util.ClosestSpecifiedGameObjectInteractConditional(constants.GO_GATE, constants.AC_OPEN, constants.AC_OPEN, true);
        main.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), 1000+util.Rand.nextInt(500));
        log("GetGrain: End");
        return 1000;
    }
}
