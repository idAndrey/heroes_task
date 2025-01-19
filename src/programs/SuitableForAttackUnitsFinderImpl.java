package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.SuitableForAttackUnitsFinder;

import java.io.IOException;
import java.util.*;


public class SuitableForAttackUnitsFinderImpl implements SuitableForAttackUnitsFinder {
    private static int a = 1085704068;

    public SuitableForAttackUnitsFinderImpl() {
    }

    @Override
    public List<Unit> getSuitableUnits(List<List<Unit>> unitsByRow, boolean isLeftArmyTarget) {

        // Ваше решение

        List<Unit> suitableUnits = new ArrayList<>();

        for(int rowIndex = 0; rowIndex < unitsByRow.size(); rowIndex++){

            List<Unit> row = unitsByRow.get(rowIndex);

            row.sort(Comparator.comparingInt(Unit::getyCoordinate) );

            for (int j = 0; j < row.size(); j++) {

                Unit current = row.get(j);

                int y = current.getyCoordinate();

                if (current == null || !current.isAlive()) continue;

                boolean isOpen;
                isOpen = isLeftArmyTarget
                        //? (j == 0 || row.get(j - 1) == null)
                        ? (j == 0 ||  (y-1) != row.get(j - 1).getyCoordinate())
                        : (j == row.size() - 1 || (y+1) != row.get(j + 1).getyCoordinate());

                if (isOpen) {
                    suitableUnits.add(current);
                }
            }
        }
        return suitableUnits;
    }
}
