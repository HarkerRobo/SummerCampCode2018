package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

public class RobotMap {
    public static final int TIMEOUT = 10;
    
    public static final class CANIDs {
        public static final int LEFT_TALON_MASTER = 0;
        public static final int RIGHT_TALON_MASTER = 0;
        public static final int LEFT_TALON_FOLLOWER = 0;
        public static final int RIGHT_TALON_FOLLOWER = 0;
    }
    
    public static final class DrivetrainConstants {
        public static final NeutralMode TALON_NEUTRAL_MODE = NeutralMode.Brake;
        
        public static final boolean LEFT_MASTER_INVERTED = false;
        public static final boolean RIGHT_MASTER_INVERTED = false;
        public static final boolean LEFT_FOLLOWER_INVERTED = false;
        public static final boolean RIGHT_FOLLOWER_INVERTED = false;
        
        public static int PEAK_CURRENT_LIMIT = 60;
        public static int PEAK_TIME_MS = 200;
        public static int CONTINUOUS_CURRENT_LIMIT = 40;
    }

}