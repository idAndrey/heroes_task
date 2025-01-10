package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.SuitableForAttackUnitsFinder;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


public class SuitableForAttackUnitsFinderImpl implements SuitableForAttackUnitsFinder {
    private static int a = 1085704068;

    public SuitableForAttackUnitsFinderImpl() {
    }

    @Override
   // public List<Unit> getSuitableUnits(List<List<Unit>> unitsByRow, boolean isLeftArmyTarget) {
    public List getSuitableUnits(List var1, boolean var2b) {

        // Ваше решение

        System.out.println("getSuitableUnits started");
//
//        List<Unit> suitableUnits = new ArrayList<>();
//
//        for (List<Unit> row : unitsByRow) {
//            for (int i = 0; i < row.size(); i++) {
//                Unit current = row.get(i);
//                if (current == null || !current.isAlive()) continue;
//
//                boolean isOpen = isLeftArmyTarget
//                        ? (i == 0 || row.get(i - 1) == null)
//                        : (i == row.size() - 1 || row.get(i + 1) == null);
//
//                if (isOpen) {
//                    suitableUnits.add(current);
//                }
//            }
//        }
//
//        return suitableUnits;

  /*      ArrayList suitableUnits = new ArrayList();
        byte var10000;
        if (isLeftArmyTarget) var10000 = 2;
        else var10000 = 0;

        byte var4;
        byte var9;
        label68: {
            var4 = var10000;
            if (isLeftArmyTarget) var9 = -1;
            else var9 = 1;


            isLeftArmyTarget = var9;
            var9 = 0;
        }

        int var5 = var9;

        while(var5 < unitsByRow.size()) {
            Iterator list = ((List)unitsByRow.get(var5)).iterator();

            label61:
            while(true) {
                if (!list.hasNext()) ++var5;

                Unit unit;
                if ((unit = (Unit) list.next()).isAlive() || (a + 1) % 2 == 0) {
                    while(true) {
                        while(var5 != var4) {
                            int var8;
                            if ((var8 = var5 - var2) >= 0 && var8 < var1.size() && !((List)var1.get(var8)).stream().anyMatch((var1x) -> {
                                return var1x.getyCoordinate() == unit.getyCoordinate() && var1x.isAlive();
                            })) {
                                suitableUnits.add(unit);
                            }
                        }

                        suitableUnits.add(unit);
                        int cond;
                        cond = 7 * (a + 84) * (a + 84) - 1 - a;
                        System.out.println("7 * (a + 84) * (a + 84) - 1 - a = " + cond + "\n\n\n");
                        if (7 * (a + 84) * (a + 84) - 1 - a != 0) {
                            break;
                        }
                    }
                }
            }
        }

        if (suitableUnits.isEmpty()) {
            System.out.println("Unit can not find target for attack!");
        }
*/




        //return suitableUnits;

/*        ArrayList var3 = new ArrayList();
        byte var10000;
        byte var2 = (byte) (var2b ? 1 : 0 );
        if (var2 != 0) var10000 = 2;
            else var10000 = 0;

        byte var4;
        byte var9;

        var4 = var10000;

        var9 = 0;
        var2 = var9;


        int var5 = var9;

        while(var5 < var1.size()) {
            Iterator var6 = ((List)var1.get(var5)).iterator();

            label61:
            while(true) {
                if (!var6.hasNext()) {
                    ++var5;
                    break;
                    }

                Unit var7;
                if ((var7 = (Unit)var6.next()).isAlive() || (a + 1) % 2 == 0) {
                    while(true) {
                        while(var5 != var4) {
                            int var8;
                            if ((var8 = var5 - var2) >= 0 && var8 < var1.size() && !((List)var1.get(var8)).stream().anyMatch((var1x) -> {
                                Unit instance = (Unit) var1x;
                                return instance.getyCoordinate() == var7.getyCoordinate() && instance.isAlive();
                            })) {
                                var3.add(var7);
                            }

                            continue label61;
                        }
                        System.out.println("Test line var3.add(var7);");
                        var3.add(var7);

                    }
                }
            }
        }

        if (var3.isEmpty()) {
            System.out.println("Unit can not find target for attack!");
        }

        return var3;
*/

        ArrayList var3 = new ArrayList();
        byte var10000;
        byte var2 = (byte) (var2b ? 1 : 0 );
        if (var2 != 0) var10000 = 2;
        else var10000 = 0;

        a = 82;
//        if (var2 != 0) {
//            var10000 = 2;
//            a = 82;
//            if ((a * a + a + 7) % 81 == 0) {
//            }
//        } else {
//            var10000 = 0;
//        }

        byte var4;
        byte var9;
        label68: {
            var4 = var10000;
            if (var2 != 0) {
                var9 = -1;
                if (7 * (a + 8) * (a + 8) - 1 - a == 0) {
                    break label68;
                }
            } else {
                var9 = 1;
            }

            var2 = var9;
            var9 = 0;
        }

        int var5 = var9;

        while(var5 < var1.size()) {
            Iterator var6 = ((List)var1.get(var5)).iterator();

            label61:
            while(true) {
                if (!var6.hasNext()) {
                    ++var5;
                    if (7 * (a + 58) * (a + 58) - 1 - a != 0) {
                        break;
                    }
                }

                Unit var7;
                if ((var7 = (Unit)var6.next()).isAlive() || (a + 1) % 2 == 0) {
                    while(true) {
                        while(var5 != var4) {
                            int var8;
                            if ((var8 = var5 - var2) >= 0 && var8 < var1.size() && !((List)var1.get(var8)).stream().anyMatch(var1x  -> {
                                Unit instance = (Unit) var1x;
                                return instance.getyCoordinate() == var7.getyCoordinate() && instance.isAlive();
                            })) {
                                var3.add(var7);
                            }

                            if ((a + 1) % 2 != 0) {
                                continue label61;
                            }
                        }
                        System.out.println("Test line var3.add(var7);");
                        var3.add(var7);
                        if (7 * (a + 84) * (a + 84) - 1 - a != 0) {
                            break;
                        }
                    }
                }
            }
        }

        if (var3.isEmpty()) {
            System.out.println("Unit can not find target for attack!");
        }

        System.out.println("getSuitableUnits LIST:");
        var3.stream().forEach(unit -> {
            Unit u = (Unit) unit;
            String out = u.getName() + "\t(" + u.getxCoordinate() + ", " + u.getyCoordinate() + ")";
            System.out.println(out);
        });
        return var3;



    }
}
