package com.rxlxr.aliyun.utils;

public class Poisson {


    public static double[][] poissonArray(double lambda, int k){
        double a[][] = new double[k][2];
        for (int i = 0; i < k; i++) {
            a[i][0] = i;
            a[i][1] = calculatePoissonProbability(lambda,i);
        }
        return a;
    }
    // 计算泊松分布的概率
    public static double calculatePoissonProbability(double lambda, int k) {
        return (Math.exp(-lambda) * Math.pow(lambda, k)) / factorial(k);
    }

    // 计算阶乘
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            int result = 1;
            for (int i = 2; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }
}
