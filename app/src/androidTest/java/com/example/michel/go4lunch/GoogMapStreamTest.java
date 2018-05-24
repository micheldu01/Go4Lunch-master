package com.example.michel.go4lunch;


import com.example.michel.go4lunch.api_google_maps.GoogleAPI;
import com.example.michel.go4lunch.api_google_maps.MapStreams;
import com.example.michel.mymaps5.apiGoogleMaps.GoogleAPI;
import com.example.michel.mymaps5.apiGoogleMaps.MapStreams;

import org.junit.Test;

import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;

public class GoogMapStreamTest {

    @Test
    public void googleApiTest() throws Exception {

        // 1 - Get the stream
        //     Recupération de la stream
        io.reactivex.Observable<GoogleAPI> observableGoogleAPI =
                MapStreams.streamGoogleApi();

        // - 2 Create a new TestObserver
        //     Création d'un nouveau TestObserver
        TestObserver<GoogleAPI> testObserver = new TestObserver<>();

        // 3 - Launch observable
        //     Lancement d'un observable
        observableGoogleAPI.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if  no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // - Await the stream terminated before continue

        // 4 - Test if streamTopStories is empty
        GoogleAPI googleAPI = testObserver.values().get(0);
        // 5 - Ask if getResults is different to null
        assertThat("result", googleAPI.getResults() != null);


    }

}













































