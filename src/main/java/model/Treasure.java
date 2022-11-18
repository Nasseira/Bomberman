package model;

import java.util.ArrayList;
import java.util.List;

public class Treasure extends Bonus {
    List<Bonus> bonusList = new ArrayList<>();
    private static final double CHANCE_CONTAINS_TREASURE = 0.6;

    Treasure(Cell cell, Game game) {
        super(cell);
        this.game = game;
    }

    private void removeAllBonus() {
        while (!bonusList.isEmpty()) {
            bonusList.remove(0);
        }
    }

    private List<Bonus> listWithAllBonus() {
        List<Bonus> listBonus = new ArrayList<>();
        listBonus.add(new LiveBonus(getCell(), game));
        listBonus.add(new BombBonus(getCell(), game));
        listBonus.add(new Mine(getCell(), game));
        listBonus.add(new Treasure(getCell(), game));
        return listBonus;
    }

    void completeListWithRandom() {
        List<Bonus> listWithAllBonus = listWithAllBonus();
        int bonusSize = (int) (Math.random() * 3) + 1;
        for (int i = 0; i < bonusSize; i++) {
            double pourc = Math.random();
            int j = (int) (Math.random() * 4);
            if (listWithAllBonus.get(j).isTreasure()) {
                if (pourc < CHANCE_CONTAINS_TREASURE) {
                    bonusList.add(listWithAllBonus.get(j));
                    completeListWithRandom();
                } else {
                    i--;
                }
            } else {
                bonusList.add(listWithAllBonus.get(j));
            }
        }
    }

    @Override
    void exploding() {
        super.exploding();
        removeAllBonus();
    }

    @Override
    boolean isBreakable() {
        return true;
    }

    @Override
    boolean isCrossable() {
        return true;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    void heroTakeMe() {
        for (Bonus bonus : bonusList) {
            getCell().addElement(bonus);
        }
        super.heroTakeMe();
    }

    public String toString() {
        String content = "";
        for (Bonus bonus : bonusList) {
            content += "\n\t" + bonus;
        }
        return "Treasure " + "(" + getCell().getRow() + "," + getCell().getCol() + ")" + content;
    }
}
