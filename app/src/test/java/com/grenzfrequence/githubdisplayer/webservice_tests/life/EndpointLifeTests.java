package com.grenzfrequence.githubdisplayer.webservice_tests.life;

import com.grenzfrequence.githubdisplayer.BuildConfig;
import com.grenzfrequence.githubdisplayer.base.BaseUnitTest;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoApi;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by grenzfrequence on 15/03/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class EndpointLifeTests extends BaseUnitTest {

    private static final int    FIRST_PAGE         = 1;
    private static final int    TWO_ITEMS_PER_PAGE = 2;
    private static final String SORT_FIELD         = "updated";

    RepoApi repoListApi;

    @Before
    public void setup() {
        repoListApi = getFragmentComponent().repoListApi();
    }

    @Test
    public void repoListModelTest() throws Exception {
        Response<List<RepoModel>> response;
        response = repoListApi
                .getRepoList("grenzfrequence", FIRST_PAGE, TWO_ITEMS_PER_PAGE, SORT_FIELD)
                .blockingSingle();

        assertThat(response.body().size()).isGreaterThan(0);
        assertThat(response.body().size()).isEqualTo(TWO_ITEMS_PER_PAGE);
        assertThat(response.headers().get("Link")).isEqualTo(
                "<https://api.github.com/user/16125495/repos?page=2&per_page=2&sort=updated>; rel=\"next\", " +
                        "<https://api.github.com/user/16125495/repos?page=2&per_page=2&sort=updated>; rel=\"last\"");
    }

}
