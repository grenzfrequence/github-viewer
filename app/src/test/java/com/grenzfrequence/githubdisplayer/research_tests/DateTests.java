package com.grenzfrequence.githubdisplayer.research_tests;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by grenzfrequence on 16/03/17.
 */

public class DateTests {

    @Test
    public void formatUTCtoStringWithJodaTest() throws Exception {
        DateTime date            = new DateTime("2004-12-13T21:39:45.618-08:00");
        String   str             = date.toString("dd.MM.yyyy");
        assertThat(str).isEqualTo("14.12.2004");
    }

}
