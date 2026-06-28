package org.example.demoproject.Week1.lab1_2;

public class ParseError {
    private final int lineNumber;
    private final String lineContent;
    private final String reason;

    public ParseError(int lineNumber, String lineContent, String reason) {
        this.lineNumber = lineNumber;
        this.lineContent = lineContent;
        this.reason = reason;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLineContent() {
        return lineContent;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "Line " + lineNumber + " | " + reason + " | " + lineContent;
    }
}