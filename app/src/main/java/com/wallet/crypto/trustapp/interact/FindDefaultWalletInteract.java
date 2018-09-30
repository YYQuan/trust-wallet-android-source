package com.wallet.crypto.trustapp.interact;

import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.WalletRepositoryType;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FindDefaultWalletInteract {

	private final WalletRepositoryType walletRepository;

	public FindDefaultWalletInteract(WalletRepositoryType walletRepository) {
		this.walletRepository = walletRepository;
	}

	public Single<Wallet> find() {
		return walletRepository
				.getDefaultWallet()
//				onErrorResumeNext操作符的意思是 如果  single 回调error的时候，
// 	·			就用 新的single（onErrorResumeNext中的参数）替代原始的single 去通知观察者
				.onErrorResumeNext(
//						这部分只在原始single  error时 起作用
						walletRepository
						.fetchWallets()
						.to(single -> Flowable.fromArray(single.blockingGet()))
//						那flowable中的第一个元素，如果该元素为null则throw 异常
						.firstOrError()
						.flatMapCompletable(walletRepository::setDefaultWallet)
						.andThen(walletRepository.getDefaultWallet()))
				.observeOn(AndroidSchedulers.mainThread());
	}
}
