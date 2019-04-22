package com.ysl.rxjava.rxjava;

import io.reactivex.Observable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        System.out.println("=========");
        deal();
        System.out.println("------------");
    }

    public static void deal(){
        Observable.just(2,8, 9, 3,4)
                .toSortedList(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer integer, Integer t1) {
                        return t1 - integer;
                    }
                })
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println("onNext : toList : " + integers.toString());
                    }
                });
    }
}
