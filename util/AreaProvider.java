package util;

import org.dreambot.api.methods.map.Area;

public class AreaProvider {
    private static AreaProvider single_instance = null;
    public static Quest quest = null;
    public static AreaProvider getInstance() {
        if (single_instance == null) {
            single_instance = new AreaProvider();
            quest = new Quest();
        }
        return single_instance;
    }
    private AreaProvider() {
        //
    }
    public static final Area cookArea = new Area(3207,3217,3210,3212,0);
    public static final Area wheatArea = new Area(3162,3289,3166,3285,0);
    public static final Area windmillArea = new Area(3165,3300,3168,3302,0);
    public static final Area cookBasementArea = new Area(3208,9618,3210,9615);
    public static final Area eggArea = new Area(3227,3300,3230,3298);
    public static final Area dairyCowArea = new Area(3254,3278,3259,3273);
    public static final Area bucketArea = new Area(3213, 9624, 3216, 9622);
    public static class Quest {
        public static SheepSheerer sheepShearer = new SheepSheerer() ;
        public static class SheepSheerer {
            public static final Area farmerFredArea = new Area(3186,3274,3191,3271);
            public static final Area goblinArea = new Area(3241,3251,3265,3224);
            public static final Area sheepPen = new Area(3193,3276,3212,3258);
            public static final Area lumbridgeCastleStaircaseOne = new Area(3206,3209,3207,3211);
            public static final Area lumbridgeCastleStaircaseTwo = new Area(3206,3227,3207,3228);
            public static final Area lumbridgeCastleSpinningWheel = new Area(3208,3216,3211,3213,1);
        }
    }
}
