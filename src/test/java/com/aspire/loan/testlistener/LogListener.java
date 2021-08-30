package com.aspire.loan.testlistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class LogListener implements ITestListener {
    protected Logger LOGGER = LoggerFactory.getLogger(getClass().getSimpleName());

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("=== Start executing the test case: {} === ", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("=== Test case: {} is passed === ", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("=== Test case: {} is failed === ", result.getName());
    }
}
