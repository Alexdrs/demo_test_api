/**
 * @author Alexandr.Drasikov
 */
package ru.autotests.helpers.reporter;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import ru.autotests.helpers.utils.DateUtils;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class LogListener implements ITestListener {

    public static final Logger LOG = Logger.getLogger(LogListener.class);
    private static long testWorkTime;
    private static Map<ITestNGMethod, Integer> duplicateMethods = new HashMap<>();
    /**
     * Счетчик тестов
     */
    private static float testCntSuccess, testCntFail, testCntSkips, testCount;
    private static final String SUCCESS = "завершен УСПЕШНО";
    private static final String FAILURE = "завершен НЕУСПЕШНО";
    private static final String SKIPPED = "ПРОПУЩЕН";

    public LogListener() {
        testCntSuccess = 0;
        testCntFail = 0;
        testCntSkips = 0;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        testCntSuccess++;
        testInfo(iTestResult, SUCCESS);
        allTestsStatistic();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        testCntFail++;
        testInfo(iTestResult, FAILURE);
        allTestsStatistic();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        testCntSkips++;
        testInfo(iTestResult, SKIPPED);
        allTestsStatistic();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        testCntSuccess++;
        testInfo(iTestResult, SUCCESS);
        allTestsStatistic();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        testCount = iTestContext.getAllTestMethods().length;
        getLogInfo("Тестов для запуска: " + (int) testCount);
        testWorkTime = System.currentTimeMillis();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    //вывод информации о тесте вида:
    //#testClass testName status time
    //for instance:
    //# Тест Тест1 завершен УСПЕШНО за 1s 53ms
    private void testInfo(ITestResult iTestResult, String status) {
        String className = iTestResult.getTestClass().getName();
        String testName = iTestResult.getName();
        //если тест с дата провайдером и количество запусков теста >1, у имени теста добавляем номер запуска
        if (duplicateMethods.keySet().contains(iTestResult.getMethod())) {
            int i = duplicateMethods.get(iTestResult.getMethod());
            testName += "(" + i + ")";
            duplicateMethods.put(iTestResult.getMethod(), i + 1);
        }
        getLogInfo("######################");
        getLogInfo(String.format("# Тест %s %s %s за %s", className, testName, status, DateUtils.getTimeForLog(iTestResult.getStartMillis(), iTestResult.getEndMillis())));
    }

    //вывод информации о всех тестах вида
    //# allTime - allTests - finishedTests(%)|unexecutedTests(%) - successTests(%)|failedTests(%)|skippedTests(%)
    //for instance:
    //# 15m 12s 01s - 200 - 120 (60%) | 80 (40%) - 83 ( 69,16% ) | 15 ( 12,50 %) | 22 ( 18,34 %)
    private void allTestsStatistic() {
        String allTime = DateUtils.getTimeForLog(testWorkTime, System.currentTimeMillis());
        NumberFormat def = NumberFormat.getPercentInstance();
        def.setMaximumFractionDigits(2);
        String finishedCount = String.format("%.0f (%s)", getFinishedTestsCount(), def.format(getFinishedTestsCount() / testCount));
        String unexecutedCount = String.format("%.0f (%s)", (testCount - getFinishedTestsCount()), def.format((testCount - getFinishedTestsCount()) / testCount));
        String failedCount = String.format("%.0f (%s)", testCntFail, def.format(testCntFail / getFinishedTestsCount()));
        String skipedCount = String.format("%.0f (%s)", testCntSkips, def.format(testCntSkips / getFinishedTestsCount()));
        String successCount = String.format("%.0f (%s)", testCntSuccess, def.format(testCntSuccess / getFinishedTestsCount()));
        String output = String.format("# %s - %.0f - %s | %s - %s | %s | %s", allTime, testCount, finishedCount, unexecutedCount, successCount, failedCount, skipedCount);
        getLogInfo(output);
        getLogInfo("######################");
    }

    //завершенные тесты
    private float getFinishedTestsCount() {
        return (testCntSuccess + testCntFail + testCntSkips);
    }

    private void getLogInfo(String info) {
        LOG.debug(info);
    }

}
