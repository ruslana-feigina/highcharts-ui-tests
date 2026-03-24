package utils;

public final class TooltipTextUtils {

    private TooltipTextUtils() {
    }

    public static String normalizeTooltipText(String text) {
        if (text == null) {
            return "";
        }

        String normalized = text
                .replace("\u200B", "")
                .replace("\u200C", "")
                .replace("\u200D", "")
                .replace("\uFEFF", "")
                .replaceAll("\\s+", " ")
                .trim();

        normalized = normalized.replaceAll("(,\\s\\d{4})(\\d+employees)", "$1 $2");
        normalized = normalized.replaceAll("(,\\s\\d{4})(\\d+\\s+employees)", "$1 $2");

        return normalized;
    }
}