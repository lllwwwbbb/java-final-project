package cs.lwb.huluwa;

import cs.lwb.log.InputRecord;
import cs.lwb.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GodWithInputRecord extends God {

    private InputRecord inputRecord;
    private Timer timer;
    private int timeCount = 0;

    public GodWithInputRecord(InputRecord ir, JComponent jcanvas) {
        super(jcanvas);
        inputRecord = ir;
    }

    @Override
    public void start() {
//        super.start();
        running = true;
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeCount += 100;
                while (true) {
                    Object o = inputRecord.read();
                    if (o == null) {
                        timer.stop();
                        inputRecord.close();
                        break;
                    }
                    if (o instanceof Integer) {
                        if (((Integer)o) > timeCount)
                            break;
                    } else if (o instanceof Creature.CreatureState) {
                        Location last, cur;
                        last = (Location)inputRecord.read();
                        cur = (Location)inputRecord.read();
                        switch ((Creature.CreatureState)o) {
                            case ATTACK:  Logger.writeLog(last + " attack " + cur);
                                checkAttack(getCreature(last), getCreature(cur));  break;
                            case MOVE:    Logger.writeLog(last + " moveTo " + cur);
                                getCreature(last).moveTo(cur); break;
                        }
                    } else
                        assert false;
                }
            }
        });
        timer.start();
    }

    @Override
    public void checkDistance(Creature creature) {
        // make it empty
    }
}
