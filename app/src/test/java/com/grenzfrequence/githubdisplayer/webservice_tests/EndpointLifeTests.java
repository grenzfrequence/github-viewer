package com.grenzfrequence.githubdisplayer.webservice_tests;

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

    RepoApi repoListApi;

    @Before
    public void setup() {
        repoListApi = getFragmentComponent().repoListApi();
    }

    @Test
    public void repoListModelTest() throws Exception {
        Response<List<RepoModel>> response;
        response = repoListApi.getRepoList("grenzfrequence").blockingSingle();

        assertThat(response.body().size()).isGreaterThan(0);
    }

}
