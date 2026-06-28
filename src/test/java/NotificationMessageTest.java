package org.example.lab2_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationMessageTest {

    @Test
    void shouldBuildNotificationSuccessfully() {

        NotificationMessage message = new NotificationMessage.Builder()
                .to("john@email.com")
                .subject("Hello")
                .body("Welcome")
                .priority(Priority.HIGH)
                .attach("file.pdf")
                .build();

        assertNotNull(message);
    }

    @Test
    void shouldThrowExceptionWhenRecipientMissing() {

        assertThrows(IllegalStateException.class, () -> {
            new NotificationMessage.Builder()
                    .subject("Hello")
                    .body("Welcome")
                    .build();
        });
    }

    @Test
    void shouldThrowExceptionWhenBodyMissing() {

        assertThrows(IllegalStateException.class, () -> {
            new NotificationMessage.Builder()
                    .to("john@email.com")
                    .subject("Hello")
                    .build();
        });
    }
}