package com.khbd.app;

public interface Controller {
    default void onCreateController(){};
    default void onCreateViewController(){};
}
