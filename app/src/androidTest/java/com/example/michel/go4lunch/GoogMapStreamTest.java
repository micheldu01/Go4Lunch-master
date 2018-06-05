package com.example.michel.go4lunch;


import com.example.michel.go4lunch.APIMaps.MapStreams;


import org.junit.Test;

import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;

public class GoogMapStreamTest {

    @Test
    public void googleApiTest() throws Exception {

        // 1 - Get the stream
        //     Recupération de la stream
        io.reactivex.Observable<GoogleApiA> observableGoogleAPI =
                MapStreams.streamGoogleApi(BuildConfig.KEY_GOOGLE_MAP);

        // - 2 Create a new TestObserver
        //     Création d'un nouveau TestObserver
        TestObserver<GoogleApiA> testObserver = new TestObserver<>();

        // 3 - Launch observable
        //     Lancement d'un observable
        observableGoogleAPI.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if  no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // - Await the stream terminated before continue

        // 4 - Test if streamTopStories is empty
        GoogleApiA googleAPI = testObserver.values().get(0);
        // 5 - Ask if getResults is different to null
        assertThat("result", googleAPI.getResults().get(0) != null);


    }

    @Test
    public void googleApiTest2() throws Exception {

        // 1 - Get the stream
        //     Recupération de la stream
        io.reactivex.Observable<GoogleAPIplaceId> googleAPIplaceIdObservable =
                MapStreams.streamGoogleAPIplaceId(BuildConfig.KEY_GOOGLE_MAP,"ChIJJ9DR8BxWi0cRY6z352rEi_0");

        // - 2 Create a new TestObserver
        //     Création d'un nouveau TestObserver
        TestObserver<GoogleAPIplaceId> testObserver = new TestObserver<>();

        // 3 - Launch observable
        //     Lancement d'un observable
        googleAPIplaceIdObservable.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if  no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // - Await the stream terminated before continue

        // 4 - Test if streamTopStories is empty
        GoogleAPIplaceId googleAPIplaceId = testObserver.values().get(0);
        // 5 - Ask if getResults is different to null
        assertThat("result", googleAPIplaceId.getResultsAPI() != null);


    }

}













































