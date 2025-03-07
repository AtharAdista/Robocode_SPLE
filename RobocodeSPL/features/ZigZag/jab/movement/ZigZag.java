package jab.movement;

import robocode.*;
import jab.module.Module;
import jab.module.Movement;

public class ZigZag extends Movement {

    private int moveDirection;
    private int lastTurnDirection; 
    private static final int MOVE_DISTANCE = 400; 
    private static final int SHORT_MOVE = 50; 
    private static final int TURN_ANGLE = 90; 

    public ZigZag(Module bot) {
        super(bot);
        moveDirection = 1;  
        lastTurnDirection = 1; 
    }

    public void move() {
        bot.setMaxVelocity(8);

        while (true) {
            
            bot.setAhead(MOVE_DISTANCE * moveDirection);
            bot.execute();
            bot.waitFor(new MoveCompleteCondition(bot));

          
            if (lastTurnDirection == 1) {
                bot.setTurnRight(TURN_ANGLE);
            } else {
                bot.setTurnLeft(TURN_ANGLE);
            }
            bot.execute();
            bot.waitFor(new TurnCompleteCondition(bot));

            
            bot.setAhead(SHORT_MOVE * moveDirection);
            bot.execute();
            bot.waitFor(new MoveCompleteCondition(bot));

            
            if (lastTurnDirection == 1) {
                bot.setTurnRight(TURN_ANGLE);
            } else {
                bot.setTurnLeft(TURN_ANGLE);
            }
            bot.execute();
            bot.waitFor(new TurnCompleteCondition(bot));

            
            lastTurnDirection *= -1;
        }
    }

    public void listen(Event e) {
        if (e instanceof HitWallEvent) {
            moveDirection *= -1; 
            bot.execute();
            bot.waitFor(new TurnCompleteCondition(bot)); 
        }
    }
}
