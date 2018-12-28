package quests.sheepSheerer;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.quest.Quest;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import quester.Main;
import quester.Node;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

import java.util.List;

import static org.dreambot.api.methods.MethodProvider.log;

public class SpinWoolNode extends Node {
    public SpinWoolNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        return main.getInventory().isFull() && main.getInventory().contains(constants.quest.sheepSheerer.IT_WOOL);
        //return true;
    }
    @Override
    public int execute() {
        if(!areas.quest.sheepShearer.lumbridgeCastleSpinningWheel.contains(main.getLocalPlayer()) && main.getLocalPlayer().getZ() == 0) {
            util.TravelTo((Math.random() < 0.5) ? (areas.quest.sheepShearer.lumbridgeCastleStaircaseOne) : (areas.quest.sheepShearer.lumbridgeCastleStaircaseTwo));
            main.sleep(Calculations.random(1000, 4000));
            util.ClimbClosest(1,true,true);
            main.sleep(Calculations.random(3000, 4000));
            util.TravelTo(areas.quest.sheepShearer.lumbridgeCastleSpinningWheel);
        }
        else if (!areas.quest.sheepShearer.lumbridgeCastleSpinningWheel.contains(main.getLocalPlayer()) && main.getLocalPlayer().getZ() == 2) {
            util.TravelTo(areas.quest.sheepShearer.lumbridgeCastleSpinningWheel);
        }
        SpinWool();
        log("spinning");
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@keeps traveling up and down the stairs and not actually spinning anything
        //spin all wool into wool balls
        //go to farmer fred
        //give fred all the wool balls you have
        log("end node");
        return 1000;
    }
    public void SpinWool(){
        /*
        //https://dreambot.org/forums/index.php?/topic/14404-how-to-get-the-widget-for-this/
        GameObject spinning = main.getGameObjects().closest(i -> i != null && i.getName().equals("Spinning wheel"));
        if (spinning.isOnScreen()) {
            spinning.interactForceRight("Spin");
            main.sleep(Calculations.random(1000, 4000));
            List<WidgetChild> tela = main.getWidgets().getWidgets(i -> i != null && i.getText().equals("What would you like to spin?"));
            for (WidgetChild t : tela)
                MethodProvider.sleepUntil(() -> !t.isVisible(), 2000);
            WidgetChild spin = main.getWidgets().getWidgetChild(270, 14);
            spin.interact();
            main.sleep(Calculations.random(1000, 4000));
        */
        GameObject spinningWheel = main.getGameObjects().closest(constants.quest.sheepSheerer.GO_SPINNING_WHEEL);
        if(spinningWheel != null) {
            spinningWheel.interact(constants.quest.sheepSheerer.AC_SPIN);
            main.sleep(Calculations.random(5000, 6000));
            main.getKeyboard().type("1");
            main.sleep(Calculations.random(20000, 25000));
            main.sleepUntil(() -> !main.getLocalPlayer().isAnimating() , Calculations.random(20000, 40250));
            log("WAITING");
        }
    }
}
