package cz.levinzonr.cryptostore.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import cz.levinzonr.cryptostore.model.Currency
import cz.levinzonr.cryptostore.model.RatesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(app: Application) : AndroidViewModel(app) {

    val currency = MutableLiveData<Currency>()
    private val compositeDisposable = CompositeDisposable()
    private val repo =  RatesRepository(app)


    fun getDetail(id: String) {
        compositeDisposable.add(repo.getCurrencyDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({t -> currency.value = t }))
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}