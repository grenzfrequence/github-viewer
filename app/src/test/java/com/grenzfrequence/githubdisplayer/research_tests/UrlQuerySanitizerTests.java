package com.grenzfrequence.githubdisplayer.research_tests;

import android.net.UrlQuerySanitizer;

import com.grenzfrequence.githubdisplayer.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by grenzfrequence on 04/04/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class UrlQuerySanitizerTests {

    @Test
    public void pageReaderTest() throws Exception {
        String githubHeaderLink = "https://api.github.com/user/16125495/repos?per_page=35&sort=updated&page=2";

        UrlQuerySanitizer sanitizer = new UrlQuerySanitizer();
        sanitizer.setAllowUnregisteredParamaters(true);
        sanitizer.parseUrl(githubHeaderLink);

        String perPage = sanitizer.getValue("per_page");
        assertThat(perPage).isEqualTo("35");

        String pageNr = sanitizer.getValue("page");
        assertThat(pageNr).isEqualTo("2");
    }

}
