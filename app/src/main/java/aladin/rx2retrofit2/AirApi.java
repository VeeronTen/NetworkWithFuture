package aladin.rx2retrofit2;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AirApi {

    @GET("places/top_ru")
    Observable<List<PlaceDTO>> getPlaces();
}
