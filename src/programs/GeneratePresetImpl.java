package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.GeneratePreset;

import java.util.*;

public class GeneratePresetImpl implements GeneratePreset {

    public GeneratePresetImpl() {
    }

    @Override
    public Army generate(List<Unit> unitList, int maxPoints) {

        // Ваше решение
        System.out.print("\033\143");
        System.out.println("generate started");



        Army army = new Army();
        int remainingPoints = maxPoints;
        int maxUnitsDefault = 11;
        List<Unit> newList;
        //newList = new ArrayList<>();

        List<Unit> sortedList;
        sortedList = new ArrayList<>(unitList);

        System.out.println("Количество юнитов: " + unitList.size());
        System.out.println("Количество очков: " + maxPoints);

        System.out.println();
        System.out.println(unitList.toString());
        unitList.stream().forEach(unit -> System.out.println(unit.getUnitType() + " " + unit.getCost()+ " x = " + unit.getxCoordinate() + " y = " + unit.getyCoordinate() + "\n"));
        System.out.println();

     /*   System.out.println("Сведения о списке юнитов - LIST\n");
        for (Unit unit : unitList) {
            System.out.println("Сведения о юните - LIST\n");
            String out = "";
            //out = unit.toString();
            out = out + "Name: " + unit.getName() + "\n";
            out = out + "Type: " + unit.getUnitType() + "\n";
            out = out + "Health: " + unit.getHealth() + "\n";
            out = out + "BaseAttack: " + unit.getBaseAttack() + "\n";
            out = out + "Cost: " + unit.getCost() + "\n";
            out = out + "AttackType: " + unit.getAttackType() + "\n";
            out = out + "AttackBonuses: " + unit.getAttackBonuses() + "\n";
            out = out + "DefenceBonuses: " + unit.getDefenceBonuses() + "\n";
            out = out + "X coordinate: " + unit.getxCoordinate() + "\n";
            out = out + "Y coordinate: " + unit.getyCoordinate() + "\n";

            //System.out.println(out + "\n");
        }
        System.out.println("Сведения о списке юнитов - LIST\n\n");
*/


        /*System.out.println("Сведения о списке юнитов - SORTED\n");
        for (Unit unit : sortedList) {
            System.out.println("Сведения о юните - SORTED\n");
            String out = "";
            //out = unit.toString();
            out = out + "Name: " + unit.getName() + "\n";
            out = out + "Type: " + unit.getUnitType() + "\n";
            out = out + "Health: " + unit.getHealth() + "\n";
            out = out + "BaseAttack: " + unit.getBaseAttack() + "\n";
            out = out + "Cost: " + unit.getCost() + "\n";
            out = out + "AttackType: " + unit.getAttackType() + "\n";
            out = out + "AttackBonuses: " + unit.getAttackBonuses() + "\n";
            out = out + "DefenceBonuses: " + unit.getDefenceBonuses() + "\n";
            out = out + "X coordinate: " + unit.getxCoordinate() + "\n";
            out = out + "Y coordinate: " + unit.getyCoordinate() + "\n";

            //System.out.println(out + "\n");
        }
        System.out.println("Сведения о списке юнитов - SORTED\n\n");
*/
        /*System.out.println("Сведения о юнитах");
        for (Unit unit : unitList) {
            //System.out.println("Сведения о юните - ЮНИТ\n");
            String out = "";
            out = "" + unit.getName() + unit.getUnitType();
           // System.out.println(out);
        }*/
        //System.out.println("Сведения о юнитах-  КОНЕЦ");

        /*sortedList.stream().peek(unit -> {
            unit.setxCoordinate();
            newList.add(unit)
        });*/

    //    newList = new ArrayList<>(sortedList);
     //   LinkedList unitLinkedList = new LinkedList(sortedList);




     /*   HashMap hashMap = new HashMap();
        Random randomInt = new Random();

        while(maxPoints > 0 && !sortedList.isEmpty()) {
            Unit nextUnit;
            String nextUnitType = (nextUnit = (Unit) unitLinkedList.peek()).getUnitType();
            int unitCost = nextUnit.getCost();
            int amountUnits;
            if ((amountUnits = (Integer) hashMap.getOrDefault(nextUnitType, 0)) < 11 && maxPoints >= unitCost) {
                int[] coordXY;
                if ((coordXY = this.findAvailableCoordinates(var4, nextUnitType, randomInt, 0)) != null) {
                    int coordX = coordXY[0];
                    int coordY = coordXY[1];
                    ++amountUnits;
                    hashMap.put(nextUnitType, amountUnits);
                    nextUnitType = nextUnitType + " " + amountUnits;
                    nextUnit = new Unit(nextUnitType, nextUnit.getUnitType(), nextUnit.getHealth(), nextUnit.getBaseAttack(), nextUnit.getCost(), nextUnit.getAttackType(), nextUnit.getAttackBonuses(), nextUnit.getDefenceBonuses(), coordX, coordY);

                    while(true) {
                        var4.add(nextUnit);
                        System.out.println("Added " + var4.size() + " unit");
                        army.getUnits().add(nextUnit);
                        maxPoints -= unitCost;
                        var7 += unitCost;
                        if ((a + 1) % 2 == 0) {
                            continue;
                        }
                    }
                } else {
                    System.out.println("Not found units coordinates: " + nextUnitType);
                }
            }
*/
   /*     int i = 0;
        for(Unit unit : newList) {

            unit.setName(unit.getName() + " " + i);
            i++;
        }
        newList.forEach(unit -> {
            Random random = new Random();
            int x = random.nextInt(3);
            int y = random.nextInt(21);

            unit.setxCoordinate(x);
            unit.setyCoordinate(y);

            //System.out.println("FOR Координаты " + unit.getName() + " x = " + unit.getxCoordinate() + " y = " + unit.getyCoordinate() + "\n");
        });

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Координаты:\n");

        newList.stream().forEach(unit -> System.out.println(unit.getName() + " " + unit.getCost()+ " x = " + unit.getxCoordinate() + " y = " + unit.getyCoordinate() + "\n"));
*/

        sortedList.sort((unit1, unit2) -> {
            double efficiency1 = (double)unit1.getBaseAttack() / (double)unit1.getCost();
            double efficiency2 = (double)unit2.getBaseAttack() / (double)unit2.getCost();
            if (Double.compare(efficiency2, efficiency1) != 0) {
                System.out.println("BaseAttack/Cost сортирую " + unit1.toString() + " и " + unit2.toString());

                return Double.compare(efficiency2, efficiency1);
            } else {
                double efficiency3 = (double)unit1.getHealth() / (double)unit1.getCost();
                double efficiency4 = (double)unit2.getHealth() / (double)unit2.getCost();
                System.out.println("Health/Cost сортирую " + unit1.toString() + " и " + unit2.toString());

                return Double.compare(efficiency4, efficiency3);
            }
        });

        sortedList.stream().forEach(unit -> System.out.println(unit.getUnitType() + " " + unit.getCost()+"\n"+ (double)unit.getBaseAttack() / (double)unit.getCost() + " " + (double)unit.getHealth() / (double)unit.getCost() + "\n\n"));

        System.out.println();
        System.out.println();
        System.out.println();


        newList = new ArrayList<>();
        for (Unit unit : sortedList) {
            int maxUnits = Math.min(remainingPoints / unit.getCost(), maxUnitsDefault);
            for (int i = 0; i < maxUnits; i++) {
                newList.add(new Unit(
                        unit.getName()+ " " + String.valueOf(i),
                        unit.getUnitType(),
                        unit.getHealth(),
                        unit.getBaseAttack(),
                        unit.getCost(),
                        unit.getAttackType(),
                        unit.getAttackBonuses(),
                        unit.getDefenceBonuses(),
                        0,
                        0));

                remainingPoints -= unit.getCost();
                if (remainingPoints < unit.getCost()) break;
            }
        }

        Random random = new Random();
        for (Unit unit : newList){
            int tempCounter = 0;
            while(tempCounter < 100) {
                int coordX = random.nextInt(3);
                int coordY = random.nextInt(21);

                if (!newList.stream().anyMatch(unit2 -> {
                    return unit2.getxCoordinate() == coordX && unit2.getyCoordinate() == coordY;
                })) {
                    unit.setxCoordinate(coordX);
                    unit.setyCoordinate(coordY);
                    break;
                    //return new int[]{coordX, coordY};
                }
                ++tempCounter;



            }
        }


        army.setUnits(newList);

//        army.setUnits(newList);
        return army;
    }
}