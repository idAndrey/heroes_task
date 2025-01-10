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

        System.out.println("simulate started");

//        int inChar;
//        String s = "";
//        try {
//            inChar = System.in.read();
//            while (System.in.available() > 0) {
//                s += (char) inChar;
//                inChar = System.in.read();
//            }
//        } catch (IOException e) {
//            System.out.println("Ошибка");
//        }

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

                playerUnitsQueue.addAll(playerUnitsAlive.stream().filter((var1x) -> {
                    return !hashSet.contains(var1x);
                }).toList());
                computerUnitsQueue.addAll(computerUnitsAlive.stream().filter((var1x) -> {
                    return !hashSet.contains(var1x);
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
//        int inChar;
//        String s = "";
//        try {
//            inChar = System.in.read();
//            while (System.in.available() > 0) {
//                s += (char) inChar;
//                inChar = System.in.read();
//            }
//        } catch (IOException e) {
//            System.out.println("Ошибка");
//        }





                        }
                    }

                    hashSet.add(computerUnit);
                        System.out.println("continue ");
                }

                if (stopFlag) {
                    ++rounds;
                    hashSet.clear();
                    System.out.println();
                    System.out.println("Round " + rounds + " is over!");
                    System.out.println("Player army has " + playerUnitsAlive.size() + " units");
                    System.out.println("Computer army has " + computerUnitsAlive.size() + " units");
                    System.out.println();
                }

//                if (playerUnitsAlive.size() != 0 && computerUnitsAlive.size() != 0) {
//                  if (playerUnitsAlive.size() != 0 && computerUnitsAlive.size() != 0) {
//                       continue;
//                   }
//
//                    return;
//                }

                System.out.println("Battle is over!");
                return;
            }
        }

//        for( Unit unit : playerUnits) {
//            if (unit.isAlive()) {
//                playerHasAlive = true;
//                break;
//            }
//        }
//
//        for( Unit unit : computerUnits) {
//            if (unit.isAlive()) {
//                computerHasAlive = true;
//                break;
//            }
//        }
//
//        boolean playerHasAlive = false;
//        boolean computerHasAlive = false;
//
//        while (playerHasAlive && computerHasAlive) {
//            allUnits.sort((u1, u2) -> Integer.compare(u2.getBaseAttack(), u1.getBaseAttack()));
//
//            for (Unit unit : allUnits) {
//                if (!unit.isAlive()) continue;
//                Unit target = unitAttack(unit);
//
//
//            }
//
//            for( Unit unit : playerUnits) {
//                if (unit.isAlive()) {
//                    playerHasAlive = true;
//                    break;
//                }
//            }
//
//            for( Unit unit : computerUnits) {
//                if (unit.isAlive()) {
//                    computerHasAlive = true;
//                    break;
//                }
//            }
//
//        }

//        System.out.println("simulate finished");

    }
    private Unit unitAttack(Unit attackUnit) throws InterruptedException {
        Unit targetUnit = attackUnit.getProgram().attack();
        if (targetUnit != null)
            this.printBattleLog.printBattleLog(attackUnit, targetUnit);
        return targetUnit;
    }
}