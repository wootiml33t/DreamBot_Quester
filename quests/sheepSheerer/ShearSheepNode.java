package quests.sheepSheerer;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.quest.Quest;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.interactive.NPC;
import quester.Main;
import quester.Node;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

import java.util.Arrays;

public class ShearSheepNode extends Node {
    public ShearSheepNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        //main.getTabs().openWithMouse(Tab.QUEST);
        //return !main.getQuests().isStarted(Quest.SHEEP_SHEARER);
        //needs to check for both inventory being full and quest completion but idk if i can do that in one line
        return !main.getInventory().isFull();
    }
    @Override
    public int execute() {
        util.TravelTo(areas.quest.sheepShearer.sheepPen);
        //while inventory is not full shear sheep
        while (!main.getInventory().isFull()) {
            main.getNpcs().closest(FLUFFY_SHEEP_FILTER).interact(constants.quest.sheepSheerer.AC_SHEAR);
            main.sleep(Calculations.random(3000, 6000));
        }
        return 1000;
    }
    private final Filter<NPC> FLUFFY_SHEEP_FILTER = new Filter<NPC>() {
        //https://dreambot.org/forums/index.php?/topic/12700-my-first-script-code-reveiw/
        @Override
        public boolean match(NPC npc) {
            if (npc == null) {
                return false;
            }
            return npc.getID() != 731 //an already sheared sheep
                   && Arrays.asList(npc.getActions()).contains(constants.quest.sheepSheerer.AC_SHEAR)
                   && areas.quest.sheepShearer.sheepPen.contains(npc);
        }
    };
}
