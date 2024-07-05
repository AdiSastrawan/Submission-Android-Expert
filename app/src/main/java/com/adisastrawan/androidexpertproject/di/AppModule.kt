package com.adisastrawan.androidexpertproject.di

import com.adisastrawan.androidexpertproject.detail.DetailViewModel
import com.adisastrawan.androidexpertproject.home.HomeViewModel
import com.adisastrawan.core.domain.usecase.DetailInteractor
import com.adisastrawan.core.domain.usecase.DetailUseCase
import com.adisastrawan.core.domain.usecase.FavoriteInteractor
import com.adisastrawan.core.domain.usecase.FavoriteUseCase
import com.adisastrawan.core.domain.usecase.NewsInteractor
import com.adisastrawan.core.domain.usecase.NewsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<NewsUseCase> {
        NewsInteractor(get())
    }
    factory<DetailUseCase> {
        DetailInteractor(get())
    }
    factory<FavoriteUseCase> {
        FavoriteInteractor(get())
    }

}
val viewModelModule = module {
    viewModel<HomeViewModel> {
        HomeViewModel(get())
    }
    viewModel<DetailViewModel> {
        DetailViewModel(get())
    }
}
