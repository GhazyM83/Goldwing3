package com.ghazy.goldwing3;

import java.util.List;

public interface MyCallback {

    void onCallback(List<Song> attractionsList);

    static void onCallBackUsers(List<User> attractionsList) {

    }

}
