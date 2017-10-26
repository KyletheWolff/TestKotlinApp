package wolffincdevelopment.com.testkotlinapp.service.call

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by Kyle Wolff on 10/19/17.
 */
abstract class AbstractCall<T> {

    protected abstract fun getObservable(): Observable<T>

    fun execute(action: Consumer<T> , error: Consumer<Throwable>): Disposable {
        return getObservable().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(action, error)
    }
}