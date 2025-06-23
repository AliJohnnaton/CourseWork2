package com.skypro.Exam.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class QuestionTest {
    @Test
    void testEqualsAndHashCode() {
        Question q1 = new Question("Q1", "A1");
        Question q2 = new Question("Q1", "A1");
        Question q3 = new Question("Q2", "A2");

        assertEquals(q1, q2);
        assertEquals(q1.hashCode(), q2.hashCode());
        assertNotEquals(q1, q3);
    }
}
