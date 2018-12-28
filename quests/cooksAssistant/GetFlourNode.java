package quests.cooksAssistant;

import quester.Main;
import quester.Node;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.wrappers.interactive.GameObject;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

import static org.dreambot.api.methods.MethodProvider.log;

public class GetFlourNode extends Node {
    public GetFlourNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        return !main.getInventory().contains(constants.IT_FLOUR)
                && main.getInventory().contains(constants.IT_POT)
                && main.getInventory().contains(constants.IT_GRAIN);
    }
    @Override
    public int execute() {
        log("GetFlour: Start");
        util.TravelTo(areas.windmillArea);
        main.sleep(Calculations.random(1000,2000));
        while (main.getGameObjects().closest(constants.GO_LARGE_DOOR).hasAction(constants.AC_OPEN))
            util.ClosestSpecifiedGameObjectInteractConditional(constants.GO_LARGE_DOOR, constants.AC_OPEN, constants.AC_OPEN, true);
        util.ClimbClosest(2,true,false);
        GameObject hopper = main.getGameObjects().closest(constants.GO_HOPPER);
        if (hopper != null)
            main.getInventory().get(constants.IT_GRAIN).useOn(hopper);
        main.sleep(Calculations.random(4000,5000));
        util.ClosestSpecifiedGameObjectInteract(constants.GO_HOPPER_CONTROLS, constants.AC_OPERATE);
        main.sleep(Calculations.random(4000,5000));
        util.ClimbClosest(2,false,false);
        while (!main.getInventory().contains(constants.IT_FLOUR) && main.getGameObjects().closest(constants.GO_FLOUR_BIN).hasAction(constants.AC_EMPTY))
            util.ClosestSpecifiedGameObjectInteract(constants.GO_FLOUR_BIN, constants.AC_EMPTY);
        main.sleep(util.Rand.nextInt(600) + 1000);
        log("GetFlour: End");
        return 1000;
    }
}
