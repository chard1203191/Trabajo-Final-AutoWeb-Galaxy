package com.store.listeners;

import com.store.utils.AllureUtils;
import com.store.utils.BaseTest;
import com.store.utils.Retry;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestListener implements ITestListener, IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(Retry.class);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).driver;
        Retry retry = (Retry) result.getMethod().getRetryAnalyzer(result);

        int currentAttempt = retry != null ? retry.getCurrentAttempt() : 1;
        int maxRetry = retry != null ? retry.getMaxRetry() : 0;

        AllureUtils.attachText("Retry Info", "Attemp: " + currentAttempt + " of " + (maxRetry + 1));

        if (driver != null) {
            AllureUtils.captureScreenshot(driver, "FAIL - Attemp " + currentAttempt);
        }

        if (driver != null && (retry == null || retry.isLastAttempt())) {
            AllureUtils.captureScreenshot(driver, "FINAL FAIL - " + result.getMethod().getMethodName());
            AllureUtils.attachPageSource(driver);
        }
    }
}
