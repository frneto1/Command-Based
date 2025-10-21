package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    private final String tableName;

    public Limelight(String name) {
        this.tableName = name;
    }

    public double getTa() {
        return NetworkTableInstance.getDefault().getTable(tableName).getEntry("ta").getDouble(0.0);
    }
    public double getTv(){
        return NetworkTableInstance.getDefault().getTable(tableName).getEntry("tv").getDouble(0.0);
    }

    public boolean detected() {
        return NetworkTableInstance.getDefault().getTable(tableName).getEntry("tv").getDouble(0) == 1;
    }
}
