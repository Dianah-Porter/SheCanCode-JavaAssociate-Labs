package org.example.lab2_2;

import java.util.ArrayList;
import java.util.List;

public class NotificationMessage {

    private final String recipient;
    private final String subject;
    private final String body;
    private final Priority priority;
    private final List<String> attachments;

    private NotificationMessage(Builder builder) {
        this.recipient = builder.recipient;
        this.subject = builder.subject;
        this.body = builder.body;
        this.priority = builder.priority;
        this.attachments = builder.attachments;
    }

    public static class Builder {
        private String recipient;
        private String subject;
        private String body;
        private Priority priority = Priority.MEDIUM;
        private List<String> attachments = new ArrayList<>();

        public Builder to(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder attach(String file) {
            this.attachments.add(file);
            return this;
        }

        public NotificationMessage build() {
            if (recipient == null || recipient.isEmpty())
                throw new IllegalStateException("Recipient is required");

            if (body == null || body.isEmpty())
                throw new IllegalStateException("Body is required");

            return new NotificationMessage(this);
        }
    }
}