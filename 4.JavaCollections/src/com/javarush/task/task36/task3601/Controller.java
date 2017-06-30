package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by mukovozchik on 30.06.2017.
 */
public class Controller {
    private Model model = new Model();

    public List<String> onDataListShow() {
        return model.getStringDataList();
    }
}
