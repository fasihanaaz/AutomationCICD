package rahulshettyacademy.resources;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count=1;
    int maxTry=1;
    @Override
    public boolean retry(ITestResult result) {

        if(count<maxTry)
        {
            return true;
        }
        return false;
    }
}
