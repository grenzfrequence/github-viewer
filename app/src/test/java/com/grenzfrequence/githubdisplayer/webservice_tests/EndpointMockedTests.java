package com.grenzfrequence.githubdisplayer.webservice_tests;

import com.grenzfrequence.githubdisplayer.BuildConfig;
import com.grenzfrequence.githubdisplayer.MockWebServer.MockWebServerRule;
import com.grenzfrequence.githubdisplayer.MockWebServer.UseMockWebServer;
import com.grenzfrequence.githubdisplayer.base.BaseUnitTest;
import com.grenzfrequence.githubdisplayer.repolist.data.OwnerApi;
import com.grenzfrequence.githubdisplayer.repolist.data.OwnerModel;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoApi;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by grenzfrequence on 15/03/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class EndpointMockedTests extends BaseUnitTest {

    @Rule
    public RuleChain ruleChain = RuleChain
            .emptyRuleChain()
            .around(new MockWebServerRule(this, getBaseUrlReference()));

    String userName;

    @Before
    public void setup() {
        userName = "TestUser";
    }

    @Test
    @UseMockWebServer(setupMethodName = "setupRepoModelListResponse")
    public void repoModelListTest() throws Exception {
        RepoApi         repoListApi = getFragmentComponent().repoListApi();
        List<RepoModel> list        = repoListApi.getRepoList(userName).blockingSingle().body();

        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(3);

        RepoModel repoListModel1 = list.get(0);
        assertThat(repoListModel1.repoId()).isEqualTo(79553105);
        assertThat(repoListModel1.repoName()).isEqualTo("Repo01");
        assertThat(repoListModel1.repoDescription()).isEqualTo("Repo01 description");
        assertThat(repoListModel1.repoOwner().id()).isEqualTo(16125495);
        assertThat(repoListModel1.repoOwner().name()).isEqualTo("TestUser01");
        assertThat(repoListModel1.repoOwner().avatarLink())
                .isEqualTo("https://avatars0.githubusercontent.com/u/16125495?v=3");

        RepoModel repoListModel2 = list.get(1);
        assertThat(repoListModel2.repoId()).isEqualTo(65045868);
        assertThat(repoListModel2.repoName()).isEqualTo("Repo02");
        assertThat(repoListModel2.repoDescription()).isNull();
        assertThat(repoListModel2.repoOwner().id()).isEqualTo(16125495);
        assertThat(repoListModel2.repoOwner().name()).isEqualTo("TestUser02");
        assertThat(repoListModel2.repoOwner().avatarLink()).isNull();

        RepoModel repoListModel3 = list.get(2);
        assertThat(repoListModel3.repoId()).isEqualTo(71264326);
        assertThat(repoListModel3.repoName()).isEqualTo("Repo03");
        assertThat(repoListModel3.repoDescription()).isNull();
        assertThat(repoListModel3.repoOwner().id()).isEqualTo(16125495);
        assertThat(repoListModel3.repoOwner().name()).isEqualTo("TestUser03");
        assertThat(repoListModel3.repoOwner().avatarLink()).isNull();

    }

    @Test
    @UseMockWebServer(setupMethodName = "setupOwnerModelAllDataFilledResponse")
    public void ownerModelAllDataFilledTest() throws Exception {
        OwnerApi   ownerApi   = getFragmentComponent().ownerApi();
        OwnerModel ownerModel = ownerApi.getOwner(userName).blockingSingle().body();

        assertThat(ownerModel).isNotNull();
        assertThat(ownerModel.id()).isEqualTo(16125495);
        assertThat(ownerModel.name()).isEqualTo("TestUser01");
        assertThat(ownerModel.avatarLink()).isEqualTo("https://avatars0.githubusercontent.com/u/16125495?v=3");
    }

    @Test
    @UseMockWebServer(setupMethodName = "setupOwnerModelNullAvatarResponse")
    public void ownerModelNullAvatarResponse() throws Exception {
        OwnerApi   ownerApi   = getFragmentComponent().ownerApi();
        OwnerModel ownerModel = ownerApi.getOwner(userName).blockingSingle().body();

        assertThat(ownerModel).isNotNull();
        assertThat(ownerModel.id()).isEqualTo(16125499);
        assertThat(ownerModel.name()).isEqualTo("TestUser02");
        assertThat(ownerModel.avatarLink()).isNull();
    }


    public void setupRepoModelListResponse(MockWebServer mockWebServer) {
        mockWebServer.enqueue(new MockResponse().setBody(
                "[\n" +
                        "   {\n" +
                        "      \"id\": 79553105,\n" +
                        "      \"name\": \"Repo01\",\n" +
                        "      \"owner\": {\n" +
                        "         \"login\": \"TestUser01\",\n" +
                        "         \"id\": 16125495,\n" +
                        "         \"avatar_url\": \"https://avatars0.githubusercontent.com/u/16125495?v=3\"\n" +
                        "      },\n" +
                        "      \"description\": \"Repo01 description\"\n" +
                        "   },\n" +
                        "   {\n" +
                        "      \"id\": 65045868,\n" +
                        "      \"name\": \"Repo02\",\n" +
                        "      \"owner\": {\n" +
                        "         \"login\": \"TestUser02\",\n" +
                        "         \"id\": 16125495,\n" +
                        "         \"avatar_url\": null\n" +
                        "      },\n" +
                        "      \"description\": null\n" +
                        "   },\n" +
                        "   {\n" +
                        "      \"id\": 71264326,\n" +
                        "      \"name\": \"Repo03\",\n" +
                        "      \"owner\": {\n" +
                        "         \"login\": \"TestUser03\",\n" +
                        "         \"id\": 16125495,\n" +
                        "         \"avatar_url\": null\n" +
                        "      },\n" +
                        "      \"description\": null\n" +
                        "   }\n" +
                        "]"
        ));
    }

    public void setupOwnerModelAllDataFilledResponse(MockWebServer mockWebServer) {
        mockWebServer.enqueue(new MockResponse().setBody(
                "{\n" +
                        "  \"login\": \"TestUser01\",\n" +
                        "  \"id\": 16125495,\n" +
                        "  \"avatar_url\": \"https://avatars0.githubusercontent.com/u/16125495?v=3\"\n" +
                        "}"
        ));
    }

    public void setupOwnerModelNullAvatarResponse(MockWebServer mockWebServer) {
        mockWebServer.enqueue(new MockResponse().setBody(
                "{\n" +
                        "  \"login\": \"TestUser02\",\n" +
                        "  \"id\": 16125499,\n" +
                        "  \"avatar_url\": null\n" +
                        "}"
        ));
    }

}
