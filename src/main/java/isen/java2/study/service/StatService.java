package isen.java2.study.service;

import isen.java2.study.data.stat.Stat;

import java.util.List;

/**
 * Created by plesur on 1/4/16.
 */
public class StatService {
    DBService dbService;

    public StatService(DBService dbService) {
        this.dbService = dbService;
    }

    public void printStats(List<Stat> stats) {
        for (Stat stat : stats) {
            System.out.println("\n\n=====");
            // TODO print unknown things bout stat
            System.out.println("=====");
            dbService.executeStat(stat);
        }
    }
}
