package aladin.rx2retrofit2;

import android.util.Log;
import android.util.LruCache;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BrutalRxSingleton {

    private static BrutalRxSingleton instance;
    private static LruCache<Class<?>, Observable<?>> apiObservables;

    public static BrutalRxSingleton getInstance(){
        if (instance==null)
            instance = new BrutalRxSingleton();
        return instance;
    }
    private  BrutalRxSingleton(){
        apiObservables = new LruCache<>(10);
    }

    //TODO нужно заменить ключ, чтоб листы нормально сохранять
    //TODO время хранения добавить
    //TODO из юзерспейса должен просто вызываться метод, решение на чем сабскрабить должно быть внутри, всегда нетворк, в юзерспейсе решаем на чем обсервить
    //TODO снаружи не должен создаваться обсервабл, создается тольео если в кэше нет ключа
    //TODO добавить ретролямбды
    //TODO добавить вью байндинг
    //TODO добавить MVP (Moxxxy)
    //TODO RxBinding
    //TODO RxLifecycle

    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable, Class<?> clazz, boolean cacheObservable, boolean useCache){
        Observable<?> preparedObservable = null;

        if(useCache)
            preparedObservable = apiObservables.get(clazz);

        if(preparedObservable!=null) {
            Log.d("ALADIN", "взяли из кэша");
            return preparedObservable;
        }else{
            Log.d("ALADIN", "делаем сетевой запрос");
        }

        preparedObservable = unPreparedObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        if(cacheObservable){
            preparedObservable = preparedObservable.cache();
            apiObservables.put(clazz, preparedObservable);
        }
        return preparedObservable;
    }
}
