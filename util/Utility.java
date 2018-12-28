package util;

import quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.GroundItem;

import java.util.Random;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleepWhile;

public class Utility {
    private static Utility single_instance = null;
    private static Main main;
    public static Random Rand = new Random();
    public static final ConstantsProvider constants = ConstantsProvider.getInstance();
    public static Utility getInstance(Main mainIn) {
        if (single_instance == null) {
            single_instance = new Utility();
            main = mainIn;
        }
        return single_instance;
    }
    private Utility() {
        //
    }
    public static void GetGroundItemIfNeeded(String itemToGet) {
        while (!main.getInventory().contains(itemToGet)) {
            GroundItem item = main.getGroundItems().closest(itemToGet);
            if (item != null) {
                item.interact("Take");
                //this sleepwhile could be broken by something interupting the grabbing action
                //for instance if the item is out of reach it wont try again to grab it
                //found here: https://dreambot.org/forums/index.php?/topic/7671-how-to-correctly-wait-until-actions-have-happend/
                sleepWhile( () -> !main.getInventory().contains(itemToGet), Calculations.random(35000, 40000));
            }
        }
    }
    public static void TravelTo(Area destination) {
        //clicks really frequently when close to location
        //think i fixed this by changing this
        //getWalking().walk(destination.getCenter()); from getRandom()
        //https://dreambot.org/forums/index.php?/topic/4512-walking-a-path-with-doors/

        /*
        while (!destination.contains(getLocalPlayer())) {
            getWalking().walk(destination.getCenter());
            sleepUntil(() -> getWalking().getDestination().distance() < Calculations.random(5, 7) , Calculations.random(3400, 4250));
            sleep(Calculations.random(300, 600));
            sleepUntil(
                    () -> !getLocalPlayer().isMoving()
                            || getLocalPlayer().distance(
                            getClient().getDestination()) < Calculations.random(
                            7, 12), Calculations.random(5000, 6666));

        }
        */

        //Walking algorithm modified from Before's Cooks Assistant quest bot
        //issues: since this will sleep until the user has stopped moving + some random time there is a lot of standing around
        //however it does not have the issue of spam clicking once you get close to the destination
        while (!destination.contains(main.getLocalPlayer()) && !destination.contains(main.getWalking().getDestination())) {
            main.getWalking().walk(destination.getRandomTile());
            //sleep(2000+Rand.nextInt(1000));
            main.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Rand.nextInt(6000));
        }
    }
    public static boolean ClimbClosest(int floors, boolean isUp, boolean isStairs) {
        //add a z change counter and return true only if zchange == floors
        //might want to change the indexing so 1 = one floor and 2 = 2 floors etc
        int zStart = main.getLocalPlayer().getZ();
        int attempts = 0;
        int zGoal = main.getLocalPlayer().getZ() + floors;
        while ((main.getLocalPlayer().getZ() != zGoal) || attempts > ((floors >= 5) ? (10) : (5))) {
            GameObject climbable = main.getGameObjects().closest((isStairs == true) ? (constants.GO_STAIRS) : (constants.GO_LADDER));
            if (climbable != null) {
                climbable.interact((isUp == true) ? (constants.AC_CLIMB_UP) : (constants.AC_CLIMB_DOWN));
                attempts++;
                main.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Rand.nextInt(6000));
            }
        }
        return true;
    }
    public static boolean ClosestSpecifiedGameObjectInteract(String gameObjectName, String interactOption) {
        //I feel like a sleep for until is not animating would be sufficeint but if that doesnt work i could do a sleep based on how far the player is from the game object
        GameObject closestSpecifiedGameObject = main.getGameObjects().closest(gameObjectName);
        if (closestSpecifiedGameObject != null) {
            closestSpecifiedGameObject.interact(interactOption);
            //sleepUntil(() -> getLocalPlayer().isStandingStill(), Rand.nextInt(6000));
            main.sleep(Calculations.random(2000,3000));
            return true;
        }
        else
            return false;
    }
    public static boolean ClosestSpecifiedGameObjectInteractConditional(String gameObjectName, String interactOption, String interactConditionalOption, boolean hasOption) {
        GameObject closestSpecifiedGameObject = main.getGameObjects().closest(gameObjectName);
        if (closestSpecifiedGameObject != null) {
            if (hasOption == true) {
                if (closestSpecifiedGameObject.hasAction(interactConditionalOption)) {
                    closestSpecifiedGameObject.interact(interactOption);
                    main.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Rand.nextInt(6000));
                    return true;
                }
            } else {
                if (!closestSpecifiedGameObject.hasAction(interactConditionalOption)) {
                    closestSpecifiedGameObject.interact(interactOption);
                    main.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Rand.nextInt(6000));
                    return true;
                }
            }
        }
        return false;
    }

}
