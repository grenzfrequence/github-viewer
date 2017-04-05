package com.grenzfrequence.githubdisplayer.utils_tests;

import com.grenzfrequence.githubdisplayer.BuildConfig;
import com.grenzfrequence.githubdisplayer.utils.GitHubUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by grenzfrequence on 31/03/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class GitHubUtilsTests {

    @Test
    public void getMaxPageNr() throws Exception {
        int pageNr = GitHubUtils.getLastPageNr(
                "<https://api.github.com/user/211411/repos?per_page=100&sort=updated&page=2>; rel=\"next\", " + "" +
                        "<https://api.github.com/user/211411/repos?per_page=100&sort=updated&page=3>; rel=\"last\"");
        assertThat(pageNr).isEqualTo(3);
    }

    @Test
    public void getMaxPageNrWithAllRel() throws Exception {
        int pageNr = GitHubUtils.getLastPageNr(
                "<https://api.github.com/user/211411/repos?page=3&per_page=12&sort=updated>; rel=\"next\", " +
                        "<https://api.github.com/user/211411/repos?page=20&per_page=12&sort=updated>; rel=\"last\", " +
                        "<https://api.github.com/user/211411/repos?page=1&per_page=12&sort=updated>; rel=\"first\", " +
                        "<https://api.github.com/user/211411/repos?page=1&per_page=12&sort=updated>; rel=\"prev\""
        );
        assertThat(pageNr).isEqualTo(20);
    }

    @Test
    public void treatNullValue() throws Exception {
        int pageNr = GitHubUtils.getLastPageNr(null);
        assertThat(pageNr).isEqualTo(1);
    }



}
