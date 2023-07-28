package com.bh.at.tester;

import com.bh.at.iapiutil.IAPIHelper;
import org.joda.time.DateTime;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.bh.at.apiutil.CyAPIUtilFactory.apif;
import static com.bh.at.main.AppConfig.*;

public final class CommonApiTester {
    public static String AuthenticationTokens;
    public static Consumer<String> testRunnerLogger;
    private IAPIHelper CommonApiTesterHelper;
    private final Map<String, Object> metaInfo = new HashMap<>();
    private final Map<String, IAPIHelper> apiHelpers = new HashMap<>();

    private String lastApiFileUsed;
    public void initTokenCall(String apiFile) {
        CommonApiTesterHelper = getApiHelper(apiPaths, apiFile);
        log2TestRunner("\n>>>>> createRISK : Function initCreateRISKApi (the api file CCUS.CreateRISK:apiJSON) not fully implemented.");
    }
    /**
     * the user calls delete:CreateRISK:apiCALL.
     *
     * @param apiCall The REST api call that is to be invoked.
     */
    public void callTokenAPI(String apiCall) {
        metaInfo.clear();
        metaInfo.put("cyborg:reqName", apiCall);
        System.out.println(CommonApiTesterHelper.getApiJsono(metaInfo));
        Assert.assertTrue(apiCall + " from file " + lastApiFileUsed + " failed.", CommonApiTesterHelper.callAPI(metaInfo));
    }

    public void validateToken() {

        AuthenticationTokens=CommonApiTesterHelper.getActRespBody().getJSONO("jsonObj").get("access_token");

        System.out.println("Cloak Token "+AuthenticationTokens);

    }


    private IAPIHelper getApiHelper(List<String> apiFilePaths, String apiFile) {
        lastApiFileUsed = apiFile;
        IAPIHelper apiHelper;
        String key = apiFilePaths.toString() + apiFile;
        if (apiHelpers.containsKey(key)) {
            apiHelper = apiHelpers.get(key);
        } else {
            apiHelper = apif.getAPIHelper(apiFilePaths, apiFile, proxyUrl, proxyPort, ignoreSSL);
            apiHelpers.put(key, apiHelper);
        }
        return apiHelper;
    }

    /**
     * Add log messages to cucumber report.
     *
     * @param msg The message to be logged.
     */
    private void log2TestRunner(String msg) {
        testRunnerLogger.accept(msg);
    }

}
