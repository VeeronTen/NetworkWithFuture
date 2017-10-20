package aladin.rx2retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {

    Button btn;
    DisposableObserver<List<PlaceDTO>> observer;
    Observable<List<PlaceDTO>> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);

        observable= ApiProvider.getInstance(getApplicationContext()).getAirApi().getPlaces();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(observer!=null&&!observer.isDisposed())
                    observer.dispose();
                Log.d("ALADIN", "wait");
                observer = ((Observable<List<PlaceDTO>>) BrutalRxSingleton.getInstance().getPreparedObservable(observable, PlaceDTO.class, true, true)).subscribeWith(new OurObserver());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(observer!=null&&!observer.isDisposed())
            observer.dispose();
    }

    private class OurObserver extends DisposableObserver<List<PlaceDTO>>{
        @Override
        public void onNext(@NonNull List<PlaceDTO> placeDTOs) {
            Log.d("ALADIN", placeDTOs.toString());
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
