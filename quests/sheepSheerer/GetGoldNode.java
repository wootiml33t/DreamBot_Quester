package quests.sheepSheerer;

import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.wrappers.items.GroundItem;
import quester.Main;
import quester.Node;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.Item;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

public class GetGoldNode extends Node {
    public GetGoldNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        return !main.getInventory().contains(constants.quest.sheepSheerer.IT_COINS)
                || !main.getInventory().contains(constants.quest.sheepSheerer.IT_SHEARS);
    }
    @Override
    public int execute() {
        //if member
        //else non member
        while (!main.getInventory().contains(constants.quest.sheepSheerer.IT_COINS))
            GoblinKiller();
        return 1000;
    }
    private final Filter<NPC> GOBLIN_FILTER = new Filter<NPC>() {
        @Override
        public boolean match(NPC npc) {
            if (npc == null) {
                return false;
            }
            return npc.getName().equals(constants.quest.sheepSheerer.NPC_GOBLIN) && !npc.isHealthBarVisible();
        }
    };
    public final void GrabCoins() {
        GroundItem item = main.getGroundItems().closest(constants.quest.sheepSheerer.IT_COINS);
        if (item != null) {
            item.interact(constants.AC_TAKE);
            //sleepWhile( () -> getInventory().capacity() > previousInventoryCapacity, Calculations.random(3000, 4000));
            main.sleep(Calculations.random(1000, 4000));
        }
    }

    public final void GoblinKiller() {
        //Check to see if health is low and if so then eat... and run? not sure how robust this should be
        if (!areas.quest.sheepShearer.goblinArea.contains(main.getLocalPlayer())) {
            util.TravelTo(areas.quest.sheepShearer.goblinArea);
        }
        if (!main.getLocalPlayer().isInCombat()) {
            NPC goblin = main.getNpcs().closest(GOBLIN_FILTER);
            if (goblin != null) {
                goblin.interact(constants.AC_ATTACK);
                main.sleep(Calculations.random(3000, 6000));
            }
            main.sleepUntil(() -> !goblin.exists(), Calculations.random(20000, 40250));
            GrabCoins();
        }
    }
}
