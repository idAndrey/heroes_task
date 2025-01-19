package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.PrintBattleLog;
import com.battle.heroes.army.programs.SimulateBattle;

import java.io.IOException;
import java.util.*;

public class SimulateBattleImpl implements SimulateBattle {
    private PrintBattleLog printBattleLog; // Позволяет логировать. Использовать после каждой атаки юнита

    @Override
    public void simulate(Army playerArmy, Army computerArmy) throws InterruptedException {

        // Ваше решение

        List<Unit> playerUnits;
        playerUnits = new ArrayList<>();
        playerUnits.addAll(playerArmy.getUnits());

        List<Unit> computerUnits;
        computerUnits = new ArrayList<>();
        computerUnits.addAll(playerArmy.getUnits());


        List<Unit> allUnits = new ArrayList<>();
        allUnits.addAll(playerUnits);
        allUnits.addAll(computerUnits);

        int rounds = 0;
        List playerUnitsAlive = playerArmy.getUnits().stream().filter(Unit::isAlive).toList();
        List computerUnitsAlive = computerArmy.getUnits().stream().filter(Unit::isAlive).toList();


        if (!playerUnitsAlive.isEmpty() && !computerUnitsAlive.isEmpty()) {
            HashSet hashSet = new HashSet();

            while(true) {
                boolean stopFlag = true;
                PriorityQueue playerUnitsQueue = new PriorityQueue(Comparator.comparingInt(Unit::getBaseAttack).reversed());
                PriorityQueue computerUnitsQueue = new PriorityQueue(Comparator.comparingInt(Unit::getBaseAttack).reversed());

                playerUnitsAlive = playerArmy.getUnits().stream().filter(Unit::isAlive).toList();
                computerUnitsAlive = computerArmy.getUnits().stream().filter(Unit::isAlive).toList();

                playerUnitsQueue.addAll(playerUnitsAlive.stream().filter((unit1) -> {
                    return !hashSet.contains(unit1);
                }).toList());
                computerUnitsQueue.addAll(computerUnitsAlive.stream().filter((unit2) -> {
                    return !hashSet.contains(unit2);
                }).toList());

                label64:
                while(true) {
                    Unit playerUnit;
                    Unit computerUnit;
                    do {
                        if (playerUnitsQueue.isEmpty() && computerUnitsQueue.isEmpty()) {
                            break label64;
                        }

                        if (!playerUnitsQueue.isEmpty()) {
                            playerUnit = (Unit) playerUnitsQueue.poll();
                            if ((computerUnit = this.unitAttack(playerUnit)) != null && !computerUnit.isAlive() && !hashSet.contains(computerUnit)) {
                                stopFlag = false;
                                break label64;
                            }
                            hashSet.add(playerUnit);
                        }
                    } while(computerUnitsQueue.isEmpty());

                    computerUnit = (Unit) computerUnitsQueue.poll();


                    if ((playerUnit = this.unitAttack(computerUnit)) != null) {

                        if(!playerUnit.isAlive() && !hashSet.contains(playerUnit)) {
                            stopFlag = false;

                            System.out.println("stopFlag " + stopFlag);
                            break label64;
                        }
                    }

                    hashSet.add(computerUnit);
                }

                if (stopFlag) {
                    ++rounds;
                    hashSet.clear();
                }
                return;
            }
        }
    }
    private Unit unitAttack(Unit attackUnit) throws InterruptedException {
        Unit targetUnit = attackUnit.getProgram().attack();
        if (targetUnit != null)
            this.printBattleLog.printBattleLog(attackUnit, targetUnit);
        return targetUnit;
    }
}