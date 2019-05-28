/*
 * Copyright (c) 2019 SureDroid. Published under the GNU General Public Use License. See LICENSE.MD for more information.
 */

import com.suredroid.discardo.Goal;

public class Main {
    public static void main(String[] args) {
        Goal goal = new Goal() {
            @Override
            public boolean hasWon(int[] ints) {
                return false;
            }
        };
    }
}
