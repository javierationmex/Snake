package a3.interfaces;

import a3.gameObjects.GameObject;

/**
 * Created by Javier G on 11/27/2014.
 */
public interface ICollider {

    public boolean collidesWith(GameObject otherObject);
    public void handleCollision(GameObject otherObject);

}
