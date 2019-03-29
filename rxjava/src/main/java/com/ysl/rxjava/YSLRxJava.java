package com.ysl.rxjava;


import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.Subject;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class YSLRxJava {
    private static Logger logger = Logger.getLogger(YSLRxJava.class);
    private static Object objectLock = new Object();

    public static void main(String[] args) {
        System.out.println("Hello World!" + "哈哈哈");



    }



    private static void testThread() {
        for (int i = 0; i < 100000; i++) {
            final int index = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                        logger.debug("------>"+index);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//    public static ExecutorService cachedThreadPool = Executors.newFixedThreadPool(100);
    public static void execute(Runnable runnable){
        logger.info("往线程池(CachedThreadPoolUtil)添加任务时，getActiveCount:" + ((ThreadPoolExecutor)cachedThreadPool).getActiveCount()
                + ";getPoolSize:" + ((ThreadPoolExecutor)cachedThreadPool).getPoolSize()
                + ";getCompletedTaskCount:" + ((ThreadPoolExecutor)cachedThreadPool).getCompletedTaskCount()
                + ";getCorePoolSize:" + ((ThreadPoolExecutor)cachedThreadPool).getCorePoolSize()
                + ",线程id："+Thread.currentThread().getId());
        cachedThreadPool.execute(runnable);
    }

    /**
     * 简单使用方法
     */
    public static void createObserver(){


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hahads");
                emitter.onComplete();
            }
        }).subscribe(/*new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext : " + s );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onNext : onComplete" );
            }
        }*/
                new Subject<String>() {
                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    protected void subscribeActual(Observer<? super String> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext : " + s );
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onNext : onComplete" );
                    }
                });
    }

    /**
     * 操作符的使用
     */
    public static void operator() {
        String[] names = new String[]{"a","b","c"};

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onNext("4");
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("onNext : Flowable" + s);
                    }
                });

        /*Observable.just(1,"2",3)
                .cast(Integer.class)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onNext : doOnComplete");
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onNext : throwable" + throwable);
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onNext : doOnTerminate");
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        System.out.println("onNext : doOnSubscribe" + disposable);
                    }
                })
                .subscribe(new Consumer<Serializable>() {
                    @Override
                    public void accept(Serializable serializable) throws Exception {
                        System.out.println("onNext : subscribe" + serializable);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onNext : subscribe" + throwable);
                    }
                });*/

        /*Observable.just(1,"2",3)
                .cast(Integer.class)
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return new ObservableSource<String>() {
                            @Override
                            public void subscribe(Observer<? super String> observer) {
                                System.out.println("onNext : retryWhen : throwableObservable");
                            }
                        };
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : retryWhen : " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onNext : retryWhen : " + throwable);
                    }
                });*/

        /*Observable.just(1,"2",3)
                .cast(Integer.class)
                .retry(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : retry : " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onNext : retry : " + throwable);
                    }
                });*/

        /*Observable.just(1,"2",3)
                .cast(Integer.class)
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        return 100;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : onErrorReturn : " + integer);
                    }
                });*/

        /*Observable.just(1,"2",3)
                .cast(Integer.class)
                .onErrorResumeNext(Observable.just(123,22,32))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : onErrorResumeNext : " + integer);
                    }
                });*/

        /*Observable.just(2,3,5,6)
                .window(2)
                .subscribe(new Consumer<Observable<Integer>>() {
                    @Override
                    public void accept(Observable<Integer> integerObservable) throws Exception {
                        integerObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                System.out.println("onNext : window : " + integer);
                            }
                        });
                    }
                });*/

        /*Observable.just(2,3,5,6)
                .buffer(2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println("onNext : buffer : " + integers);
                    }
                });*/

        /*Observable.just(2,3,5,6)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer % 2 == 0 ? "偶数" : "奇数";
                    }
                })
                .subscribe(new Consumer<GroupedObservable<String, Integer>>() {
                    @Override
                    public void accept(final GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
                        stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                System.out.println("onNext : groupBy : " + stringIntegerGroupedObservable.getKey()+":"+integer.toString());
                            }
                        });
                    }
                });*/

        /*Observable.just(2,3,5)
                .scan(-1,new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : scan : " + integer);
                    }
                });*/
        /*Observable.just(2,3,5)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : scan : " + integer);
                    }
                });*/

        /*Observable.just(2,3,5)
                .concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(final Integer integer) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                emitter.onNext(integer*1+"");
                                emitter.onNext(integer*10+"");
                                emitter.onNext(integer*100+"");
                                emitter.onNext(integer*1000+"");
                                emitter.onComplete();
                            }
                        });
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("onNext : concatMap : " + s);
                    }
                });*/

        /*Observable.just(2,3,5)
                .flatMapIterable(new Function<Integer, List<String>>() {
                    @Override
                    public List<String> apply(final Integer integer) throws Exception {
                        return Arrays.asList(integer*10+"",integer*100+"");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("onNext : flatMapIterable : " + s);
                    }
                });*/

        /*Observable.just(2,3,5)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(final Integer integer) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                emitter.onNext(integer*1+"");
                                emitter.onNext(integer*10+"");
                                emitter.onNext(integer*100+"");
                                emitter.onNext(integer*1000+"");
                                emitter.onComplete();
                            }
                        });
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("onNext : flatMap : " + s);
                    }
                });*/

        /*Observable.just(2,8, 9, 3,4)
                .cast(Integer.class)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : cast : " + integer);
                    }
                });*/

        /*Observable.just(2,8, 9, 3,4)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "item:"+integer;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("onNext : map : " + s);
                    }
                });*/

        /*Observable.just(2,8, 9, 3,4)
                .toMultimap(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "key" + integer;
                    }
                }, new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "value" + integer;
                    }
                })
                .subscribe(new Consumer<Map<String, Collection<String>>>() {
                    @Override
                    public void accept(Map<String, Collection<String>> stringCollectionMap) throws Exception {
                        System.out.println("onNext : toMultimap : " + stringCollectionMap.toString());
                    }
                });*/

        /*Observable.just(2,8, 9, 3,4)
                .toMap(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "key" + integer;
                    }
                }, new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "value"+integer;
                    }
                })
                .subscribe(new Consumer<Map<String, String>>() {
                               @Override
                               public void accept(Map<String, String> stringStringMap) throws Exception {
                                   System.out.println("onNext : toMap : " + stringStringMap.toString());
                               }
                           }
                        *//*new Consumer<Map<String, Integer>>() {
                    @Override
                    public void accept(Map<String, Integer> stringIntegerMap) throws Exception {
                        System.out.println("onNext : toMap : " + stringIntegerMap.toString());
                    }
                }*//*);*/

        /*Observable.just(2,8, 9, 3,4)
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
                });*/

        /*Observable.just(2,3,4)
                .toList()
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println("onNext : toList : " + integers.toString());
                    }
                });*/

        /*Observable.just(2,3,4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : doOnNext : 准备发射");
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : doOnNext : " + integer);
                    }
                });*/

        /*Observable.just(2,3,4)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("onNext : count : " + aLong);
                    }
                });*/

        /*Observable.just(2,3,4,5)
                .collect(new Callable<List<Integer>>() {
                    @Override
                    public List<Integer> call() throws Exception {
                        return new ArrayList<Integer>();
                    }
                }, new BiConsumer<List<Integer>, Integer>() {
                    @Override
                    public void accept(List<Integer> integers, Integer integer) throws Exception {
                        integers.add(integer);
                    }
                })
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println("onNext : collect : " + integers.toString());
                    }
                });*/

        /*Observable.just(2,3,4,5)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer+integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : reduce : " + integer);
                    }
                });*/

        /*Observable.just(2,3,4,5)//TODO 未通过验证
                .skipUntil(Observable.just(5))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : skipUntil : " + integer);
                    }
                });*/

        /*Observable.just(2,3,4,5)
                .skipWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 10;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : skipWhile : " + integer);
                    }
                });*/

        /*Observable.just(2,3,4,5)
                .takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer == 3;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : takeUntil : " + integer);
                    }
                });*/

        /*Observable.just(2,3,4,5)
                .takeWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer <= 3;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : takeWhile : " + integer);
                    }
                });*/

        /*Observable.empty()
                .switchIfEmpty(Observable.just(2,3,4))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object integer) throws Exception {
                        System.out.println("onNext : switchIfEmpty : " + integer);
                    }
                });*/

        /*Observable<Integer> observable1=Observable.create(new ObservableOnSubscribe<Integer>() {//TODO 未通过验证
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    emitter.onError(e);
                }
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        });

        Observable<Integer> observable2=Observable.create(new ObservableOnSubscribe<Integer>() {
                                                              @Override
                                                              public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                                                                  emitter.onNext(3);
                                                                  emitter.onNext(4);
                                                                  emitter.onComplete();
                                                              }
                                                          });

        Observable.amb(observable1, observable2).subscribe();*/

        /*Observable.just(5,6,4,8798)
                .isEmpty()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        System.out.println("onNext : isEmpty : " + aBoolean);
                    }
                });*/

        /*Observable.sequenceEqual(Observable.just(2,4,5),Observable.just(2,3,4,5))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        System.out.println("onNext : sequenceEqual : " + aBoolean);
                    }
                });*/

        /*Observable.just(7,8,65,23,42)
                .contains(8).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println("onNext : contains : " + aBoolean);
            }
        });*/

        /*Observable.just(7,8,65,23,42)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer < 100;
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println("onNext : all : " + aBoolean);
            }
        });*/

        /*Observable.just(3,4,5,6,3,3,4,9)
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : distinct : " + integer);
                    }
                });*/

        /*Observable.just(3,4,5,6,3,3,4,9)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : distinct : " + integer);
                    }
                });*/

        /*Observable.just(30,4,50,6)
                .skipLast(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : skipLast : " + integer);
                    }
                });*/

        /*Observable.just(30,4,50,6)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : skip : " + integer);
                    }
                });*/

        /*Observable.just(30,4,5,6)
                .first(0)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : first : " + integer);
                    }
                });*/

        /*Observable.just(3,4,5,6)
                .last(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : last : " + integer);
                    }
                });*/

        /*Observable.just(3,4,5,6,3,4,5,6,3,4)
                .takeLast(2)//发射前三个数据项
                .takeLast(100, TimeUnit.MILLISECONDS)//发射100ms内的数据
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : takeLast : " + integer);
                    }
                });*/

        /*Observable.just(3,4,5,6,3,4,5,6,3,4)
                .take(10)//发射前三个数据项
                .take(100, TimeUnit.MILLISECONDS)//发射100ms内的数据
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : take : " + integer);
                    }
                });*/

        /*Observable.just(1,2,"w", "s")
                .ofType(String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("onNext : ofType : " + s);
                    }
                });*/

        /*Observable.just(7,8,65,23,42)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer < 10;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("onNext : filter : " + integer);
            }
        });*/

        /*String[] aStrings = {"A1", "A2", "A3", "A4"};
        String[] bStrings = {"B1", "B2", "B3"};

        Observable<String> aObservable = Observable.fromArray(aStrings);
        Observable<String> bObservable = Observable.fromArray(bStrings);

        Observable.merge(bObservable, aObservable)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("onNext : merge : " + s);
                    }
                });*/

        /*Observable<Integer> observable1=Observable.just(1,2,3,4);
        Observable<Integer> observable2=Observable.just(4,5,6);*/

        /*Observable.concat(observable2, observable1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : concat : " + integer);
                    }
                });*/
        /*Observable.just(1,2,3,4,5)
                .startWith(observable2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : startWith : " + integer);
                    }
                });*/


        /*Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hahahhah";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("onNext : fromCallable : " + s);
            }
        });*/

        /*Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                return Observable.just("hello");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("onNext : defer : " + s);
            }
        });*/

        /*Observable.range(2,5)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("onNext : range : " + integer);
                    }
                });*/

        /*Observable.interval(1, TimeUnit.SECONDS)//TODO 未通过测试
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("onNext : interval : " + aLong);
                    }
                });*/
        /*Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("onNext : timer : " + aLong);
                        Log.e("a","onNext : timer : " + aLong);
                    }
                });*/

        /*Observable.never();//创建一个什么都不做的Observable,直接调用onCompleted。
        Observable.error(new RuntimeException());//创建一个什么都不做直接通知错误的Observable,直接调用onError。这里可以自定义异常
        Observable.empty();//创建一个什么都不做直接通知完成的Observable*/
        /*Observable.fromArray(names)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.print("onNext : fromArray : " + s + "\n");
                    }
                });*/
        /*Observable.just(names)
                .subscribe(new Consumer<String[]>() {
                    @Override
                    public void accept(String[] strings) throws Exception {
                        System.out.print("onNext : just : " + Arrays.toString(strings) + "\n");
                    }
                });*/
        /*Observable.just("ad", "bgf", "dsf")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.print("onNext : just3 : " + s + "\n");
                    }
                });*/
    }
}
