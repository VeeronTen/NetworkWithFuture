package aladin.rx2retrofit2;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * Created by worker on 20.10.17.
 */

public class ServerMock {

    private static ServerMock instance;

    private MockWebServer server;
    public static ServerMock getInstance(){
        if(instance==null)
            instance = new ServerMock();
        return instance;
    }
    private ServerMock(){
        server = new MockWebServer();

        //server.start();

        // Ask the server for its URL. You'll need this to make HTTP requests.
        HttpUrl baseUrl = server.url("http://nano.aviasales.ru/");

        // Exercise your application code, which should make those HTTP requests.
        // Responses are returned in the same order that they are enqueued.
        //Chat chat = new Chat(baseUrl);


    }
    public void shutDown(){
        try {
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MockDispatcher extends Dispatcher{
        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
            if (request.getPath().equals("places/top_ru")){
                return new MockResponse().setResponseCode(200).setBody("[{\"iata\":\"MOW\",\"name\":\"Москва, Россия\",\"coordinates\":[55.755786,37.617633],\"index_strings\":[\"russia\",\"russische föderation\",\"россия\",\"federazione russa\",\"rusia\",\"russie\",\"нерезиновая\",\"понаехавск\",\"нерезиновск\",\"defaultcity\",\"moscow\",\"москва\",\"莫斯科\",\"moskau\",\"moskova\",\"mosca\",\"moscú\",\"moscou\",\"มอสโก\",\"moskwa\",\"moscovo\",\"maskva\",\"moskow\",\"モスクワ\",\"모스크바\",\"موسكو\",\"mow\",null],\"airport_name\":null,\"searches_count\":7305318},{\"iata\":\"LED\",\"name\":\"Санкт-Петербург, Россия\",\"coordinates\":[59.806084,30.3083],\"index_strings\":[\"russia\",\"russische föderation\",\"россия\",\"federazione russa\",\"rusia\",\"russie\",\"sankt petersburg\",\"st petersburg\",\"leningrad\",\"ленинград\",\"питер\",\"спб\",\"saint petersburg\",\"санкт-петербург\",\"圣彼得堡\",\"san pietroburgo\",\"san petersburgo\",\"saint-pétersbourg\",\"เซนต์ ปีเตอร์สเบิร์ก\",\"são petersburgo\",\"sankt peterburgas\",\"サンクト・ペテルブルグ\",\"聖彼得堡\",\"상트페테르부르크\",\"سانت بطرسبرغ\",\"pulkovo airport\",\"пулково\",\"圣彼得堡机场\",\"pulkowo\",\"st petersburg pulkovo\",\"pulkovo\",\"สนามบินพุลโคโว\",\"pułkowo\",\"são petersburgo pulkovo\",\"プルコボ空港\",\"펄코보 공항\",\"مطار بولكوفو\",\"led\"],\"airport_name\":\"Пулково\",\"searches_count\":2457865}]");
            }else {
                return new MockResponse().setResponseCode(404);
            }
        }
    }
}

