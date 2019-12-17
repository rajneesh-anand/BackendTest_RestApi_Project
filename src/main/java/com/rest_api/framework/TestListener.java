package com.rest_api.framework;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    private int m_count = 0;


    @Override
    public void onTestFailure(ITestResult tr) {
        Throwable th = tr.getThrowable();
        if (th != null) {
           // System.out.println(th.getMessage());
            log("Test '" + tr.getName() + "' FAILED");
            tr.setThrowable(null);
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("Skipped");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log("Test Case " + tr.getName() +" executed successfully");
         System.out.println("\n");
    }

    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
            System.out.println("--");
        }
    }
}
