package com.framework.qa.uicore.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
/**
 * StringUtil.java
 * <p/>
 * Class containing testing framework core utility methods
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified on 04_23_2016
 * @since 04/23/2016.
 */
public class StringUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;

    public static String getSubDirPath(String path) {
        String subDirPath = "";

        String[] pkgSize = path.split("\\.");
        for (int i = 0; (pkgSize.length - 6) > i; i++) {
            subDirPath = subDirPath + pkgSize[6 + i] + "/";
        }

        return subDirPath;
    }

    public static String throwableToString(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    public static String createVerificationFailMessage(String errorMsg) {
        String verificationMsg = null;
        if (errorMsg.contains("expected")) {
            verificationMsg = "\n" + "expected" + errorMsg.split("expected")[1] + " ; message: " + errorMsg.split("expected")[0];
        } else {
            verificationMsg = "\n" + errorMsg;
        }

        return verificationMsg;
    }

    /**
     * Generate random String only
     *
     * @return
     */
    public static String generateRandomStringOnly() {
        String strRandom = RandomStringUtils.randomAlphabetic(6);
        return strRandom;
    }

    /**
     * Generate random String , get its length , generate char list and append
     * to random string generated.
     *
     * @return generate String
     * @see StringUtil#getRandomNumber()
     */
    public static String generateRandomString() {
        StringBuffer strBfrRandom = new StringBuffer();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int intNumber = getRandomNumber();
            char character = CHAR_LIST.charAt(intNumber);
            strBfrRandom.append(character);
        }
        return strBfrRandom.toString();
    }


    /**
     * Generate random number
     *
     * @return random number
     */
    public static int getRandomNumber() {
        int intRandom = 0;
        Random rdmGenerator = new Random();
        intRandom = rdmGenerator.nextInt(CHAR_LIST.length());
        if (intRandom - 1 == -1) {
            return intRandom;
        } else {
            return intRandom - 1;
        }
    }
}
