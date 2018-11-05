package ru.yzhiharevich.Threads_lesson5;

public class MainThreads {
    public static void main(String[] args) {
        oneStreamCalculation();
        twoStreamsCalculation();
        multiStreamsCalculation(8);
    }

    public static void oneStreamCalculation() {
        final int size = 10000000;
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int j = 0; j < size; j++) {
            arr[j] = (float) (arr[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
        }
        System.currentTimeMillis();
        System.out.println("Время расчета по формуле без потока " + (System.currentTimeMillis() - a));
    }

    public static void twoStreamsCalculation() {
        final int size = 10000000;
        final int h = size / 2;
        final float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        final float[] a1 = new float[h];
        final float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        long a = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < a1.length; j++) {
                    a1[j] = (float) (a1[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
                }
                System.out.println(1);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < a2.length; j++) {
                    a2[j] = (float) (a2[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
                }
                System.out.println(2);
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.currentTimeMillis();
        System.out.println("Время расчета по формуле с 2 потоками" + (System.currentTimeMillis() - a));
    }

    public static void multiStreamsCalculation(int thr) {
        final int size = 10000000;
        final float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        final Thread[] threads = new Thread[thr];
        final int h = size / threads.length;

        final float[][] a1 = new float[threads.length][h];
        int lower = 0;
        final long a = System.currentTimeMillis();

        for (int i = 0; i < threads.length; i++) {
            System.arraycopy(arr, lower, a1[i], 0, h);
            lower += h;
        }

        for (int i = 0; i < threads.length; i++) {
            final float[] arr2 = a1[i];
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                        for (int j = 0; j < arr2.length; j++) {
                            arr2[j] = (float) (arr2[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
                        }
                }
            });
            threads[i].start();
        }


        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int upper = 0;
        for (int i = 0; i < threads.length; i++) {
            System.arraycopy(a1[i], 0, arr, upper, h);
            upper += h;
        }

        System.currentTimeMillis();
        System.out.println("Время расчета по формуле с указаным кол-вом потоков" + (System.currentTimeMillis() - a));
    }
}
