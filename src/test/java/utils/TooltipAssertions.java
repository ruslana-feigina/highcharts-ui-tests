package utils;

import org.testng.Assert;

import java.util.List;

public class TooltipAssertions {

    public static void assertTooltipsMatch(List<String> actual, List<String> expected) {
        Assert.assertEquals(actual.size(), expected.size(), "Tooltip count mismatch");

        for (int i = 0; i < expected.size(); i++) {
            String actualText = TooltipTextUtils.normalizeTooltipText(actual.get(i));
            String expectedText = TooltipTextUtils.normalizeTooltipText(expected.get(i));

            Assert.assertEquals(actualText, expectedText,
                    "Tooltip mismatch at index " + i +
                            " Expected: " + expectedText +
                            " Actual: " + actualText);
        }
    }
}