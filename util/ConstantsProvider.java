package util;

public class ConstantsProvider {
    private static ConstantsProvider single_instance = null;
    public static Quest quest = null;
    public static ConstantsProvider getInstance() {
        if (single_instance == null) {
            single_instance = new ConstantsProvider();
            quest = new Quest();
        }
        return single_instance;
    }
    private ConstantsProvider() {
        //
    }
    /*
    NPC - Non playing character
    IT - Item
    AC - Action
    GO - GameObject
     */
    public static final String NPC_COOK = "Cook";

    public static final String IT_BUCKET = "Bucket";
    public static final String IT_MILK = "Bucket of milk";
    public static final String IT_FLOUR = "Pot of flour";
    public static final String IT_POT = "Pot";
    public static final String IT_GRAIN = "Grain";
    public static final String IT_EGG = "Egg";

    public static final String AC_TALK = "Talk-to";
    public static final String AC_TAKE = "Take";
    public static final String AC_CLIMB = "Climb-down";
    public static final String AC_OPEN = "Open";
    public static final String AC_OPERATE = "Operate";
    public static final String AC_EMPTY = "Empty";
    public static final String AC_PICK = "Pick";
    public static final String AC_ATTACK = "Attack";
    public static final String AC_CLIMB_UP = "Climb-up";
    public static final String AC_CLIMB_DOWN = "Climb-down";

    public static final String GO_TRAPDOOR = "Trapdoor";
    public static final String GO_LARGE_DOOR = "Large Door";
    public static final String GO_HOPPER = "Hopper";
    public static final String GO_HOPPER_CONTROLS = "Hopper controls";
    public static final String GO_FLOUR_BIN = "Flour bin";
    public static final String GO_DAIRYCOW = "Dairy cow";
    public static final String GO_GATE = "Gate";
    public static final String GO_WHEAT = "Wheat";
    public static final String GO_LADDER = "Ladder";
    public static final String GO_STAIRS = "Staircase";
    public static class Quest {
        public static final SheepSheerer sheepSheerer = new SheepSheerer();
        public static class SheepSheerer {
            public static final String NPC_FRED = "Fred the Farmer";
            public static final String NPC_GOBLIN = "Goblin";
            public static final String NPC_SHEEP = "Sheep";

            public static final String IT_COINS = "Coins";
            public static final String IT_SHEARS = "Shears";
            public static final String IT_WOOL = "Wool";
            public static final String IT_BALL_OF_WOOL = "Ball of wool";

            public static final String AC_SHEAR = "Shear";
            public static final String AC_SPIN = "Spin";

            public static final String GO_SPINNING_WHEEL = "Spinning wheel";

        }
    }
}
