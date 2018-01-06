package cs.lwb.huluwa;

import cs.lwb.log.OutputRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GodWithOutputRecord extends God {

    private OutputRecord outputRecord;
    Timer timer;
    int timeCount = 0;

    public GodWithOutputRecord(OutputRecord or, JComponent jcanvas) {
        super(jcanvas);
        outputRecord = or;
    }

    @Override
    public void start() {
        super.start();
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                increaseTimeCnt(100);
            }
        });
        timer.start();
    }

    private synchronized void increaseTimeCnt(int cnt) {
        timeCount += cnt;
        outputRecord.write(timeCount);
    }

    @Override
    public synchronized void checkMove(Location lastLocation, Creature creature) {
        outputRecord.write(Creature.CreatureState.MOVE);
        outputRecord.write(lastLocation);
        outputRecord.write(creature.getLocation());
        super.checkMove(lastLocation, creature);
    }

    @Override
    protected synchronized void checkAttack(Creature creature, Creature enemy) {
        outputRecord.write(Creature.CreatureState.ATTACK);
        outputRecord.write(creature.getLocation());
        outputRecord.write(enemy.getLocation());
        super.checkAttack(creature, enemy);
    }

    @Override
    protected void end() {
        super.end();
        File file = outputRecord.close();
        timer.stop();
        Runnable showMsg = ()-> {
            JOptionPane.showMessageDialog(jcanvas,
                    "回放文件已保存到" + file.getAbsolutePath());
        };
        SwingUtilities.invokeLater(showMsg);
    }
}
