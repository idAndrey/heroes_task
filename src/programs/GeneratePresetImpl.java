package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.GeneratePreset;

import java.io.IOException;
import java.util.*;

public class GeneratePresetImpl implements GeneratePreset {

    @Override
    public Army generate(List<Unit> unitList, int maxPoints) {

        // Ваше решение

        Army army = new Army();
        int remainingPoints = maxPoints;
        int maxUnitsDefault = 11;
        List<Unit> newList;

        List<Unit> sortedList;
        sortedList = new ArrayList<>(unitList);

        sortedList.sort(Comparator.comparingDouble( u -> {
            double efficiency1 = (double)u.getBaseAttack() / (double)u.getCost();
            double efficiency2 = (double)u.getHealth() / (double)u.getCost();
            return efficiency1 + efficiency2;
        }) );

        Collections.reverse(sortedList);

        newList = new ArrayList<>();
        for (Unit unit : sortedList) {
            int maxUnits = Math.min(remainingPoints / unit.getCost(), maxUnitsDefault);
            for (int i = 0; i < maxUnits; i++) {

                remainingPoints -= unit.getCost();
                if (remainingPoints < unit.getCost()) break;

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
                        0)
                );
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
        return army;
    }
}