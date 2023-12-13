package rocketleague2d;

public abstract class Kart {

    private static final double INITIAL_POSITION_X = 500.0;
    private static final double INITIAL_POSITION_Y = 500.0;
    private static final double INITIAL_VELOCITY = 0.0;
    private static final double INITIAL_ROTATION = 0.0;
    private static final double MAX_VELOCITY = 2.0; // Adjust the maximum velocity as needed
    private static final double ACCELERATION = 0.5; // Adjust the acceleration factor as needed
    private static final double DECELERATION = 0.001; // Adjust the deceleration factor as needed
    private static final double TURN_RATE = 0.8; // Adjust the turn rate as needed

    private static final double INCRESS_SPEED_ACCELERATION = 0.0003; // Adjust the turn rate as needed
    private static final double INCRESS_SPEED_DECELERATION = 0.0005; // Adjust the turn rate as needed
    private static final double INCRESS_SPEED_MAX = 2.0; // Adjust the turn rate as needed
    
    private double positionX;
    private double positionY;
    public double velocity;
    private double rotation;
    public double IncressSpeed = 0.0;
    
    public boolean KART_IS_STOP = true;

    public Kart() {
        this.positionX = INITIAL_POSITION_X;
        this.positionY = INITIAL_POSITION_Y;
        this.velocity = INITIAL_VELOCITY;
        this.rotation = INITIAL_ROTATION;
    }

    public void update() {
        if(IncressSpeed > INCRESS_SPEED_MAX){
            IncressSpeed = INCRESS_SPEED_MAX;
        }
        
        // Update position based on velocity and rotation
        double deltaX = (velocity+IncressSpeed) * Math.cos(Math.toRadians(rotation));
        double deltaY = (velocity+IncressSpeed) * Math.sin(Math.toRadians(rotation));

        positionX += deltaX;
        positionY += deltaY;

        // Apply friction to simulate deceleration
        double friction = 0.02; // Adjust the friction factor as needed
        velocity *= (1.0 - friction);

        // Ensure velocity does not go below zero
        if (velocity < 0.0) {
            velocity = 0.0;
            KART_IS_STOP = true;
        }
        if(KART_IS_STOP == true){
          IncressSpeed -= INCRESS_SPEED_DECELERATION;
          if(IncressSpeed < 0){
            IncressSpeed = 0;
          }
        }
    }

    public void accelerate() {
        // Accelerate the kart, considering velocity and maximum speed
        KART_IS_STOP = false;
        IncressSpeed += INCRESS_SPEED_ACCELERATION;
        
        if (velocity < MAX_VELOCITY) {
            velocity += ACCELERATION;
        }
    }

    public void decelerate() {
        // Decelerate the kart, considering velocity and minimum speed
        if (velocity > 0.0) {
            velocity -= DECELERATION;
        }
    }

    public void turnLeft() {
        // Turn the kart left, considering velocity and turn rate
        rotation -= TURN_RATE * velocity / MAX_VELOCITY;
    }

    public void turnRight() {
        // Turn the kart right, considering velocity and turn rate
        rotation += TURN_RATE * velocity / MAX_VELOCITY;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getRotation() {
        return rotation;
    }
    
    void setPositionX(double x) {
        positionX = x;
    }

    void setPositionY(double y) {
        positionY = y;        
    }
    
}
