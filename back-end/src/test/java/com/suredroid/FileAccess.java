/*
 * Copyright (c) 2019 SureDroid. Published under the GNU General Public Use License. See LICENSE.MD for more information.
 */

package com.suredroid;

import com.suredroid.discardo.Goal;
import com.suredroid.discardo.Player;

import java.io.File;

public class FileAccess implements Player {
    @Override
    public void init(int[] hand, Goal goal) {
        File file = new File("./something.txt");
        System.out.println(file.exists());

    }

    @Override
    public void maybeReplaceCard(int card) {

    }
}
